package com.api_pagamento.redis_json.service;

import com.api_pagamento.redis_json.domain.dto.TransacaoDTO;
import com.api_pagamento.redis_json.domain.exception.InsercaoNaoPermitidaException;
import com.api_pagamento.redis_json.domain.exception.TransacaoInexistenteException;
import com.api_pagamento.redis_json.domain.model.Transacao;

import javax.transaction.Transactional;
import java.util.List;

//@Transactional

//https://www.devmedia.com.br/conheca-o-spring-transactional-annotations/32472
//"A boa prática é sempre colocar o @Transactional nos métodos que precisam de transação, por exemplo: salvar, alterar,
//excluir, etc., pois assim você garante que eles vão ser executados dentro um contexto transacional e o rollback
//será feito caso ocorra algum erro

@Transactional
public interface TransacaoService {

    TransacaoDTO getById(Long id) throws TransacaoInexistenteException;
    List<TransacaoDTO> getAll() throws TransacaoInexistenteException;
    TransacaoDTO pagar(Transacao transacao) throws InsercaoNaoPermitidaException;
    TransacaoDTO estornar(Long id) throws TransacaoInexistenteException;

}
