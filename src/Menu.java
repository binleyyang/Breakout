import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;

public class Menu extends JPanel {
	
	static JFrame frame;
	static JButton game, highscore;
	private Image picture;
	
	public static void main (String[] args) {
		
		frame = new JFrame ("Breakout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().add(new Menu());
		frame.pack();
		frame.setVisible(true);
		frame.setSize(930, 800);
	}
	
	public Menu (String name) throws IOException {
		
		setLayout(new FlowLayout());
		picture = ImageIO.read(new File(name));
		highscore = new JButton("High Scores");
		game = new JButton("New Game");
		add(game);
		add(highscore);
		
		game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frame.setVisible(false);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						PlayGame.Game();
					}
				});
			}
		});
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(picture, 0, 0, null);
	  }
}