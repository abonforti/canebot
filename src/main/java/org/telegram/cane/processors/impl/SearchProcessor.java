package org.telegram.cane.processors.impl;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.cane.constants.CaneConstants;
import org.telegram.cane.processors.AbstractMessageProcessor;
import org.telegram.cane.processors.MessageProcessor;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;

public class SearchProcessor extends AbstractMessageProcessor implements MessageProcessor {

    @Override
    protected boolean isUnderAuthentication() {
        return false;
    }

    private static final String LINK = "link";
    private static final String ITEMS = "items";
    private static final String URL_PART2 = "&searchType=image&fileType=jpg&alt=json&lr=lang_it";
    private static final String URL_PART1 = "https://www.googleapis.com/customsearch/v1?key=AIzaSyCemUqQy2UUfAd3X7ZsDgJNtZEJf_dGPX0&cx=001190623412265690532:hqcxjj-hhck&q=";
    private static final String FILE_EXT = "jpg";
    private static final String FILE_NAME = "imgSrc";

    @Override
    public boolean process(final Message message, RequestHandler requestHandler) throws JsonParsingException, TelegramServerException {
        String incomingMsg = message.getText();
        if (StringUtils.isNotEmpty(message.getText()) && incomingMsg.contains(CaneConstants.SEARCH_IMG_COMMAND)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Search command detected: " + incomingMsg);
            }

            final String toSearch = StringUtils.trim(incomingMsg.replace(CaneConstants.SEARCH_IMG_COMMAND, StringUtils.EMPTY));
            LOG.info("Launching search for: " + toSearch);
            if (StringUtils.isNotEmpty(toSearch)) {
                final SearchProcessor caneSearchEngine = new SearchProcessor();
                final File pic = caneSearchEngine.getImageFromGoogle(toSearch);
                TelegramRequest request = TelegramRequestFactory.createSendPhotoRequest(message.getChat().getId(), pic, null, null, null);
                requestHandler.sendRequest(request);

                return true;
            } else {
                LOG.warn("Tried to search empty or null field [" + toSearch + "], returning...");
            }
        }
        return false;
    }

    public File getImageFromGoogle(final String word) {
        try {
            final String keyword = URLEncoder.encode(word, StandardCharsets.UTF_8.name());
            final URLConnection urlConnection = new URL(URL_PART1 + keyword + URL_PART2).openConnection();
            String line;
            final StringBuilder resultsBuilder = new StringBuilder();
            // Reading answer from Google
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                resultsBuilder.append(line);
            }
            final JSONObject jsonObject = new JSONObject(resultsBuilder.toString());
            final JSONArray jsonArray = jsonObject.getJSONArray(ITEMS);
            for (int i = 0; i < jsonArray.length(); i++) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug(jsonArray.getJSONObject(i).get(LINK));
                }
            }
            final URL urlImage = new URL(jsonArray.getJSONObject(new Random().nextInt(jsonArray.length())).get(LINK).toString());
            final BufferedImage img = ImageIO.read(urlImage);

            File image = File.createTempFile(FILE_NAME, "." + FILE_EXT);
            if (LOG.isDebugEnabled()) {
                LOG.debug("Image downloaded at: " + image.getAbsolutePath());
            }
            ImageIO.write(img, FILE_EXT, image);
            image.deleteOnExit();
            return image;
        } catch (final MalformedURLException malformedURLException) {
            LOG.error("Cannot parse URL for given query " + malformedURLException, malformedURLException);
        } catch (final IOException ioException) {
            LOG.error("Impossible to save the image searched " + ioException, ioException);
        }
        return null;
    }
}
