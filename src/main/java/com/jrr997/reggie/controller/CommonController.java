package com.jrr997.reggie.controller;

import com.jrr997.reggie.common.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${reggie.fileLocation}")
    private String basePath;
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        File dir = new File(basePath);
        if (!dir.exists()){
            dir.mkdirs();
        }
        try {
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID() + suffix;
            file.transferTo(new File(basePath + fileName));
            return Result.success(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("上传失败");
        }

    }

    @GetMapping("/download")
    public Result<String> download(String name, HttpServletResponse response){
        File file = new File(basePath + name);
        if (!file.exists()){
            return Result.error("文件不存在");
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + name);
        byte[] buffer = new byte[1024];
        try {
            java.io.FileInputStream fis = new java.io.FileInputStream(file);
            java.io.BufferedInputStream bis = new java.io.BufferedInputStream(fis);
            java.io.OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            bis.close();
            fis.close();
            return Result.success("下载成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("下载失败");
        }
    }
}
