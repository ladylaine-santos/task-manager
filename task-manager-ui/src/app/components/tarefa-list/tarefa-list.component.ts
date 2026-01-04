import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TarefaService } from '../../services/tarefa.service';
import { Tarefa } from '../../models/tarefa';

@Component({
  selector: 'app-tarefa-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './tarefa-list.component.html', // Apontando para o arquivo HTML
  styleUrl: './tarefa-list.component.scss'    // Apontando para o arquivo de estilos
})
export class TarefaListComponent implements OnInit {
  tarefaService = inject(TarefaService);
  tarefas = this.tarefaService.tarefas;
  novaTarefa: Partial<Tarefa> = { titulo: '', descricao: '' };
  tarefaEmEdicao: Partial<Tarefa> | null = null;
  idTarefaSendoEditada: number | null = null;
  ngOnInit() {
    this.tarefaService.listarTodas();
  }

  async adicionar() {
    if (this.novaTarefa.titulo) {
      await this.tarefaService.criar(this.novaTarefa);
      this.novaTarefa = { titulo: '', descricao: '' };
    }
  }

  async concluir(id: number) {
    await this.tarefaService.concluir(id);
  }

  async iniciar(id: number) {
    await this.tarefaService.iniciar(id);
  }

  async remover(id: number) {
    if (confirm('Deseja realmente excluir esta tarefa?')) {
      await this.tarefaService.remover(id);
    }
  }

iniciarEdicao(tarefa: Tarefa) {
    this.idTarefaSendoEditada = tarefa.id!;
    // Criamos uma cópia para não alterar o original antes de clicar em salvar
    this.tarefaEmEdicao = { ...tarefa };
  }

  cancelarEdicao() {
    this.idTarefaSendoEditada = null;
    this.tarefaEmEdicao = null;
  }

  async salvarAlteracao() {
    if (this.idTarefaSendoEditada && this.tarefaEmEdicao) {
      await this.tarefaService.alterar(this.idTarefaSendoEditada, this.tarefaEmEdicao);
      this.cancelarEdicao();
    }
  }
}
