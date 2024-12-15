package cn.fullstack.od.common.core.model;

/**
 * @date 2024/12/7
 */
public class ResponseResult<T> {

    private static final int DEFAULT_SUCCESS_CODE = 0;

    private Integer code;

    private String message;

    private T data;
}
