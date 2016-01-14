package me.smart.order.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import me.smart.order.enums.ResultCode;

import java.util.Map;

/**
 * Created by zhangxiong on 15/12/23.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

public class Result<T> {

    public static final Result SUCCESS_RESULT = new Result();
    private T resultData;
    private ResultCode resultCode;
    private String resultMsg;
    private Map<String, Object> ext;

    public Result() {
        this.resultCode = ResultCode.SUCCESS;
    }

    public Result(T resultData) {
        this.resultData = resultData;
        this.resultCode = ResultCode.SUCCESS;
    }

    public Result(T resultData, Map<String, Object> ext) {
        this.resultData = resultData;
        this.resultCode = ResultCode.SUCCESS;
        this.ext = ext;
    }


    public Result(ResultCode resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }


    public Result(Result errorResult) {
        this.resultCode = errorResult.resultCode;
        this.resultMsg = errorResult.resultMsg;
    }

    public static <T> Result<T> createResult(T result) {
        return new Result<T>(result);
    }

    public static <T> Result<T> errorResult(Result errorResult) {
        return new Result<T>(errorResult);
    }

    public boolean isSuccess() {
        return (resultCode == ResultCode.SUCCESS);
    }

    @JsonIgnore
    public boolean isFailure() {
        return !isSuccess();
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return (resultMsg != null) ? resultMsg :
                (resultCode != null ? resultCode.getDesc() : null);
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Map<String, Object> getExt() {
        return ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }
}
