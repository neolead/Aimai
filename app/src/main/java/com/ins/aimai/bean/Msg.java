package com.ins.aimai.bean;


import java.io.Serializable;

/**
 * 消息
 * Created by Eric Xie on 2017/7/13 0013.
 */
public class Msg implements Serializable {

    /**
     * 主键ID
     */
    private int id;

    /**
     * title
     */
    private String title;

    /**
     * 摘要
     */
    private String digest;

    /**
     * 消息类型 0:资讯推送
     */
    private int type;

    /**
     * 如果type是 0  则资讯ID
     */
    private int newsId;

    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 是否查看过 0:否  1:是
     */
    private int isChecked;

    ////////////////////  业务方法 ////////////////

    public boolean isChecked() {
        return isChecked == 1;
    }

    ///////////////////////////////////////////////

    /**
     * 获取 主键ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * 设置 主键ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取 title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置 title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取 摘要
     */
    public String getDigest() {
        return this.digest;
    }

    /**
     * 设置 摘要
     */
    public void setDigest(String digest) {
        this.digest = digest;
    }

    /**
     * 获取 消息类型 0:资讯推送
     */
    public int getType() {
        return this.type;
    }

    /**
     * 设置 消息类型 0:资讯推送
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取 如果type是 0  则资讯ID
     */
    public int getNewsId() {
        return this.newsId;
    }

    /**
     * 设置 如果type是 0  则资讯ID
     */
    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    /**
     * 获取 创建时间
     */
    public long getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置 创建时间
     */
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 是否查看过 0:否  1:是
     */
    public int getIsChecked() {
        return this.isChecked;
    }

    /**
     * 设置 是否查看过 0:否  1:是
     */
    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }
}
