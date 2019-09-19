
/**
 *
 * Copyright (C) 2015 Roberto Dominguez Estrada and Juan Carlos Sedano Salas
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 */
package io.github.nixtabyte.telegram.jtelebot.client;
/**
*
* This object represents an HttpProxy in case you need to use one
*
* @since 0.0.1
*/
public class HttpProxy {

	private String host;
	private int port;
	private String protocol;

	/**
	 * <p>Constructor for HttpProxy.</p>
	 *
	 * @param host a {@link java.lang.String} object.
	 * @param port a int.
	 * @param protocol a {@link java.lang.String} object.
	 */
	public HttpProxy(final String host, final int port, final String protocol) {
		this.host = host;
		this.port = port;
		this.protocol = protocol;
	}

	/**
	 * <p>Getter for the field <code>host</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getHost() {
		return host;
	}

	/**
	 * <p>Setter for the field <code>host</code>.</p>
	 *
	 * @param host a {@link java.lang.String} object.
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * <p>Getter for the field <code>port</code>.</p>
	 *
	 * @return a int.
	 */
	public int getPort() {
		return port;
	}

	/**
	 * <p>Setter for the field <code>port</code>.</p>
	 *
	 * @param port a int.
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * <p>Getter for the field <code>protocol</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * <p>Setter for the field <code>protocol</code>.</p>
	 *
	 * @param protocol a {@link java.lang.String} object.
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

}
