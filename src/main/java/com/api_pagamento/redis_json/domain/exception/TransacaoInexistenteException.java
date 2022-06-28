package com.api_pagamento.redis_json.domain.exception;


import com.api_pagamento.redis_json.domain.dto.ResponseErrorDTO;

public class TransacaoInexistenteException extends Exception{
    public TransacaoInexistenteException() {}
    public ResponseErrorDTO getResponseError(){
        ResponseErrorDTO rmDTO = new ResponseErrorDTO();
        rmDTO.setStatus(404);
        rmDTO.setError("Not Found");
        rmDTO.setMessage("Transação(ões) inexistente(s)");
        return rmDTO;
    }
}
