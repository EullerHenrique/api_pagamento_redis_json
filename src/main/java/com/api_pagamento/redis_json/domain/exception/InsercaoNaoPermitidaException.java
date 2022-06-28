package com.api_pagamento.redis_json.domain.exception;


import com.api_pagamento.redis_json.domain.dto.ResponseErrorDTO;

public class InsercaoNaoPermitidaException extends Exception{

    public InsercaoNaoPermitidaException( ){}

    public ResponseErrorDTO getResponseError(){
        ResponseErrorDTO rmDTO = new ResponseErrorDTO();
        rmDTO.setStatus(400);
        rmDTO.setError("Bad Request");
        rmDTO.setMessage("O id, o código de autorização, o nsu e o status não podem ser inseridos pelo usuário");
        return rmDTO;
    }

}
