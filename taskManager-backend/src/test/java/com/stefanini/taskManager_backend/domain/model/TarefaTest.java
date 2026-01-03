package com.stefanini.taskManager_backend.domain.model;

import com.stefanini.taskManager_backend.domain.model.StatusTarefa;
import com.stefanini.taskManager_backend.domain.model.Tarefa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TarefaTest {
    @Test
    @DisplayName("Deve concluir uma tarefa com sucesso e definir a data de conclusão")
    void deveConcluirTarefa() {
        // GIVEN (Dado que tenho uma tarefa pendente)
        Tarefa tarefa = new Tarefa();
        tarefa.setStatus(StatusTarefa.PENDENTE);

        // WHEN (Quando eu concluo a tarefa)
        tarefa.concluirTarefa();

        // THEN (Então o status deve ser CONCLUIDA e a data não deve ser nula)
        assertEquals(StatusTarefa.CONCLUIDA, tarefa.getStatus());
        assertNotNull(tarefa.getDataConclusao());
    }
}
