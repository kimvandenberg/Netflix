package domain.Listeners.SerieListeners;

import application.*;
import domain.Serie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * SerieGetRecommendedSerieFromProfileListener.java
 * This ActionListener will show all of the recommended series.
 * Author: Marc Verwijmeren
 */

public class SerieGetRecommendedSerieFromProfileListener implements ActionListener {
    private GUI ui;
    private SerieManagerImpl serieManager ;
    private EpisodeManagerlmpl episodeManager ;
    private ProfileManagerImpl profileManager;
    private AccountManagerImpl accountManager;
    private WatchBehaviourManagerImpl watchBehaviourManager;

    // Constructor
    public SerieGetRecommendedSerieFromProfileListener(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.profileManager = new ProfileManagerImpl();
        this.accountManager = new AccountManagerImpl();
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
    }

    @Override
    // Show the recommended serie form watchbehaviour
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if the comboboxe's aren't empty
            if (ui.getCbRecommendedSerieForAccount() != null  && ui.getCbRecommendedSerieForProfile().getItemCount() > 0) {

                // Create strings from the selected comboboxe's
                String profileName = ui.getCbRecommendedSerieForProfile().getSelectedItem().toString();
                String accountName = ui.getCbRecommendedSerieForAccount().getSelectedItem().toString();

                // Get the profileID
                int profileID = profileManager.getIdOfProfile(profileName, accountName);

                // Create a hashset with all tht watched series based on the profileID
                HashSet<Serie> watchedSeries = watchBehaviourManager.getWatchedSerie(profileID);

                // Create a stringbuilder
                StringBuilder sb = new StringBuilder();

                sb.append("Aanbevolen series: \n");

                // Checked it thar are watched series
                if(!watchedSeries.isEmpty())
                {
                    for(Serie watchedSerie : watchedSeries)
                    {
                        Serie serie = serieManager.getSerieById(watchedSerie.getRecommendedSerie());
                        ArrayList<Serie> serieArrayList = serieManager.getSerieByRecommendedNumber(watchedSerie.getRecommendedSerie());

                        int buffer = 0;
                        String bufferTitles = "";

                        for (Serie serie1 : serieArrayList){
                            if(buffer == 0){
                                bufferTitles += serie1.getTitle();
                            }
                            else {
                                bufferTitles += " en " + serie1.getTitle();
                            }
                            buffer++;
                        }

                        sb.append("Omdat u naar " + bufferTitles + " hebt gekeken.\n");
                        sb.append("Bevelen wij u de volgende serie aan: " + serie.getTitle() + " \n \n");

                    }
                }
                else{
                    sb.append("U heeft nog geen serie bekeken");
                }

                // Fill the pane with the average from a serie
                ui.getTxtRecommendedSerie().setText(sb.toString());
            }

            else{
                return;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
