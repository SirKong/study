package com.ccnu.test.springboot.constant;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.http.HttpStatus;

/**
 * @author SirKong
 * @date 2018/8/28.
 */
public class OpenAPIResult<T> implements Serializable {

    private int statusCode;

    private String code;

    private String zhMessage;

    private String enMessage;

    private T result;

    private List<T> resultList;

    private Integer total;

    public OpenAPIResult(int statusCode, String code, String zhMessage, String enMessage, T result) {
        this.statusCode = statusCode;
        this.code = code;
        this.zhMessage = zhMessage;
        this.enMessage = enMessage;
        this.result = result;
    }

    public OpenAPIResult(int statusCode, String code, String zhMessage, String enMessage, List<T> resultList) {
        this.statusCode = statusCode;
        this.code = code;
        this.zhMessage = zhMessage;
        this.enMessage = enMessage;
        this.resultList = resultList;
    }

    public OpenAPIResult(int statusCode, String code, String zhMessage, String enMessage, List<T> resultList, Integer
            total) {
        this.statusCode = statusCode;
        this.code = code;
        this.zhMessage = zhMessage;
        this.enMessage = enMessage;
        this.resultList = resultList;
        this.total = total;
    }

    public OpenAPIResult(int statusCode, String code, String zhMessage, String enMessage) {
        this.statusCode = statusCode;
        this.code = code;
        this.zhMessage = zhMessage;
        this.enMessage = enMessage;
    }

    public static <T> OpenAPIResult<T> buildSuccess(T result) {
        return new OpenAPIResult<>(ErrorCodeEnum.Success.getStatusCode(), ErrorCodeEnum.Success.getCode(),
                ErrorCodeEnum.Success.getMsg(), ErrorCodeEnum.Success.getEnMsg(), result);
    }

    public static <T> OpenAPIResult<T> buildSuccess(List<T> resultList) {
        return new OpenAPIResult<>(ErrorCodeEnum.Success.getStatusCode(), ErrorCodeEnum.Success.getCode(),
                ErrorCodeEnum.Success.getMsg(), ErrorCodeEnum.Success.getEnMsg(), resultList);
    }

    public static <T> OpenAPIResult<T> buildSuccess(List<T> resultList, Integer total) {
        return new OpenAPIResult<>(ErrorCodeEnum.Success.getStatusCode(), ErrorCodeEnum.Success.getCode(),
                ErrorCodeEnum.Success.getMsg(), ErrorCodeEnum.Success.getEnMsg(), resultList, total);
    }

    public static <T> OpenAPIResult<T> buildSuccess() {
        return new OpenAPIResult<>(ErrorCodeEnum.Success.getStatusCode(), ErrorCodeEnum.Success.getCode(),
                ErrorCodeEnum.Success.getMsg(), ErrorCodeEnum.Success.getEnMsg());
    }

    public static OpenAPIResult buildError(ErrorCodeEnum errorCodeEnum) {
        return new OpenAPIResult(errorCodeEnum.getStatusCode(), errorCodeEnum.getCode(), errorCodeEnum.getMsg(),
                errorCodeEnum.getEnMsg(), null);
    }

    public static OpenAPIResult buildError(ErrorCodeEnum errorCodeEnum, Object... args) {
        String enMessage = String.format(errorCodeEnum.getEnMsg(), args);
        String zhMessage = String.format(errorCodeEnum.getMsg(), args);
        return new OpenAPIResult(errorCodeEnum.getStatusCode(), errorCodeEnum.getCode(), zhMessage, enMessage);
    }

    public static boolean isSuccess(OpenAPIResult openAPIResult) {
        return null != openAPIResult && openAPIResult.isSuccess();
    }

    public static boolean isSingle(OpenAPIResult openAPIResult) {
        return null != openAPIResult && openAPIResult.isSingle();
    }

    public static boolean isList(OpenAPIResult openAPIResult) {
        return null != openAPIResult && openAPIResult.isList();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getZhMessage() {
        return zhMessage;
    }

    public void setZhMessage(String zhMessage) {
        this.zhMessage = zhMessage;
    }

    public String getEnMessage() {
        return enMessage;
    }

    public void setEnMessage(String enMessage) {
        this.enMessage = enMessage;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public boolean isSuccess() {
        return HttpStatus.OK.value() == this.getStatusCode() && ErrorCodeEnum.Success.getCode().equals(this.getCode());
    }

    public boolean isSingle() {
        return null != result;
    }

    public boolean isList() {
        return null != resultList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("statusCode", statusCode)
                .append("code", code)
                .append("zhMessage", zhMessage)
                .append("enMessage", enMessage)
                .append("result", result)
                .append("resultList", resultList)
                .append("total", total)
                .toString();
    }
}
