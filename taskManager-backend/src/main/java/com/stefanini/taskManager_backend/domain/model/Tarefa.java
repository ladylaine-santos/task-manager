package com.stefanini.taskManager_backend.domain.model;
import com.stefanini.taskManager_backend.domain.model.StatusTarefa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_tarefas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    private String descricao;

    @Enumerated(EnumType.STRING) // Salva o nome (PENDENTE) no banco em vez do número (0)
    private StatusTarefa status = StatusTarefa.PENDENTE;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;
    private String insightMotivacional;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        if (this.status == null) {
            this.status = StatusTarefa.PENDENTE;
        }
    }

    // --- REGRAS DE NEGÓCIO (DDD) ---

    public void iniciarTarefa() {
        if (this.status == StatusTarefa.PENDENTE) {
            this.status = StatusTarefa.EM_ANDAMENTO;
        } else {
            throw new IllegalStateException("A tarefa só pode ser iniciada se estiver PENDENTE.");
        }
    }

    public void concluirTarefa() {
        this.status = StatusTarefa.CONCLUIDA;
        this.dataConclusao = LocalDateTime.now();
    }
}