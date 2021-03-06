package com.javarush.task.pro.task14.task1415;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* 
Стек в домашних условиях
*/

public class MyStack {

    private final List<String> storage = new ArrayList<>();

    public void push(String s) {
        storage.add(0, s);
    }

    public String pop() {
        String string = storage.get(0);
        storage.remove(0);
        return string;
    }

    public String peek() {
        return storage.get(0);
    }

    public boolean empty() {
        return storage.isEmpty();
    }

    public int search(String s) {
        int index = -1;
        for (int i = 0; i <storage.size() ; i++) {
            if(storage.get(i).equals(s)){
                 index = i;
            }
        }
        return index ;
    }
}
