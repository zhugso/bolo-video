package com.zhugso.web.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@Tag(name = "用户视频管理")
@RestController
@RequestMapping("/user")
public class VideoController {

    @Resource
    VideoService videoService;

    @Resource
    UserService userService;

    @GetMapping("videoCards/{count}")
    public ResultData<List<VideoCardVo>> getVideoCardList(@PathVariable Integer count) {
        LambdaQueryWrapper<Video> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Video::getIsDelete, IsDelete.NO.ordinal())
                .eq(Video::getStatus, VideoStatus.Pass.ordinal());

        ArrayList<VideoCardVo> list = new ArrayList<>();
        VideoCardVo videoCardVo;
        for (Video video : videoService.list(queryWrapper)) {

            videoCardVo = new VideoCardVo();
            BeanUtils.copyProperties(video, videoCardVo);

            videoCardVo.setNickname(userService.getById(video.getUserId()).getNickname());
            list.add(videoCardVo);
        }

        return ResultData.success(list);
    }


    @GetMapping("videos/{id}")
    public ResultData<VideoVo> getVideoInfo(@PathVariable("id") Long id) {
        Video video = videoService.getById(id);
        User user = userService.getById(video.getUserId());

        VideoVo videoVo = new VideoVo();

        BeanUtils.copyProperties(video, videoVo);
        BeanUtils.copyProperties(user, videoVo);

        return ResultData.success(videoVo);
    }


    /**
     * @param text
     * @return
     */
    @GetMapping("search-videos/{text}")
    public ResultData<List<VideoCardVo>> getSearchInfo(@PathVariable("text") String text, @RequestParam("page") Integer pageNum) {

        Page<Video> page = new Page<>(pageNum, 30);

        LambdaQueryWrapper<Video> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Video::getTitle, text);

        List<Video> list = videoService.list(page, queryWrapper);
        List<VideoCardVo> videoCardVoList = new ArrayList<>();

        VideoCardVo videoCardVo;

        for (Video video : list) {
            videoCardVo = new VideoCardVo();
            BeanUtils.copyProperties(video, videoCardVo);
            videoCardVo.setNickname(userService.getById(video.getUserId()).getNickname());
            videoCardVoList.add(videoCardVo);
        }

        return ResultData.success(videoCardVoList);
}


}
