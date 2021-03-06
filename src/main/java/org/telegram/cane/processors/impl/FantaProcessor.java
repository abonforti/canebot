package org.telegram.cane.processors.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.cane.constants.CaneConstants;
import org.telegram.cane.fanta.data.Match;
import org.telegram.cane.processors.AbstractMessageProcessor;
import org.telegram.cane.processors.MessageProcessor;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;

/**
 * Created by Federico on 22/09/16.
 */
public class FantaProcessor extends AbstractMessageProcessor implements MessageProcessor {

    private static final String DIV_ROW_ITEM_BOX = ".list-group-item.match.match-result.row.highlight";
    public static final String SPAN_NUMBIG4_PULL_RIGHT = "span.numbig4.pull-right";
    public static final String NUMBIG_3 = "numbig3";

    @Override
    protected boolean isUnderAuthentication() {
        return false;
    }

    @Override
    public boolean process(final Message message, RequestHandler requestHandler) {
        String response = "";
        LOG.info("FANTAPROCESSOR RUNNING### 2");
        String text = message.getText();
        if (StringUtils.isNotEmpty(text)) {
            Document document = null;
            if (StringUtils.containsIgnoreCase(text, CaneConstants.FANTASERIEA)) {
                document = getDocument(CaneConstants.SERIEA_ID);
            } else if (StringUtils.containsIgnoreCase(text, CaneConstants.FANTACOPPA)) {
                document = getDocument(CaneConstants.CUP_ID);
            }
            if (document != null) {
                Elements itemBoxes = document.select(DIV_ROW_ITEM_BOX);
                int index = 0;
                List<String> matches = new ArrayList<String>();
                for (Element itemBox : itemBoxes) {
                    Elements teams = itemBox.select(".team-name");
                    Elements goals = itemBox.select(".team-score");
                    String matchResult = goals.size() > 1 ? goals.get(0).text() + "-" + goals.get(1).text() : "Riposa";
                    String team1 = teams.size() > 0 ? teams.get(0).text() : "";
                    String team2 = teams.size() > 1 ? teams.get(1).text() : "";
                    Match match = new Match(team1, team2, matchResult, null);
                    matches.add(match.onlyResultToString());
                }
                try {
                    for (String matchString : matches) {
                        response = response.concat(matchString + " \r\n");
                    }
                    replyTextMessage(response, message, requestHandler);
                    return true;
                } catch (JsonParsingException e) {
                    e.printStackTrace();
                } catch (TelegramServerException e) {
                    e.printStackTrace();
                }

            }
        }
        return false;
    }

    private static String getMatchResults(Elements names) {
        String matchResults = "";
        for (Element h3 : names) {
            if (h3.hasClass(NUMBIG_3)) {
                matchResults = h3.text();
            }
        }
        return matchResults;
    }

    private static Map<Integer, String> getTeamData(int index, Elements names) {
        Map<Integer, String> teams = new HashMap<Integer, String>();
        for (Element h3 : names) {
            if (!h3.hasClass(NUMBIG_3)) {
                if (index % 2 == 0) {
                    teams.put(0, h3.text());
                } else {
                    teams.put(1, h3.text());
                }
                index++;
            }
        }
        return teams;
    }

    private static Map<Integer, String> getScoresMap(Elements results) {
        Map<Integer, String> scoresMap = new HashMap<Integer, String>();
        int resultIndex = 0;
        boolean foundResult = false;
        for (Element result : results) {
            if (resultIndex % 2 == 0) {
                if (!foundResult) {
                    scoresMap.put(0, result.text());
                    foundResult = true;
                } else {
                    scoresMap.put(1, result.text());
                }
            }
            resultIndex++;
        }
        return scoresMap;
    }

    /*This method open a connection to fantaGazzetta
    and returns the Document containing the html of the fantaUrl*/
    private static Document getDocument(String competitionId) {
        Document document = null;
        try {
            URL url = new URL(CaneConstants.FANTAURL + competitionId);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            document = Jsoup.parse(response.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}