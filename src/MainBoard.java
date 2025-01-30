import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainBoard extends JPanel implements KeyListener, ActionListener {

    Bird bird = new Bird();
    ArrayList<Pipes> pipes = new ArrayList<>(); // Around 4 pipes can fit the screen
    int birdSpeed = 0;
    int pipeSpeed = -5;
    Boolean collision = false;
    Integer score = 0;
    Timer timer;
    
    // label to store the score
    JLabel label = new JLabel("Score: " + String.valueOf(score));
    
    //label to check score history and replay
    JLabel replay = new JLabel("<html>Press space to restart<br>Press S to the Score Board</html>");	//HTML tags makes it easier for line break
    
    // Set to keep track of pipes that the bird has passed
    Set<Pipes> passedPipes = new HashSet<>();
    
    
    public MainBoard() {
        this.setPreferredSize(new Dimension(400, 640));
        this.setFocusable(true); 
        this.requestFocusInWindow(); 
        this.addKeyListener(this);
        this.setLayout(null);
        
        // Initialize score label
        label.setFont(new Font("Fontsquirrel", Font.BOLD, 25));
        label.setForeground(Color.BLUE);
        label.setBounds(0, 0, 200, 50);
        this.add(label);
        
        // Initialize replay label
        replay.setFont(new Font("Fontsquirrel", Font.BOLD, 25));
        replay.setForeground(Color.DARK_GRAY);
        replay.setBounds(5, 320, 400, 100);
        replay.setHorizontalAlignment(JLabel.CENTER); 
        replay.setVerticalAlignment(JLabel.CENTER); 
        
        pipes.add(new Pipes(400));
        pipes.add(new Pipes(600));
        pipes.add(new Pipes(800));
        pipes.add(new Pipes(1000));
        
        timer = new Timer(1000 / 60, this); // 60fps
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // background
        g.drawImage(new ImageIcon("Background.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);

        // bird
        bird.draw(g, this);

        // pipes
        for (Pipes pipe : pipes) {
            pipe.draw(g, this);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        birdSpeed -= 1; // -1 acts as gravity, making the speed lower by each from
        bird.move(birdSpeed);
        bird.YCoordinate = Math.max(bird.YCoordinate, 0); // bird can't get off the frame
        
        // Move and reset pipes if they go off-screen
        for (Pipes pipe : pipes) {
            pipe.move(pipeSpeed);
            if (pipe.XCoordinate + pipe.width < 0) {
                pipe.reset(740); // Reset pipe position to the right side
                passedPipes.clear(); // Clear passed pipes when they reset
            }
          
            if (pipeNextToBird(pipe)) {
                collision = true;
                break;
            }

            // Track if the bird has passed the pipe (only once per pipe)
            if (birdPassed(pipe) && !passedPipes.contains(pipe)) {
                passedPipes.add(pipe); // Mark the pipe as passed
                score++;
                label.setText("Score: " + score); // Update the score label
            }
        }

        if (collision) {
            timer.stop();
            this.add(replay);
        }
        
        repaint();
    }

    public Boolean pipeNextToBird(Pipes pipe) {
        Boolean horizontalCollision = (bird.XCoordinate + bird.width >= pipe.XCoordinate && pipe.XCoordinate + pipe.width > bird.XCoordinate);
        Boolean verticalCollision = (pipe.YPositionBottom < bird.YCoordinate + bird.height || pipe.randomYPositionTop + pipe.height > bird.YCoordinate);
        Boolean fellDown = bird.YCoordinate + bird.height >= 640;
        return (horizontalCollision && verticalCollision) || fellDown;
    }

    public Boolean birdPassed(Pipes pipe) {
        // Check if the bird's X-coordinate has crossed the pipe's position.
        return bird.XCoordinate + bird.width > pipe.XCoordinate && !passedPipes.contains(pipe);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            birdSpeed = 10; // resets the speed
            bird.move(birdSpeed); // makes the bird jump
            if(collision) {
            	restart();
            }
        }
        
        else if(e.getKeyCode() == KeyEvent.VK_S && collision) {
        	//ScoreBoard scoreBoard = new ScoreBoard();
        }
    }
    
    public void restart() {
        // Reset the bird
        bird.reset();			

        // Reset the pipes and add them back
        pipes.clear();
        pipes.add(new Pipes(400));
        pipes.add(new Pipes(600));
        pipes.add(new Pipes(800));
        pipes.add(new Pipes(1000));

        // Reset passed pipes
        passedPipes.clear();

        birdSpeed = 0;
        collision = false;
        score = 0;

        label.setText("Score: " + String.valueOf(score));

        this.remove(replay);

        timer.start();

        repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}

