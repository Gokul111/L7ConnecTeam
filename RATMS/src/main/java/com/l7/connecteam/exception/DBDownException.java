package com.l7.connecteam.exception;

public class DBDownException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DBDownException() {
		// TODO Auto-generated constructor stub
					super();
	}
	public DBDownException(String s) {
		// TODO Auto-generated constructor stub
		super(s);
	}
	
	public DBDownException(String s, Exception e) {
		super(s,e);
		System.out.println("cause: "+e.getCause());
	}

}
