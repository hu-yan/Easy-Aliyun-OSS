package com.tigermemo.cloudstorage.controllers;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author : Yan Hu
 * @date : 2019/9/27 19:33
 */
@RestController
public class UploadController {
    private static final String accessKeyId = "";
    private static final String accessKeySecret = "";
    private static final String bucketName = "";
    private static final String endpoint = "";
    
     /**
     *
     * @param fileLocation 上传文件存放的本地路径，其中要包含下载的文件名，如：/Users/kamitora/Downloads/我的文件.doc 。
     * @param objectName 云端存储的文件名（严格区分是否有后缀名，如 我的文件.doc 和 我的文件 是两个不同的文件）。
     * @return 提示字符串
     */
    @PostMapping("/file/upload")
    private String ossFileUpload(String fileLocation, String objectName){
        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            ossClient.putObject(bucketName, objectName, new File(fileLocation));
            ossClient.shutdown();
            return "上传成功";
        }catch (Exception e){
            return "上传失败";
        }
    }
}
