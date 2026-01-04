package com.stefanini.taskManager_backend.application.controller;

import com.stefanini.taskManager_backend.application.dto.TarefaRequest;
import com.stefanini.taskManager_backend.application.dto.TarefaResponse;
import com.stefanini.taskManager_backend.domain.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Endpoints para gerenciamento da To-Do List")
@CrossOrigin(origins = "*") // Permitir que o Angular acesse a API
public class TarefaController {

    private final TarefaService service;

    @PostMapping
    @Operation(summary = "Cria uma nova tarefa com um insight motivacional")
    public ResponseEntity<TarefaResponse> criar(@RequestBody @Valid TarefaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarTarefa(request));
    }

    @GetMapping
    @Operation(summary = "Lista todas as tarefas")
    public List<TarefaResponse> listar() {
        return service.listarTodas();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza título e descrição de uma tarefa")
    public ResponseEntity<TarefaResponse> atualizar(@PathVariable Long id, @RequestBody @Valid TarefaRequest request) {
        return ResponseEntity.ok(service.alterarTarefa(id, request));
    }

    @PatchMapping("/{id}/concluir")
    @Operation(summary = "Marca uma tarefa como concluída")
    public ResponseEntity<TarefaResponse> concluir(@PathVariable Long id) {
        return ResponseEntity.ok(service.concluirTarefa(id));
    }

    @PatchMapping("/{id}/iniciar")
    @Operation(summary = "Marca uma tarefa como iniciada")
    public ResponseEntity<TarefaResponse> iniciar(@PathVariable Long id) {
        return ResponseEntity.ok(service.iniciarTarefa(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma tarefa do sistema")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.removerTarefa(id);
    }
}