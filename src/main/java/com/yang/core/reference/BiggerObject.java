package com.yang.core.reference;

//一个大对象
public class BiggerObject {
    public int[] values;
    public String name;

    public BiggerObject(String name) {
        this.name = name;
        values = new int[1024];
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
