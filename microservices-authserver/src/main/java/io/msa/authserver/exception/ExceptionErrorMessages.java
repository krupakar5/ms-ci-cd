package io.msa.authserver.exception;

public enum ExceptionErrorMessages {
	INTERNAL_SERVER_ERROR("Something Went Wrong. Please Repeat This Operation Later."),
	RECORD_ALREADY_EXISTS(" username is already exists.Please try with different username..."), 
	RECORD_NOT_AVAILABLE("user id not found in database..."),
	USERNAME_NOT_AVAILABLE("username not found in database..."),
	INVALID_ACTIVATION_KEY("activation key is not found in database");


	private String errMessage;

	ExceptionErrorMessages(String errorMessage) {
		this.errMessage = errorMessage;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

}
