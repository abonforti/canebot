/**
 * 
 * Copyright (C) 2015 Roberto Dominguez Estrada and Juan Carlos Sedano Salas
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 */
package io.github.nixtabyte.telegram.jtelebot.client;
/**
 *
 * Type of action to broadcast.
 * Choose one, depending on what the user is about to receive:
 *
 * @since 0.0.1
 */
public enum BroadcastActionType {

	  TYPING("typing"), //FOR TEXT MESSAGES, 
	  UPLOAD_PHOTO("upload_photo"),// FOR PHOTOS, 
	  RECORD_VIDEO("record_video"),
	  UPLOAD_VIDEO("upload_video"),// FOR VIDEOS, 
	  RECORD_AUDIO("record_audio"),
	  UPLOAD_AUDIO("upload_audio"),// FOR AUDIO FILES, 
	  UPLOAD_DOCUMENT("upload_document"),// FOR GENERAL FILES, 
	  FIND_LOCATION("find_location");// FOR LOCATION DATA.
	  
	  private String actionToBroadcast;

	  private BroadcastActionType(final String actionToBroadcast){
		  this.actionToBroadcast = actionToBroadcast;
	  }

	/**
	 * <p>Getter for the field <code>actionToBroadcast</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getActionToBroadcast() {
		return actionToBroadcast;
	}
	  
	  
}
