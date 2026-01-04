import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TarefaList } from './tarefa-list';

describe('TarefaList', () => {
  let component: TarefaList;
  let fixture: ComponentFixture<TarefaList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TarefaList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TarefaList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
