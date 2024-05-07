import {TodoCategory} from "../../model/todo/todoCategory";
import {HttpClient} from "../http/httpClient";

export class TodoCategoryService {

  httpClient: HttpClient;

  constructor() {
    this.httpClient = new HttpClient();
  }

  deleteTodoCategory(category: TodoCategory, onSuccess: (category: TodoCategory) => void, onError?: (status: number) => void) {
    this.httpClient.delete('todo/category/delete', category.category).then(r =>{
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }
      onSuccess(TodoCategoryService.mapCategory(r.data))
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }})
  }

  saveCategory(category: TodoCategory, onSuccess?: (category: TodoCategory) => void, onError?: (status: number) => void) {
    this.httpClient.post('todo/category/save', category).then(r =>{
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }
      if (onSuccess) {
        onSuccess(TodoCategoryService.mapCategory(r.data))
      }
    }).catch(error => {
      if (onError) {
        onError(error.status)
      }})
  }

  getAllCategories(onSuccess: (categories: TodoCategory[]) => void, onError?: (status: number) => void) {

    this.httpClient.get('todo/category/get').then(r => {
      if (r.status != 200){
        if (onError) {
          onError(r.status);
        }
        return;
      }

      let responseCategories: TodoCategory[] = [];

      for (let dataItem of r.data){
        responseCategories.push(TodoCategoryService.mapCategory(dataItem));
      }
      onSuccess(responseCategories)
    }).catch((reason: any) => {
      if (onError) {
        onError(reason.status)
      }
    })
  }

  static mapCategory(data: TodoCategoryResponseData): TodoCategory {
    return new TodoCategory(
      data.category,
      data.color,
      data.description
    )
  }
}

export interface TodoCategoryResponseData {
  category: string;
  description: string;
  color: string;
}
