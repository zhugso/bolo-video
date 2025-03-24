package com.zhugso.web.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "管理信息")
public class AdminVo {
    /**
     * 管理账号
     */
    @Schema(description = "管理账号")
    private String adminName;

    /**
     * 管理昵称
     */
    @Schema(description = "管理昵称")
    private String nickName;

}
