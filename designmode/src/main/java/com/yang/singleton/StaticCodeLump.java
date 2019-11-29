package com.yang.singleton;

/**
 * @author yangyang
 * @date 2019/8/24 14:46
 */
public class StaticCodeLump {

    private static class Singleton {
        public static final StaticCodeLump singleton = new StaticCodeLump();
    }

    private StaticCodeLump() {
    }

    public static StaticCodeLump getSingleton() {
        return Singleton.singleton;
    }

    public void doSomething() {
        System.out.println("i will do");
    }
}
