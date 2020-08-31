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

    public void retrieveRoom(String name){
        this.chatRoom = this.service.getRoomBy(name);
        if(this.chatRoom != null){
            System.out.println("Sala: " + this.chatRoom.name);
        } else {
            System.out.println("Não achado");
        }
    }

    public void writeRoom(String name){
        this.service.writeRoom(name);
        System.out.println("Sala escrita");
    }

    public void listAllRooms() throws ServiceUnavailableException {
        this.chatRoomList = this.service.listAllRooms();
        for (ChatRoom chatRoom : this.chatRoomList) {
            System.out.println("Sala: " + chatRoom.name);
        }
        System.out.println("Salas retornadas");
    }

    public void listUserByRoom(String user) throws ServiceUnavailableException {
        this.userList = this.service.listUsersByRoom(user);
        for (User usr: this.userList) {
            System.out.println("Usuário: " + usr.name);
        }
        System.out.println("Usuários retornados");
    }

    public void moveUser(String name, String to){
        this.service.moveUser(name, to);
    }

}
