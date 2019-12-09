package com.yang.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: TODO
 * @Author: tona.sun
 * @Date: 2019/12/05 21:28
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap();
        /*
        以put为入口进行了解,第一次put会初始化table
        1.if (tab == null || (n = tab.length) == 0) tab = initTable();

        private final Node<K,V>[] initTable() {
            Node<K,V>[] tab; int sc;
            while ((tab = table) == null || tab.length == 0) {
                if ((sc = sizeCtl) < 0)//如果其他线程已经把sizeCtl置为-1，则此处当前现场会让出执行权
                    Thread.yield(); // lost initialization race; just spin
                else if (U.compareAndSetInt(this, SIZECTL, sc, -1)) { //用cas方式比较sizeCtl是否为0，为零则置为-1，表示在初始化，保证线程安全
                    try {
                        if ((tab = table) == null || tab.length == 0) {
                            int n = (sc > 0) ? sc : DEFAULT_CAPACITY;
                            @SuppressWarnings("unchecked")
                            Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n];
                            table = tab = nt;
                            sc = n - (n >>> 2);
                        }
                    } finally {
                        sizeCtl = sc;
                    }
                    break;
                }
            }
            return tab;
        }

        2.
        //f = tabAt(tab, i = (n - 1) & hash)获取tab第(n - 1) & hash下标（即元素的存放位置）的元素值，此hash是经过(h ^ (h >>> 16))& HASH_BITS处理的，
        else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
            //此处使用Unsafe直接操作内存将new Node<K,V>(hash, key, value)放到当前位置，即放到table中
            if (casTabAt(tab, i, null, new Node<K,V>(hash, key, value)))
            break;                   // no lock when adding to empty bin
         }
         3.
         // 表示正在扩容元素转移，当前线程去协助转移数据
         else if ((fh = f.hash) == MOVED)
                tab = helpTransfer(tab, f);


          4
          else {
                V oldVal = null;
                //此处f即为第2步的f = tabAt(tab, i = (n - 1) & hash)即链表的头（或者红黑树），实现了根据下标（(n - 1) & hash）锁一个链表（或者红黑树），分段加锁，提高效率
                synchronized (f) {}
           }

          5
          //判断是否需要扩容，转移数据
          addCount(1L, binCount);->transfer
         */
        map.put("a", "a");
    }
}
