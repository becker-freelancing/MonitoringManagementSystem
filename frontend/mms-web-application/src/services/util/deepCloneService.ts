import _ from 'lodash';

export class DeepCloneService{

  deepCopy<T>(obj: T): T {
    return _.cloneDeep(obj);
  }

}
