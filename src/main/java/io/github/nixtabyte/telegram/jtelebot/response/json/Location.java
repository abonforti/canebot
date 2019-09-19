
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
* Location
*
* @since 0.0.1
*/
public class Location{

	/**
	 * Longitude as defined by sender
	 * */
	@JsonProperty("longitude")
	private Double longitude;

	/**
	 * Latitude as defined by sender
	 * */
	@JsonProperty("latitude")
	private Double latitude;
	/**
	 * <p>Getter for the field <code>longitude</code>.</p>
	 *
	 * @return a {@link java.lang.Double} object.
	 */
	public Double getLongitude() {
		return longitude;

	}
	/**
	 * <p>Setter for the field <code>longitude</code>.</p>
	 *
	 * @param longitude a {@link java.lang.Double} object.
	 */
	public void setLongitude(final Double longitude) {
		this.longitude = longitude;

	}
	/**
	 * <p>Getter for the field <code>latitude</code>.</p>
	 *
	 * @return a {@link java.lang.Double} object.
	 */
	public Double getLatitude() {
		return latitude;

	}
	/**
	 * <p>Setter for the field <code>latitude</code>.</p>
	 *
	 * @param latitude a {@link java.lang.Double} object.
	 */
	public void setLatitude(final Double latitude) {
		this.latitude = latitude;

	}
	
	

	@Override
	public int hashCode() {
		/** {@inheritDoc} */
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((longitude == null) ? 0 : longitude.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		/** {@inheritDoc} */
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		/** {@inheritDoc} */
		return "Location [longitude=" + longitude + ", latitude=" + latitude
				+ "]";
	}
}
