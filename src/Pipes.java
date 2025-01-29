import java.awt.*;
import javax.swing.*;

public class Pipes {
    int XCoordinate;
    int width = 50;
    int height = 500;
    int gap = 120;
    int randomYPositionTop;
    int YPositionBottom;

    public Pipes(int startX) {
        this.XCoordinate = startX;
        this.randomYPositionTop = (int) (-400 + (Math.random() * 401)); // Sets the position between -400 and 0
        this.YPositionBottom = randomYPositionTop + height + gap; // The Y position for the bottom pipe depends on the top one
    }

    public void move(int pipeSpeed) {
        XCoordinate += pipeSpeed; // Move the pipe leftward
    }

    public void draw(Graphics g, JPanel panel) {
        g.drawImage(new ImageIcon("toppipe.png").getImage(), XCoordinate, randomYPositionTop, width, height, panel);
        g.drawImage(new ImageIcon("bottompipe.png").getImage(), XCoordinate, YPositionBottom, width, height, panel);
    }

    public void reset(int newX) {
        XCoordinate = newX;
        randomYPositionTop = (int) (-400 + (Math.random() * 401)); // Random new position for top pipe
        YPositionBottom = randomYPositionTop + height + gap; // Reset bottom pipe position based on the top pipe
    }
}

