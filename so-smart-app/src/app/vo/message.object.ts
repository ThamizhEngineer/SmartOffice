export class messageObject{
    fromName ?:string;
    toName ?:string;
    messageContent ?:string;
    time ?:string;
  }

  export class individualChatLog{
    id ?:string;
    fromAuthUserId ?:string;
    toAuthUserId ?:string;
    messagePayload ?:string;
    messageType ?:string;
    sentDt ?:string;
    createdBy ?:string;
    createdDt ?:string;
    modifiedBy ?:string;
    modifiedDt ?:string;
  }