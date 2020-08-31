package br.com.wb.tuples;

import net.jini.core.entry.Entry;

public class Message implements Entry{

    public User fromUser;
    public User toUser;
    public ChatRoom room;
    public String content;

    public Message() {   }
}