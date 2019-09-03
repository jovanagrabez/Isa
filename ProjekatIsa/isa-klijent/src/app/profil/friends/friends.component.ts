import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user-service/user.service';
import {FriendsService} from '../../services/friends.service';
import {AppComponent} from '../../app.component';


// @ts-ignore
@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit {

  user: any;
  friends: any;         // lista objekata tipa User
  friendships: any;     // lista objekata Friends
  friendRequests: any;  // lista objekata Friends
  usersWhoAreNotFriends: any; // lista koja se popunjava nakon pretrage
  friendsSearch: string;
  userRole: any;
  userTwo= false;
  constructor(private userService: UserService, private friendsService: FriendsService, private appCom: AppComponent) {
    this.user = {username: '', firstName: '', lastName: ''};
  //  this.usersWhoAreNotFriends = [{firstName: '', lastName: ''}];
    this.friendRequests = [];
    this.friends = [];
    this.friendships = [];
    this.friendsSearch = '';
  }

  ngOnInit() {
    this.userService.getLogged(this.appCom.token).subscribe(data => {
      this.user = data;
    this.friendsService.getFriendsByUser(this.user.username).subscribe(allFriendshipsList => {
      let list;
      list = allFriendshipsList;
      for (const f of list) {
         if (f.user2.username === this.user.username) {
           this.userTwo = true;
        if (f.accepted === false) {
          this.friendRequests.push(f);                  // ako nije prihvatio dodaj prijateljstvo u listu da moze da prihvati
        } else {
          this.friends.push(f.user1);
          this.friendships.push(f);
        }
          } else  {                 // ako je trenutni user1 dodaj user2 u prijatelje ako je prihvaceno
           if (f.accepted === false) {
             this.friendRequests.push(f);                  // ako nije prihvatio dodaj prijateljstvo u listu da moze da prihvati
           } else {
             this.friends.push(f.user2);
             this.friendships.push(f);
           }
          }

      }
    });
    });
  }


  deleteFriendship(friend: any) {

    let friendshipIndex;
    let friendsIndex;
    for (let i = 0; i < this.friends.length; i++) {                   // uzme index gde se taj prijatelj sad nalazi u listi
      if (this.friends[i]['username'] === friend['username'] ||  // zbog brisanja iz nje
        this.friends[i]['username'] === friend['username']) {
        friendsIndex = i;
        break;
      }
    }
    for (let i = 0; i < this.friendships.length; i++) {               // uzima index friendship-a koje se brise
      if (this.friendships[i]['user1']['username'] === friend['username'] ||
        this.friendships[i]['user2']['username'] === friend['username']) {
        friendshipIndex = i;
        break;
      }
    }
    this.friendsService.deleteFriendship(this.friendships[friendshipIndex]['id']).subscribe( allFriendships => {
      this.friendships.splice(friendshipIndex, 1);
      this.friends.splice(friendsIndex, 1);
    });
  }


  deleteRequest(friendship: any, index: any) {
    this.friendsService.deleteFriendship(friendship.id).subscribe( allFriendships => {
      this.friendRequests.splice(index, 1);
    });
  }

  acceptRequest(friendship: any, index: any) {
    friendship.accepted = true;
    this.friendsService.acceptFriendship(friendship.id).subscribe( acceptedFriendship => {
      this.friendRequests.splice(index, 1);                 // obrise ga iz request-ova jer je prihvacen
      if (this.user.username === acceptedFriendship['user1']['username']) {
        this.friends.push(acceptedFriendship['user2']);
      } else {
        this.friends.push(acceptedFriendship['user1']);
      }

      this.friendships.push(acceptedFriendship);
    });
  }

  search() {
    this.friendsService.search(this.friendsSearch, this.user.id).subscribe( notFriends => {
      this.usersWhoAreNotFriends = notFriends;
    });
  }

  addFriend(user: any, index: number) {
    const friendship = {user1: {}, user2: {}, accepted: false};
    friendship.user1 = this.user.id;
    friendship.user2 = user.id;
    this.friendsService.sendFriendRequest(friendship).subscribe( friends => {
      this.usersWhoAreNotFriends.splice(index, 1);
    });
  }

}
