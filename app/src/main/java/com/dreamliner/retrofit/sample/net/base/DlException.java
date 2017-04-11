package com.dreamliner.retrofit.sample.net.base;

/**
 * @author chenzj
 * @Title: DlException
 * @Description: 类的描述 -
 * @date 2017/2/28 11:02
 * @email admin@chenzhongjin.cn
 */
public class DlException extends RuntimeException {

    private int mErrorCode;
    private String mErrorMessage;

    public DlException(int errorCode, String errorMessage) {
        mErrorCode = errorCode;
        mErrorMessage = errorMessage;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }
}
