import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class GameOver extends JPanel{

	static JFrame frame;
	static JLabel message;
	private Image picture;
	static JButton restart, highscore, home;
	static String text;

	 public static void main(String[] args) {
	      SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	        	 try {
					init();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         }
	      });
	   }

	static void init() throws IOException {

		frame = new JFrame ("Game Over");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.getContentPane().add(new GameOver("gameover.jpg"));
		frame.pack();
		frame.setVisible(true);
		frame.setSize(930,840);	

	}
	
	public GameOver(String filename) throws IOException{
		
		text = JOptionPane.showInputDialog(null, "Please enter your name.");
		String file = "highscore.txt";
		HighScore.app.add(file, text + " - " + Ball.scoreCount);
		setLayout(new FlowLayout());
	    picture = ImageIO.read(new File(filename));
	    message = new JLabel("Game Over " + text + "! " + "Your score: " + Ball.scoreCount + ". Lives left: " + Ball.lives + ".");
	    message.setForeground(Color.YELLOW);
	    message.setFont(new Font("Serif", Font.BOLD, 24));
	    restart = new JButton("New Game");
	    highscore = new JButton("High Scores"); 
	    home = new JButton("Home");
	    
	    home.addActionListener(new ActionListener() {	 
            public void actionPerformed(ActionEvent e)
            {
            	frame.setVisible(false);
            	SwingUtilities.invokeLater(new Runnable() {
       	         public void run() {
       	           try {
					Menu.init();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
       	         }
       	      });
            }
        });

	    restart.addActionListener(new ActionListener() {	 
            public void actionPerformed(ActionEvent e)
            {
            	frame.setVisible(false);
            	SwingUtilities.invokeLater(new Runnable() {
       	         public void run() {
       	        	 PlayGame.Game();
       	        	 Rebound.paddle.setLocation(400, 741);
       	         }
       	      });
            }
        });    
	    
	    highscore.addActionListener(new ActionListener() {	 
            public void actionPerformed(ActionEvent e)
            {
            	frame.setVisible(false);
            	SwingUtilities.invokeLater(new Runnable() {
       	         public void run() {
       	        	 HighScore.init();
       	         }
       	      });
            }
        }); 

	    add(message);
	    add(restart);
	    add(home);
	    add(highscore);
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(picture, 0, 0, null);
	  }

}
