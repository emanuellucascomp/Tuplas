package br.com.wb.controllers;

import br.com.wb.services.DispositiveService;
import br.com.wb.tuples.Dispositive;

public class DispositiveController {

    private Dispositive dispositive;
    private DispositiveService service;

    public DispositiveController(){
        this.dispositive = new Dispositive();
        this.service = new DispositiveService();
    }

    public void retrieveDispositive(String name){
        this.dispositive = this.service.getDispositiveBy(name);
        if(this.dispositive!= null){
            System.out.println("Dispositivo: " + this.dispositive.name + " / Ambiente: " + this.dispositive.environment);
        } else {
            System.out.println("Não achado");
        }
    }

    public void writeDispositive(String name, String environment){
        this.service.writeDispositive(name, environment);
        System.out.println("Dispositivo escrito");
    }
}
