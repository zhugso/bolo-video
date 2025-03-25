package com.zhugso.web.admin.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CheckVideoVo {

    private Long videoId;

    /**
     * 标题
     */
    private String title;

    /**
     * 简介
     */
    private String description;

    /**
     * 视频时间
     */
    private Long duration;

    /**
     * 封面地址
     */
    private String coverUrl;

    /**
     * 视频地址
     */
    private String videoUrl;


    /**
     * 版权信息，（0自制，1转载）
     */
    private Integer copyright;

    /**
     * 上传用户id
     */
    private Long userId;

    /**
     * 上传时间
     */
    private Date uploadTime;

}
