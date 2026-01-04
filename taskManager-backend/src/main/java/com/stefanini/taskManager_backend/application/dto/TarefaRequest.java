package com.stefanini.taskManager_backend.application.dto;

import jakarta.validation.constraints.NotBlank;

public record TarefaRequest(
        @NotBlank String titulo,
        String descricao
) {}
