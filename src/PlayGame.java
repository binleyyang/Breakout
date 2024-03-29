import javax.swing.*;

public class PlayGame {
	
	public static void main (String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Game();
				ScoreBoard.init();
			}
		});
	}
	
	public static void Game() {
		
		Rebound mainPanel = new Rebound();
		
		JFrame frame = new JFrame("Prison Break");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().add(mainPanel);
	    frame.pack();
	    frame.setLocationByPlatform(true);
	    frame.setVisible(true);
     	frame.setSize(930,800);
	}

	public static void close() {
		JFrame frame = new JFrame("Prison Break");
		frame.setVisible(false);
	}
	

}
