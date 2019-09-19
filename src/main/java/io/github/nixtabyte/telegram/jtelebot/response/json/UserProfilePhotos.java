
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
* UserProfilePhotos
*
* @since 0.0.1
*/
public class UserProfilePhotos{

	/**
	 * Total number of profile pictures the target user has
	 * */
	@JsonProperty("total_count")
	private Integer totalCount;

	/**
	 * Requested profile pictures (in up to 4 sizes each)
	 * */
	@JsonProperty("photos")
	private PhotoSize[][] photos;
	/**
	 * <p>Getter for the field <code>totalCount</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getTotalCount() {
		return totalCount;

	}
	/**
	 * <p>Setter for the field <code>totalCount</code>.</p>
	 *
	 * @param totalCount a {@link java.lang.Integer} object.
	 */
	public void setTotalCount(final Integer totalCount) {
		this.totalCount = totalCount;

	}
	/**
	 * <p>Getter for the field <code>photos</code>.</p>
	 *
	 * @return an array of {@link io.github.nixtabyte.telegram.jtelebot.response.json.PhotoSize} objects.
	 */
	public PhotoSize[][] getPhotos() {
		return photos;

	}
	/**
	 * <p>Setter for the field <code>photos</code>.</p>
	 *
	 * @param photos an array of {@link io.github.nixtabyte.telegram.jtelebot.response.json.PhotoSize} objects.
	 */
	public void setPhotos(final PhotoSize[][] photos) {
		this.photos = photos;

	}
	
	

	@Override
	public int hashCode() {
		/** {@inheritDoc} */
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(photos);
		result = prime * result
				+ ((totalCount == null) ? 0 : totalCount.hashCode());
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
		UserProfilePhotos other = (UserProfilePhotos) obj;
		if (!Arrays.deepEquals(photos, other.photos))
			return false;
		if (totalCount == null) {
			if (other.totalCount != null)
				return false;
		} else if (!totalCount.equals(other.totalCount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		/** {@inheritDoc} */
		return "UserProfilePhotos [totalCount=" + totalCount + ", photos="
				+ photos + "]";
	}
}
