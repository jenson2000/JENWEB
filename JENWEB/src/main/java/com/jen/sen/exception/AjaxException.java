package com.jen.sen.exception;

public class AjaxException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String errCode;
	private String errMsg;

	public AjaxException (String errCode,String errMsg){
		this.errCode=errCode;
		this.errMsg=errMsg;
	}
	
	
	public AjaxException() {

	}


	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public static AjaxException create(String errCode, String errMsg){  
        return new AjaxException(errCode, errMsg);  
    } 
	

}
