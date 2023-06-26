import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { ChatService } from '../chat.service';
import { chatUser } from '../vo/chatUser';
import { messageRespose } from '../vo/messageResponse';
import { messageObject } from '../vo/message';
@Component({
    selector: 'chat-detail',
    templateUrl: 'chat-detail.component.html'
})

export class ChatDetailComponent implements OnInit {

    jsonData: messageRespose[] =
        [
            { fromName: "khizer", toName: "sundar", messageContent: "what are you working on?", time: "2020-04-02T12:56:37.615" },
            { fromName: "khizer", toName: "sundar", messageContent: "There?", time: "2020-04-02T12:56:37.615" },
            { fromName: "khizer", toName: "sundar", messageContent: "Need something?", time: "2020-04-02T12:56:37.615" },
            { fromName: "khizer", toName: "sundar", messageContent: "Lol!", time: "2020-04-02T12:56:37.615" },
            { fromName: "khizer", toName: "sundar", messageContent: "whatever", time: "2020-04-02T12:56:37.615" },
            { fromName: "Imran", toName: "Shoaib", messageContent: "Simply", time: "2020-04-02T12:56:37.615" },
            { fromName: "Sundar", toName: "khizer", messageContent: "Reply text", time: "2020-04-02T12:56:37.615" }
        ];

    jsonDataChatUser: chatUser[] =
        [
            { id: "1001", loggedInUserName: "khizer", toName: "Sundar", toId: "1002" },
            { id: "1001", loggedInUserName: "khizer", toName: "Dinesh", toId: "1003" },
            { id: "1001", loggedInUserName: "khizer", toName: "Arun", toId: "1004" },
            { id: "1001", loggedInUserName: "khizer", toName: "Abul", toId: "1005" },
            { id: "1001", loggedInUserName: "khizer", toName: "Imran", toId: "1006" },
            { id: "1001", loggedInUserName: "khizer", toName: "Muzammil", toId: "1007" },
            { id: "1002", loggedInUserName: "Sundar", toName: "khizer", toId: "1001" }

        ];
    // ----------------
    messageObj: messageObject;
    messageRes: Array<messageRespose>;
    fromN: string = "";
    toN: string = "";
    plainObj: any;

    constructor(private _ChatService: ChatService, private router: Router, private activatedRoute: ActivatedRoute) { }

    ngOnInit() {
        let id = this.activatedRoute.snapshot.paramMap.get('id');
        console.log(id)
        this.filterSelectedUser(id);
        this.messageObj = new messageObject();
        this.messageRes = new Array<messageRespose>();
        // this.messageRes = this.jsonData;
        this.triggerReceiveDataEvent()
        this.connect()
    }

    filterSelectedUser(id) {
        var output: any = this.jsonDataChatUser.filter(x => x.toId == id)
        console.log(output)
        this.fromN = output[0].loggedInUserName;
        this.toN = output[0].toName
    }

    connect() {
        this._ChatService._connect();
    }

    disconnect() {
        this._ChatService._disconnect();
    }

    sendMessage() {
        this.messageObj.fromName = this.fromN
        this.messageObj.toName = this.toN
        console.log('sending message -', this.messageObj)
        this._ChatService._send(this.messageObj);
    }

    handleMessage(message) {
        console.log('handling')
        if ((message.fromName == this.fromN || message.toName == this.fromN) && (message.toName == this.toN || message.fromName == this.toN)) {
            // var x = this.formData(message);
            // console.log('formed',x)
            this.messageRes.push(message);
            console.log(this.messageRes)
        }
    }

    formData(obj) {
        console.log(obj)
        var x: any
        x.fromName = obj.fromName;
        x.toName = obj.toName;
        x.messageContent = obj.messageContent;
        x.time = obj.time;
        return x;
    }

    triggerReceiveDataEvent() {
        console.log('Data receiver event triggered')
        this._ChatService.chatMessageAdded.subscribe((data) => {
            console.log('data is back', data)
            this.handleMessage(data)
        });
    }
}