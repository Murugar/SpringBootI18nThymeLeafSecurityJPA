package com.iqmsoft.boot.thymeleaf.userservice.util.exceptions;

public class EmptyUserListException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyUserListException(String message) {
        super(message);
    }
}
