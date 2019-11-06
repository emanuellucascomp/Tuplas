package br.com.wb.services;

import br.com.wb.tuples.Dispositive;
import br.com.wb.tuples.Lookup;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;

import java.rmi.RemoteException;

public class DispositiveService {

    public Dispositive getDispositiveBy(String name){
        try {
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                System.exit(-1);
            }
            Dispositive template = new Dispositive();
            template.name = name;
            Dispositive returnedDispositive = (Dispositive) space.read(template, null, 60 * 1000);
            return returnedDispositive;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Dispositive getDispositiveByEnvironment(String name, String from){
        try {
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                System.exit(-1);
            }
            Dispositive template = new Dispositive();
            template.name = name;
            template.environment = from;
            Dispositive returnedDispositive = (Dispositive) space.take(template, null, 60 * 1000);
            return returnedDispositive;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeDispositive(String name, String environment) {
        //TODO checar se já existe o dispositivo naquele ambiente
        //TODO checar se o dispositivo já está em algum ambiente
        try {
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                System.exit(-1);
            }
            Dispositive dispositive = new Dispositive();
            dispositive.type = "Dispositive";
            dispositive.name = name;
            dispositive.environment = environment;
            space.write(dispositive, null, 60 * 1000);
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
