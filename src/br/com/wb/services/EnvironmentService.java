package br.com.wb.services;

import br.com.wb.tuples.Environment;
import br.com.wb.tuples.Lookup;
import net.jini.core.entry.Entry;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;

import javax.naming.ServiceUnavailableException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

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

    public List<Environment> listAllEnvironments() throws ServiceUnavailableException {
        List<Environment> list = new LinkedList<Environment>();

        Environment env = null;
        Environment template = new Environment();
        do{
            env = (Environment) this.take(template);
            if(env != null) list.add(env);
        } while (env != null);

        return list;
    }

    public Entry take(Entry template) throws ServiceUnavailableException {
        Entry entry = null;
        Lookup finder = new Lookup(JavaSpace.class);
        JavaSpace space = (JavaSpace) finder.getService();
        try {
            entry = space.takeIfExists(template, null, 60*1000);
        } catch (RemoteException|
                TransactionException|
                InterruptedException|
                UnusableEntryException e) {
            e.printStackTrace();
            throw new ServiceUnavailableException(e.getMessage());
        }
        return entry;
    }
}
