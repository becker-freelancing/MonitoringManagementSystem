export class ReasonForContact{

  reason: string;
  description: string;

  constructor(reason: string, description?: string) {
    this.reason = reason;
    this.description = description ?? '';
  }
}
