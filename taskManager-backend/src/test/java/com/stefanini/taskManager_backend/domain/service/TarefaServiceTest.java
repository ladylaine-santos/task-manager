package com.stefanini.taskManager_backend.domain.service;

import com.stefanini.taskManager_backend.application.dto.TarefaRequest;
import com.stefanini.taskManager_backend.application.dto.TarefaResponse;
import com.stefanini.taskManager_backend.domain.model.Tarefa;
import com.stefanini.taskManager_backend.domain.service.TarefaService;
import com.stefanini.taskManager_backend.infraestructure.client.AdviceClient;
import com.stefanini.taskManager_backend.infraestructure.repository.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @Mock
    private TarefaRepository repository;

    @Mock
    private AdviceClient adviceClient;

    @InjectMocks
    private TarefaService service;

    @Test
    void deveCriarTarefaComInsight() {
        // GIVEN
        TarefaRequest request = new TarefaRequest("Teste", "Descricao");
        when(adviceClient.buscarConselhoMotivacional()).thenReturn("Conselho de Teste");
        when(repository.save(any(Tarefa.class))).thenAnswer(i -> i.getArguments()[0]);

        // WHEN
        TarefaResponse response = service.criarTarefa(request);

        // THEN
        assertNotNull(response);
        assertEquals("Conselho de Teste", response.insightMotivacional());
        verify(repository, times(1)).save(any(Tarefa.class));
    }
}
