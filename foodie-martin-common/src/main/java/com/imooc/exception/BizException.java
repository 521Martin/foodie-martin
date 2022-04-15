/**
 * 
 */
package com.imooc.exception;


import com.imooc.result.ResultCode;

/**
 * @author rong yang
 *
 */
@SuppressWarnings("serial")
public class BizException extends RuntimeException {
	
	private ResultCode resultCode;

	/**
	 * 
	 */
	public BizException() {
		
	}

	/**
	 * @param message
	 */
	public BizException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public BizException(Throwable cause) {
		super(cause);
	}

    /**
     * @param cause
     */
    public BizException(ResultCode resultCode,Throwable cause) {
        super(cause);
        this.resultCode = resultCode;
    }
	
	public BizException(String message, Throwable cause, ResultCode resultCode) {
		super(message, cause);
		this.resultCode = resultCode;
	}
	
	public BizException(String message, ResultCode resultCode) {
		super(message);
		this.resultCode = resultCode;
	}
	
	public BizException(ResultCode resultCode) {
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the resultCode
	 */
	public ResultCode getResultCode() {
		return resultCode;
	}
}
