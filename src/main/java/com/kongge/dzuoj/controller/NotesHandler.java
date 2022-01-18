package com.kongge.dzuoj.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kongge.dzuoj.demo.Result;
import com.kongge.dzuoj.entity.Notes;
import com.kongge.dzuoj.mapper.NotesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;


@RestController
@RequestMapping("/notes")
public class NotesHandler {
    @Autowired
    private NotesMapper notesMapper;


    @GetMapping("/get_notes")
    public Result<?> getNotes(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam String author){
        QueryWrapper<Notes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author",author)
                .like("content",search);
        Page<Notes> notesPage = notesMapper.selectPage(new Page<>(pageNum,pageSize),queryWrapper);
        return Result.success(notesPage);
    }

    @PostMapping("/add_notes")
    public Result<?> addNotes(@RequestBody Notes notes){
        Integer flag = notesMapper.insert(notes);
        if(flag == 1){
            return Result.success(notes.getTitle());
        }else {
            return Result.error("-1","插入失败");
        }
    }

    @GetMapping("/get_notes_id")
    public Result<?> getNotesById(@RequestParam String id){
        System.out.println(id);
        Notes notes = notesMapper.selectById(id);
        if(notes != null){
            return Result.success(notes);
        }else {
            return Result.error("-1","查询失败");
        }
    }
    @GetMapping("/delete_notes")
    public Result<?> deleteNotesById(@RequestParam String id){
        Integer flag = notesMapper.deleteById(id);
        if(flag == 1){
            return Result.success();
        }else {
            return Result.error("-1","删除失败");
        }
    }



    @PostMapping("/update_notes")
    public Result<?> updateNotesById(@RequestBody Notes postNotes){
        System.out.println(postNotes);
        // 使用乐观锁必须的先查出来
        Notes notes = notesMapper.selectById(postNotes.getId());
        notes.setTitle(postNotes.getTitle());
        notes.setContent(postNotes.getContent());
        Integer flag = notesMapper.updateById(notes);
        if(flag == 1){
            return Result.success(notes.getTitle());
        }else {
            return Result.error("-1","更新错误");
        }
    }
}
