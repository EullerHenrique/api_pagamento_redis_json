package com.api_pagamento.redis_json.service;

import com.api_pagamento.redis_json.domain.dto.TransacaoDTO;
import com.api_pagamento.redis_json.domain.dto.util.Mapper;
import com.api_pagamento.redis_json.domain.enumeration.StatusEnum;
import com.api_pagamento.redis_json.domain.exception.InsercaoNaoPermitidaException;
import com.api_pagamento.redis_json.domain.exception.TransacaoInexistenteException;
import com.api_pagamento.redis_json.domain.model.Transacao;
import com.api_pagamento.redis_json.repository.database.DescricaoRepository;
import com.api_pagamento.redis_json.repository.cache.TransacaoCacheRepository;
import com.api_pagamento.redis_json.repository.database.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


//@Service

//A anotação @Service é usada em sua camada de serviço e anota classes que realizam tarefas de serviço, muitas vezes
//você não a usa, mas em muitos casos você usa essa anotação para representar uma prática recomendada. Por exemplo,
//você poderia chamar diretamente uma classe DAO para persistir um objeto em seu banco de dados, mas isso é horrível.
//É muito bom chamar uma classe de serviço que chama um DAO. Isso é uma boa coisa para executar o padrão de separação
//de interesses.

@Service

//@Transactional
//https://www.devmedia.com.br/conheca-o-spring-transactional-annotations/32472
//"A boa prática é sempre colocar o @Transactional nos métodos que precisam de transação, por exemplo: salvar, alterar,
//excluir, etc., pois assim você garante que eles vão ser executados dentro um contexto transacional e o rollback
//será feito caso ocorra algum erro."

@Transactional

//@RequiredArgsConstructor
//Gera um construtor com argumentos necessários. Os argumentos obrigatórios são campos finais e campos com restrições como @NonNull.

@RequiredArgsConstructor
public class TransacaoServiceImp implements TransacaoService {

    private final TransacaoRepository transacaoRepository;

    private final DescricaoRepository descricaoRepository;

    private final TransacaoCacheRepository transacaoCacheRepository;

    @Override
    public TransacaoDTO getById(Long id) throws TransacaoInexistenteException {

        //Get of Cache
        TransacaoDTO transacaoDTO = transacaoCacheRepository.getJsonKey("transacao::"+id.toString());
        if(transacaoDTO != null) {
            return transacaoDTO;
        }

        //Get of DataBase
        transacaoDTO = (TransacaoDTO) transacaoRepository.findById(id).map(t -> Mapper.convert(t, TransacaoDTO.class)).orElse(null);
        if(transacaoDTO != null){
            //Save on Cache
            transacaoCacheRepository.setJsonKey("transacao::"+transacaoDTO.getId(), transacaoDTO);
            return transacaoDTO;
        }else{
            throw new TransacaoInexistenteException();
        }

    }

    @Override
    public List<TransacaoDTO> getAll() throws TransacaoInexistenteException {

        //Get of Cache
        List<TransacaoDTO> transacoesDTO = transacaoCacheRepository.getAllJsonKey();
        Long sizeDB = transacaoCacheRepository.getKey("size::transacoes");
        if(sizeDB != null) {
            if(transacoesDTO.size() == sizeDB) {
                return transacoesDTO;
            }
        }

        //Get of Database
        transacoesDTO = transacaoRepository
                .findAll()
                .stream()
                .map(t -> {
                    TransacaoDTO transacaoDTO = (TransacaoDTO) Mapper.convert(t, TransacaoDTO.class);
                    //Save on Cache
                    transacaoCacheRepository.setJsonKey("transacao::"+t.getId(), transacaoDTO);
                    return transacaoDTO;
                })
                .collect(Collectors.toList());
        if(transacoesDTO.size() != 0){
            return transacoesDTO;
        }else{
            throw new TransacaoInexistenteException();
        }

    }

    @Override
    public TransacaoDTO pagar(Transacao transacao) throws InsercaoNaoPermitidaException {

        if(transacao.getDescricao().getStatus() == null && transacao.getDescricao().getNsu() == null && transacao.getDescricao().getCodigoAutorizacao() == null && transacao.getId() == null && transacao.getDescricao().getId() == null && transacao.getFormaPagamento().getId() == null) {

            transacao.getDescricao().setNsu("1234567890");
            transacao.getDescricao().setCodigoAutorizacao("147258369");
            transacao.getDescricao().setStatus(StatusEnum.AUTORIZADO);

            //Save on Database
            Transacao transacaoSave = transacaoRepository.save(transacao);

            //Save on Cache
            transacaoCacheRepository.setKey("size::transacoes", transacaoSave.getId().toString());

            return (TransacaoDTO) Mapper.convert(transacaoSave, TransacaoDTO.class);

        }else{
            throw new InsercaoNaoPermitidaException();
        }

    }

    public TransacaoDTO estornar(Long id) throws TransacaoInexistenteException {

        //Get of cache
        TransacaoDTO transacaoDTO = transacaoCacheRepository.getJsonKey("transacao::"+id.toString());
        if(transacaoDTO != null) {
            if(transacaoDTO.getDescricao().getStatus() == StatusEnum.NEGADO){
                return transacaoDTO;
            }
        }

        //Get of Database, Save on DataBase
        Transacao transacao = (Transacao) Mapper.convert(getById(id), Transacao.class);
        transacao.getDescricao().setStatus(StatusEnum.NEGADO);

        descricaoRepository.save(transacao.getDescricao());

        //Save on cache
        transacaoDTO = transacaoCacheRepository.updateJsonKey("transacao::"+id, "descricao.status", StatusEnum.NEGADO.toString());
        if(transacaoDTO!=null){
            return transacaoDTO;
        }else{
            throw new TransacaoInexistenteException();
        }

    }

}
