package com.zhugso.web.app.controller;

import com.zhugso.common.result.ResultData;
import com.zhugso.common.utils.JwtUtil;
import com.zhugso.web.app.service.UploadService;
import com.zhugso.web.app.vo.CoverUrlVo;
import com.zhugso.web.app.vo.VideoInfoVo;
import com.zhugso.web.app.vo.VideoUrlVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@Tag(name = "上传管理")
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    UploadService uploadService;

    @Operation(summary = "获取上传视频封面地址")
    @GetMapping("getUpCoverUrl")
    public ResultData<CoverUrlVo> getUploadCoverUrl(){
        CoverUrlVo coverUrl = uploadService.getUploadCoverUrl();
        return ResultData.success(coverUrl);
    }

    @Operation(summary = "获取上传视频地址")
    @GetMapping("getUpVideoUrl")
    public ResultData<VideoUrlVo> getUploadVideoUrl(){
        VideoUrlVo videoUrl = uploadService.getUploadVideoUrl();
        return ResultData.success(videoUrl);
    }

    @Operation(summary = "发布Video")
    @PostMapping("submitVideo")
    public ResultData<?> submitVideo(@RequestHeader("Authorization") String token, @RequestBody VideoInfoVo videoInfoVo){
        Long userId = JwtUtil.getUserIdToken(token);
        uploadService.submitVideo(userId, videoInfoVo);
        return ResultData.success(null);
    }
}
