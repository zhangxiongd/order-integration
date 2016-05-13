package me.smart.order.api.merchant.request;

import lombok.ToString;
import me.smart.order.enums.ResultCode;
import me.smart.order.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiong on 16/4/3.
 */
@ToString
public class TokenRequest extends BaseRequest {
    private String mobile;
    private Integer operationType;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    @Override
    public void validate() throws BusinessException {
        super.validate(source, token, sign);
        assertNotNull(mobile, "手机号", LENGTH_11);
        if (operationType == null) {
            throw new BusinessException(ResultCode.REQUEST_NOT_VALID);
        }
    }


    public static void main(String[] args) {
        System.out.println(String.class.isInstance("ewwr"));

        System.out.println("werwe" instanceof String);

        System.out.println(ArrayList.class.isAssignableFrom(List.class));

    }


}
