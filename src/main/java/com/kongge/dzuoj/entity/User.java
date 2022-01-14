package com.kongge.dzuoj.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    @TableId(type=IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String password;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @Version
    private Integer version;
    @TableLogic
    private Integer deleted;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
