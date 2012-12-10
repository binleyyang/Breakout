import javax.swing.*;

public class PlayGame {
	
	public static void main (String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Game();
			}
		});
	}
	
	private static void Game() {
		
		Rebound mainPanel = new Rebound();
		
		JFrame frame = new JFrame("Scaffolding");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().add(mainPanel);
	    frame.pack();
	    frame.setLocationByPlatform(true);
	    frame.setVisible(true);
     	frame.setSize(930,800);
	}
}
