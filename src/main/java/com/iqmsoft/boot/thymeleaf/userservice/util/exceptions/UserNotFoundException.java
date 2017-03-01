package com.iqmsoft.boot.thymeleaf.userservice.util.exceptions;

public class UserNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
