package com.ivo.test.modules.system;

import com.ivo.AbstractTest;
import com.ivo.modules.system.domain.Menu;
import com.ivo.modules.system.repository.MenuRepository;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @Author: wj
 * @Date: 2019-06-11 00:38
 * @Version 1.0
 */
public class MenuTest extends AbstractTest {

    @Autowired
    private MenuRepository repository;

    @Test
    public void test() {
        Menu menu = new Menu();


        // 创建匹配器，进行动态查询匹配
        ExampleMatcher matcher = ExampleMatcher.matching().
                withMatcher("title", match -> match.contains());

        // 获取菜单列表
        Example<Menu> example = Example.of(menu, matcher);
        Sort sort = new Sort(Sort.Direction.ASC, "sort");


        List list = repository.findAll(example, sort);
        Assert.notEmpty(list);

    }
}
