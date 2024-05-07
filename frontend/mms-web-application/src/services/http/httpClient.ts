import axios, { AxiosResponse, AxiosError } from "axios";

export class HttpClient {

  baseUrl: string;

  constructor(baseUrl?: string) {
    this.baseUrl = baseUrl ?? 'http://localhost:45001/mms/api';
  }

  public get(endpoint: string): Promise<AxiosResponse<any>> {
    return axios.get(this.baseUrl + '/' + endpoint);
  }

  public post(endpoint: string, data: any): Promise<AxiosResponse<any>>{
    return axios.post(this.baseUrl + '/' + endpoint, data)
  }

  public delete(endpoint: string, id: any, param?: any): Promise<AxiosResponse<any>>{
    let url = this.baseUrl + '/' + endpoint + '/' + id;
    if(param){
      url += '/' + param
    }
    return axios.delete(url)
  }

  public put(endpoint: string, id: string, data: any): Promise<AxiosResponse<any>>{
    return axios.put(this.baseUrl + '/' + endpoint + '/' + id, data)
  }
}
