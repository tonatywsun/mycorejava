package com.yang.singleton;

public class SingletonTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread() {
                @Override
                public void run() {
                    System.out.println(Lazy.getSingleton().toString());
                    System.out.println(EnumSingleton.INSTANCE == EnumSingleton.INSTANCE);
                }
            }.start();
        }
    }

}
