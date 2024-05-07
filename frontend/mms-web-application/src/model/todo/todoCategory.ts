export class TodoCategory {

  category: String;
  color: String;
  description?: String;


  constructor(category: String, color: String, description?: String) {
    this.category = category;
    this.color = color;
    this.description = description;
  }
}
