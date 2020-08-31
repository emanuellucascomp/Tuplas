package br.com.wb.controllers;

import br.com.wb.services.MessageService;
import br.com.wb.tuples.ChatRoom;
import br.com.wb.tuples.Message;
import br.com.wb.tuples.User;

public class MessageController {

    private Message message;
    private MessageService service;

    public MessageController(){
        this.message = new Message();
        this.service = new MessageService();
    }

    public void writeMessage(String from, String to, boolean channel, String content){
        if(!channel){
            this.service.writeDirect(from, to, content);
            System.out.println("Enviada mensagem direta");
        } else {
            this.service.writeToChannel(from, content);
            System.out.println("Enviada para o canal");
        }
    }

    public void readMessage(String from, String to, boolean channel){
        if(!channel){
            this.service.readDirect(from, to);
        } else {
            this.service.readChannel(from);
        }
    }


}
