package com.brave.test.guava;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxinyu
 * @since 2022/6/7 20:14
 */
public class ListsApiTest {
    @Test
    public void testListsNewArrayList() {
        List<List<String>> headList = new ArrayList<List<String>>();
        // 代替创建List对象
        headList.add(Lists.newArrayList("姓名"));
        headList.add(Lists.newArrayList("性别"));
        headList.add(Lists.newArrayList("年龄"));
        headList.add(Lists.newArrayList("月份", "1月"));
        headList.add(Lists.newArrayList("月份", "2月"));
    }
}
