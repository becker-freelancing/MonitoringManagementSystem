
export class ContactPersonPosition {

  position: string;
  description: string;

  constructor(position: string, description?: string) {
    this.position = position;
    this.description = description ?? '';
  }

}
