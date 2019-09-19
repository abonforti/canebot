/**
 * 
 * Copyright (C) 2015 Roberto Dominguez Estrada and Juan Carlos Sedano Salas
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 */
package io.github.nixtabyte.telegram.jtelebot.response.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * This object represents a sticker.
 *
 * @since 0.0.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sticker {
    /**
     * Unique identifier for this file
     */
    @JsonProperty("file_id")
    private String fileId;
    /**
     * Sticker width
     */
    @JsonProperty("width")
    private Integer width;
    /**
     * Sticker height
     */
    @JsonProperty("height")
    private Integer height;
    /**
     * Sticker thumbnail in .webp or .jpg format
     */
    @JsonProperty("thumb")
    private PhotoSize thumb;
    /**
     * Optional. File size
     */
    @JsonProperty("file_size")
    private Integer fileSize;

    /**
     * <p>
     * Getter for the field <code>fileId</code>.
     * </p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * <p>
     * Setter for the field <code>fileId</code>.
     * </p>
     *
     * @param fileId
     *            a {@link java.lang.String} object.
     */
    public void setFileId(final String fileId) {
        this.fileId = fileId;
    }

    /**
     * <p>
     * Getter for the field <code>width</code>.
     * </p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * <p>
     * Setter for the field <code>width</code>.
     * </p>
     *
     * @param width
     *            a {@link java.lang.Integer} object.
     */
    public void setWidth(final Integer width) {
        this.width = width;
    }

    /**
     * <p>
     * Getter for the field <code>height</code>.
     * </p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * <p>
     * Setter for the field <code>height</code>.
     * </p>
     *
     * @param height
     *            a {@link java.lang.Integer} object.
     */
    public void setHeight(final Integer height) {
        this.height = height;
    }

    /**
     * <p>
     * Getter for the field <code>thumb</code>.
     * </p>
     *
     * @return a {@link io.github.nixtabyte.telegram.jtelebot.response.json.PhotoSize} object.
     */
    public PhotoSize getThumb() {
        return thumb;
    }

    /**
     * <p>
     * Setter for the field <code>thumb</code>.
     * </p>
     *
     * @param thumb
     *            a {@link io.github.nixtabyte.telegram.jtelebot.response.json.PhotoSize} object.
     */
    public void setThumb(final PhotoSize thumb) {
        this.thumb = thumb;
    }

    /**
     * <p>
     * Getter for the field <code>fileSize</code>.
     * </p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getFileSize() {
        return fileSize;
    }

    /**
     * <p>
     * Setter for the field <code>fileSize</code>.
     * </p>
     *
     * @param fileSize
     *            a {@link java.lang.Integer} object.
     */
    public void setFileSize(final Integer fileSize) {
        this.fileSize = fileSize;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
        result = prime * result + ((fileSize == null) ? 0 : fileSize.hashCode());
        result = prime * result + ((height == null) ? 0 : height.hashCode());
        result = prime * result + ((thumb == null) ? 0 : thumb.hashCode());
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
        Sticker other = (Sticker) obj;
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
        return "Sticker [fileId=" + fileId + ", width=" + width + ", height=" + height + ", thumb=" + thumb + ", fileSize=" + fileSize + "]";
    }

}
