package com.stefanini.taskManager_backend.infraestructure.repository;
import com.stefanini.taskManager_backend.domain.model.StatusTarefa;
import com.stefanini.taskManager_backend.domain.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    // Busca tarefas com status pendente
    List<Tarefa> findByStatus(StatusTarefa statusTarefa);

}
