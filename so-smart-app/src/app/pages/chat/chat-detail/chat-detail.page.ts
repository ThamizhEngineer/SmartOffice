import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ChatService } from '../chat.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-chat-detail',
  templateUrl: './chat-detail.page.html',
  styleUrls: ['./chat-detail.page.scss'],
})
export class ChatDetailPage implements OnInit {

  messageRes: any[] = [];
  fromUserId: string = "";
  toUserId: string = "";
  currentUserId:any
  currentMessagePayload:string="";
  currentType:string="";
  isTypeGrp:boolean=false;
  constructor(private _ChatService: ChatService, private router: Router, private activatedRoute: ActivatedRoute, private _uitls:UtilsService) { }

  ngOnInit() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.currentUserId = currentUser.id;
    this.fromUserId = currentUser.id;
    this.toUserId = this.activatedRoute.snapshot.paramMap.get('id');
    this.currentType = this.activatedRoute.snapshot.paramMap.get('type');
    if (this.currentType=="ind") {
      this.isTypeGrp=false
      this.loadIntChatLogs()
    } else {
      this.isTypeGrp=true
      this.loadGrpChatLogs()
    }
    this.triggerReceiveDataEvent()
  }

  connect() {
    this._ChatService._connect();
  }

  disconnect() {
    this._ChatService._disconnect();
  }

  sendMessage() {
    console.log(this.fromUserId,this.toUserId,this.currentMessagePayload)
    var obj = {fromAuthUserId:this.fromUserId,toAuthUserId: this.toUserId,messagePayload:this.currentMessagePayload};
    console.log('sending message -', this.currentType,' - ', obj)
    this._ChatService._send(obj);
    this.currentMessagePayload="";
  }

  sendGroupMessage(){
    console.log(this.fromUserId,this.toUserId,this.currentMessagePayload)
    var obj = {groupChatId:this.toUserId,authUserId: this.currentUserId,messagePayload:this.currentMessagePayload};
    console.log('sending message -', this.currentType,' - ', obj)
    this._ChatService._sendGrp(obj);
    this.currentMessagePayload="";
  }

  triggerReceiveDataEvent() {
    console.log('Data receiver event triggered')
    this._ChatService.chatMessageAdded.subscribe((data) => {
      console.log(data)
      this.messageRes.push(data)
    });
  }

  chatListClicked() {
    this.router.navigate(['/home/chat']);
  }


  // ---------------------

  loadIntChatLogs() {
    this._uitls.presentLoader().then((x) => {
      this.fetchIndChatLogs();
    });
  }

  fetchIndChatLogs() {
    this._ChatService.fetchIndividualChatLogs(this.fromUserId,this.toUserId).subscribe(x => {
      var res:any = x
      this.messageRes=res
      console.log(this.messageRes)
      this._uitls.dismissLoader();
    }, error => {
      let message = this._uitls.getErrorStatus(error.status);
      this._uitls.dismissLoader();
      this._uitls.presentAlert(message);
    });
  }

  // ---------------------- 

  loadGrpChatLogs() {
    this._uitls.presentLoader().then((x) => {
      this.fetchGrpChatLogs();
    });
  }

  fetchGrpChatLogs() {
    var groupChatId = this.toUserId 
    this._ChatService.fetchGroupChatLogs(groupChatId).subscribe(x => {
      var res:any = x
      this.messageRes=res
      console.log(this.messageRes)
      this._uitls.dismissLoader();
    }, error => {
      let message = this._uitls.getErrorStatus(error.status);
      this._uitls.dismissLoader();
      this._uitls.presentAlert(message);
    });
  }

}
