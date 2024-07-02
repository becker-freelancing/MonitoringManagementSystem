export class FileStructure {

  current: string;
  children: FileStructure[];


  constructor(current: string, children?: FileStructure[]) {
    this.current = current;
    this.children = children ? children : [];
  }

  addChildren(children: FileStructure){
    this.children.push(children);
  }
}
