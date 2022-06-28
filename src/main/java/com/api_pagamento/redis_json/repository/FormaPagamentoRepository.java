package com.api_pagamento.redis_json.repository;

import com.api_pagamento.redis_json.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> { }
