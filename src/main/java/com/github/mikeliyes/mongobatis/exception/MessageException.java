package com.github.mikeliyes.mongobatis.exception;

public class MessageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	  public MessageException() {
	    super();
	  }

	  public MessageException(String message) {
	    super(message);
	  }

	  public MessageException(String message, Throwable cause) {
	    super(message, cause);
	  }

	  public MessageException(Throwable cause) {
	    super(cause);
	  }
}
