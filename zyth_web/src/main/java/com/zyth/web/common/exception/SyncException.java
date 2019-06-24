package com.zyth.web.common.exception;

public class SyncException extends RuntimeException {

	/**
	 * ͬ同步Exception
	 */
	private static final long serialVersionUID = 1L;

//	private String businessCode;
	private String errorCode;
	private String errorMessage;
	private String errorResolution;
	private String errorLevle;

	@SuppressWarnings("unused")
	private SyncException(){
		super();
	}
	public SyncException(String message){
		super(message);
	}

	@SuppressWarnings("unused")
	private SyncException(Throwable cause) {
        super(cause);
    }

	public SyncException(String errorCode,String errorMessage,String errorResolution,String errorLevle,Throwable cause){
		this.errorCode = errorCode;
		this.errorLevle = errorLevle;
		this.errorMessage = errorMessage;
		this.errorResolution = errorResolution;
	}

	public SyncException(String errorCode,String errorMessage,String errorResolution,String errorLevle){
		this( errorCode, errorMessage, errorResolution, errorLevle,null);
	}

	public String getErrorCode() {
		return errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public String getErrorResolution() {
		return errorResolution;
	}
	public String getErrorLevle() {
		return errorLevle;
	}

}
