import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainBoard extends JPanel implements KeyListener, ActionListener{
	
	Bird bird = new Bird();
	ArrayList<Pipes> pipes = new ArrayList<>(); // Around 4 pipes can fit the screen
	int birdSpeed = 10;
	int pipeSpeed = -5;
	 
    public MainBoard() {
    	 this.setPreferredSize(new Dimension(400, 640));
    	 this.setFocusable(true); 
         this.requestFocusInWindow(); 
    	 this.addKeyListener(this);
    	 
    	 pipes.add(new Pipes(400));
         pipes.add(new Pipes(600));
         pipes.add(new Pipes(800));
         pipes.add(new Pipes(1000));
         
    	 new Timer(1000/60, this).start();	//60fps 
    } 
         

    @Override
    public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //background
            g.drawImage(new ImageIcon("Background.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            
            //bird
            bird.draw(g, this);
            
            //pipes
            for (Pipes pipe : pipes) {
                pipe.draw(g, this);
            }
    }


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			birdSpeed = 10;		//resets the speed
			bird.move(birdSpeed);;	//makes the bird jump
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		birdSpeed -= 1;			//-1 acts as gravity, making the speed lower by each from
		bird.move(birdSpeed);
		bird.YCoordinate = Math.max(bird.YCoordinate, 0);	//bird can't get off the frame
		
		
		// Move and reset pipes if they go off-screen
        for (Pipes pipe : pipes) {
            pipe.move(pipeSpeed);
            if (pipe.XCoordinate + pipe.width < 0) {
                pipe.reset(740); // Reset pipe position to the right side
            }
        }
        
		repaint();
	}
    
	@Override
	public void keyTyped(KeyEvent e) {}


	@Override
	public void keyReleased(KeyEvent e) {}

}
