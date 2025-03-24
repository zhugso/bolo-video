package com.zhugso.web.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "投稿信息")
public class VideoInfoVo {

    @Schema(description = "视频标题")
    private String title;

    @Schema(description = "视频简介")
    private String description;

    @Schema(description = "视频key")
    private String videoKey;

    @Schema(description = "视频封面key")
    private String coverKey;

    @Schema(description = "版权信息")
    private Integer copyright;

}
