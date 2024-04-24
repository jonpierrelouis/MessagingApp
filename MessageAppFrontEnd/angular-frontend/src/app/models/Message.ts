export default class Message {
    messageId: number;
    senderId: number;
    recipientId: number;
    messageBody: string;

    constructor(messageId: number, senderId: number, recipientId: number, messageBody: string){

        this.messageId = messageId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.messageBody = messageBody;
    }

}