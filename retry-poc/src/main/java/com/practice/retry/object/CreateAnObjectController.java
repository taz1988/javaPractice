package com.practice.retry.object;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/object/")
public class CreateAnObjectController {

    private final Gson GSON = new Gson();

    @RequestMapping("/create/{region}/{bucketName}/{objectName}/{content}")
    public String createBucket(@PathVariable("region") String region, @PathVariable("bucketName") String bucketName, @PathVariable("objectName") String objectName, @PathVariable("content") String content) {
        AmazonS3ClientBuilder clientBuilder = AmazonS3ClientBuilder.standard();
        clientBuilder.setRegion(region);
        AmazonS3 amazonS3 = clientBuilder.build();
        PutObjectResult putObjectResult = amazonS3.putObject(bucketName, objectName, content);
        return GSON.toJson(putObjectResult);
    }

    /*@RequestMapping("/create/{region}/{bucketName}/{objectName}/")
    public String createBucket(@PathVariable("region") String region, @PathVariable("bucketName") String bucketName, @PathVariable("objectName") String objectName, List<String> contents) {
        AmazonS3ClientBuilder clientBuilder = AmazonS3ClientBuilder.standard();
        clientBuilder.setRegion(region);
        AmazonS3 amazonS3 = clientBuilder.build();
        PutObjectResult putObjectResult = amazonS3.putObject(bucketName, objectName, content);
        return GSON.toJson(putObjectResult);
    }*/
}
