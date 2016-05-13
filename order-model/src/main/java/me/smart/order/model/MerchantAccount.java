package me.smart.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhangxiong on 16/4/2.
 */
public class MerchantAccount implements Serializable {
    private Long id;
    private Long merchantId;
    private String mobile;
    private String realName;
    private String email;
    private String accountImg;
    private String loginPassword;
    private String loginSalt;
    private String tradePassword;
    private String tradeSalt;
    private Boolean isAdmin;
    private Integer source;
    private String token;
    private Boolean isDelete;
    private Date lastLoginAt;
    private Date createdAt;
    private Date updatedAt;

    public MerchantAccount() {
    }

    public MerchantAccount(Long merchantId, String mobile, String realName,
                           String email, String loginPassword, String loginSalt, Boolean isAdmin) {
        this.merchantId = merchantId;
        this.mobile = mobile;
        this.realName = realName;
        this.email = email;
        this.loginPassword = loginPassword;
        this.loginSalt = loginSalt;
        this.isAdmin = isAdmin;
        this.isDelete = false;
        this.lastLoginAt = new Date();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountImg() {
        return accountImg;
    }

    public void setAccountImg(String accountImg) {
        this.accountImg = accountImg;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLoginSalt() {
        return loginSalt;
    }

    public void setLoginSalt(String loginSalt) {
        this.loginSalt = loginSalt;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public String getTradeSalt() {
        return tradeSalt;
    }

    public void setTradeSalt(String tradeSalt) {
        this.tradeSalt = tradeSalt;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Date lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
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

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
