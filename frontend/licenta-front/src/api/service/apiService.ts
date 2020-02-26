import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

const API_URL = environment.apiUrl;

@Injectable()
export class ApiService {

  constructor(private httpClient: HttpClient) {
  }

  public greetings(){
    return this.httpClient.get<String>(`${API_URL}/greeting`)
      .subscribe(res => {
        console.log(res);
      });
  }

}