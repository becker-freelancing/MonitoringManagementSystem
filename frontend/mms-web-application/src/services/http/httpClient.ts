import axios, { AxiosResponse, AxiosError } from "axios";

export class HttpClient {

  constructor(private baseUrl: string) {}

  public get(endpoint: string): Promise<AxiosResponse<any>> {
    return axios.get(this.baseUrl + '/' + endpoint);
  }

  public post(endpoint: string, data: any): Promise<AxiosResponse<any>>{
    return axios.post(this.baseUrl + '/' + endpoint, data)
  }

  public delete(endpoint: string, id: string): Promise<AxiosResponse<any>>{
    return axios.delete(this.baseUrl + '/' + endpoint + '/' + id)
  }

  public put(endpoint: string, id: string, data: any): Promise<AxiosResponse<any>>{
    return axios.put(this.baseUrl + '/' + endpoint + '/' + id, data)
  }
}
