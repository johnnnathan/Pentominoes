package main;

import java.io.*;

import static main.GamePanel.highScores;

public class WriteScore {

    public static final String FILE_PATH = "/Users/kitzu/Desktop/PentominosProject1-1/bcs_group24_2023/Petris/src/main/Resources/Highscore.csv";
    private static final int MAX_HIGH_SCORES = 7;

    public WriteScore() throws IOException {
        addNewHighScore();
        sortHighScores();
        writeHighScoresToFile();
    }

    private void addNewHighScore() {
        highScores.add(StartPanel.playerName.getText() + "," + GamePanel.score);
    }

    private void sortHighScores() {
        highScores.sort((score1, score2) -> {
            int value1 = Integer.parseInt(score1.split(",")[1]);
            int value2 = Integer.parseInt(score2.split(",")[1]);
            return Integer.compare(value2, value1); // Sort in descending order
        });

        if (highScores.size() > MAX_HIGH_SCORES) {
            highScores.subList(MAX_HIGH_SCORES, highScores.size()).clear();
        }
    }

    private void writeHighScoresToFile() throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (String highScore : highScores) {
                writer.write(highScore + System.lineSeparator());
            }
        }
    }
}
