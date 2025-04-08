package com.zhugso.web.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "视频播放页信息")
public class VideoVo {
    @Schema(description = "视频id")
    private Long videoId;

    @Schema(description = "视频标题")
    private String title;

    @Schema(description = "视频简介")
    private String description;

    @Schema(description = "视频播放数量")
    private Long playCount;

    @Schema(description = "视频时间")
    private Long duration;

    @Schema(description = "视频地址")
    private String videoUrl;

    @Schema(description = "视频分类")
    private Long categoryId;

    @Schema(description = "视频版权信息")
    private Integer copyright;

    @Schema(description = "视频上传时间")
    private Date uploadTime;

    @Schema(description = "视频上传用户id")
    private Long userId;

    @Schema(description = "视频上传昵称")
    private String nickname;

    @Schema(description = "视频上传用户签名")
    private String signature;

    @Schema(description = "视频上传用户头像Url")
    private String avatarUrl;

    @Schema(description = "视频点赞数")
    private Integer thumbs;

    @Schema(description = "视频收藏数")
    private Integer collect;

}
