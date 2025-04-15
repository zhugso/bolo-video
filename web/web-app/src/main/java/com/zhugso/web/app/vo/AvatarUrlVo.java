package com.zhugso.web.app.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@Schema(description = "头像上传url信息")
public class AvatarUrlVo {
    @Schema(description = "上传url")
    private String url;

    @Schema(description = "头像key")
    private String avatarKey;
}
