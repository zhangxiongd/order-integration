package me.smart.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhangxiong on 16/3/31.
 */
public class VerifyCode implements Serializable {
    private Long id;
    private String mobile;
    private String verifyCode;
    private Integer deadLine;
    private Boolean isValidated;
    private Boolean isDelete;
    private Date createdAt;
    private Date updatedAt;

    public VerifyCode(String mobile, String verifyCode, Integer deadLine) {
        this.mobile = mobile;
        this.verifyCode = verifyCode;
        this.deadLine = deadLine;
        this.isDelete = false;
        this.isValidated = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Integer getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Integer deadLine) {
        this.deadLine = deadLine;
    }

    public Boolean getIsValidated() {
        return isValidated;
    }

    public void setIsValidated(Boolean isValidated) {
        this.isValidated = isValidated;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
