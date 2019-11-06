package br.com.wb.services;

import br.com.wb.tuples.Dispositive;
import br.com.wb.tuples.Environment;
import br.com.wb.tuples.Lookup;
import br.com.wb.tuples.User;
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
        List<Environment> list = new LinkedList<>();

        Environment env = null;
        Environment template = new Environment();
        do{
            env = (Environment) this.take(template);
            if(env != null) list.add(env);
        } while (env != null);

        return list;
    }

    public List<Dispositive> listDispositiveByEnvironment(String dispositive) throws ServiceUnavailableException  {
        List<Dispositive> list = new LinkedList<>();

        Dispositive env = null;
        Dispositive template = new Dispositive();
        template.name = dispositive;
        do{
            env = (Dispositive) this.take(template);
            if(env != null) list.add(env);
        } while (env != null);

        return list;
    }

    public List<User> listUserByEnvironment(String usr) throws ServiceUnavailableException  {
        List<User> list = new LinkedList<>();

        User env = null;
        User template = new User();
        template.name = usr;
        do{
            env = (User) this.take(template);
            if(env != null) list.add(env);
        } while (env != null);

        return list;
    }

    public void moveUser(String name, String from, String to){
        UserService service = new UserService();
        User user = service.getUserByEnvironment(name, from);
        if(user != null){
            service.writeUser(user.name, to);
            System.out.println("Usuário movido");
        }
    }

    public void moveDispositive(String name, String from, String to){
        DispositiveService service = new DispositiveService();
        Dispositive dispositive = service.getDispositiveByEnvironment(name, from);
        if(dispositive != null){
            service.writeDispositive(dispositive.name, to);
            System.out.println("Dispositivo movido");
        }
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
