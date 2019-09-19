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
 * This object represents a video file.
 *
 * @since 0.0.1
 */
public class Video {
	/**
	 * Unique identifier for this file
	 */
	@JsonProperty("file_id")
	private String fileId;
	/**
	 * Video width as defined by sender
	 */
	@JsonProperty("width")
	private Integer width;
	/**
	 * Video height as defined by sender
	 */
	@JsonProperty("height")
	private Integer height;
	/**
	 * Duration of the video in seconds as defined by sender
	 */
	@JsonProperty("duration")
	private Integer duration;
	/**
	 * Video thumbnail
	 */
	@JsonProperty("thumb")
	private PhotoSize thumb;
	/**
	 * Optional. Mime type of a file as defined by sender
	 */
	@JsonProperty("mime_type")
	private String mimeType;
	/**
	 * Optional. File size
	 */
	@JsonProperty("file_size")
	private Integer fileSize;
	/**
	 * Optional. Text description of the video (usually empty)
	 */
	@JsonProperty("caption")
	private String caption;
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
	 * <p>Getter for the field <code>width</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getWidth() {
		return width;
	}
	/**
	 * <p>Setter for the field <code>width</code>.</p>
	 *
	 * @param width a {@link java.lang.Integer} object.
	 */
	public void setWidth(final Integer width) {
		this.width = width;
	}
	/**
	 * <p>Getter for the field <code>height</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getHeight() {
		return height;
	}
	/**
	 * <p>Setter for the field <code>height</code>.</p>
	 *
	 * @param height a {@link java.lang.Integer} object.
	 */
	public void setHeight(final Integer height) {
		this.height = height;
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
	 * <p>Getter for the field <code>thumb</code>.</p>
	 *
	 * @return a {@link io.github.nixtabyte.telegram.jtelebot.response.json.PhotoSize} object.
	 */
	public PhotoSize getThumb() {
		return thumb;
	}
	/**
	 * <p>Setter for the field <code>thumb</code>.</p>
	 *
	 * @param thumbnail a {@link io.github.nixtabyte.telegram.jtelebot.response.json.PhotoSize} object.
	 */
	public void setThumb(final PhotoSize thumbnail) {
		this.thumb = thumbnail;
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
	/**
	 * <p>Getter for the field <code>caption</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getCaption() {
		return caption;
	}
	/**
	 * <p>Setter for the field <code>caption</code>.</p>
	 *
	 * @param caption a {@link java.lang.String} object.
	 */
	public void setCaption(final String caption) {
		this.caption = caption;
	}
	
	
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caption == null) ? 0 : caption.hashCode());
		result = prime * result
				+ ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
		result = prime * result
				+ ((fileSize == null) ? 0 : fileSize.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result
				+ ((mimeType == null) ? 0 : mimeType.hashCode());
		result = prime * result
				+ ((thumb == null) ? 0 : thumb.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
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
		Video other = (Video) obj;
		if (caption == null) {
			if (other.caption != null)
				return false;
		} else if (!caption.equals(other.caption))
			return false;
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
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (mimeType == null) {
			if (other.mimeType != null)
				return false;
		} else if (!mimeType.equals(other.mimeType))
			return false;
		if (thumb == null) {
			if (other.thumb != null)
				return false;
		} else if (!thumb.equals(other.thumb))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		return true;
	}
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "Video [fileId=" + fileId + ", width=" + width + ", height="
				+ height + ", duration=" + duration + ", thumbnail="
				+ thumb + ", mimeType=" + mimeType + ", fileSize="
				+ fileSize + ", caption=" + caption + "]";
	}
	
	
}


