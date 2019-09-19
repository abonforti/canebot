/**
 * 
 * Copyright (C) 2015 Roberto Dominguez Estrada and Juan Carlos Sedano Salas
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 */
package io.github.nixtabyte.telegram.jtelebot.response.json;

import io.github.nixtabyte.telegram.jtelebot.mapper.json.MapperHandler;

import java.util.Arrays;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * This object represents a message.
 *
 * @since 0.0.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
	/**
	 * Unique message identifier
	 */
	@JsonProperty("message_id")
	private Long id;
	/**
	 * Sender
	 */
	@JsonProperty("from")
	private User fromUser;
	/**
	 * Date the message was sent in Unix time
	 */
	@JsonProperty("date")
	private Long unixTimeDate;
	/**
	 * Conversation the message belongs to ????????? user in case of a private message,
	 * GroupChat in case of a group
	 */
	@JsonProperty("chat")
	private Chat chat;
	/**
	 * Optional. For forwarded messages, sender of the original message
	 */
	@JsonProperty("forward_from")
	private User forwardFromUser;
	/**
	 * Optional. For forwarded messages, date the original message was sent in
	 * Unix time
	 */
	@JsonProperty("forward_date")
	private Long forwardDate;
	/**
	 * Optional. For replies, the original message. Note that the Message object
	 * in this field will not contain further reply_to_message fields even if it
	 * itself is a reply.
	 */
	@JsonProperty("reply_to_message")
	private Message replyToMessage;
	/**
	 * Optional. For text messages, the actual UTF-8 text of the message
	 */
	@JsonProperty("text")
	private String text;

	/**
	 * Optional. Message is an audio file, information about the file
	 * */
	@JsonProperty("audio")
	private Audio audio;

	/**
	 * Optional. Message is a general file, information about the file
	 * */
	@JsonProperty("document")
	private Document document;

	/**
	 * Optional. Message is a photo, available sizes of the photo
	 * */
	@JsonProperty("photo")
	private PhotoSize[] photo;

	/**
	 * Optional. Message is a sticker, information about the sticker
	 * */
	@JsonProperty("sticker")
	private Sticker sticker;

	/**
	 * Optional. Message is a video, information about the video
	 * */
	@JsonProperty("video")
	private Video video;

	/**
	 * Optional. Message is a shared contact, information about the contact
	 * */
	@JsonProperty("contact")
	private Contact contact;

	/**
	 * Optional. Message is a shared location, information about the location
	 * */
	@JsonProperty("location")
	private Location location;

	/**
	 * Optional. A new member was added to the group, information about them
	 * (this member may be bot itself)
	 */
	@JsonProperty("new_chat_participant")
	private User newChatParticipantUser;
	/**
	 * Optional. A member was removed from the group, information about them
	 * (this member may be bot itself)
	 */
	@JsonProperty("left_chat_participant")
	private User leftChatParticipantUser;
	/**
	 * Optional. A group title was changed to this value
	 */
	@JsonProperty("new_chat_title")
	private String newChatTitle;

	/**
	 * new_chat_photo Array of PhotoSize Optional. A group photo was change to
	 * this value
	 */
	@JsonProperty("new_chat_photo")
	private PhotoSize[] newChatPhoto;

	/**
	 * Optional. Informs that the group photo was deleted
	 */
	@JsonProperty("delete_chat_photo")
	private Boolean deleteChatPhoto;
	/**
	 * Optional. Informs that the group has been created
	 */
	@JsonProperty("group_chat_created")
	private Boolean groupChatCreated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public User getFromUser() {
		return fromUser;
	}


	public void setFromUser(final User fromUser) {
		this.fromUser = fromUser;
	}


	public Long getUnixTimeDate() {
		return unixTimeDate;
	}


	public void setUnixTimeDate(final Long unixTimeDate) {
		this.unixTimeDate = unixTimeDate;
	}


	public Chat getChat() {
		return chat;
	}

	@JsonProperty
	public void setChat(final JsonNode chat) {
		if (chat != null
				&& (chat.toString() != null && chat.toString().length() != 0)) {
			if (chat.toString().contains("title")) {// must be groupchat
				this.chat = MapperHandler.INSTANCE.getObjectMapper()
						.convertValue(chat, GroupChat.class);
			
			} else if (chat.toString().contains("first_name")) {// must be user
				this.chat = MapperHandler.INSTANCE.getObjectMapper()
						.convertValue(chat, User.class);
			}
		}
	}

	public void setChat(final Chat chat) {
		this.chat = chat;
	}

	public User getForwardFromUser() {
		return forwardFromUser;
	}

	public void setForwardFromUser(final User forwardFromUser) {
		this.forwardFromUser = forwardFromUser;
	}

	public Long getForwardDate() {
		return forwardDate;
	}

	public void setForwardDate(final Long forwardDate) {
		this.forwardDate = forwardDate;
	}

	public Message getReplyToMessage() {
		return replyToMessage;
	}

	public void setReplyToMessage(final Message replyToMessage) {
		this.replyToMessage = replyToMessage;
	}

	public String getText() {
		return text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public Audio getAudio() {
		return audio;
	}

	public void setAudio(final Audio audio) {
		this.audio = audio;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(final Document document) {
		this.document = document;
	}
	
	public PhotoSize[] getPhoto() {
		return photo;
	}

	public void setPhoto(final PhotoSize[] photo) {
		this.photo = photo;
	}


	public Sticker getSticker() {
		return sticker;
	}


	public void setSticker(final Sticker sticker) {
		this.sticker = sticker;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(final Video video) {
		this.video = video;
	}


	public Contact getContact() {
		return contact;
	}


	public void setContact(final Contact contact) {
		this.contact = contact;
	}


	public Location getLocation() {
		return location;
	}


	public void setLocation(final Location location) {
		this.location = location;
	}

	public User getNewChatParticipantUser() {
		return newChatParticipantUser;
	}


	public void setNewChatParticipantUser(final User newChatParticipantUser) {
		this.newChatParticipantUser = newChatParticipantUser;
	}


	public User getLeftChatParticipantUser() {
		return leftChatParticipantUser;
	}


	public void setLeftChatParticipantUser(final User leftChatParticipantUser) {
		this.leftChatParticipantUser = leftChatParticipantUser;
	}


	public String getNewChatTitle() {
		return newChatTitle;
	}


	public void setNewChatTitle(final String newChatTitle) {
		this.newChatTitle = newChatTitle;
	}


	public PhotoSize[] getNewChatPhoto() {
		return newChatPhoto;
	}

	public void setNewChatPhoto(final PhotoSize[] newChatPhoto) {
		this.newChatPhoto = newChatPhoto;
	}

	public Boolean getDeleteChatPhoto() {
		return deleteChatPhoto;
	}

	public void setDeleteChatPhoto(final Boolean deleteChatPhoto) {
		this.deleteChatPhoto = deleteChatPhoto;
	}


	public Boolean getGroupChatCreated() {
		return groupChatCreated;
	}

	public void setGroupChatCreated(final Boolean groupChatCreated) {
		this.groupChatCreated = groupChatCreated;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
	
		int result = 1;
		result = prime * result + ((audio == null) ? 0 : audio.hashCode());
		result = prime * result + ((chat == null) ? 0 : chat.hashCode());
		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
		/** {@inheritDoc} */
		result = prime * result
				+ ((deleteChatPhoto == null) ? 0 : deleteChatPhoto.hashCode());
		result = prime * result
				+ ((document == null) ? 0 : document.hashCode());
		result = prime * result
				+ ((forwardDate == null) ? 0 : forwardDate.hashCode());
		result = prime * result
				+ ((forwardFromUser == null) ? 0 : forwardFromUser.hashCode());
		result = prime * result
				+ ((fromUser == null) ? 0 : fromUser.hashCode());
		result = prime
				* result
				+ ((groupChatCreated == null) ? 0 : groupChatCreated.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((leftChatParticipantUser == null) ? 0
						: leftChatParticipantUser.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime
				* result
				+ ((newChatParticipantUser == null) ? 0
						: newChatParticipantUser.hashCode());
		result = prime * result + Arrays.hashCode(newChatPhoto);
		result = prime * result
				+ ((newChatTitle == null) ? 0 : newChatTitle.hashCode());
		result = prime * result + Arrays.hashCode(photo);
		result = prime * result
				+ ((replyToMessage == null) ? 0 : replyToMessage.hashCode());
		result = prime * result + ((sticker == null) ? 0 : sticker.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result
				+ ((unixTimeDate == null) ? 0 : unixTimeDate.hashCode());
		result = prime * result + ((video == null) ? 0 : video.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			/** {@inheritDoc} */
			return false;
		Message other = (Message) obj;
		if (audio == null) {
			if (other.audio != null)
				return false;
		} else if (!audio.equals(other.audio))
			return false;
		if (chat == null) {
			if (other.chat != null)
				return false;
		} else if (!chat.equals(other.chat))
			return false;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.equals(other.contact))
			return false;
		if (deleteChatPhoto == null) {
			if (other.deleteChatPhoto != null)
				return false;
		} else if (!deleteChatPhoto.equals(other.deleteChatPhoto))
			return false;
		if (document == null) {
			if (other.document != null)
				return false;
		} else if (!document.equals(other.document))
			return false;
		if (forwardDate == null) {
			if (other.forwardDate != null)
				return false;
		} else if (!forwardDate.equals(other.forwardDate))
			return false;
		if (forwardFromUser == null) {
			if (other.forwardFromUser != null)
				return false;
		} else if (!forwardFromUser.equals(other.forwardFromUser))
			return false;
		if (fromUser == null) {
			if (other.fromUser != null)
				return false;
		} else if (!fromUser.equals(other.fromUser))
			return false;
		if (groupChatCreated == null) {
			if (other.groupChatCreated != null)
				return false;
		} else if (!groupChatCreated.equals(other.groupChatCreated))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (leftChatParticipantUser == null) {
			if (other.leftChatParticipantUser != null)
				return false;
		} else if (!leftChatParticipantUser
				.equals(other.leftChatParticipantUser))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (newChatParticipantUser == null) {
			if (other.newChatParticipantUser != null)
				return false;
		} else if (!newChatParticipantUser.equals(other.newChatParticipantUser))
			return false;
		if (!Arrays.equals(newChatPhoto, other.newChatPhoto))
			return false;
		if (newChatTitle == null) {
			if (other.newChatTitle != null)
				return false;
		} else if (!newChatTitle.equals(other.newChatTitle))
			return false;
		if (!Arrays.equals(photo, other.photo))
			return false;
		if (replyToMessage == null) {
			if (other.replyToMessage != null)
				return false;
		} else if (!replyToMessage.equals(other.replyToMessage))
			return false;
		if (sticker == null) {
			if (other.sticker != null)
				return false;
		} else if (!sticker.equals(other.sticker))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (unixTimeDate == null) {
			if (other.unixTimeDate != null)
				return false;
		} else if (!unixTimeDate.equals(other.unixTimeDate))
			return false;
		if (video == null) {
			if (other.video != null)
				return false;
		} else if (!video.equals(other.video))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", fromUser=" + fromUser
				+ ", unixTimeDate=" + unixTimeDate + ", chat=" + chat
				+ ", forwardFromUser=" + forwardFromUser + ", forwardDate="
				+ forwardDate + ", replyToMessage=" + replyToMessage
				+ ", text=" + text + ", audio=" + audio + ", document="
				/** {@inheritDoc} */
				+ document + ", photo=" + Arrays.toString(photo) + ", sticker="
				+ sticker + ", video=" + video + ", contact=" + contact
				+ ", location=" + location + ", newChatParticipantUser="
				+ newChatParticipantUser + ", leftChatParticipantUser="
				+ leftChatParticipantUser + ", newChatTitle=" + newChatTitle
				+ ", newChatPhoto=" + Arrays.toString(newChatPhoto)
				+ ", deleteChatPhoto=" + deleteChatPhoto
				+ ", groupChatCreated=" + groupChatCreated + "]";
	}

	
}
