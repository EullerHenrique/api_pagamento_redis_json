package com.api_pagamento.redis_json.repository.database;

import com.api_pagamento.redis_json.domain.model.Descricao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescricaoRepository extends JpaRepository<Descricao, Long> { }
