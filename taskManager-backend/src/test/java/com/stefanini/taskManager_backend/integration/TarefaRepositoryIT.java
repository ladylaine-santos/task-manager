package com.stefanini.taskManager_backend.integration;

import com.stefanini.taskManager_backend.domain.model.Tarefa;
import com.stefanini.taskManager_backend.domain.model.StatusTarefa;
import com.stefanini.taskManager_backend.infraestructure.repository.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest  // Sobe o contexto do Spring (mais lento, mas testa tudo)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Usa o seu SQL Server do Docker
public class TarefaRepositoryIT {

    @Autowired
    private TarefaRepository repository;

    @Test
    void devePersistirTarefaNoBancoReal() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Teste Integrado");
        tarefa.setStatus(StatusTarefa.PENDENTE);

        Tarefa salva = repository.save(tarefa);

        assertNotNull(salva.getId());
        assertEquals("Teste Integrado", salva.getTitulo());
    }
}