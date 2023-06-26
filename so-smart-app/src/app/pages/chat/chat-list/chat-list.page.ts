import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UtilsService } from 'src/app/services/utils.service';
import { ChatService } from '../chat.service';

@Component({
  selector: 'app-chat-list',
  templateUrl: './chat-list.page.html',
  styleUrls: ['./chat-list.page.scss'],
})
export class ChatListPage implements OnInit {

  users: any = []
  isGroup: boolean = false;
  constructor(private router: Router, private utilsService: UtilsService, private _service: ChatService) { }

  ngOnInit() {
    this.loadNotof("Individual");
  }

  connect() {
    this._service._connect();
  }

  segmentChanged(event) {
    this._service._disconnect();
    this.users = [];
    console.log(event)
    if (event.detail.value) {
      this.loadNotof(event.detail.value);
    }
  }

  onIndSelect(item) {
    console.log(item.id)
    this.router.navigate(['/home/chat/', item.id, 'ind']);
  }

  onGrpSelect(item) {
    console.log(item)
    this.router.navigate(['/home/chat/', item.chatGroupId, 'grp']);
  }

  loadNotof(value) {
    this.utilsService.presentLoader().then((x) => {
      if (value == "Group") {
        this._service._connectGroup();
        this.isGroup = true
        this.fetchGroupList()
      } else {
        this.connect();
        this.isGroup = false
        this.fetchChatList()
      }
    });
  }

  fetchChatList() {
    this._service.fetchAllUsers().subscribe(x => {
      console.log(x)
      this.users = x;
      this.addColor()
      this.utilsService.dismissLoader();
    }, error => {
      let message = this.utilsService.getErrorStatus(error.status);
      this.utilsService.dismissLoader();
      this.utilsService.presentAlert(message);
    });
  }

  fetchGroupList() {
    this._service.fetchGroups().subscribe(x => {
      console.log(x)
      this.users = x;
      this.utilsService.dismissLoader();
    }, error => {
      let message = this.utilsService.getErrorStatus(error.status);
      this.utilsService.dismissLoader();
      this.utilsService.presentAlert(message);
    });
  }

  addColor() {
    this.users.forEach(element => {
      if (element.chatAvailability) {
        if (element.chatAvailability == "online") {
          element.chatColor = "secondary"
        }
        if (element.chatAvailability == "offline") {
          element.chatColor = "danger"
        } if (element.chatAvailability == "away") {
          element.chatColor = "warning"
        }
      } else {
        element.chatColor = "warning"
      }
    });
    console.log(this.users)
  }

}
