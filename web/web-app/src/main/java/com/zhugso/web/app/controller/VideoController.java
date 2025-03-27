package com.zhugso.web.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhugso.common.result.ResultData;
import com.zhugso.model.entity.User;
import com.zhugso.model.entity.Video;
import com.zhugso.model.enums.IsDelete;
import com.zhugso.model.enums.VideoStatus;
import com.zhugso.web.app.service.UserService;
import com.zhugso.web.app.service.VideoService;
import com.zhugso.web.app.vo.VideoCardVo;
import com.zhugso.web.app.vo.VideoVo;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Tag(name = "用户视频管理")
@RestController
@RequestMapping("/user")
public class VideoController {

    @Resource
    VideoService videoService;

    @Resource
    UserService userService;

    @GetMapping("getVideoCardList")
    public ResultData<List<VideoCardVo>> getVideoCardList(){
        LambdaQueryWrapper<Video> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Video::getIsDelete, IsDelete.NO.ordinal())
                .eq(Video::getStatus, VideoStatus.Pass.ordinal());

        ArrayList<VideoCardVo> list = new ArrayList<>();
        VideoCardVo videoCardVo;
        for (Video video : videoService.list(queryWrapper)) {

            videoCardVo = new VideoCardVo();
            videoCardVo.setVideoId(video.getVideoId());
            videoCardVo.setTitle(video.getTitle());
            videoCardVo.setCoverUrl(video.getCoverUrl());
            videoCardVo.setUploadTime(video.getUploadTime());
            videoCardVo.setUserId(video.getUserId());
            videoCardVo.setNickname(userService.getById(video.getUserId()).getNickname());
            list.add(videoCardVo);
        }

        return ResultData.success(list);
    }



    @GetMapping("videos/{id}")
    public ResultData<VideoVo> getVideoInfo(@PathVariable("id") Long id){
        Video video = videoService.getById(id);
        User user = userService.getById(video.getUserId());

        VideoVo videoVo = new VideoVo();

        videoVo.setVideoId(video.getVideoId());

        videoVo.setTitle(video.getTitle());

        videoVo.setDescription(video.getDescription());

        videoVo.setPlayCount(video.getPlayCount());

        videoVo.setDuration(video.getDuration());

        videoVo.setVideoUrl(video.getVideoUrl());

        videoVo.setCategoryId(video.getCategoryId());

        videoVo.setCopyright(video.getCopyright());

        videoVo.setUploadTime(video.getUploadTime());

        videoVo.setUserId(video.getUserId());

        videoVo.setNickname(user.getNickname());

        videoVo.setSignature(user.getSignature());

        videoVo.setAvatarUrl(user.getAvatarUrl());

        return ResultData.success(videoVo);
    }

}
