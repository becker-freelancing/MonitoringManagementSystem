import {Customer} from "../../../model/cutomer/customer";
import {Project} from "../../../model/project/project";
import {Todo} from "../../../model/todo/todo";


export class CustomerManagementCustomer{

  uiId: number;
  customer: Customer;
  projects: Project[];
  todos: Todo[];


  constructor(uiId: number, customer: Customer, projects?:Project[], todo?:Todo[]) {
    this.uiId = uiId;
    this.customer = customer;
    this.projects = projects ?? [];
    this.todos = todo ?? []
  }

  activeProjects(): number{

    let activeProjects = 0;

    for (const project of this.projects){
      activeProjects += project.active ? 1 : 0;
    }

    return activeProjects;
  }

  activeTodos(): number{

    let activeTodos = 0;

    for (const todo of this.todos){
      activeTodos += todo.finished ? 0 : 1;
    }

    return activeTodos;
  }
}
