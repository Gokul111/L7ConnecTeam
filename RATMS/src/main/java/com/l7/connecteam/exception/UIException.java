package com.l7.connecteam.exception;

public class UIException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UIException() {
		// TODO Auto-generated constructor stub
		super();
	}
	public UIException(String s) {
		// TODO Auto-generated constructor stub
				super(s);
	}
	
	public UIException(String s, Exception e) {
		super(s,e);
		//System.out.println("Cause: "+ e.getCause());
	}
}
