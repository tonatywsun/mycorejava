package com.yang.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 会发生内存泄漏问题  暂时没有解决
 * 
 * @date 2018年4月28日 下午2:45:36
 * @author tonasun
 */
public class DateFormatUtil {

    /** 存放不同的日期模板格式的sdf的Map */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     * 
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);
        // System.out.println("tl=" + tl);
        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            // System.out.println("1" + Thread.currentThread());
            synchronized (DateFormatUtil.class) {
                // System.out.println("2" + Thread.currentThread());
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    // System.out.println("3" + Thread.currentThread());
                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
                    // System.out.println("put new sdf of pattern " + pattern + " to map");

                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    tl = new ThreadLocal<SimpleDateFormat>() {

                        @Override
                        protected SimpleDateFormat initialValue() {
                            System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                            // System.out.println("simpleDateFormat:" + simpleDateFormat);
                            return simpleDateFormat;
                        }
                    };
                    // tl.set(new SimpleDateFormat(pattern));
                    sdfMap.put(pattern, tl);
                }
            }
        }
        SimpleDateFormat simpleDateFormat = tl.get();
        tl.remove();
        return simpleDateFormat;
    }

    /**
     * 是用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
     * 多个线程之间不公用不会发生异常
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return getSdf(pattern).format(date);
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat sdf = getSdf(pattern);
        /*
         * SimpleDateFormat sdf2 = getSdf(pattern); System.out.println(sdf ==sdf2);
         * 同一个线程获得到的SimpleDateFormat是同一个对象
         */
        return sdf.parse(dateStr);
    }
}
