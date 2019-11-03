package br.com.wb.services;

import br.com.wb.tuples.Environment;
import br.com.wb.tuples.Lookup;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;

import java.rmi.RemoteException;

public class EnvironmentService {

    public Environment getEnvironmentBy(String name) {
        try {
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                System.exit(-1);
            }
            Environment template = new Environment();
            template.name = name;
            Environment returnedEnvironment = (Environment) space.read(template, null, 60 * 1000);
            return returnedEnvironment;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeEnvironment(String name) {
        try {
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                System.exit(-1);
            }
            Environment environment = new Environment();
            environment.type = "Environment";
            environment.name = name;
            space.write(environment, null, 60 * 1000);
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
