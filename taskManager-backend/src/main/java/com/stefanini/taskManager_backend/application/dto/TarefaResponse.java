package com.stefanini.taskManager_backend.application.dto;

import com.stefanini.taskManager_backend.domain.model.StatusTarefa;

import java.time.LocalDateTime;

public record TarefaResponse(Long id,
                             String titulo,
                             String descricao,
                             StatusTarefa status,
                             String insightMotivacional,
                             LocalDateTime dataCriacao) {
}
