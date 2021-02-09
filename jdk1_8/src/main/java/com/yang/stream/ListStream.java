package com.yang.stream;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * java 8 Stream各种使用场景
 *
 * @version V1.0
 * @className: ListStream
 * @date 2020/11/28 15:13
 */
public class ListStream {
    public static void main(String[] args) {
        List list = Lists.newArrayList();
        //collcetion中方法
        Stream stream = list.stream().distinct();
        System.out.println(stream);
    }

    public void flatMapToLong(Long tagId) {
        List<String> blacklistList = Lists.newArrayList("1,2,3");
        //判断这个tagID是不是 null
        List<Long> longs = Optional.ofNullable(tagId)
                                   //直接装到list中去
                                   .map(Lists::newArrayList)
                                   .orElse(blacklistList.stream()
                                                        //先切开
                                                        .map(u -> Arrays.asList(u.trim().split(",")))
                                                        //转成long
                                                        .flatMapToLong(x -> x.stream().mapToLong(Long::valueOf))
                                                        //去重
                                                        .distinct()
                                                        //装到list中去
                                                        .collect(Lists::newArrayList,
                                                                 ArrayList::add,
                                                                 List::addAll));

    }
}
