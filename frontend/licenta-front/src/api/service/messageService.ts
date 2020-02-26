import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';


const API_URL = environment.apiUrl;

@Injectable()
export class MessageService {

  messagesReceived: Message[];
  constructor(private httpClient: HttpClient) {
  }

  public getMessagesReceivedForUser(idUser : number) {
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    return this.httpClient.get<String>(
        `${API_URL}/messages/${idUser}`,
        {headers});
  }

  public saveMessage(message : Message){
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    const data = JSON.stringify(message);
    return this.httpClient.post<String>(
      `${API_URL}/messages/save`,
      data,
      {headers: headers}
      );
  }

  public getMessage(idMessage: number){
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    return this.httpClient.get<String>(
      `${API_URL}/messages/details/${idMessage}`,
      {headers});
  }

  public getMessagesSentByUser(idUser : number) {
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    return this.httpClient.get<String>(
        `${API_URL}/messages/0/${idUser}`,
        {headers});
  }

}