
import { Injectable, signal, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Tarefa } from '../models/tarefa';
import { firstValueFrom } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class TarefaService {
  private http = inject(HttpClient); // Nova forma de injeção (Angular 20)
  private readonly API_URL = '/api/tarefas';

  // O Signal é a fonte da verdade para a UI
  tarefas = signal<Tarefa[]>([]);

  async listarTodas() {
    const dados = await firstValueFrom(this.http.get<Tarefa[]>(this.API_URL));
    this.tarefas.set(dados); // Atualiza o signal
  }

  async criar(tarefa: Partial<Tarefa>) {
    const nova = await firstValueFrom(this.http.post<Tarefa>(this.API_URL, tarefa));
    this.tarefas.update(lista => [...lista, nova]); // Adiciona à lista sem recarregar tudo
  }

  async concluir(id: number) {
    await firstValueFrom(this.http.patch<Tarefa>(`${this.API_URL}/${id}/concluir`, {}));
    await this.listarTodas(); // Recarrega para atualizar status e data de conclusão
  }

  async iniciar(id: number) {
    await firstValueFrom(this.http.patch<Tarefa>(`${this.API_URL}/${id}/iniciar`, {}));
    await this.listarTodas(); // Recarrega para atualizar status;
  }


  async remover(id: number) {
    await firstValueFrom(this.http.delete(`${this.API_URL}/${id}`));
    this.tarefas.update(lista => lista.filter(t => t.id !== id));
  }

async alterar(id: number, tarefa: Partial<Tarefa>) {
    const atualizada = await firstValueFrom(this.http.put<Tarefa>(`${this.API_URL}/${id}`, tarefa));
    this.tarefas.update(lista =>
      lista.map(t => t.id === id ? atualizada : t)
    );
  }
}
