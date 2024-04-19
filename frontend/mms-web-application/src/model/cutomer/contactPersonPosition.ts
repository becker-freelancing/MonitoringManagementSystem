
export class ContactPersonPosition {

  id: number;
  position: string;
  description: string;

  constructor(id: number, position: string, description?: string) {
    this.id = id;
    this.position = position;
    this.description = description ?? '';
  }

}
