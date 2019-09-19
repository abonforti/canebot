package io.github.nixtabyte.telegram.jtelebot.exception;

public class TelegramServerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4998014921081729009L;

	
	public TelegramServerException(final Exception exception){
		super(exception);
	}
	
	public TelegramServerException(final String message){
		super(message);
	}
}
