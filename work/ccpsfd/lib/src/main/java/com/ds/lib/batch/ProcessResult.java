package com.ds.lib.batch;

/**
 * @author ds
 * @date 2023/4/13
 * @description
 */
public class ProcessResult {

    private Integer code;

    private String msg;

    public ProcessResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 成功返回
     * @return
     */
    public static ProcessResult success() {
        return new ProcessResult(200, "success");
    }

    /**
     * 成功返回
     * @param msg
     * @return
     */
    public static ProcessResult success(String msg) {
        return new ProcessResult(200, msg);
    }

    /**
     * 失败返回
     * @return
     */
    public static ProcessResult fail() {
        return new ProcessResult(500, "fail");
    }

    /**
     * 应答返回
     * @return
     */
    public static ProcessResult ack() {
        return new ProcessResult(300, "ack");
    }

}
