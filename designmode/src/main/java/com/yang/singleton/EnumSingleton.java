package com.yang.singleton;

/**
 * 使用枚举线程安全，序列化和反射无法破解
 * 枚举类都继承Enum,此类中clone方法直接抛出异常，所以clone也无法破解
 * 反射newInstance时也无法创建枚举对象
 *
 * @author tonasun
 * @Description: 使用枚举实现单例模式
 * @ClassName: SingletonEnum
 * @date 2018年6月19日
 */
public enum EnumSingleton {
    INSTANCE;

    public void doSomething() {
        System.out.println("i will do");
    }

}
