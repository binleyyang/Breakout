import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ScoreBoard extends JPanel {

	static JLabel score, lives;
	static JButton restart;
	static JFrame frame;
	
	public ScoreBoard() {
		setLayout(new FlowLayout());
		
		score = new JLabel("Score: 0");
		lives = new JLabel("Lives: 3");
		restart = new JButton("New Game");
		
		 restart.addActionListener(new ActionListener() {	 
	            public void actionPerformed(ActionEvent e)
	            {
	            	frame.setVisible(false);
	            	SwingUtilities.invokeLater(new Runnable() {
	       	         public void run() {
	       	        	 PlayGame.Game();
	       	        	 init();
	       	        	 Rebound.paddle.setLocation(400, 741);
	       	         }
	       	      });
	            }
	        });   
		
		add(score);
		add(lives);
		add(restart);
	}
	
	public static void init() {
		frame = new JFrame ("Score Board");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.getContentPane().add(new ScoreBoard());
		frame.pack();
		frame.setVisible(true);
		frame.setSize(400, 200);
	}
	
	public static void main (String[] args) {
		init();
	}
}
