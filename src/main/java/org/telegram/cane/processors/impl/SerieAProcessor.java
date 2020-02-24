package org.telegram.cane.processors.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.telegram.cane.processors.AbstractMessageProcessor;
import org.telegram.cane.processors.MessageProcessor;
import org.telegram.cane.soccerdata.Match;
import org.telegram.cane.soccerdata.MatchDay;
import org.telegram.cane.soccerdata.Standing;
import org.telegram.cane.soccerdata.Standings;
import org.telegram.cane.soccerdata.Table;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;

public class SerieAProcessor extends AbstractMessageProcessor implements MessageProcessor {

    private static final Logger LOG = Logger.getLogger(SerieAProcessor.class);

    private static final String SERIE_A_STANDINGS_URL = "http://api.football-data.org/v2/competitions/2019/standings";
    private static final String SERIE_A_GET_MATCH_URL = "http://api.football-data.org/v2/competitions/2019/matches?matchday=";
    private static final String SERIEA_STANDINGS_COMMAND = "/seriea";
    private static final String SERIEA_CURRENT_MATCHES_COMMAND = "/serieacm";
    private static final String SERIEA_NEXT_MATCHES_COMMAND = "/serieanm";
    private static final String SERIEA_PREVIOUS_MATCHES_COMMAND = "/serieapm";
    private static final String SERIEA_SPECIFIC_MATCHES_COMMAND = "/serieamatch=";

    private final SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssX");
    private final SimpleDateFormat outFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @Override
    public boolean process(Message message, RequestHandler requestHandler) throws JsonParsingException, TelegramServerException {
        if (StringUtils.equalsIgnoreCase(message.getText(), SERIEA_STANDINGS_COMMAND)) {
            File file = getStandings();
            TelegramRequest request = TelegramRequestFactory.createSendPhotoRequest(message.getChat().getId(), file, null, null, null);
            requestHandler.sendRequest(request);
            return true;
        } else if (StringUtils.equalsIgnoreCase(message.getText(), SERIEA_CURRENT_MATCHES_COMMAND)) {

            int currentMatchDay = getCurrentMatchDay();
            sendMatchDayImg(message, requestHandler, currentMatchDay);
            return true;
        } else if (StringUtils.equalsIgnoreCase(message.getText(), SERIEA_NEXT_MATCHES_COMMAND)) {

            int nextMatchDay = getCurrentMatchDay() + 1;
            sendMatchDayImg(message, requestHandler, nextMatchDay);
            return true;
        } else if (StringUtils.equalsIgnoreCase(message.getText(), SERIEA_PREVIOUS_MATCHES_COMMAND)) {

            int prevMatchDay = getCurrentMatchDay() - 1;
            sendMatchDayImg(message, requestHandler, prevMatchDay);
            return true;
        } else if (StringUtils.startsWithIgnoreCase(message.getText(), SERIEA_SPECIFIC_MATCHES_COMMAND)) {
            String match = message.getText().replace(SERIEA_SPECIFIC_MATCHES_COMMAND, "");
            int matchDay = 1;
            try {
                matchDay = Integer.parseInt(match);
                sendMatchDayImg(message, requestHandler, matchDay);

            } catch (NumberFormatException ex) {
                String name = extractSenderUserName(message);
                name = StringUtils.isNotBlank(name) ? name : "Scemino";
                replyTextMessage(name + "...magari manda un numero!", message, requestHandler);
            }
            return true;
        }
        return false;
    }

    private void sendMatchDayImg(Message message, RequestHandler requestHandler, int matchDay) throws JsonParsingException, TelegramServerException {
        File file = getMatches(matchDay);
        TelegramRequest request = TelegramRequestFactory.createSendPhotoRequest(message.getChat().getId(), file, null, null, null);
        requestHandler.sendRequest(request);
    }

    private int getCurrentMatchDay() {
        URL url;
        try {
            url = new URL(SERIE_A_STANDINGS_URL);

            HttpURLConnection myURLConnection = (HttpURLConnection) url.openConnection();
            myURLConnection.setRequestProperty("X-Auth-Token", "a8b6d9847a864aa28ce4a43bfc42e6b5");

            InputStream is = myURLConnection.getInputStream();

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Standings standings = mapper.readValue(is, Standings.class);

            return standings.getSeason().getCurrentMatchday();
        } catch (MalformedURLException e) {
            LOG.error("An error occurred while retrieving current matchday. " + e, e);
        } catch (IOException e) {
            LOG.error("An error occurred while retrieving current matchday. " + e, e);
        }
        return 1;
    }

    private File getStandings() {
        try {
            URL url = new URL(SERIE_A_STANDINGS_URL);

            HttpURLConnection myURLConnection = (HttpURLConnection) url.openConnection();
            myURLConnection.setRequestProperty("X-Auth-Token", "a8b6d9847a864aa28ce4a43bfc42e6b5");

            InputStream is = myURLConnection.getInputStream();

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Standings standings = mapper.readValue(is, Standings.class);

            File file = writeStandingsToFile(standings);
            LOG.info("Standings refreshed to file:" + file.getAbsolutePath());
            return file;

        } catch (IOException e) {
            LOG.error("Ostia. " + e, e);
            return null;
        }
    }

    private File getMatches(int currentMatchDay) {
        try {
            URL url = new URL(SERIE_A_GET_MATCH_URL + currentMatchDay);
            HttpURLConnection myURLConnection = (HttpURLConnection) url.openConnection();
            myURLConnection.setRequestProperty("X-Auth-Token", "a8b6d9847a864aa28ce4a43bfc42e6b5");

            InputStream is = myURLConnection.getInputStream();

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            MatchDay matchDay = mapper.readValue(is, MatchDay.class);

            File file = writeMatchDayToFile(matchDay);

            return file;

        } catch (IOException e) {
            LOG.error("Ostia. " + e, e);
        }
        return null;
    }

    private File writeMatchDayToFile(MatchDay md) throws IOException {
        String filename = URLEncoder.encode("matchDay" + md.getCount(), "UTF-8");
        Object[][] data = new Object[md.getMatches().size()][4];
        for (int i = 0; i < md.getMatches().size(); i++) {
            Match match = md.getMatches().get(i);
            data[i][0] = match.getHomeTeam().getName();
            data[i][1] = match.getAwayTeam().getName();
            if (match.getStatus().equalsIgnoreCase("FINISHED")) {
                data[i][2] = match.getScore().getFullTime().getHomeTeam();
                data[i][3] = match.getScore().getFullTime().getAwayTeam();
            } else {
                String matchTime = match.getUtcDate();
                try {
                    Date date = inFormat.parse(matchTime);
                    matchTime = outFormat.format(date);
                } catch (ParseException e) {
                    LOG.error("Error while parsing: " + e, e);
                }
                data[i][2] = matchTime;
                data[i][3] = "";
            }
        }

        String[] columns = { "Home", "Away", "", "" };

        return writeTableToImage(filename, data, columns);
    }

    private File writeStandingsToFile(Standings standings) throws IOException {
        String filename = URLEncoder.encode(standings.getCompetition().getName(), "UTF-8");
        Standing standing = standings.getStandings().get(0);
        Object[][] data = new Object[standing.getTable().size()][4];
        for (int i = 0; i < standing.getTable().size(); i++) {
            Table team = standing.getTable().get(i);
            data[i][0] = team.getTeam().getName();
            data[i][1] = team.getPoints();
            data[i][2] = team.getGoalsFor();
            data[i][3] = team.getGoalsAgainst();
        }

        String[] columns = { "Nome", "Punti", "GolFatti", "GolSubiti" };

        return writeTableToImage(filename, data, columns);
    }

    private File writeTableToImage(String filename, Object[][] data, String[] columns) throws IOException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception useDefault) {
        }
        JTable table = new JTable(data, columns);
        JScrollPane scroll = new JScrollPane(table);

        resizeColumnWidth(table);
        JPanel p = new JPanel(new BorderLayout());
        p.add(scroll, BorderLayout.CENTER);

        // JTable must have been added to a TLC in order to render
        // correctly - go figure.
        JFrame f = new JFrame("Never shown");
        f.setContentPane(scroll);
        f.pack();

        JTableHeader h = table.getTableHeader();
        Dimension dH = h.getSize();
        Dimension dT = table.getSize();
        int x = (int) dH.getWidth();
        int y = (int) dH.getHeight() + (int) dT.getHeight();

        scroll.setDoubleBuffered(false);

        BufferedImage bi = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);

        Graphics g = bi.createGraphics();
        h.paint(g);
        g.translate(0, h.getHeight());
        table.paint(g);
        g.dispose();

        //JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(bi)));
        File file = File.createTempFile(filename, ".jpg");
        ImageIO.write(bi, "png", file);
        file.deleteOnExit();
        return file;
    }

    private static File writeTableToImage2() throws IOException {
        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();

        g.drawString("www.stackoverflow.com", 20, 20);
        g.drawString("www.google.com", 20, 40);
        g.drawString("www.facebook.com", 20, 60);
        g.drawString("www.youtube.com", 20, 80);
        g.drawString("www.oracle.com", 20, 1000);

        File f = File.createTempFile("prova", "jpg");
        System.out.println(f.getAbsolutePath());
        ImageIO.write(bufferedImage, "jpg", f);
        return null;

    }

    private void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public static void main(String[] args) {
        try {
            writeTableToImage2();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected boolean isUnderAuthentication() {
        return false;
    }

}
