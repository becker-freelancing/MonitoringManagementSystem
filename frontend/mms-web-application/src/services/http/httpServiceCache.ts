import {DeepCloneService} from "../util/deepCloneService";

export class HttpServiceCache<T> {

  static caches: Map<any, HttpServiceCache<any>> = new Map<any, HttpServiceCache<any>>();

  static getInstance<T>(obj: any): HttpServiceCache<T>{
    if(!HttpServiceCache.caches.has(obj)){
      HttpServiceCache.caches.set(obj, new HttpServiceCache<typeof obj>());
    }

    return HttpServiceCache.caches.get(obj) ?? new HttpServiceCache<typeof obj>();
  }

  private cacheItems: T[] = []

  getItems(): T[]{
    let deepCloneService = new DeepCloneService();
    return this.cacheItems.map(i => deepCloneService.deepCopy(i));
  }

  setItems(items: T[]) {
    this.cacheItems = items;
  }

  clearCache(){
    this.cacheItems = [];
  }

  isCacheEmpty(): boolean {
    return this.cacheItems.length === 0;
  }

  isCacheFilled(): boolean {
    return !this.isCacheEmpty();
  }
}
