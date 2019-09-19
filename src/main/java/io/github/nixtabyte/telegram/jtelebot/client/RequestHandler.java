
/**
 *
 * Copyright (C) 2015 Roberto Dominguez Estrada and Juan Carlos Sedano Salas
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 */
package io.github.nixtabyte.telegram.jtelebot.client;


import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.response.json.TelegramResponse;
/**
*
* This class manages the requests to the Telegram Bot Api
*
* @since 0.0.1
*/
public interface RequestHandler {

	
	/**
	 * <p>sendRequest.</p>
	 *
	 * @param telegramRequest a {@link io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest} object.
	 * @return a {@link io.github.nixtabyte.telegram.jtelebot.response.json.TelegramResponse} object.
	 * @throws JsonParsingException 
	 * @throws TelegramServerException 
	 */
	public TelegramResponse<?> sendRequest(final TelegramRequest telegramRequest) throws JsonParsingException, TelegramServerException;
	


}
