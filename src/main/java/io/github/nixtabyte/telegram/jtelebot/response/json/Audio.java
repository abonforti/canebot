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
 * 	This object represents an audio file (voice note).
 *
 * @since 0.0.1
 */
public class Audio{
	/**
	 * Unique identifier for this file
	 */
	@JsonProperty("file_id")
	private String fileId;
	/**
	 * Duration of the audio in seconds as defined by sender
	 */
	@JsonProperty("duration")
	private Integer duration;
	/**
	 *  Optional. MIME type of the file as defined by sender
	 */
	@JsonProperty("mime_type")
	private String mimeType;
	/**
	 * Optional. File size
	 */
	@JsonProperty("file_size")
	private Integer fileSize;
	/**
	 * <p>Getter for the field <code>fileId</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getFileId() {
		return fileId;
	}
	/**
	 * <p>Setter for the field <code>fileId</code>.</p>
	 *
	 * @param fileId a {@link java.lang.String} object.
	 */
	public void setFileId(final String fileId) {
		this.fileId = fileId;
	}
	/**
	 * <p>Getter for the field <code>duration</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getDuration() {
		return duration;
	}
	/**
	 * <p>Setter for the field <code>duration</code>.</p>
	 *
	 * @param duration a {@link java.lang.Integer} object.
	 */
	public void setDuration(final Integer duration) {
		this.duration = duration;
	}
	/**
	 * <p>Getter for the field <code>mimeType</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getMimeType() {
		return mimeType;
	}
	/**
	 * <p>Setter for the field <code>mimeType</code>.</p>
	 *
	 * @param mimeType a {@link java.lang.String} object.
	 */
	public void setMimeType(final String mimeType) {
		this.mimeType = mimeType;
	}
	/**
	 * <p>Getter for the field <code>fileSize</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getFileSize() {
		return fileSize;
	}
	/**
	 * <p>Setter for the field <code>fileSize</code>.</p>
	 *
	 * @param fileSize a {@link java.lang.Integer} object.
	 */
	public void setFileSize(final Integer fileSize) {
		this.fileSize = fileSize;
	}
	
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
		result = prime * result
				+ ((fileSize == null) ? 0 : fileSize.hashCode());
		result = prime * result
				+ ((mimeType == null) ? 0 : mimeType.hashCode());
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
		final Audio other = (Audio) obj;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		if (fileSize == null) {
			if (other.fileSize != null)
				return false;
		} else if (!fileSize.equals(other.fileSize))
			return false;
		if (mimeType == null) {
			if (other.mimeType != null)
				return false;
		} else if (!mimeType.equals(other.mimeType))
			return false;
		return true;
	}
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "Audio [fileId=" + fileId + ", duration=" + duration
				+ ", mimeType=" + mimeType + ", fileSize=" + fileSize + "]";
	}

		
}
