package com.kongge.dzuoj.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.kongge.dzuoj.demo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


@RestController
@RequestMapping("/files")
public class FilesHandler {
    @Value("${server.port}")
    private String port;

    private static final String ip = "http://localhost";


    @PostMapping("/upload")
    public String upload(@RequestParam(value = "uploadFile",required = false) MultipartFile uploadFile) throws IOException {
        String originalFilename = uploadFile.getOriginalFilename();
        System.out.println(originalFilename);
        // 定义文件的唯一表示类
        String id = IdUtil.randomUUID();
        String rootFilePath = System.getProperty("user.dir")+"/src/main/resources/files/"+ id + "_" + originalFilename;
        System.out.println(rootFilePath);
        FileUtil.writeBytes(uploadFile.getBytes(),rootFilePath);
        return id;
    }

    @GetMapping("/download/{flag}")
    public void getFiles(@PathVariable("flag") String flag ,HttpServletResponse response) throws IOException {
        // 输出流对象
        OutputStream os; //创建输出流对象
        String basePath = System.getProperty("user.dir")+"/src/main/resources/files/";
        List<String> fileNames = FileUtil.listFileNames(basePath);
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        if(StrUtil.isNotEmpty(fileName)){
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(fileName,"UTF-8"));
            byte[] bytes = FileUtil.readBytes(basePath+fileName);
            os = response.getOutputStream();
            os.write(bytes);
            os.flush();
            os.close();
        }
    }
}
