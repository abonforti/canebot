
/**
 *
 * Copyright (C) 2015 Roberto Dominguez Estrada and Juan Carlos Sedano Salas
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 */
package io.github.nixtabyte.telegram.jtelebot.response.json;

import org.codehaus.jackson.annotate.JsonProperty;
/**
*
* ReplyKeyboardHide
*
* @since 0.0.1
*/
public class ReplyKeyboardHide implements CustomReplyKeyboard{

	/**
	 * Requests clients to hide the custom keyboard
	 * */
	@JsonProperty("hide_keyboard")
	private Boolean hideKeyboard;

	/**
	 * Optional. Use this parameter if you want to hide keyboard for specific
	 * users only. Targets: 1) users that are @mentioned in the text of the
	 * Message object; 2) if the bot's message is a reply (has
	 * reply_to_message_id), sender of the original message.
	 * 
	 * Example: A user votes in a poll, bot returns confirmation message in
	 * reply to the vote and hides keyboard for that user, while still showing
	 * the keyboard with poll options to users who haven't voted yet.
	 * */
	@JsonProperty("selective")
	private Boolean selective;
	/**
	 * <p>isHideKeyboard.</p>
	 *
	 * @return a {@link java.lang.Boolean} object.
	 */
	public Boolean isHideKeyboard() {
		return hideKeyboard;
	}
	/**
	 * <p>Setter for the field <code>hideKeyboard</code>.</p>
	 *
	 * @param hideKeyboard a {@link java.lang.Boolean} object.
	 */
	public void setHideKeyboard(final Boolean hideKeyboard) {
		this.hideKeyboard = hideKeyboard;
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
		/**
		 * <p>Setter for the field <code>selective</code>.</p>
		 *
		 * @param selective a {@link java.lang.Boolean} object.
		 */
		int result = 1;
		result = prime * result
				+ ((hideKeyboard == null) ? 0 : hideKeyboard.hashCode());
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
		ReplyKeyboardHide other = (ReplyKeyboardHide) obj;
		if (hideKeyboard == null) {
			if (other.hideKeyboard != null)
				return false;
		} else if (!hideKeyboard.equals(other.hideKeyboard))
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
		return "ReplyKeyboardHide [hideKeyboard=" + hideKeyboard
				+ ", selective=" + selective + "]";
	}

}
