package com.tigermemo.cloudstorage.controllers;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author : Yan Hu
 * @date : 2019/10/11 00:13
 */

@RestController
public class CreateUserFolderById {
    private static final String accessKeyId = "";
    private static final String accessKeySecret = "";
    private static final String bucketName = "";
    private static final String endpoint = "";
    private static final boolean CreatedSuccessfully = true;
    private static final boolean FolderCreationError = false;

    /**
     * @param keySuffixWithSlash 文件夹名称，随后创建前添加 / 符号
     * @param userId 用户的ID
     * @return OSS 创建一个名称为用户ID的文件夹。
     * @throws IOException 字符串拼接
     */
    @PostMapping("/user/trial/initialization")
    private Boolean createUserSpace(int userId) throws IOException {
        OSS client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            String keySuffixWithSlash = userId + "/";
            client.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            OSSObject object = client.getObject(bucketName, keySuffixWithSlash);
            object.getObjectContent().close();
            return CreatedSuccessfully;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            client.shutdown();
        }
        return FolderCreationError;
    }




}
