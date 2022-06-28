package com.api_pagamento.redis_json.repository;

import com.api_pagamento.redis_json.domain.dto.DescricaoDTO;
import com.api_pagamento.redis_json.domain.dto.FormaPagamentoDTO;
import com.api_pagamento.redis_json.domain.dto.TransacaoDTO;
import com.api_pagamento.redis_json.domain.dto.util.Mapper;
import com.api_pagamento.redis_json.domain.enumeration.StatusEnum;
import com.api_pagamento.redis_json.domain.enumeration.TipoEnum;
import com.api_pagamento.redis_json.domain.model.Transacao;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.*;
import redis.clients.jedis.json.JsonSetParams;
import redis.clients.jedis.json.Path;
import redis.clients.jedis.json.Path2;
import redis.clients.jedis.providers.PooledConnectionProvider;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class TransacaoCacheRepository {

    private final UnifiedJedis jedis = new UnifiedJedis(new PooledConnectionProvider(new HostAndPort(Protocol.DEFAULT_HOST,
            Protocol.DEFAULT_PORT)));

    private final Gson gson = new Gson();
    public boolean verifyIfExistKey(String key) {
        return jedis.jsonGet(key) != null;
    }

    public void setKey(String key, TransacaoDTO value) {
        jedis.jsonSet(key, gson.toJson(value));
        jedis.expire(key, 60*5);
    }
    public TransacaoDTO getKey(String key){
        if (verifyIfExistKey(key)){
            return (TransacaoDTO) Mapper.convert(jedis.jsonGet(key), TransacaoDTO.class);
        }
        return null;
    }

    public List<TransacaoDTO> getAllKey() {
        List<TransacaoDTO> transacoesDTO = new ArrayList<>();
        for (String key : jedis.keys("transacao::*")) {
            TransacaoDTO transacaoDTO = getKey(key);
            if(transacaoDTO != null){
                transacoesDTO.add(transacaoDTO);
            };
        }
        return transacoesDTO;
    }

    public TransacaoDTO updateKey(String key, String field, String value){
        if (verifyIfExistKey(key)) {
            jedis.jsonSet(key, Path.of(field), value);
            return (TransacaoDTO) Mapper.convert(jedis.jsonGet(key), TransacaoDTO.class);
        }
        return null;
    }

}
