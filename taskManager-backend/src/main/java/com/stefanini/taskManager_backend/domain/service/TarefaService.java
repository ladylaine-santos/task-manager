package com.stefanini.taskManager_backend.domain.service;

import com.stefanini.taskManager_backend.application.dto.TarefaRequest;
import com.stefanini.taskManager_backend.application.dto.TarefaResponse;
import com.stefanini.taskManager_backend.domain.model.Tarefa;
import com.stefanini.taskManager_backend.infraestructure.client.AdviceClient;
import com.stefanini.taskManager_backend.infraestructure.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Cria o construtor para injeção de dependência (SOLID - DIP)
public class TarefaService {

    private final TarefaRepository repository;
    private final AdviceClient adviceClient;

    @Transactional
    public TarefaResponse criarTarefa(TarefaRequest request){
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(request.titulo());
        tarefa.setDescricao(request.descricao());
        // chamada ao webclient para buscar o "insight motivacional"
        String conselho=adviceClient.buscarConselhoMotivacional();
        tarefa.setInsightMotivacional(conselho);

        Tarefa salva=repository.save(tarefa);
        return toResponse(salva);
    }

    public List<TarefaResponse> listarTodas() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Mapper Simples (Poderia usar MapStruct em projetos maiores)
    private TarefaResponse toResponse(Tarefa t) {
        return new TarefaResponse(
                t.getId(), t.getTitulo(), t.getDescricao(),
                t.getStatus(), t.getInsightMotivacional(), t.getDataCriacao()
        );
    }

    @Transactional
    public TarefaResponse alterarTarefa(Long id, TarefaRequest request){
        Tarefa tarefa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o ID: " + id));

        tarefa.setTitulo(request.titulo());
        tarefa.setDescricao(request.descricao());
        Tarefa atualizada = repository.save(tarefa);
        return toResponse(atualizada);
    }

    @Transactional
    public void removerTarefa(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Não é possível remover: Tarefa não encontrada.");
        }
        repository.deleteById(id);
    }

    @Transactional
    public TarefaResponse concluirTarefa(Long id) {
        Tarefa tarefa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));

        // Chamamos a regra de negócio que criamos dentro da Entidade (DDD)
        tarefa.concluirTarefa();

        return toResponse(repository.save(tarefa));
    }

    @Transactional
    public TarefaResponse iniciarTarefa(Long id) {
        Tarefa tarefa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));

        // Chamamos a regra de negócio que criamos dentro da Entidade (DDD)
        tarefa.iniciarTarefa();

        return toResponse(repository.save(tarefa));
    }
}
