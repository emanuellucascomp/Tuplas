package br.com.wb.services;

import br.com.wb.tuples.ChatRoom;
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

public class ChatRoomService {

    public ChatRoom getRoomBy(String name) {
        try {
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                System.exit(-1);
            }
            ChatRoom template = new ChatRoom();
            template.name = name;
            ChatRoom returnedChatRoom = (ChatRoom) space.read(template, null, 60 * 1000);
            return returnedChatRoom;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeRoom(String name) {
        try {
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                System.exit(-1);
            }
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.type = "ChatRoom";
            chatRoom.name = name;
            space.write(chatRoom, null, 60 * 1000);
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public List<ChatRoom> listAllRooms() throws ServiceUnavailableException {
        List<ChatRoom> list = new LinkedList<>();

        ChatRoom env = null;
        ChatRoom template = new ChatRoom();
        do{
            env = (ChatRoom) this.take(template);
            if(env != null) list.add(env);
        } while (env != null);

        return list;
    }

    public List<User> listUsersByRoom(String chatRoom) throws ServiceUnavailableException  {
        List<User> list = new LinkedList<>();

        User env = null;
        User template = new User();
        template.chatRoom = chatRoom;
        do{
            env = (User) this.take(template);
            if(env != null) list.add(env);
        } while (env != null);

        return list;
    }

    public void moveUser(String name, String to){
        UserService service = new UserService();
        User user = service.getUserBy(name);
        if(user != null){
            service.writeUser(user.name, to);
            System.out.println("Usuário movido");
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
