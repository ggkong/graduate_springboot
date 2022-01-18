package com.kongge.dzuoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kongge.dzuoj.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMapper extends BaseMapper<Book> {

}
