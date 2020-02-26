import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const API_URL = environment.apiUrl;

@Injectable()
export class PredictionService {

  constructor(private httpClient: HttpClient) {
  }

  public getPredictions(prefix: Prefix) {
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    return this.httpClient.post<String>(
        `${API_URL}/predictions`,
        prefix,
        {headers});
  }

  public updateModel(text: String){
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    return this.httpClient.put<String>(
        `${API_URL}/predictions/update`,
        text,
        {headers});
  }

  public updateAccuracy(accuracy: Accuracy){
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    return this.httpClient.post<String>(
        `${API_URL}/predictions/accuracy`,
        accuracy,
        {headers});
  }



}