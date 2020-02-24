package org.telegram.cane.processors.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import org.codehaus.jackson.map.ObjectMapper;
import org.telegram.cane.processors.AbstractMessageProcessor;
import org.telegram.cane.processors.MessageProcessor;
import org.telegram.cane.soccerdata.Fixture;
import org.telegram.cane.soccerdata.LeagueTable;
import org.telegram.cane.soccerdata.MatchDay;
import org.telegram.cane.soccerdata.Season;
import org.telegram.cane.soccerdata.Standing;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;

public class SerieAProcessor extends AbstractMessageProcessor implements MessageProcessor {

    private static final Logger LOG = Logger.getLogger(SerieAProcessor.class);

    private static final String SERIE_A_STANDINGS_URL = "http://api.football-data.org/v1/soccerseasons/456/leagueTable";
    private static final String SERIE_A_NEXT_MATCH_URL = "http://api.football-data.org/v1/soccerseasons/456/fixtures?matchday=";
    private static final String SERIE_A_SEASON_URL = "http://api.football-data.org/v1/soccerseasons/456";
    private static final String SERIEA_STANDINGS_COMMAND = "/seriea";
    private static final String SERIEA_CURRENT_MATCHES_COMMAND = "/currentmatch";
    private static final String SERIEA_NEXT_MATCHES_COMMAND = "/nextmatch";
    private static final String SERIEA_PREVIOUS_MATCHES_COMMAND = "/previousmatch";
    private static final String SERIEA_SPECIFIC_MATCHES_COMMAND = "/match=";

    private final SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssX");
    private final SimpleDateFormat outFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

    @Override
    public boolean process(Message message, RequestHandler requestHandler) throws JsonParsingException, TelegramServerException {
        if (StringUtils.startsWithIgnoreCase(message.getText(), SERIEA_STANDINGS_COMMAND)) {
            File file = getStandings();
            TelegramRequest request = TelegramRequestFactory.createSendPhotoRequest(message.getChat().getId(), file, null, null, null);
            requestHandler.sendRequest(request);
            return true;
        } else if (StringUtils.startsWithIgnoreCase(message.getText(), SERIEA_CURRENT_MATCHES_COMMAND)) {

            int currentMatchDay = getCurrentMatchDay();
            sendMatchDayImg(message, requestHandler, currentMatchDay);
            return true;
        } else if (StringUtils.startsWithIgnoreCase(message.getText(), SERIEA_NEXT_MATCHES_COMMAND)) {

            int nextMatchDay = getCurrentMatchDay() + 1;
            sendMatchDayImg(message, requestHandler, nextMatchDay);
            return true;
        } else if (StringUtils.startsWithIgnoreCase(message.getText(), SERIEA_PREVIOUS_MATCHES_COMMAND)) {

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
            url = new URL(SERIE_A_SEASON_URL);

            InputStream is = url.openStream();
            ObjectMapper mapper = new ObjectMapper();
            Season season = mapper.readValue(is, Season.class);
            return season.getCurrentMatchday();
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
            InputStream is = url.openStream();
            ObjectMapper mapper = new ObjectMapper();

            LeagueTable standings = mapper.readValue(is, LeagueTable.class);

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
            URL url = new URL(SERIE_A_NEXT_MATCH_URL + currentMatchDay);
            InputStream is = url.openStream();
            ObjectMapper mapper = new ObjectMapper();

            MatchDay md = mapper.readValue(is, MatchDay.class);
            LOG.info("MatchDay: " + md);

            File file = writeMatchDayToFile(md);

            return file;

        } catch (IOException e) {
            LOG.error("Ostia. " + e, e);
        }
        return null;
    }

    private File writeMatchDayToFile(MatchDay md) throws IOException {
        String filename = URLEncoder.encode("matchDay" + md.getCount(), "UTF-8");
        Object[][] data = new Object[md.getFixtures().size()][4];
        for (int i = 0; i < md.getFixtures().size(); i++) {
            Fixture fixt = md.getFixtures().get(i);
            data[i][0] = fixt.getHomeTeamName();
            data[i][1] = fixt.getAwayTeamName();
            if (fixt.getStatus().equalsIgnoreCase("FINISHED")) {
                data[i][2] = fixt.getResult().getGoalsHomeTeam();
                data[i][3] = fixt.getResult().getGoalsAwayTeam();
            } else {
                String matchTime = fixt.getDate();
                try {
                    Date date = inFormat.parse(fixt.getDate());
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

    private File writeStandingsToFile(LeagueTable standings) throws IOException {
        String filename = URLEncoder.encode(standings.getLeagueCaption(), "UTF-8");
        Object[][] data = new Object[standings.getStanding().size()][4];
        for (int i = 0; i < standings.getStanding().size(); i++) {
            Standing team = standings.getStanding().get(i);
            data[i][0] = team.getTeamName();
            data[i][1] = team.getPoints();
            data[i][2] = team.getGoals();
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

}
