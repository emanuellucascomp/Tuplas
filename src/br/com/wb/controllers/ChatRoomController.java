package br.com.wb.controllers;

import br.com.wb.services.ChatRoomService;
import br.com.wb.tuples.ChatRoom;
import br.com.wb.tuples.User;

import javax.naming.ServiceUnavailableException;
import java.util.List;

public class ChatRoomController {

    //TODO destruir ambientes vazios

    private ChatRoom chatRoom;
    private ChatRoomService service;
    private List<ChatRoom> chatRoomList;
    private List<User> userList;

    public ChatRoomController(){
        this.chatRoom = new ChatRoom();
        this.service = new ChatRoomService();
    }

    public void retrieveEnvironment(String name){
        this.chatRoom = this.service.getEnvironmentBy(name);
        if(this.chatRoom != null){
            System.out.println("Ambiente: " + this.chatRoom.name);
        } else {
            System.out.println("Não achado");
        }
    }

    public void writeEnvironment(String name){
        this.service.writeEnvironment(name);
        System.out.println("Ambiente escrito");
    }

    public void listAllEnvironments() throws ServiceUnavailableException {
        this.chatRoomList = this.service.listAllEnvironments();
        for (ChatRoom chatRoom : this.chatRoomList) {
            System.out.println("Ambiente: " + chatRoom.name);
        }
        System.out.println("Ambientes retornados");
    }

    public void listUserByEnvironment(String user) throws ServiceUnavailableException {
        this.userList = this.service.listUserByEnvironment(user);
        for (User usr: this.userList) {
            System.out.println("Usuário: " + usr.name);
        }
        System.out.println("Usuários retornados");
    }

    public void moveUser(String name, String to){
        this.service.moveUser(name, to);
    }
    
    public void startChat(String userChat) {

    }
}
