package com.zhugso.web.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "视频card信息")
public class VideoCardVo {

    @Schema(description = "视频id")
    private Long videoId;

    @Schema(description = "视频标题")
    private String title;

    @Schema(description = "视频封面url")
    private String coverUrl;

    @Schema(description = "视频上传用户id")
    private Long userId;

    @Schema(description = "视频上传用户昵称")
    private String nickname;

    @Schema(description = "视频上传时间")
    private Date uploadTime;

}
