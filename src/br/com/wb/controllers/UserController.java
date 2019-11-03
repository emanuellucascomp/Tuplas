package br.com.wb.controllers;

import br.com.wb.services.UserService;
import br.com.wb.tuples.User;
import net.jini.core.entry.Entry;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    private User user;
    private UserService service;

    public UserController(){
        this.user = new User();
        this.service = new UserService();
    }

    public void retrieveUser(String user){
        this.user = this.service.getUserBy(user);
        System.out.println(user);
    }

    public void writeUser(String name){
        this.service.writeUser(name);
        System.out.println("Usuário escrito");
    }

}
