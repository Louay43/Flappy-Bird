import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreBoard extends JFrame {
    private ArrayList<Integer> scores = new ArrayList<>();
    private JLabel scoreLabel;
    private static final String SCORE_FILE = "scores.txt";

    public ScoreBoard(int latestScore) {
        setTitle("Score Board");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        loadScores();
        addScore(latestScore);
        saveScores();
        
        scoreLabel = new JLabel(formatScores(), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(scoreLabel, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    private void loadScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("No previous scores found or file error.");
        }
    }
    
    private void addScore(int score) {
        scores.add(score);
        Collections.sort(scores, Collections.reverseOrder());
        while (scores.size() > 5) {
            scores.remove(scores.size() - 1);
        }
    }
    
    private void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            for (int score : scores) {
                writer.write(score + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving scores.");
        }
    }
    
    private String formatScores() {
        StringBuilder sb = new StringBuilder("<html><h2>Top 5 Scores</h2>");
        for (int i = 0; i < scores.size(); i++) {
            sb.append((i + 1)).append(". ").append(scores.get(i)).append("<br>");
        }
        sb.append("</html>");
        return sb.toString();
    }
}
