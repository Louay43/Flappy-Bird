//bird properties

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Bird{
	int YCoordinate = 250;
	int XCoordinate = 30;
	int width = 34;
	int height = 24; 
	
	public void draw(Graphics g, JPanel panel) {
		g.drawImage(new ImageIcon("flappybird.png").getImage(), XCoordinate, YCoordinate, width, height, panel);
    }
	
	public void move(int birdSpeed) {
		this.YCoordinate -= birdSpeed; //moves to the right
    }
	
	public void reset() {
        YCoordinate = 250;
    	XCoordinate = 30;
    }
	
}

	