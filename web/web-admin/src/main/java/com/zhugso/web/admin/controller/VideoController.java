package com.zhugso.web.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zhugso.common.result.ResultCodeEnum;
import com.zhugso.common.result.ResultData;
import com.zhugso.model.entity.CheckNopass;
import com.zhugso.model.entity.Video;
import com.zhugso.model.enums.VideoStatus;
import com.zhugso.web.admin.service.CheckNopassService;
import com.zhugso.web.admin.service.VideoService;
import com.zhugso.web.admin.vo.CheckVideoVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "视频信息管理")
@RestController
@RequestMapping("/admin")
public class VideoController {

    @Resource
    VideoService videoService;

    @Resource
    CheckNopassService checkNopassService;


    @GetMapping("getCheckVideoList")
    public ResultData<List<CheckVideoVo>> getCheckVideoList() {
        LambdaQueryWrapper<Video> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Video::getIsDelete, 0);
        queryWrapper.eq(Video::getStatus, 2);
        List<CheckVideoVo> list = new ArrayList<>();

        CheckVideoVo checkVideoVo;

        for (Video video : videoService.list(queryWrapper)) {
            checkVideoVo = new CheckVideoVo();
            checkVideoVo.setVideoId(video.getVideoId());
            checkVideoVo.setTitle(video.getTitle());
            checkVideoVo.setDescription(video.getDescription());
            checkVideoVo.setDuration(video.getDuration());
            checkVideoVo.setCoverUrl(video.getCoverUrl());
            checkVideoVo.setVideoUrl(video.getVideoUrl());
            checkVideoVo.setCopyright(video.getCopyright());
            checkVideoVo.setUploadTime(video.getUploadTime());
            checkVideoVo.setUserId(video.getUserId());
            list.add(checkVideoVo);
        }
        return ResultData.success(list);
    }

    @GetMapping("getVideoList")
    public ResultData<?> getVideoList() {
        return ResultData.success(null);
    }

    @PostMapping("checkPass/{id}")
    public ResultData<?> checkPassVideo(@PathVariable("id") Long id) {
        LambdaUpdateWrapper<Video> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(Video::getVideoId, id)
                .set(Video::getStatus, VideoStatus.Pass.ordinal());
        boolean update = videoService.update(updateWrapper);
        return update ? ResultData.success(null) : ResultData.fail(ResultCodeEnum.FAIL);
    }

    @PostMapping("checkNotPass")
    public ResultData<?> checkPassVideo(@RequestBody CheckNopass checkNopass) {
        LambdaUpdateWrapper<Video> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(Video::getVideoId, checkNopass.getVideoId())
                .set(Video::getStatus, VideoStatus.NotPass.ordinal());
        boolean update = videoService.update(updateWrapper);
        if(update){
            checkNopassService.save(checkNopass);
        }
        return ResultData.success(null);
    }


}
