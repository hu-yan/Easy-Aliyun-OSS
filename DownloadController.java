package com.tigermemo.cloudstorage.controllers;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author : Yan Hu
 * @date : 2019/9/28 20:01
 */
@RestController
public class DownloadController {
    private static final String accessKeyId = "";
    private static final String accessKeySecret = "";
    private static final String bucketName = "";
    private static final String endpoint = "";

    /**
     *
     * @param downloadPath 下载文件存放的路径，其中要包含下载的文件名（可自定义）如：/Users/kamitora/Downloads/我的文件.doc 。
     * @param objectName 云端存储的文件名（严格区分是否有后缀名，如 我的文件.doc 和 我的文件 是两个不同的文件）。
     * @return
     */
    @PostMapping("/file/download")
    public String ossFileDownload(String downloadPath, String objectName){
        try {

            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(downloadPath));
            ossClient.shutdown();
            return "下载成功";
        }catch (Exception e){
            return "下载失败";
        }
    }
}
