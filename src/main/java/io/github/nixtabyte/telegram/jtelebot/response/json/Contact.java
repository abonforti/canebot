
/**
 *
 * Copyright (C) 2015 Roberto Dominguez Estrada and Juan Carlos Sedano Salas
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 */
package io.github.nixtabyte.telegram.jtelebot.response.json;

import org.codehaus.jackson.annotate.JsonProperty;
/**
*
* Contact class
*
* @since 0.0.1
*/
public class Contact {

	/**
	 * Contact's phone number
	 * */
	@JsonProperty("phone_number")
	private String phoneNumber;

	/**
	 * Contact's first name
	 * */
	@JsonProperty("first_name")
	private String firstName;

	/**
	 * Optional. Contact's last name
	 * */
	@JsonProperty("last_name")
	private String lastName;

	/**
	 * Optional. Contact's user identifier in Telegram
	 * */
	@JsonProperty("user_id")
	private String userId;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * <p>Getter for the field <code>phoneNumber</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * <p>Setter for the field <code>phoneNumber</code>.</p>
	 *
	 * @param phoneNumber a {@link java.lang.String} object.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * <p>Getter for the field <code>firstName</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * <p>Setter for the field <code>firstName</code>.</p>
	 *
	 * @param firstName a {@link java.lang.String} object.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * <p>Getter for the field <code>lastName</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * <p>Setter for the field <code>lastName</code>.</p>
	 *
	 * @param lastName a {@link java.lang.String} object.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * <p>Getter for the field <code>userId</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
	}

/**
 * <p>Setter for the field <code>userId</code>.</p>
 *
 * @param userId a {@link java.lang.String} object.
 */
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		/** {@inheritDoc} */
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		/** {@inheritDoc} */
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contact [phoneNumber=" + phoneNumber + ", firstName="
				+ firstName + ", lastName=" + lastName + ", userId=" + userId
				/** {@inheritDoc} */
				+ "]";
	}
}
