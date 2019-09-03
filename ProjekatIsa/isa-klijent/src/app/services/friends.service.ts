import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FriendsService {

  constructor(private  http: HttpClient) { }


  getFriendsByUser(username: string) {
    return this.http.get('http://localhost:8080/friends/'.concat(username));
  }

  getAll() {
    return this.http.get('http://localhost:8080/friends/all');
  }

  acceptFriendship(id: string) {
    return this.http.get('http://localhost:8080/friends/accept/'.concat(id));
  }

  deleteFriendship(id: string) {
    return this.http.delete('http://localhost:8080/friends/'.concat(id));
  }

  search(searchString: string, id: number) {
    return this.http.get('http://localhost:8080/friends/search/'.concat(id.toString()).concat('/').concat(searchString));
  }

  sendFriendRequest(friendship: any) {
    return this.http.post('http://localhost:8080/friends', friendship);
  }
}
