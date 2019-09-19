
/**
 *
 * Copyright (C) 2015 Roberto Dominguez Estrada and Juan Carlos Sedano Salas
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 */
package io.github.nixtabyte.telegram.jtelebot.client;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
/**
*
* An http client factory
*
* @since 0.0.1
*/
public final class HttpClientFactory {

	private HttpClientFactory() {
	}

	/**
	 * <p>createHttpClient.</p>
	 *
	 * @return a {@link org.apache.http.client.HttpClient} object.
	 */
	public static HttpClient createHttpClient() {
		return HttpClientBuilder.create().build();
	}

}
