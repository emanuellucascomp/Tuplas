package br.com.wb.controllers;

import br.com.wb.services.EnvironmentService;
import br.com.wb.tuples.Environment;

public class EnvironmentController {

    private Environment environment;
    private EnvironmentService service;

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
        System.out.println("Dispositivo escrito");
    }
}
