package com.api_pagamento.redis_json.repository.cache;

import com.api_pagamento.redis_json.domain.dto.TransacaoDTO;
import com.api_pagamento.redis_json.domain.dto.util.Mapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.*;
import redis.clients.jedis.json.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TransacaoCacheRepository {


    //Jedis: Jedis é um cliente Java para Redis projetado para desempenho e facilidade de uso.

    //JedisPooled: Extende a classe UnifiedJedisPool.
    // |
    // v
    //UnifiedJedis jedis = new UnifiedJedis(new PooledConnectionProvider(new HostAndPort(Protocol.DEFAULT_HOST, Protocol.DEFAULT_PORT)));

    //UnifiedJedis: Unifica o Jedis ao implementar as interfaces: JedisCommands, JedisBinaryCommands, SampleKeyedCommands,
    //SampleBinaryKeyedCommands, RedisModuleCommands

    //PooledConnectionProvider: Implementação da interface ConnectionProvider (Provedor de conexão) que cria um pool de conexões.

    //Pool de conexões: Um pool de conexões é um cache de conexões de banco de dados mantido de forma que as conexões
    //possam ser reutilizadas quando requisições futuras ao banco de dados forem requeridas.
    //Pools de conexões são usados para garantir o desempenho da execução de comandos sobre um banco de dados.

    //HostAndPort: localhost:6379
    private final JedisPooled jedis = new JedisPooled(Protocol.DEFAULT_HOST, Protocol.DEFAULT_PORT);
    private final Gson gson = new Gson();
    public boolean verifyIfExistJsonKey(String key) {
        //jedis.exist(): Verifica se a chave existe no Redis.
        return jedis.exists(key);
    }

    public void setJsonKey(String key, TransacaoDTO value) {
        if(!verifyIfExistJsonKey(key)) {
            //jedis.jsonSet(key, value): Cria uma chave e associa um valor a ela
            jedis.jsonSet(key, gson.toJson(value));
            //jedis.expire(): Define o tempo de vida da chave.
            jedis.expire(key, 60 * 5);
        }
    }
    public TransacaoDTO getJsonKey(String key){
        if (verifyIfExistJsonKey(key)){
            //jedis.jsonGet(key): Retorna o valor associado a determinada chave
            return (TransacaoDTO) Mapper.convert(jedis.jsonGet(key), TransacaoDTO.class);
        }
        return null;
    }

    public List<TransacaoDTO> getAllJsonKey() {
        //jedis.keys("transacao::*"): Retorna todas as chaves que começam com "transacao::"
        return jedis.keys("transacao::*")
                                                .stream()
                                                .map( key -> getJsonKey(key))
                                                .sorted(Comparator.comparing(TransacaoDTO::getId))
                                                .collect(Collectors.toList());

    }

    public TransacaoDTO updateJsonKey(String key, String field, String value){
        if (verifyIfExistJsonKey(key)) {
            //jedis.jsonSet(key, Path.of(field), value): Atualiza o valor de determinado campo de determinada chave.
            jedis.jsonSet(key, Path.of(field), value);
            return (TransacaoDTO) Mapper.convert(jedis.jsonGet(key), TransacaoDTO.class);
        }
        return null;
    }

    public void setKey(String key, String value) {
        jedis.set(key, value);
    }

    public Long getKey(String key) {
        String value = jedis.get(key);
        if (value != null) {
            return Long.parseLong(value);
        }
        return null;
    }

}
