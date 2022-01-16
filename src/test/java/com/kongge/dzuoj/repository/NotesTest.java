package com.kongge.dzuoj.repository;

import com.kongge.dzuoj.entity.Notes;
import com.kongge.dzuoj.mapper.NotesMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NotesTest {
    @Autowired
    private NotesMapper notesMapper;

    @Test
    public void save(){

        for (int i = 0; i < 300; i++) {
            Notes notes = new Notes();
            notes.setAuthor("admin");
            notes.setContent("管理员hello world" + i);
            notes.setTitle("adminTest" + i);
            notesMapper.insert(notes);
        }

    }
}
