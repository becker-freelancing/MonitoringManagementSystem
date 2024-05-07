export class TodoCategory {

  category: string;
  color: string;
  description?: string;


  constructor(category: string, color: string, description?: string) {
    this.category = category;
    this.color = color;
    this.description = description;
  }
}
