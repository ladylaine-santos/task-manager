export interface Tarefa {
  id?: number;
    titulo: string;
    descricao: string;
    status: 'PENDENTE' | 'EM_ANDAMENTO' | 'CONCLUIDA';
    insightMotivacional?: string;
    dataCriacao?: string;
}
