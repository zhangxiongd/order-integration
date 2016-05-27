package me.smart.order.api.member.Request;

import lombok.ToString;
import me.smart.order.exception.BusinessException;

/**
 * Created by zhangxiong on 2016-05-24.
 */
@ToString
public class MemberMenuOrderQueryRequest extends BaseRequest{

    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public void validate() throws BusinessException {
           assertNotNull(memberId,"用户ID",LENGTH_11);
    }
}
