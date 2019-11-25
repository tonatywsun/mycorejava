package com.yang.core.list;

import java.util.Arrays;

public class MyArrayList<E> {
    private Object[] elementData;
    private int size;
    private static int DEFAULT_CAPACITY = 10;
    private static Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    public MyArrayList() {
        elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**  
    * @Description: 确认内部容量
    * @param @param minCapacity 最小需要的容量
    * @return void
    * @throws  
    */  
    private void ensureCapacityInternal(int minCapacity) {
        ensureCapacity(calculateCapacity(elementData, minCapacity));
    }

    private void ensureCapacity(int minCapacity) {
        if (elementData.length < minCapacity) {
            grow(minCapacity);
        }
    }

    /**  
    * @Description: 数组扩容
    * @param @param minCapacity 最小需要的容量
    * @return void
    * @throws  
    */  
    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (minCapacity > newCapacity) {
            newCapacity = minCapacity;
        }
        if (newCapacity > Integer.MAX_VALUE) {
            newCapacity = Integer.MAX_VALUE;
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    public boolean add(E e) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
        return true;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E)elementData[index];
    }
    
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        E e =  (E)elementData[index];
        int i = size-index-1;
        if(i>0) {
            System.arraycopy(elementData, index+1, elementData, index,i);
        }
        elementData[size--]=null;
        return e;
    }
    
    public boolean remove(Object e) {
        if(e==null) {
            for(int i=0;i<size;i++) {
                if(e==elementData[i]) {
                    remove(i);
                    return true;
                }
            }
        }else {
            for(int i=0;i<size;i++) {
                if(e.equals(elementData[i])) {
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    
    private void checkIndex(int index) {
        if(index<0||index>=size) {
            throw new RuntimeException("数组越界");
        }
    }
    
    public int size() {
        return size;
    }
}
