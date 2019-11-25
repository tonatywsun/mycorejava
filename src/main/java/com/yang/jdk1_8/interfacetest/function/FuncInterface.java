package com.yang.jdk1_8.interfacetest.function;

/**
 * 函数接口
 *
 * @author yangyang
 * @date 2019/8/21 14:21
 */
@FunctionalInterface
public interface FuncInterface {
    /**
     * 函数接口只能有唯一一个抽象方法
     *
     * @return void
     * @author yangyang
     * @date 2019/8/21 14:24
     */
    public String fun(String s);

    @Override
    public boolean equals(Object obj);

    @Override
    public String toString();
}
