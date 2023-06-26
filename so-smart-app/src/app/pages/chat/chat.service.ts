import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

import { environment } from '../../../environments/environment';
import { UtilsService } from 'src/app/services/utils.service';


@Injectable({
  providedIn: 'root'
})
export class ChatService {
  baseUrl:string = environment.baseUrl+"/so-chat-service/";
  // Web Socket Configuration
  chatMessageAdded = new EventEmitter();
  webSocketEndPoint: string = this.baseUrl + 'chat';
  grpTopic: string = "/topic/group-chat-logs";
  indTopic: string = "/topic/ind-chat-logs"
  stompClient: any;

  constructor(private http: HttpClient, private _utils:UtilsService) { }

  private jwt() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var currentToken = this._utils.getCurrentToken(currentUser);
    console.log(currentUser)
    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': currentToken
    });
    return { headers: httpHeaders };
  }


  //------------------HTTP-------------------------

  fetchAllUsers() {
		return this.http.get(this.baseUrl + '/chat/profile')
  }

  fetchGroups(){
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    return this.http.get(this.baseUrl + '/chat/groups/'+ currentUser.id);
  }

  fetchGroupChatLogs(groupChatId){
    return this.http.get(this.baseUrl + '/chat/group-chat-logs/'+groupChatId);
  }

  fetchIndividualChatLogs(fromUserId, toUserId){
    return this.http.get(this.baseUrl + '/chat/ind-chat-logs/'+fromUserId+'/'+toUserId);
  }


  // -------------------WEBSOCKET-API--------------------

  _connect() {
    console.log("Initialize ind WebSocket Connection");
    let ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    const _this = this;
    _this.stompClient.connect({}, function (frame) {
      _this.stompClient.subscribe(_this.indTopic, function (sdkEvent) {
        console.log('when', sdkEvent)
        _this.onMessageReceived(sdkEvent);
      });
      // _this.stompClient.reconnect_delay = 2000;
    }, this.errorCallBack);
  };

  _connectGroup() {
    console.log("Initialize Group WebSocket Connection");
    let ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    const _this = this;
    _this.stompClient.connect({}, function (frame) {
      _this.stompClient.subscribe(_this.grpTopic, function (sdkEvent) {
        console.log('when', sdkEvent)
        _this.onMessageReceivedFromGrp(sdkEvent);
      });
      // _this.stompClient.reconnect_delay = 2000;
    }, this.errorCallBack);
  };

  _disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
    console.log("Disconnected");
  }

  // on error, schedule a reconnection attempt
  errorCallBack(error) {
    console.log("errorCallBack -> " + error)
    setTimeout(() => {
      this._connect();
    }, 5000);
  }

  /**
  * Send message to sever via web socket
  * @param {*} message 
  */
  _send(message) {
    this.stompClient.send("/app/ind-chat-logs", {}, JSON.stringify(message));
  }

  _sendGrp(message) {
    this.stompClient.send("/app/group-chat-logs", {}, JSON.stringify(message));
  }

  onMessageReceived(message) {
    console.log("Message Recieved from Server :: " + message);
    var obj = JSON.parse(message.body);
    this.chatMessageAdded.emit(obj)
  }

  onMessageReceivedFromGrp(message) {
    console.log("Message Recieved from grp Server :: " + message);
    var obj = JSON.parse(message.body);
    this.chatMessageAdded.emit(obj)
  }

}
