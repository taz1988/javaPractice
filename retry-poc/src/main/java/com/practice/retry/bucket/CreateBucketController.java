package com.practice.retry.bucket;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bucket/")
public class CreateBucketController {

    private final Gson GSON = new Gson();

    @RequestMapping("/create/{region}/{bucketName}")
    public String createBucket(@PathVariable("region") String region, @PathVariable("bucketName") String bucketName) {
        AmazonS3ClientBuilder clientBuilder = AmazonS3ClientBuilder.standard();
        clientBuilder.setRegion(region);
        AmazonS3 amazonS3 = clientBuilder.build();
        Bucket bucket = amazonS3.createBucket(bucketName);
        return GSON.toJson(bucket);
    }

}
