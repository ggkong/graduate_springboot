package com.kongge.dzuoj.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kongge.dzuoj.demo.Result;
import com.kongge.dzuoj.entity.User;
import com.kongge.dzuoj.mapper.UserMapper;
import net.sf.jsqlparser.statement.ResetStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername())
                .eq("password",user.getPassword());
        User res = userMapper.selectOne(queryWrapper);
        if(res == null){
            return Result.error("-1","用户名或者密码错误");
        }

        return Result.success();
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        // 看是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        if(userMapper.selectOne(queryWrapper) == null ){
            //成功
            userMapper.insert(user);
            System.out.println(user);
            return Result.success(user.getId());
        }
        return Result.error("-1","用户名已经存在");
    }
}
