package br.com.wb.controllers;

import br.com.wb.services.EnvironmentService;
import br.com.wb.tuples.Environment;

import javax.naming.ServiceUnavailableException;
import java.util.List;

public class EnvironmentController {

    private Environment environment;
    private EnvironmentService service;
    private List<Environment> environmentList;

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
}
