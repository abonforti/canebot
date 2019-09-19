
/**
 *
 * Copyright (C) 2015 Roberto Dominguez Estrada and Juan Carlos Sedano Salas
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 * @author buzz
 * @version $Id: $Id
 * @since 0.0.2
 */
package io.github.nixtabyte.telegram.jtelebot.response.json;

import org.codehaus.jackson.annotate.JsonProperty;
/**
*
* ForceReplay
*
* @since 0.0.1
*/
public class ForceReply implements CustomReplyKeyboard{

	/**
	 * Shows reply interface to the user, as if they manually selected the bots
	 * message and tapped Reply'
	 * */
	@JsonProperty("force_reply")
	private Boolean forceReply;

	/**
	 * Optional. Use this parameter if you want to force reply from specific
	 * users only. Targets: 1) users that are mentioned in the text of the
	 * Message object; 2) if the bot's message is a reply (has
	 * reply_to_message_id), sender of the original message
	 * */
	@JsonProperty("selective")
	private Boolean selective;
	/**
	 * <p>isForceReply.</p>
	 *
	 * @return a {@link java.lang.Boolean} object.
	 */
	public Boolean isForceReply() {
		return forceReply;
	}

	/**
	 * <p>Setter for the field <code>forceReply</code>.</p>
	 *
	 * @param forceReply a {@link java.lang.Boolean} object.
	 */
	public void setForceReply(final Boolean forceReply) {
		this.forceReply = forceReply;

	}
	/**
	 * <p>isSelective.</p>
	 *
	 * @return a {@link java.lang.Boolean} object.
	 */
	public Boolean isSelective() {
		return selective;

	}
	/**
	 * <p>Setter for the field <code>selective</code>.</p>
	 *
	 * @param selective a {@link java.lang.Boolean} object.
	 */
	public void setSelective(final Boolean selective) {
		this.selective = selective;

	}
	/** {@inheritDoc} */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((forceReply == null) ? 0 : forceReply.hashCode());
		result = prime * result
				+ ((selective == null) ? 0 : selective.hashCode());
		return result;
	}
	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ForceReply other = (ForceReply) obj;
		if (forceReply == null) {
			if (other.forceReply != null)
				return false;
		} else if (!forceReply.equals(other.forceReply))
			return false;
		if (selective == null) {
			if (other.selective != null)
				return false;
		} else if (!selective.equals(other.selective))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ForceReply [forceReply=" + forceReply + ", selective="
				+ selective + "]";
	}
}
