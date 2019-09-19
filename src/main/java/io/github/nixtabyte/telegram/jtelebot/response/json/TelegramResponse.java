
/**
 *
 * Copyright (C) 2015 Roberto Dominguez Estrada and Juan Carlos Sedano Salas
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 */
package io.github.nixtabyte.telegram.jtelebot.response.json;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
/**
*
* The response from Telegram
*
* @since 0.0.1
*/
public class TelegramResponse<T> {

	/**
	 * The response contains a JSON object, which always has a Boolean field
	 * ok and may have an optional String field description with a
	 * human-readable description of the result. If ok equals true, the
	 * request was successful and the result of the query can be found in the
	 * result field.
	 * 
	 * In case of an unsuccessful request, ok equals false and the error is
	 * explained in the description. An Integer error_code field is also
	 * returned, but its contents are subject to change in the future
	 */
	@JsonProperty("ok")
	private Boolean successful;

	@JsonProperty("description")
	private String description;

	@JsonProperty("error_code")
	private Integer errorCode;
	
	@JsonProperty("result")
	private List<T> result;

	/**
	 * <p>isSuccessful.</p>
	 *
	 * @return a {@link java.lang.Boolean} object.
	 */
	public Boolean isSuccessful() {
		return successful;
	}

	/**
	 * <p>Setter for the field <code>successful</code>.</p>
	 *
	 * @param successful a {@link java.lang.Boolean} object.
	 */
	public void setSuccessful(final Boolean successful) {
		this.successful = successful;
	}

	/**
	 * <p>Getter for the field <code>description</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <p>Setter for the field <code>description</code>.</p>
	 *
	 * @param description a {@link java.lang.String} object.
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * <p>Getter for the field <code>errorCode</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getErrorCode() {
		return errorCode;
	}

	/**
	 * <p>Setter for the field <code>errorCode</code>.</p>
	 *
	 * @param errorCode a {@link java.lang.Integer} object.
	 */
	public void setErrorCode(final Integer errorCode) {
		this.errorCode = errorCode;
	}


	/**
	 * <p>Getter for the field <code>result</code>.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * <p>Setter for the field <code>result</code>.</p>
	 *
	 * @param result a {@link java.util.List} object.
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}


	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((errorCode == null) ? 0 : errorCode.hashCode());
		result = prime * result
				+ ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result
				+ ((successful == null) ? 0 : successful.hashCode());
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
		TelegramResponse other = (TelegramResponse) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (errorCode == null) {
			if (other.errorCode != null)
				return false;
		} else if (!errorCode.equals(other.errorCode))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (successful == null) {
			if (other.successful != null)
				return false;
		} else if (!successful.equals(other.successful))
			return false;
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "TelegramResponse [successful=" + successful + ", description="
				+ description + ", errorCode=" + errorCode + ", result="
				+ result + "]";
	}



	
}
