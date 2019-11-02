package br.com.wb.tuples;

import net.jini.core.entry.Entry;

import java.util.List;

public class Environment implements Entry {

    public String name;
    public String type;
    public List<User> users;
    public List<Dispositive> dispositives;

    public Environment(){ }
}
