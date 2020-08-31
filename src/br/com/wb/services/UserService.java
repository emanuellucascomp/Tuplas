package br.com.wb.services;

import br.com.wb.tuples.Lookup;
import br.com.wb.tuples.User;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;

import java.rmi.RemoteException;

public class UserService {

    public User getUserBy(String user) {
        try {
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                System.exit(-1);
            }
            User template = new User();
            template.name = user;
            User returnedUser = (User) space.read(template, null, 60 * 1000);
            return returnedUser;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByRoom(String name, String amb) {
        try {
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                System.exit(-1);
            }
            User template = new User();
            template.chatRoom = amb;
            template.name = name;
            User returnedUser = (User) space.take(template, null, 60 * 1000);
            return returnedUser;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeUser(String name, String chatRoom) {
        //TODO checar se já existe o usuário naquele ambiente
        //TODO checar se o usuário já está em algum ambiente
        try {
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                System.exit(-1);
            }
            User user = new User();
            user.type = "User";
            user.name = name;
            user.chatRoom = chatRoom;
            space.write(user, null, 60 * 1000);
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
