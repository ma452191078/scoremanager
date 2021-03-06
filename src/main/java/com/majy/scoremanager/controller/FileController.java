package com.majy.scoremanager.controller;

import com.majy.scoremanager.properties.ScoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by majingyuan on 2017/5/30.
 * 文件上传
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private ScoreProperties properties;
    /**
     * 文件上传具体实现方法;
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Map<String,String> handleFileUpload(@RequestParam("file")MultipartFile file, @RequestParam("gameId") String gameId){
        Map<String,String> param = new HashMap<>();
        String flag = "failed";
        String message = "上传失败，请检查图片大小或稍后重试。";
        if(!file.isEmpty()){
            //文件存储路径
            String filePath = properties.getImgPath() + properties.getImgDictory() + gameId+"/";

            // 获取文件的后缀名
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            fileName = UUID.randomUUID() + suffixName;

            File dest = new File(filePath + fileName);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                flag = "success";
                message = "上传成功";
                param.put("filePath","/" + properties.getImgDictory()+gameId+"/"+fileName);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            message = "上传失败，因为文件是空的.";
        }
        param.put("flag",flag);
        param.put("message",message);
        return param;
    }
}
