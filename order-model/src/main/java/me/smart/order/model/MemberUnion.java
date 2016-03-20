package me.smart.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhangxiong on 16/3/9.
 */
public class MemberUnion implements Serializable {
    private Long id;
    private Long memberId;
    /**
     * 微信的openId 以及其他第三方登录方token
     */
    private String openId;
    /**
     * 微信开放平台的唯一标识
     */
    private String unionId;
    /**
     * 标识来源
     * 微信：1:服务号  2:APP  3:订阅号
     */
    private int source;
    private boolean isDelete;
    private Date createdAt;

    public MemberUnion() {
    }

    public MemberUnion(Long memberId, String openId, String unionId, int source) {
        this.memberId = memberId;
        this.openId = openId;
        this.unionId = unionId;
        this.source = source;
        this.isDelete = false;
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
