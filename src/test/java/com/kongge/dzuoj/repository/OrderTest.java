package com.kongge.dzuoj.repository;

import com.kongge.dzuoj.entity.Order;
import com.kongge.dzuoj.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderTest {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertTest(){
        Order order = new Order();
        orderMapper.insert(order);
    }
}
