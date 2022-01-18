package com.kongge.dzuoj.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kongge.dzuoj.entity.User;
import com.kongge.dzuoj.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class UserTest {

    // 欺骗spring
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect(){
        List<User> userList = userMapper.selectList(null);
        for (User user:
             userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert(){
        User addUSer = new User();
        addUSer.setUsername("kongge12456");
        addUSer.setPassword("123456");
        int result = userMapper.insert(addUSer);
        System.out.println(result);
        System.out.println(addUSer);
    }

    @Test
    public void update(){
        // 乐观锁必须得先查询才能起作用
        User user = userMapper.selectById(1481843239567757313L);
        user.setUsername("konggge");
        user.setId(1481843239567757313L);
        userMapper.updateById(user);
    }

    @Test
    public void select(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1481843239567757313L,1481836769782538242L));
        users.forEach(System.out::println);
    }

    @Test
    public void testPage(){
        // 第一页 每页五个
        Page<User> pages = new Page<>(1,5);
        userMapper.selectPage(pages,null);
        pages.getRecords().forEach(System.out::println);
    }

    @Test
    public void delete(){
        userMapper.deleteById(1481846885470740481L);
    }

    @Test
    public void manyLoads(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username","kongge")
                .eq("password","123456");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }




}
