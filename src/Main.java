
import java.io.IOException;
import javax.swing.*;

public class Main {
	public static void main(String[] args) throws IOException{
		//Setting up the frame
		JFrame frame = new JFrame("Flappy Bird");
		frame.setIconImage(new ImageIcon((".\\flappybird.png")).getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 640);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
		
		frame.add(new MainBoard());
		
		frame.setVisible(true);
		   
		
	}
}
