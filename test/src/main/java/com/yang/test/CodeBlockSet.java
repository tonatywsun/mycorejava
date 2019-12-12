package com.yang.test;

/**
 * @Description: TODO
 * @Author: tona.sun
 * @Date: 2019/12/12 16:32
 */
public class CodeBlockSet {
    {
        this.setStr("111");
    }

    public CodeBlockSet(String str) {
        this.str = str;
    }

    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public static void main(String[] args) {
        System.out.println("new -----" + new CodeBlockSet("222").getStr());
        System.out.println("{{}}-----" + new CodeBlockSet("222") {{
            setStr("333");
        }}.getStr());
    }
}
