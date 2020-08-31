package br.com.wb.controllers;

import br.com.wb.services.UserService;
import br.com.wb.tuples.User;

public class UserController {

    private User user;
    private UserService service;

    public UserController(){
        this.user = new User();
        this.service = new UserService();
    }

    public void retrieveUser(String user){
        this.user = this.service.getUserBy(user);
        if(this.user!= null){
            System.out.println("User: " + this.user.name + " / Chat Room: " + this.user.chatRoom);
        } else {
            System.out.println("Não achado");
        }
    }

    public void writeUser(String name, String chatRoom){
        this.service.writeUser(name, chatRoom);
        System.out.println("Usuário escrito");
    }

}
