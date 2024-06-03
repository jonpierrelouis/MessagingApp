export default class MessageDTO {
    senderName: string;
    messageBody: string;

    constructor(senderName: string, messageBody: string){

        this.senderName = senderName;
        this.messageBody = messageBody;
    }

}