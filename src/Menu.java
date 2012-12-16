import java.awt.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;

import java.io.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Menu extends JPanel{

	static JButton restart, highscore, music;
	private Image picture;
	static JFrame frame;

	public static void main (String[] args) throws IOException{
		init();
	}
	
	static void init() throws IOException {
		frame = new JFrame ("Prison Break");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.getContentPane().add(new Menu("background.jpg"));
		frame.pack();
		frame.setVisible(true);
		frame.setSize(930, 800);
	}

	public Menu(String name) throws IOException{
		setLayout(new FlowLayout());
	    picture = ImageIO.read(new File(name));
	    restart = new JButton("New Game");
	    highscore = new JButton("High Scores"); 
	    music = new JButton("Play Song");
	    add(restart);
	    add(highscore);
	    add(music);
	    
	    music.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
					Music();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    });

	    restart.addActionListener(new ActionListener() {	 
            public void actionPerformed(ActionEvent e)
            {
            	frame.setVisible(false);
            	SwingUtilities.invokeLater(new Runnable() {
       	         public void run() {
       	        	 PlayGame.Game();
       	        	 ScoreBoard.init();
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
	}

	public void Music() throws Exception {
		try {
			AudioInputStream music = AudioSystem.getAudioInputStream(new File("levels.wav"));
			
			Clip clip = AudioSystem.getClip();
			clip.open(music);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (IOException error) {
			System.out.println("File Not Found!");
		}
	}

	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(picture, 0, 0, null);
	  }
}