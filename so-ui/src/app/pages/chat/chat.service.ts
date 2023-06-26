import { Injectable, EventEmitter } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { environment } from '../../../environments/environment';

@Injectable()
export class ChatService {
    baseUrl = environment.chatApiUrl;
    chatMessageAdded = new EventEmitter();

    webSocketEndPoint: string = this.baseUrl + '/chat';
    grpTopic: string = "/topic/group-chat-logs";
    indTopic: string = "/topic/ind-chat-logs";
    stompClient: any;

    constructor(private http: Http) { }

    //------------------HTTP-------------------------

    fetchAllUsers() {
        return this.http.get(this.baseUrl + '/chat/profile').map((response: Response) => response.json());
    }

    fetchGroups() {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        return this.http.get(this.baseUrl + '/chat/groups/' + currentUser.id).map((response: Response) => response.json());
    }

    fetchGroupChatLogs(groupChatId) {
        return this.http.get(this.baseUrl + '/chat/group-chat-logs/' + groupChatId).map((response: Response) => response.json());
    }

    fetchIndividualChatLogs(fromUserId, toUserId) {
        return this.http.get(this.baseUrl + '/chat/ind-chat-logs/' + fromUserId + '/' + toUserId).map((response: Response) => response.json());
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

    onMessageReceived(message) {
        console.log("Message Recieved from Server :: " + message);
        var obj = JSON.parse(message.body);
        this.chatMessageAdded.emit(obj)
    }

}