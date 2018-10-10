package domain.Listeners.SerieListeners;

import application.SerieManagerImpl;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerieCreateListener implements ActionListener {
    private GUI ui;
    private SerieManagerImpl serieManager ;

    public SerieCreateListener(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}