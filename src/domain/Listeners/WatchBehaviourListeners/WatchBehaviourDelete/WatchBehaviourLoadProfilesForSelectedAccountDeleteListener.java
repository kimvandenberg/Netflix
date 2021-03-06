package domain.Listeners.WatchBehaviourListeners.WatchBehaviourDelete;

import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.Account;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * WatchBehaviourLoadProfilesForSelectedAccountDeleteListener.java
 * This ActionListener gets all the profiles matching the selected account and places it into the profile comboBox.
 * <p>
 * Author: Dylan ten Böhmer
 */


public class WatchBehaviourLoadProfilesForSelectedAccountDeleteListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;
    private Account account;

    // Constructor
    public WatchBehaviourLoadProfilesForSelectedAccountDeleteListener(GUI ui) {
        this.ui = ui;
        accountManager = new AccountManagerImpl();
        profileManager = new ProfileManagerImpl();
        account = new Account();
    }

    // OnActionPerformed: Get all the profiles matching the selected account and place it into the profile comboBox.
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if input wasn't empty
            if (this.ui.getCbDeleteWatchedMediaAccount().getSelectedItem() != null) {
                // Empty the comboBox to avoid double data.
                profileManager.initializeProfileComboBoxes(ui);
                // Declare/initialize variables
                String strSelectedAccount = this.ui.getCbDeleteWatchedMediaAccount().getSelectedItem().toString();
                // Get the account data that matches the selected Account.
                account = accountManager.getAccountByName(strSelectedAccount);
                // Get the ID of the account.
                int id = this.account.getId();
                this.ui.getCbDeleteWatchedMediaProfile().setEnabled(true);
                // Place all profiles that belong to the variable account into the comboBox.
                profileManager.addProfilesToComboBox(this.ui.getCbDeleteWatchedMediaProfile(), profileManager.getMatchingProfiles(id));
                this.ui.getCbDeleteWatchedMediaProfile().setSelectedItem(null);
                ui.getCbDeleteWatchedMediaTitle().removeAllItems();
            } else {
                return;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
