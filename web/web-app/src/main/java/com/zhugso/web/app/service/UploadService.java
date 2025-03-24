package com.zhugso.web.app.service;

import com.zhugso.web.app.vo.CoverUrlVo;
import com.zhugso.web.app.vo.VideoInfoVo;
import com.zhugso.web.app.vo.VideoUrlVo;

public interface UploadService {
    VideoUrlVo getUploadVideoUrl();

    CoverUrlVo getUploadCoverUrl();

    void submitVideo(Long userId, VideoInfoVo videoInfoVo);
}
