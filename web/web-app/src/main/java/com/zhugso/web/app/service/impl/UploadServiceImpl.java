package com.zhugso.web.app.service.impl;

import com.zhugso.common.exception.BoloException;
import com.zhugso.common.minio.MinioProperties;
import com.zhugso.common.result.ResultCodeEnum;
import com.zhugso.model.entity.Video;
import com.zhugso.web.app.mapper.VideoMapper;
import com.zhugso.web.app.service.UploadService;
import com.zhugso.web.app.vo.AvatarUrlVo;
import com.zhugso.web.app.vo.CoverUrlVo;
import com.zhugso.web.app.vo.VideoInfoVo;
import com.zhugso.web.app.vo.VideoUrlVo;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {


    @Resource
    private MinioClient minioClient;

    @Resource
    private MinioProperties minioProperties;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private VideoMapper videoMapper;

    @Override
    public VideoUrlVo getUploadVideoUrl() {
        // 设置文件路径及文件名
        String fileName = "v/" + System.currentTimeMillis() + UUID.randomUUID();
        // 设置redis key
        String key = "upVideo:" + UUID.randomUUID();
        // 存入redis
        stringRedisTemplate.opsForValue()
                .set(key, fileName, 6, TimeUnit.HOURS);

        // 获取上传url
        String url;
        Map<String, String> reqParams = new HashMap<>();
        reqParams.put("content_type", "application/octet-stream");
        try {
            url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.PUT)
                    .bucket(minioProperties.getBucketName())
                    .object(fileName)
                    .expiry(6, TimeUnit.HOURS)
                    .extraQueryParams(reqParams)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BoloException(ResultCodeEnum.FAIL);
        }

        return new VideoUrlVo(url, key);
    }

    @Override
    public CoverUrlVo getUploadCoverUrl() {
        // 设置文件路径及文件名
        String fileName = "c/" + System.currentTimeMillis() + UUID.randomUUID();
        // 设置redis key
        String key = "upCover:" + UUID.randomUUID();
        // 存入redis
        stringRedisTemplate.opsForValue()
                .set(key, fileName, 6, TimeUnit.HOURS);

        String url;
        Map<String, String> reqParams = new HashMap<>();
        reqParams.put("content_type", "application/octet-stream");
        try {
            url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.PUT)
                    .bucket(minioProperties.getBucketName())
                    .object(fileName)
                    .expiry(6, TimeUnit.HOURS)
                    .extraQueryParams(reqParams)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BoloException(ResultCodeEnum.FAIL);
        }

        return new CoverUrlVo(url, key);
    }



    @Override
    public void submitVideo(Long userId, VideoInfoVo videoInfoVo) {
        // 获取key
        String videoUrl = stringRedisTemplate.opsForValue().get(videoInfoVo.getVideoKey());
        if(videoUrl == null) {
            throw new BoloException(ResultCodeEnum.FAIL);
        }

        String coverUrl = stringRedisTemplate.opsForValue().get(videoInfoVo.getCoverKey());
        if(coverUrl == null)
            throw new BoloException(ResultCodeEnum.FAIL);

        Video video = new Video();
        video.setUserId(userId);
        video.setTitle(videoInfoVo.getTitle());
        video.setDescription(videoInfoVo.getDescription());
        video.setVideoUrl(videoUrl);
        video.setCoverUrl(coverUrl);
        video.setCopyright(videoInfoVo.getCopyright());

        videoMapper.insert(video);

        stringRedisTemplate.delete(videoInfoVo.getVideoKey());
        stringRedisTemplate.delete(videoInfoVo.getCoverKey());

    }


    @Override
    public AvatarUrlVo getUploadAvatarUrl() {
        // 设置文件路径及文件名
        String fileName = "a/" + System.currentTimeMillis() + UUID.randomUUID();
        // 设置redis key
        String key = "upAvatar:" + UUID.randomUUID();
        // 存入redis
        stringRedisTemplate.opsForValue()
                .set(key, fileName, 10, TimeUnit.MINUTES);

        String url;
        Map<String, String> reqParams = new HashMap<>();
        reqParams.put("content_type", "application/octet-stream");
        try {
            url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.PUT)
                    .bucket(minioProperties.getBucketName())
                    .object(fileName)
                    .expiry(10, TimeUnit.MINUTES)
                    .extraQueryParams(reqParams)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BoloException(ResultCodeEnum.FAIL);
        }

        return new AvatarUrlVo(url, key);
    }


}
