package com.zhugso.web.app.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户顶部信息")
public class UserHeadInfoVo {

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户头像Url")
    private String avatarUrl;

    @Schema(description = "用户关注数")
    private Integer followNums;

    @Schema(description = "用户动态数")
    private Integer dynamicNums;

    @Schema(description = "用户粉丝数")
    private Integer fansNums;

}
