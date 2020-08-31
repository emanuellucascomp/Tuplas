package br.com.wb.services;

import br.com.wb.tuples.ChatRoom;
import br.com.wb.tuples.Lookup;
import br.com.wb.tuples.Message;
import br.com.wb.tuples.User;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;

import java.rmi.RemoteException;

public class MessageService {

    private UserService userService;
    private ChatRoomService chatRoomService;

    public MessageService(){
        this.userService = new UserService();
        this.chatRoomService = new ChatRoomService();
    }

    public void writeDirect(String from, String to, String content) {
        try{
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                System.exit(-1);
            }
            User userFrom = userService.getUserBy(from);
            User userTo = userService.getUserBy(to);
            Message message = new Message();
            message.fromUser = userFrom;
            message.toUser = userTo;
            message.content = content;
            space.write(message, null, 60 * 1000);
        } catch (TransactionException e){
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void writeToChannel(String from, String content) {
        try{
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                System.exit(-1);
            }
            User userFrom = userService.getUserBy(from);
            ChatRoom chatRoom = chatRoomService.getRoomBy(userFrom.chatRoom);
            Message message = new Message();
            message.fromUser = userFrom;
            message.room = chatRoom;
            message.content = content;
            space.write(message, null, 60 * 1000);
        } catch (TransactionException e){
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void readDirect(String from, String to) {
        try {
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                System.exit(-1);
            }
            User userFrom = userService.getUserBy(from);
            User userTo = userService.getUserBy(to);
            Message template = new Message();
            template.fromUser = userFrom;
            template.toUser = userTo;
            Message returnedMessage = (Message) space.read(template, null, 60 * 1000);
            System.out.println("Mensagem: " + returnedMessage.content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readChannel(String from) {
        try {
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                System.exit(-1);
            }
            User userFrom = userService.getUserBy(from);
            ChatRoom chatRoom = chatRoomService.getRoomBy(userFrom.chatRoom);
            Message template = new Message();
            template.fromUser = userFrom;
            template.room = chatRoom;
            Message returnedMessage = (Message) space.read(template, null, 60 * 1000);
            System.out.println("Mensagem: " + returnedMessage.content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
