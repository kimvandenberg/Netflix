package application;

import datastorage.EpisodeDAO;
import domain.Episode;
import presentation.GUI;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class EpisodeManagerlmpl {

    private EpisodeDAO episodeDAO = new EpisodeDAO();
    private ArrayList<Episode> episodeList;
    private GUI gui;

    public EpisodeManagerlmpl(GUI gui){
        this.gui = gui;
    }

    public ArrayList<Episode> getEpisode()  {

        return episodeList;
    }

    public void setEpisodeList(int id){
        // Returns an ArrayList filled with all episodes in the database.
        try {
            episodeList = episodeDAO.getEpisodes(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void fillAllEpisodesCbx(int id){
        gui.getcbAvgOfWatchedEpisode().removeAllItems();
        gui.getcbAvgOfWatchedEpisode().setEnabled(true);
        setEpisodeList(id);

        appendComboBox(gui.getcbAvgOfWatchedEpisode(),episodeList);
    }

    public void appendComboBox(JComboBox comboBox, ArrayList<Episode> episodes)
    {
        for ( Episode episode : episodes)
        {
            comboBox.addItem(episode.getTitle());
        }
    }
}
