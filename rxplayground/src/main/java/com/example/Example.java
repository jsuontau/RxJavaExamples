package com.example;

/**
 * Created by jsuontaus on 30/08/2017.
 */

public abstract class Example {

    public String getName(){
        return getClass().getSimpleName();
    }

    public abstract void run();

    static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void print(String what){
        System.out.println(what);
    }
}
