package com.example.mealer;

import java.util.List;

public class Cloud {
    public static List<Data> database;
    private static Cloud cloud = new Cloud();
    private Cloud(){

    }

    public Cloud getInstance(){
        return cloud;
    }
    public void add(Data d){
        database.add(d);
    }
    public void get(int id){
        database.get(id);
    }
}
