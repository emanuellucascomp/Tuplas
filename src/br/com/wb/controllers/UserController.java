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
        if(this.user!= null){
            System.out.println("User: " + this.user.name + " / Environment: " + this.user.environment);
        } else {
            System.out.println("Não achado");
        }
    }

    public void writeUser(String name, String environment){
        this.service.writeUser(name, environment);
        System.out.println("Usuário escrito");
    }

}
