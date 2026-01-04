import { Component } from '@angular/core';
import { TarefaListComponent } from './components/tarefa-list/tarefa-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [TarefaListComponent], // IMPORTANTE: Isso permite usar a tag <app-tarefa-list>
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  title = 'Task Manager 2026';
}
