import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ChatService } from '../chat.service';

@Component({
  selector: 'chat-list',
  templateUrl: 'chat-list.component.html',
  styleUrls: ['./chat.scss']
})

export class ChatListComponent implements OnInit {
  users: any
  messageRes: any = [];
  currentUserId: string = ""
  fromUserId: string = ""
  toUserId: string = ""
  currentMessagePayload: string = ""
  constructor(private router: Router, private _service: ChatService) { }

  ngOnInit() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.currentUserId = currentUser.id;
    this.fromUserId = currentUser.id;
    this.fetchChatList()
    this.connect()
    this.triggerReceiveDataEvent()
  }

  connect() {
    this._service._connect();
  }

  fetchChatList() {
    this._service.fetchAllUsers().subscribe(x => {
      console.log(x)
      this.users = x;
    }, error => {
      console.log('error - ', error)
    });
  }

  onSelect(item) {
    console.log(item)
    var toUserId = item.id
    this.toUserId = item.id
    this.fetchIndChatLogs(toUserId)
  }

  fetchIndChatLogs(toUserId) {
    this._service.fetchIndividualChatLogs(this.fromUserId, toUserId).subscribe(x => {
      var res: any = x
      this.messageRes = res
      console.log(this.messageRes)
    }, error => {
      console.log('error - ', error)
    });
  }

  // --------------

  sendMessage() {
    console.log(this.fromUserId, this.toUserId, this.currentMessagePayload)
    var obj = { fromAuthUserId: this.fromUserId, toAuthUserId: this.toUserId, messagePayload: this.currentMessagePayload };
    console.log(obj)
    this._service._send(obj);
    this.currentMessagePayload = "";
  }

  triggerReceiveDataEvent() {
    console.log('Data receiver event triggered')
    this._service.chatMessageAdded.subscribe((data) => {
      console.log(data)
      this.messageRes.push(data)
    });
  }
}