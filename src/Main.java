import presentation.GUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create a new GUI object
        GUI ui = new GUI(1500,950);
        SwingUtilities.invokeLater(ui);
    }
}
