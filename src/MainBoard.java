import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainBoard extends JPanel implements KeyListener, ActionListener{
	
	Bird bird = new Bird();
	Pipes pipes = new Pipes();
	int birdSpeed = 10;
	int pipeSpeed = -5;
    public MainBoard() {
    	 this.setPreferredSize(new Dimension(400, 640));
    	 this.setFocusable(true); 
         this.requestFocusInWindow(); 
    	 this.addKeyListener(this);
    	 new Timer(1000/60, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				birdSpeed -= 1;			//-1 acts as gravity, making the speed lower by each from
				bird.YCoordinate -= birdSpeed;  
				pipes.XCoordinate += pipeSpeed;  //makes the pipes move to the left
				repaint();
			}
    		 
    	 }).start();	//60fps
 
    } 
         

    @Override
    public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //background
            g.drawImage(new ImageIcon("Background.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            //bird
            g.drawImage(new ImageIcon("flappybird.png").getImage(), bird.XCoordinate, bird.YCoordinate, bird.width, bird.heihgt, this);
            //top pipe
            g.drawImage(new ImageIcon("toppipe.png").getImage(), pipes.XCoordinate, pipes.randomYPositionTop, pipes.width, pipes.height, this);
            //bottom pipe
            g.drawImage(new ImageIcon("bottompipe.png").getImage(), pipes.XCoordinate, pipes.YPositionBottom, pipes.width, pipes.height, this);
    }


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			birdSpeed = 10;		//resets the speed
			bird.YCoordinate -= birdSpeed;	//makes the bird jump
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
				
	}
    
	@Override
	public void keyTyped(KeyEvent e) {}


	@Override
	public void keyReleased(KeyEvent e) {}

}
