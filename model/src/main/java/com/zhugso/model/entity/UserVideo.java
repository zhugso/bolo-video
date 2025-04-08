package com.zhugso.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName user_video
 */
@TableName(value ="user_video")
@Data
public class UserVideo {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 视频id
     */
    private Long videoId;

    /**
     * 播放数量
     */
    private Integer playCount;

    /**
     * 是否点赞（0没点，1点赞）
     */
    private Integer isThumbs;

    /**
     * 是否收藏（0没收，1收藏）
     */
    private Integer isCollect;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserVideo other = (UserVideo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getVideoId() == null ? other.getVideoId() == null : this.getVideoId().equals(other.getVideoId()))
            && (this.getPlayCount() == null ? other.getPlayCount() == null : this.getPlayCount().equals(other.getPlayCount()))
            && (this.getIsThumbs() == null ? other.getIsThumbs() == null : this.getIsThumbs().equals(other.getIsThumbs()))
            && (this.getIsCollect() == null ? other.getIsCollect() == null : this.getIsCollect().equals(other.getIsCollect()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getVideoId() == null) ? 0 : getVideoId().hashCode());
        result = prime * result + ((getPlayCount() == null) ? 0 : getPlayCount().hashCode());
        result = prime * result + ((getIsThumbs() == null) ? 0 : getIsThumbs().hashCode());
        result = prime * result + ((getIsCollect() == null) ? 0 : getIsCollect().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", videoId=").append(videoId);
        sb.append(", playCount=").append(playCount);
        sb.append(", isThumbs=").append(isThumbs);
        sb.append(", isCollect=").append(isCollect);
        sb.append("]");
        return sb.toString();
    }
}