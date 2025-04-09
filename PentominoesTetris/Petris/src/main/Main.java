package main;

import javax.swing.*    ;
import java.io.IOException;

public class Main {
    public static JFrame window = new JFrame("Petris");
    public static void main(String[] args) throws IOException {

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);



        PanelManager panelManager = new PanelManager();

        window.add(panelManager);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panelManager.launchGame();
        window.add(StartPanel.playerName);


    }
}
