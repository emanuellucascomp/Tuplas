package br.com.wb.services;

import br.com.wb.tuples.Lookup;
import br.com.wb.tuples.Message;
import br.com.wb.tuples.User;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class UserService {

    public User getUserBy(String user) {
        try {
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                System.exit(-1);
            }
            User template = new User();
            User returnedUser = (User) space.read(template, null, 60 * 1000);
            return returnedUser;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeUser(String name) {
        try {
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                System.exit(-1);
            }
            User user = new User();
            user.name = name;
            space.write(user, null, 60 * 1000);
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
