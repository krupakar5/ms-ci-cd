package io.msa.authserver.exception;

import java.util.Date;

public class ExceptionResponse {

	private String errMessage;
	private String errCode;
	private Date timestamp;

	public ExceptionResponse(String errMessage, String errCode, Date timestamp) {
		super();
		this.errMessage = errMessage;
		this.errCode = errCode;
		this.timestamp = timestamp;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
