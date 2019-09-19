/**
 * 
 * Copyright (C) 2015 Roberto Dominguez Estrada and Juan Carlos Sedano Salas
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 */
package io.github.nixtabyte.telegram.jtelebot.request.factory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import io.github.nixtabyte.telegram.jtelebot.client.BroadcastActionType;
import io.github.nixtabyte.telegram.jtelebot.client.RequestType;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.mapper.json.MapperHandler;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.response.json.CustomReplyKeyboard;

/**
 * 
 * This class is a Factory of TelegramRequest objects it allows to easily create a request, for more information on the
 * methods that the Telegram Bots api support please check See
 * <a href="https://core.telegram.org/bots/api#available-methods">https://core.telegram.org/bots/api#available-methods
 * </a>
 * 
 * @since 0.0.1
 */
public final class TelegramRequestFactory {
    /**
     * TODO: Validation class...
     * 
     */

    private TelegramRequestFactory() {
    }

    /**
     * Creates a request to use the getMe method of the Telegram Bot api
     * 
     * @return A TelegramRequest prepared to consume the getMe method
     * @see TelegramRequest
     */
    public static TelegramRequest createGetMeRequest() {
        return new TelegramRequest(RequestType.GET_ME, new ArrayList<BasicNameValuePair>());
    }

    /**
     * 
     * Creates a Telegram request ready to use to consume the sendMessage method of the Telegram bot api
     * 
     * @param chatId
     *            Unique identifier for the message recipient ��� User or GroupChat id
     * @param text
     *            Text of the message to be sent
     * @param disableWebPagePreview
     *            Optional - Disables link previews for links in this message
     * @param replyToMessageId
     *            Optional - If the message is a reply, ID of the original message
     * @param customReplyKeyboard
     *            Optional - Additional interface options. A JSON-serialized object for a custom reply keyboard,
     *            instructions to hide keyboard or to force a reply from the user.
     * @return A TelegramRequest prepared to consume the sendMessage method
     * @see TelegramRequest
     * @throws org.codehaus.jackson.JsonGenerationException
     *             if any.
     * @throws org.codehaus.jackson.map.JsonMappingException
     *             if any.
     * @throws java.io.IOException
     *             if any.
     * @throws JsonParsingException
     */
    public static TelegramRequest createSendMessageRequest(final long chatId, final String text, final boolean disableWebPagePreview, final Long replyToMessageId,
            final CustomReplyKeyboard customReplyKeyboard) throws JsonParsingException {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("chat_id", String.valueOf(chatId)));
        basicNameValuePair.add(new BasicNameValuePair("text", text));
        basicNameValuePair.add(new BasicNameValuePair("disable_web_page_preview", String.valueOf(disableWebPagePreview)));
        TelegramRequestFactory.addIfNotNull("reply_to_message_id", replyToMessageId, basicNameValuePair);
        TelegramRequestFactory.addIfNotNull("reply_markup", customReplyKeyboard, basicNameValuePair);
        return new TelegramRequest(RequestType.SEND_MESSAGE, basicNameValuePair);
    }

    /**
     * 
     * Creates a request to use the getMe method of the Telegram Bot api
     * 
     * @param chatId
     *            Unique identifier for the message recipient ��� User or GroupChat id
     * @param fromChatId
     *            Unique identifier for the chat where the original message was sent ��� User or GroupChat id
     * @param messageId
     *            Unique message identifier
     * @return A request to use the getMe method of the Telegram Bot api
     * @see TelegramRequest
     */
    public static TelegramRequest createForwardMessageRequest(final long chatId, final int fromChatId, final int messageId) {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("chat_id", String.valueOf(chatId)));
        basicNameValuePair.add(new BasicNameValuePair("from_chat_id", String.valueOf(fromChatId)));
        basicNameValuePair.add(new BasicNameValuePair("message_id", String.valueOf(messageId)));
        return new TelegramRequest(RequestType.FORWARD_MESSAGE, basicNameValuePair);
    }

    /**
     * 
     * Creates a request to use the sendPhoto method of the Telegram Bot api
     * 
     * @param chatId
     *            Unique identifier for the message recipient ��� User or GroupChat id
     * @param inputFile
     *            Photo to upload
     * @param caption
     *            Optional - Photo caption (may also be used when resending photos by file_id).
     * @param replyToMessageId
     *            Optional - If the message is a reply, ID of the original message
     * @param customReplyKeyboard
     *            Optional - Additional interface options. A JSON-serialized object for a custom reply keyboard,
     *            instructions to hide keyboard or to force a reply from the user.
     * @return A request to use the sendPhoto method of the Telegram Bot api
     * @see TelegramRequest
     * @throws org.codehaus.jackson.JsonGenerationException
     *             if any.
     * @throws org.codehaus.jackson.map.JsonMappingException
     *             if any.
     * @throws java.io.IOException
     *             if any.
     * @throws JsonParsingException
     */
    public static TelegramRequest createSendPhotoRequest(final long chatId, final File inputFile, final String caption, final Long replyToMessageId,
            final CustomReplyKeyboard customReplyKeyboard) throws JsonParsingException {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("chat_id", String.valueOf(chatId)));
        TelegramRequestFactory.addIfNotNull("caption", caption, basicNameValuePair);
        TelegramRequestFactory.addIfNotNull("reply_to_message_id", replyToMessageId, basicNameValuePair);
        TelegramRequestFactory.addIfNotNull("reply_markup", customReplyKeyboard, basicNameValuePair);
        return new TelegramRequest(RequestType.SEND_PHOTO, basicNameValuePair, inputFile, "photo");
    }

    /**
     * 
     * Creates a request to use the sendPhoto method of the Telegram Bot api
     * 
     * @param chatId
     *            Unique identifier for the message recipient ��� User or GroupChat id
     * @param photoId
     *            Re-send a photo that is already on the Telegram servers
     * @param caption
     *            Optional - Photo caption (may also be used when resending photos by file_id).
     * @param replyToMessageId
     *            Optional - If the message is a reply, ID of the original message
     * @param customReplyKeyboard
     *            Optional - Additional interface options. A JSON-serialized object for a custom reply keyboard,
     *            instructions to hide keyboard or to force a reply from the user.
     * @return A request to use the sendPhoto method of the Telegram Bot api
     * @see TelegramRequest
     * @throws org.codehaus.jackson.JsonGenerationException
     *             if any.
     * @throws org.codehaus.jackson.map.JsonMappingException
     *             if any.
     * @throws java.io.IOException
     *             if any.
     * @throws JsonParsingException
     */
    public static TelegramRequest createSendPhotoRequest(final long chatId, final String photoId, final String caption, final Long replyToMessageId,
            final CustomReplyKeyboard customReplyKeyboard) throws JsonParsingException {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("chat_id", String.valueOf(chatId)));
        basicNameValuePair.add(new BasicNameValuePair("photo", photoId));
        TelegramRequestFactory.addIfNotNull("caption", caption, basicNameValuePair);
        TelegramRequestFactory.addIfNotNull("reply_to_message_id", replyToMessageId, basicNameValuePair);
        TelegramRequestFactory.addIfNotNull("reply_markup", customReplyKeyboard, basicNameValuePair);
        return new TelegramRequest(RequestType.SEND_PHOTO, basicNameValuePair);
    }

    /**
     * 
     * Creates a request to use the sendAudio method of the Telegram Bot api
     * 
     * @param chatId
     *            Unique identifier for the message recipient ��� User or GroupChat id
     * @param inputFile
     *            Audio file to send
     * @param replyToMessageId
     *            Optional - If the message is a reply, ID of the original message
     * @param customReplyKeyboard
     *            Optional - Additional interface options. A JSON-serialized object for a custom reply keyboard,
     *            instructions to hide keyboard or to force a reply from the user.
     * @return A request to use the sendAudio method of the Telegram Bot api
     * @see TelegramRequest
     * @throws org.codehaus.jackson.JsonGenerationException
     *             if any.
     * @throws org.codehaus.jackson.map.JsonMappingException
     *             if any.
     * @throws java.io.IOException
     *             if any.
     * @throws JsonParsingException
     */
    public static TelegramRequest createSendAudioRequest(final long chatId, final File inputFile, final Long replyToMessageId, final CustomReplyKeyboard customReplyKeyboard)
            throws JsonParsingException {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("chat_id", String.valueOf(chatId)));
        TelegramRequestFactory.addIfNotNull("reply_to_message_id", replyToMessageId, basicNameValuePair);
        TelegramRequestFactory.addIfNotNull("reply_markup", customReplyKeyboard, basicNameValuePair);
        return new TelegramRequest(RequestType.SEND_AUDIO, basicNameValuePair, inputFile, "audio");
    }

    /**
     * 
     * Creates a request to use the sendAudio method of the Telegram Bot api
     * 
     * @param chatId
     *            Unique identifier for the message recipient ��� User or GroupChat id
     * @param audioId
     *            Re-send an audio that is already on the Telegram servers
     * @param replyToMessageId
     *            Optional - If the message is a reply, ID of the original message
     * @param customReplyKeyboard
     *            Optional - Additional interface options. A JSON-serialized object for a custom reply keyboard,
     *            instructions to hide keyboard or to force a reply from the user.
     * @return A request to use the sendAudio method of the Telegram Bot api
     * @see TelegramRequest
     * @throws org.codehaus.jackson.JsonGenerationException
     *             if any.
     * @throws org.codehaus.jackson.map.JsonMappingException
     *             if any.
     * @throws java.io.IOException
     *             if any.
     * @throws JsonParsingException
     */
    public static TelegramRequest createSendAudioRequest(final long chatId, final String audioId, final Long replyToMessageId, final CustomReplyKeyboard customReplyKeyboard)
            throws JsonParsingException {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("chat_id", String.valueOf(chatId)));
        basicNameValuePair.add(new BasicNameValuePair("audio", audioId));
        TelegramRequestFactory.addIfNotNull("reply_to_message_id", replyToMessageId, basicNameValuePair);
        TelegramRequestFactory.addIfNotNull("reply_markup", customReplyKeyboard, basicNameValuePair);
        return new TelegramRequest(RequestType.SEND_AUDIO, basicNameValuePair);
    }

    /**
     * Creates a telegram request ready to consume the sendDocument request method of the Telegram Bot Api
     * 
     * @param chatId
     *            Unique identifier for the message recipient ��� User or GroupChat id
     * @param inputFile
     *            File to send
     * @param replyToMessageId
     *            Optional - If the message is a reply, ID of the original message
     * @param customReplyKeyboard
     *            Optional - Additional interface options. A JSON-serialized object for a custom reply keyboard,
     *            instructions to hide keyboard or to force a reply from the user.
     * @return A telegram request ready to consume the sendDocument request method of the Telegram Bot Api
     * @see TelegramRequest
     * @throws org.codehaus.jackson.JsonGenerationException
     *             if any.
     * @throws org.codehaus.jackson.map.JsonMappingException
     *             if any.
     * @throws java.io.IOException
     *             if any.
     * @throws JsonParsingException
     */
    public static TelegramRequest createSendDocumentRequest(final long chatId, final File inputFile, final Long replyToMessageId, final CustomReplyKeyboard customReplyKeyboard)
            throws JsonParsingException {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("chat_id", String.valueOf(chatId)));
        TelegramRequestFactory.addIfNotNull("reply_to_message_id", replyToMessageId, basicNameValuePair);
        TelegramRequestFactory.addIfNotNull("reply_markup", customReplyKeyboard, basicNameValuePair);
        return new TelegramRequest(RequestType.SEND_DOCUMENT, basicNameValuePair, inputFile, "document");
    }

    /**
     * Creates a telegram request ready to consume the sendDocument request method of the Telegram Bot Api
     * 
     * @param chatId
     *            Unique identifier for the message recipient ��� User or GroupChat id
     * @param documentId
     *            Re-send a file that is already on the Telegram servers, or upload a new file using
     *            multipart/form-data.
     * @param replyToMessageId
     *            Optional - If the message is a reply, ID of the original message
     * @param customReplyKeyboard
     *            Optional - Additional interface options. A JSON-serialized object for a custom reply keyboard,
     *            instructions to hide keyboard or to force a reply from the user.
     * @return A telegram request ready to consume the sendDocument request method of the Telegram Bot Api
     * @see TelegramRequest
     * @throws org.codehaus.jackson.JsonGenerationException
     *             if any.
     * @throws org.codehaus.jackson.map.JsonMappingException
     *             if any.
     * @throws java.io.IOException
     *             if any.
     * @throws JsonParsingException
     */
    public static TelegramRequest createSendDocumentRequest(final long chatId, final String documentId, final Long replyToMessageId, final CustomReplyKeyboard customReplyKeyboard)
            throws JsonParsingException {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("chat_id", String.valueOf(chatId)));
        basicNameValuePair.add(new BasicNameValuePair("document", documentId));
        TelegramRequestFactory.addIfNotNull("reply_to_message_id", replyToMessageId, basicNameValuePair);
        TelegramRequestFactory.addIfNotNull("reply_markup", customReplyKeyboard, basicNameValuePair);
        return new TelegramRequest(RequestType.SEND_DOCUMENT, basicNameValuePair);
    }

    /**
     * 
     * Creates a telegram request ready to consume the sendSticker request method of the Telegram Bot Api
     * 
     * @param chatId
     *            Unique identifier for the message recipient ��� User or GroupChat id
     * @param inputFile
     *            Sticker to upload
     * @param replyToMessageId
     *            Optional - If the message is a reply, ID of the original message
     * @param customReplyKeyboard
     *            Optional - Additional interface options. A JSON-serialized object for a custom reply keyboard,
     *            instructions to hide keyboard or to force a reply from the user.
     * @return A telegram request ready to consume the sendSticker request method of the Telegram Bot Api
     * @see TelegramRequest
     * @throws org.codehaus.jackson.JsonGenerationException
     *             if any.
     * @throws org.codehaus.jackson.map.JsonMappingException
     *             if any.
     * @throws java.io.IOException
     *             if any.
     * @throws JsonParsingException
     */
    public static TelegramRequest createSendStickerRequest(final long chatId, final File inputFile, final Long replyToMessageId, final CustomReplyKeyboard customReplyKeyboard)
            throws JsonParsingException {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("chat_id", String.valueOf(chatId)));
        TelegramRequestFactory.addIfNotNull("reply_to_message_id", replyToMessageId, basicNameValuePair);
        TelegramRequestFactory.addIfNotNull("reply_markup", customReplyKeyboard, basicNameValuePair);
        return new TelegramRequest(RequestType.SEND_STICKER, basicNameValuePair, inputFile, "sticker");
    }

    /**
     * 
     * Creates a telegram request ready to consume the sendSticker request method of the Telegram Bot Api
     * 
     * @param chatId
     *            Unique identifier for the message recipient ��� User or GroupChat id
     * @param stickerId
     *            Re-send a sticker that is already on the Telegram servers
     * @param replyToMessageId
     *            Optional - If the message is a reply, ID of the original message
     * @param customReplyKeyboard
     *            Optional - Additional interface options. A JSON-serialized object for a custom reply keyboard,
     *            instructions to hide keyboard or to force a reply from the user.
     * @return A telegram request ready to consume the sendSticker request method of the Telegram Bot Api
     * @throws JsonParsingException
     * @see TelegramRequest
     * @throws org.codehaus.jackson.JsonGenerationException
     *             if any.
     * @throws org.codehaus.jackson.map.JsonMappingException
     *             if any.
     * @throws java.io.IOException
     *             if any.
     */
    public static TelegramRequest createSendStickerRequest(final long chatId, final String stickerId, final Long replyToMessageId, final CustomReplyKeyboard customReplyKeyboard)
            throws JsonParsingException {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("chat_id", String.valueOf(chatId)));
        basicNameValuePair.add(new BasicNameValuePair("sticker", stickerId));
        TelegramRequestFactory.addIfNotNull("reply_to_message_id", replyToMessageId, basicNameValuePair);
        TelegramRequestFactory.addIfNotNull("reply_markup", customReplyKeyboard, basicNameValuePair);
        return new TelegramRequest(RequestType.SEND_STICKER, basicNameValuePair);
    }

    /**
     * 
     * Creates a telegram request ready to consume the sendVideo request method of the Telegram Bot Api
     * 
     * @param chatId
     *            Unique identifier for the message recipient ��� User or GroupChat id
     * @param inputFile
     *            Video to upload
     * @param replyToMessageId
     *            Optional - If the message is a reply, ID of the original message
     * @param customReplyKeyboard
     *            Optional - Additional interface options. A JSON-serialized object for a custom reply keyboard,
     *            instructions to hide keyboard or to force a reply from the user.
     * @return A telegram request ready to consume the sendVideo request method of the Telegram Bot Api
     * @see TelegramRequest
     * @throws org.codehaus.jackson.JsonGenerationException
     *             if any.
     * @throws org.codehaus.jackson.map.JsonMappingException
     *             if any.
     * @throws java.io.IOException
     *             if any.
     * @throws JsonParsingException
     */
    public static TelegramRequest createSendVideoRequest(final long chatId, final File inputFile, final Long replyToMessageId, final CustomReplyKeyboard customReplyKeyboard)
            throws JsonParsingException {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("chat_id", String.valueOf(chatId)));
        TelegramRequestFactory.addIfNotNull("reply_to_message_id", replyToMessageId, basicNameValuePair);
        TelegramRequestFactory.addIfNotNull("reply_markup", customReplyKeyboard, basicNameValuePair);
        return new TelegramRequest(RequestType.SEND_VIDEO, basicNameValuePair, inputFile, "video");
    }

    /**
     * 
     * Creates a telegram request ready to consume the sendVideo request method of the Telegram Bot Api
     * 
     * @param chatId
     *            Unique identifier for the message recipient ��� User or GroupChat id
     * @param videoId
     *            Re-send a video that is already on the Telegram servers.
     * @param replyToMessageId
     *            Optional - If the message is a reply, ID of the original message
     * @param customReplyKeyboard
     *            Optional - Additional interface options. A JSON-serialized object for a custom reply keyboard,
     *            instructions to hide keyboard or to force a reply from the user.
     * @return A telegram request ready to consume the sendVideo request method of the Telegram Bot Api
     * @see TelegramRequest
     * @throws org.codehaus.jackson.JsonGenerationException
     *             if any.
     * @throws org.codehaus.jackson.map.JsonMappingException
     *             if any.
     * @throws java.io.IOException
     *             if any.
     * @throws JsonParsingException
     */
    public static TelegramRequest createSendVideoRequest(final long chatId, final String videoId, final Long replyToMessageId, final CustomReplyKeyboard customReplyKeyboard)
            throws JsonParsingException {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("chat_id", String.valueOf(chatId)));
        basicNameValuePair.add(new BasicNameValuePair("video", videoId));
        TelegramRequestFactory.addIfNotNull("reply_to_message_id", replyToMessageId, basicNameValuePair);
        TelegramRequestFactory.addIfNotNull("reply_markup", customReplyKeyboard, basicNameValuePair);
        return new TelegramRequest(RequestType.SEND_VIDEO, basicNameValuePair);
    }

    /**
     * 
     * Creates a telegram request ready to consume the sendLocation request method of the Telegram Bot Api
     * 
     * @param chatId
     *            Unique identifier for the message recipient ��� User or GroupChat id
     * @param latitude
     *            Latitude of location
     * @param longitude
     *            Longitude of location
     * @param replyToMessageId
     *            Optional - If the message is a reply, ID of the original message
     * @param customReplyKeyboard
     *            Optional - Additional interface options. A JSON-serialized object for a custom reply keyboard,
     *            instructions to hide keyboard or to force a reply from the user.
     * @return A telegram request ready to consume the sendLocation request method of the Telegram Bot Api
     * @see TelegramRequest
     * @throws org.codehaus.jackson.JsonGenerationException
     *             if any.
     * @throws org.codehaus.jackson.map.JsonMappingException
     *             if any.
     * @throws java.io.IOException
     *             if any.
     * @throws JsonParsingException
     */
    public static TelegramRequest createSendLocationRequest(final long chatId, final double latitude, final double longitude, final Long replyToMessageId,
            final CustomReplyKeyboard customReplyKeyboard) throws JsonParsingException {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("chat_id", String.valueOf(chatId)));
        basicNameValuePair.add(new BasicNameValuePair("latitude", String.valueOf(latitude)));
        basicNameValuePair.add(new BasicNameValuePair("longitude", String.valueOf(longitude)));
        TelegramRequestFactory.addIfNotNull("reply_to_message_id", replyToMessageId, basicNameValuePair);
        TelegramRequestFactory.addIfNotNull("reply_markup", customReplyKeyboard, basicNameValuePair);
        return new TelegramRequest(RequestType.SEND_LOCATION, basicNameValuePair);
    }

    /**
     * 
     * Creates a telegram request ready to consume the sendChatAction request method of the Telegram Bot Api
     * 
     * @param chatId
     *            Unique identifier for the message recipient ��� User or GroupChat id
     * @param broadcastActionType
     *            Type of action to broadcast. Choose one, depending on what the user is about to receive: typing for
     *            text messages, upload_photo for photos, record_video or upload_video for videos, record_audio or
     *            upload_audio for audio files, upload_document for general files, find_location for location data.
     * @return A telegram request ready to consume the sendChatAction request method of the Telegram Bot Api
     * @see TelegramRequest
     */
    public static TelegramRequest createSendChatActionRequest(final long chatId, final BroadcastActionType broadcastActionType) {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("chat_id", String.valueOf(chatId)));
        basicNameValuePair.add(new BasicNameValuePair("action", broadcastActionType.getActionToBroadcast()));
        return new TelegramRequest(RequestType.SEND_CHAT_ACTION, basicNameValuePair);
    }

    /**
     * 
     * Creates a telegram request ready to consume the getUserProfilePhotos request method of the Telegram Bot Api
     * 
     * @param userId
     *            Unique identifier of the target user
     * @param offset
     *            Sequential number of the first photo to be returned. By default, all photos are returned.
     * @param limit
     *            Limits the number of photos to be retrieved. Values between 1���100 are accepted. Defaults to
     *            100.
     * @return A telegram request ready to consume the getUserProfilePhotos request method of the Telegram Bot Api
     * @see TelegramRequest
     */
    public static TelegramRequest createGetUserProfilePhotosRequest(final long userId, final Integer offset, final Integer limit) {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("user_id", String.valueOf(userId)));
        TelegramRequestFactory.addIfNotNull("offset", offset, basicNameValuePair);
        TelegramRequestFactory.addIfNotNull("limit", limit, basicNameValuePair);
        return new TelegramRequest(RequestType.GET_USER_PROFILE_PHOTOS, basicNameValuePair);
    }

    /**
     * 
     * Creates a telegram request ready to consume the getUpdates request method of the Telegram Bot Api
     * 
     * @param offset
     *            Optional - Identifier of the first update to be returned. Must be greater by one than the highest
     *            among the identifiers of previously received updates. By default, updates starting with the earliest
     *            unconfirmed update are returned. An update is considered confirmed as soon as getUpdates is called
     *            with an offset higher than its update_id.
     * @param limit
     *            Optional - Limits the number of updates to be retrieved. Values between 1���100 are accepted.
     *            Defaults to 100
     * @param timeout
     *            Optional - Timeout in seconds for long polling. Defaults to 0, i.e. usual short polling
     * @return A telegram request ready to consume the getUpdates request method of the Telegram Bot Api
     * @see TelegramRequest
     */
    public static TelegramRequest createGetUpdatesRequest(final Long offset, final Long limit, final Long timeout) {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("offset", String.valueOf(offset)));
        basicNameValuePair.add(new BasicNameValuePair("limit", String.valueOf(limit)));
        basicNameValuePair.add(new BasicNameValuePair("timeout", String.valueOf(timeout)));
        return new TelegramRequest(RequestType.GET_UPDATES, basicNameValuePair);
    }

    public static TelegramRequest createGetFileRequest(String fileId) throws JsonParsingException {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("file_id", String.valueOf(fileId)));

        return new TelegramRequest(RequestType.GET_FILE, basicNameValuePair);
    }

    /**
     * 
     * Creates a telegram request ready to consume the setWebhook request method of the Telegram Bot Api
     * 
     * @param url
     *            HTTPS url to send updates to. Use an empty string to remove webhook integration
     * @return A telegram request ready to consume the setWebhook request method of the Telegram Bot Api
     * @see TelegramRequest
     */
    public static TelegramRequest createSetWebhookRequest(final String url) {
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
        basicNameValuePair.add(new BasicNameValuePair("url", String.valueOf(url)));
        return new TelegramRequest(RequestType.SET_WEBHOOK, basicNameValuePair);
    }

    private static void addIfNotNull(final String name, final String value, List<BasicNameValuePair> basicNameValuePair) {
        if (value != null) {
            basicNameValuePair.add(new BasicNameValuePair(name, value));
        }
    }

    private static void addIfNotNull(final String name, final Integer value, List<BasicNameValuePair> basicNameValuePair) {
        if (value != null) {
            basicNameValuePair.add(new BasicNameValuePair(name, value.toString()));
        }
    }

    private static void addIfNotNull(final String name, final Long value, List<BasicNameValuePair> basicNameValuePair) {
        if (value != null) {
            basicNameValuePair.add(new BasicNameValuePair(name, value.toString()));
        }
    }

    private static void addIfNotNull(final String name, final CustomReplyKeyboard customReplyKeyboard, List<BasicNameValuePair> basicNameValuePair) throws JsonParsingException {
        if (customReplyKeyboard != null) {
            try {
                final String jsonString = MapperHandler.INSTANCE.getObjectMapper().writeValueAsString(customReplyKeyboard);
                basicNameValuePair.add(new BasicNameValuePair(name, jsonString));
            } catch (IOException e) {
                throw new JsonParsingException(e);
            }
        }
    }
}
