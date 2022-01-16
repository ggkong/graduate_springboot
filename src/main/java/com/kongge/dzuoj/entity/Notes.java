package com.kongge.dzuoj.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("notes")
public class Notes {
    @TableId(type= IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String author;
    private String content;
    private String title;
    @Version
    private Integer version;
    @TableLogic
    private Integer deleted;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public Notes(String author, String content, String title) {
        this.author = author;
        this.content = content;
        this.title = title;
    }

    public Notes(Long id, String content, String title) {
        this.id = id;
        this.content = content;
        this.title = title;
    }
}
