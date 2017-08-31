package com.example;

import java.util.HashMap;

public class PlayGround {

    private static final HashMap<Integer, Example> EXAMPLES = new HashMap<>();
    static {
        EXAMPLES.put(1, new Example1());
        EXAMPLES.put(2, new Example2());
        EXAMPLES.put(3, new Example3());
    }

    private static final int CURRENT_EXAMPLE = 3;

    public static void main(String[] args){
        Example example = EXAMPLES.get(CURRENT_EXAMPLE);
        System.out.println("- Run " + example.getName() + "-");
        example.run();
        sleep(3000);
        System.out.println("- End -");
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
