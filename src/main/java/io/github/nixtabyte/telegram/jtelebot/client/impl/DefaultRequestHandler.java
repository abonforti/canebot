
/**
 *
 * Copyright (C) 2015 Roberto Dominguez Estrada and Juan Carlos Sedano Salas
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 */
package io.github.nixtabyte.telegram.jtelebot.client.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;

import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import io.github.nixtabyte.telegram.jtelebot.client.HttpClientFactory;
import io.github.nixtabyte.telegram.jtelebot.client.HttpProxy;
import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.mapper.json.MapperHandler;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.response.json.TelegramResponse;

/**
 *
 * This is the default request handler
 *
 * @since 0.0.1
 */
public class DefaultRequestHandler implements RequestHandler {

    private static final Logger LOG = Logger.getLogger(DefaultRequestHandler.class);

    // TODO This should be in a CommonConstants class
    private static final String URL_TEMPLATE = "https://api.telegram.org/bot{0}/{1}";

    private final HttpClient httpClient;
    private HttpProxy httpProxy;
    private String token;

    /**
     * <p>
     * Constructor for DefaultRequestHandler.
     * </p>
     */
    public DefaultRequestHandler() {
        httpClient = HttpClientFactory.createHttpClient();
    }

    public DefaultRequestHandler(final String token, final HttpProxy httpProxy) {
        this(token);
        this.httpProxy = httpProxy;
    }

    /**
     * <p>
     * Constructor for DefaultRequestHandler.
     * </p>
     *
     * @param token
     *            a {@link java.lang.String} object.
     */
    public DefaultRequestHandler(final String token) {
        this();
        this.token = token;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws JsonParsingException
     * @throws TelegramServerException
     */
    @Override
    public TelegramResponse<?> sendRequest(TelegramRequest telegramRequest) throws JsonParsingException, TelegramServerException {
        TelegramResponse<?> telegramResponse = null;
        final String response = callHttpService(telegramRequest);

        telegramResponse = parseJsonResponse(response, telegramRequest.getRequestType().getResultClass());

        return telegramResponse;
    }

    private String callHttpService(TelegramRequest telegramRequest) throws TelegramServerException {
        final String url = MessageFormat.format(URL_TEMPLATE, token, telegramRequest.getRequestType().getMethodName());

        final HttpPost request = new HttpPost(url);
        if (telegramRequest.getFile() != null) {
            final MultipartEntityBuilder mpeb = MultipartEntityBuilder.create();
            mpeb.addBinaryBody(telegramRequest.getFileType(), telegramRequest.getFile());
            for (BasicNameValuePair bnvp : telegramRequest.getParameters()) {
                mpeb.addTextBody(bnvp.getName(), bnvp.getValue());
            }

            request.setEntity(mpeb.build());
        } else {
            request.setEntity(new UrlEncodedFormEntity(telegramRequest.getParameters(), Consts.UTF_8));
        }
        try {
            // PROXY Usage
            if (httpProxy != null) {
                HttpHost proxyHost = new HttpHost(httpProxy.getHost(), httpProxy.getPort(), httpProxy.getProtocol());
                RequestConfig config = RequestConfig.custom().setProxy(proxyHost).build();
                request.setConfig(config);
            }
            LOG.debug("Request: " + request.toString());
            final HttpResponse response = httpClient.execute(request);

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new TelegramServerException(result.toString());
            }

            return result.toString();

        } catch (IOException e) {
            throw new TelegramServerException(e);
        }

    }

    // TODO This method should be implemented in a ResponseParser class
    private TelegramResponse<?> parseJsonResponse(final String jsonResponse, final Class<?> resultTypeClass) throws JsonParsingException {
        try {
            LOG.trace(jsonResponse);
            final TelegramResponse<?> telegramResponse = (TelegramResponse<?>) MapperHandler.INSTANCE.getObjectMapper().readValue(jsonResponse,
                    MapperHandler.INSTANCE.getObjectMapper().getTypeFactory().constructParametricType(TelegramResponse.class, resultTypeClass));
            LOG.trace(telegramResponse.toString());
            return telegramResponse;

        } catch (IOException e) {
            throw new JsonParsingException(e);
        }

    }

    /**
     * <p>
     * Getter for the field <code>token</code>.
     * </p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getToken() {
        return token;
    }

    /**
     * <p>
     * Setter for the field <code>token</code>.
     * </p>
     *
     * @param token
     *            a {@link java.lang.String} object.
     */
    public void setToken(String token) {
        this.token = token;
    }

}
