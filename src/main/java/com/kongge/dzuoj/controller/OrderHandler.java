package com.kongge.dzuoj.controller;

import cn.hutool.core.util.IdUtil;
import com.kongge.dzuoj.demo.Result;
import com.kongge.dzuoj.entity.Book;
import com.kongge.dzuoj.entity.Order;
import com.kongge.dzuoj.entity.User;
import com.kongge.dzuoj.mapper.BookMapper;
import com.kongge.dzuoj.mapper.OrderMapper;
import com.kongge.dzuoj.mapper.UserMapper;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/order")
public class OrderHandler{

    @Autowired
    private OrderMapper OrderMapper;

    @Autowired
    private BookMapper BookMapper;

    @Autowired
    private UserMapper userMapper;

    @PutMapping
    public Result<?> update(@RequestBody Order Order) {
        OrderMapper.updateById(Order);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        OrderMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(OrderMapper.selectById(id));
    }

    @GetMapping("/buy")
    public Result<?> buy(@RequestParam(required = false) String bookId, @RequestParam(required = false) String userId) {
        System.out.println("---------------->生成连接");
        System.out.println(bookId);
        System.out.println(userId);
        Book book = BookMapper.selectById(bookId);
        String orderNo = IdUtil.getSnowflake().nextIdStr();
        String payUrl = "http://localhost:8181/alipay/pay?subject=" + "hello_world" + "&traceNo=" + orderNo + "&totalAmount=" + 10;
        // 获取用户
        User user = userMapper.selectById(userId);
        Order order = new Order();

        order.setName("test测试数据");
        order.setTotalPrice(new BigDecimal(10.00));
        order.setPayPrice(new BigDecimal(5.0));
        order.setTransportPrice(new BigDecimal(5.0));
        order.setUserId(user.getId());
        order.setState(0);
        order.setOrderNo(orderNo);
        OrderMapper.insert(order);
        // 新建订单，扣减库存
        return Result.success(payUrl);
    }

//    @GetMapping
//    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
//                              @RequestParam(defaultValue = "10") Integer pageSize,
//                              @RequestParam(defaultValue = "") String search) {
//        LambdaQueryWrapper<Order> wrapper = Wrappers.lambdaQuery();
//        if (StrUtil.isNotBlank(search)) {
//            wrapper.like(Order::getOrderNo, search);
//        }
//        Page<Order> OrderPage = OrderMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
//        return Result.success(OrderPage);
//    }
}