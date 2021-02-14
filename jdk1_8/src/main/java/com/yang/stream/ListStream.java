package com.yang.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
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

    public List<Long> flatMapToLong(Long tagId) {
        List<String> blacklistList = Lists.newArrayList("1,2,3");
        //判断这个tagID是不是 null
        List<Long> tagIdList = Optional.ofNullable(tagId)
                                       //直接装到list中去
                                       .map(Lists::newArrayList)
                                       //据说parallelStream比stream更快
                                       .orElse(blacklistList.parallelStream()
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
        System.out.println(tagIdList);
        return tagIdList;
    }

    @Test
    public  void anyMatchTest() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        System.out.println("匹配第一个值：" + findFirst.get());
        System.out.println("匹配任意一个值：" + findAny.get());
        System.out.println("是否存在大于6的值：" + anyMatch);
    }

    @Test
    public  void partitioningByTest() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        //将list按x > 6条件分组
        Map<Boolean, List<Integer>> part = list.stream().collect(Collectors.partitioningBy(x -> x > 6));
        System.out.println(part);
    }
}
