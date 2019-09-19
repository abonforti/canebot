
/**
 *
 * Copyright (C) 2015 Roberto Dominguez Estrada and Juan Carlos Sedano Salas
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 */
package io.github.nixtabyte.telegram.jtelebot.response.json;

import java.util.Arrays;

import org.codehaus.jackson.annotate.JsonProperty;
/**
*
* ReplyKeyboardMarkup
*
* @since 0.0.1
*/
public class ReplyKeyboardMarkup implements CustomReplyKeyboard{

	/**
	 * Array of button rows, each represented by an Array of Strings
	 * */
	@JsonProperty("keyboard")
	private String[][] keyboard;

	/**
	 * Optional. Requests clients to resize the keyboard vertically for optimal
	 * fit (e.g., make the keyboard smaller if there are just two rows of
	 * buttons). Defaults to false, in which case the custom keyboard is always
	 * of the same height as the app's standard keyboard.
	 * */
	@JsonProperty("resize_keyboard")
	private Boolean resizeKeyboard;

	/**
	 * Optional. Requests clients to hide the keyboard as soon as it's been
	 * used. Defaults to false.
	 * */
	@JsonProperty("one_time_keyboard")
	private Boolean oneTimeKeyboard;

	/**
	 * Optional. Use this parameter if you want to show the keyboard to specific
	 * users only. Targets: 1) users that are @mentioned in the text of the
	 * Message object; 2) if the bot's message is a reply (has
	 * reply_to_message_id), sender of the original message.
	 * 
	 * Example: A user requests to change the bot???????????????????????????s language, bot replies to the
	 * request with a keyboard to select the new language. Other users in the
	 * group don???????????????????????????t see the keyboard.
	 * */
	@JsonProperty("selective")
	private Boolean selective;

	public String[][] getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(final String[][] keyboard) {
		this.keyboard = keyboard;
	}

	public Boolean getResizeKeyboard() {
		return resizeKeyboard;
	}

	public void setResizeKeyboard(final Boolean resizeKeyboard) {
		this.resizeKeyboard = resizeKeyboard;
	}
/**
 * <p>Getter for the field <code>keyboard</code>.</p>
 *
 * @return an array of {@link java.lang.String} objects.
 */

	public Boolean getOneTimeKeyboard() {
		return oneTimeKeyboard;
	}
/**
 * <p>Setter for the field <code>keyboard</code>.</p>
 *
 * @param keyboard an array of {@link java.lang.String} objects.
 */

	public void setOneTimeKeyboard(final Boolean oneTimeKeyboard) {
		this.oneTimeKeyboard = oneTimeKeyboard;
	}
/**
 * <p>Getter for the field <code>resizeKeyboard</code>.</p>
 *
 * @return a {@link java.lang.Boolean} object.
 */

	public Boolean getSelective() {
		return selective;
	}
/**
 * <p>Setter for the field <code>resizeKeyboard</code>.</p>
 *
 * @param resizeKeyboard a {@link java.lang.Boolean} object.
 */

	public void setSelective(final Boolean selective) {
		this.selective = selective;
	}
/**
 * <p>Getter for the field <code>oneTimeKeyboard</code>.</p>
 *
 * @return a {@link java.lang.Boolean} object.
 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		/**
		 * <p>Setter for the field <code>oneTimeKeyboard</code>.</p>
		 *
		 * @param oneTimeKeyboard a {@link java.lang.Boolean} object.
		 */
		int result = 1;
		result = prime * result + Arrays.hashCode(keyboard);
		result = prime * result
				+ ((oneTimeKeyboard == null) ? 0 : oneTimeKeyboard.hashCode());
		/**
		 * <p>Getter for the field <code>selective</code>.</p>
		 *
		 * @return a {@link java.lang.Boolean} object.
		 */
		result = prime * result
				+ ((resizeKeyboard == null) ? 0 : resizeKeyboard.hashCode());
		result = prime * result
				+ ((selective == null) ? 0 : selective.hashCode());
		/**
		 * <p>Setter for the field <code>selective</code>.</p>
		 *
		 * @param selective a {@link java.lang.Boolean} object.
		 */
		return result;
	}

	@Override
	/** {@inheritDoc} */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReplyKeyboardMarkup other = (ReplyKeyboardMarkup) obj;
		if (!Arrays.deepEquals(keyboard, other.keyboard))
			return false;
		if (oneTimeKeyboard == null) {
			if (other.oneTimeKeyboard != null)
				return false;
		} else if (!oneTimeKeyboard.equals(other.oneTimeKeyboard))
			/** {@inheritDoc} */
			return false;
		if (resizeKeyboard == null) {
			if (other.resizeKeyboard != null)
				return false;
		} else if (!resizeKeyboard.equals(other.resizeKeyboard))
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
		return "ReplyKeyboardMarkup [keyboard=" + keyboard
				+ ", resizeKeyboard=" + resizeKeyboard + ", oneTimeKeyboard="
				+ oneTimeKeyboard + ", selective=" + selective + "]";
	}
}
