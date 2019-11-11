package br.com.wb.controllers;

import br.com.wb.services.EnvironmentService;
import br.com.wb.tuples.Dispositive;
import br.com.wb.tuples.Environment;
import br.com.wb.tuples.User;

import javax.naming.ServiceUnavailableException;
import java.util.List;

public class EnvironmentController {

    //TODO destruir ambientes vazios

    private Environment environment;
    private EnvironmentService service;
    private List<Environment> environmentList;
    private List<Dispositive> dispositiveList;
    private List<User> userList;

    public EnvironmentController(){
        this.environment = new Environment();
        this.service = new EnvironmentService();
    }

    public void retrieveEnvironment(String name){
        this.environment = this.service.getEnvironmentBy(name);
        if(this.environment!= null){
            System.out.println("Ambiente: " + this.environment.name);
        } else {
            System.out.println("Não achado");
        }
    }

    public void writeEnvironment(String name){
        this.service.writeEnvironment(name);
        System.out.println("Ambiente escrito");
    }

    public void listAllEnvironments() throws ServiceUnavailableException {
        this.environmentList = this.service.listAllEnvironments();
        for (Environment environment: this.environmentList) {
            System.out.println("Ambiente: " + environment.name);
        }
        System.out.println("Ambientes retornados");
    }

    public void listDispositiveByEnvironment(String dispositive) throws ServiceUnavailableException {
        this.dispositiveList = this.service.listDispositiveByEnvironment(dispositive);
        for (Dispositive disp: this.dispositiveList) {
            System.out.println("Dispositivo: " + disp.name);
        }
        System.out.println("Dispositivos retornados");
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

    public void moveDispositive(String name, String to){
        this.service.moveDispositive(name, to);
    }

    public void startChat(String userChat) {

    }
}
