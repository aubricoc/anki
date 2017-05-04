package com.smartholiday.test.anki;

public class AnkiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AnkiException(String msg) {
		super(msg);
	}
	
	public AnkiException(String msg, Throwable e) {
		super(msg, e);
	}
}
