package com.brave.string.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 拼接字符串的方式：
 *
 * 1. +  String是不可变对象，+号就会在内存中创建新的String，但是拼接变量，编译器会优化为StringBuilder
 * 2. concat  形参不能为null
 * 3. StringBuilder
 * 4. StringJoiner                  1.8
 * 5. Stream流的collect : Collectors.joining      1.8  如果不拼接null的元素，需要filter过滤
 * 6. String.join()  数组或者Iterable的子类        1.8
 * 7. commons.lang3.StringUtils.join()      不会拼接为null的元素
 *
 * @author zhangxinyu
 * @since 2022/6/7 16:07
 */
public class StringSplicingTest {
    private String[] values = {"I ", "want to ", "splicing ", "some ", "characters ", null};
    private List<String> values2 = Arrays.asList("I ", "want to ", "splicing ", "some characters ", null, "wow");

    @Test
    public void demo1() {
        String result = "";
        // 方式一
        for (String value : values) {
            result += nullToString(value);
        }
        System.out.println(result);
    }

    @Test
    public void stringJoinerApiTest() {
        // 可以指定连接符、前缀、后缀
        StringJoiner stringJoiner = new StringJoiner("");
//        StringJoiner stringJoiner = new StringJoiner(",", "start ", "end");
        for (String value : values) {
            stringJoiner.add(nullToString(value));
        }
        System.out.println(stringJoiner);

    }

    @Test
    public void collectorsJoiningApiTest() {
        // Stream API 中的方法使用了StringJoiner
        String result = values2.stream().filter(Objects::nonNull).collect(Collectors.joining());
        System.out.println(result);
    }

    @Test
    public void stringJoinApiTest() {
        // String添加分隔符的API,内部逻辑也使用了StringJoiner
        String join = String.join(",", values2);
        System.out.println(join);
    }

    @Test
    public void commonsLang3StringUtilsTest() {
        // 对于null元素不会拼接，底层是使用StringBuilder
        String join = StringUtils.join(values, ",");
        System.out.println(join);
        // 拼接，使用separator, 索引从startIndex -> endIndex
        String result = StringUtils.join(values2, '1', 2, 5);
        System.out.println(result);

    }

    public String nullToString(String value) {
        return value == null ? "" : value;
    }
}
