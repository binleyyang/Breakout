import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class HighScore extends JPanel {
	
	private Scanner file;
	static JFrame frame;
	static JLabel info;
	static String s = "highscore.txt";

	static HighScore app = new HighScore();

	public static void main(String[] args){
		init();
	}

	static void init(){	
		frame = new JFrame ("HighScores");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.getContentPane().add(new HighScore());
		frame.pack();
		frame.setVisible(true);
		frame.setSize(930,840);		
	}

	public HighScore() {
		
		JButton restart = new JButton("New Game");
		JButton menu = new JButton ("Menu");
		JLabel info = new JLabel("");
		ArrayList records = new ArrayList();
		records = read(s);

		for (int i = 0; i < records.size(); i++) {
			info.setText(info.getText() + " \n" + records.get(i));
		}
		
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
		
		menu.addActionListener(new ActionListener() {	 
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
		
		add(info);
		add(restart);
		add(menu);
	}

	public ArrayList<String> read (String f) {

		ArrayList records = new ArrayList();

		try{
			try {
				file = new Scanner(new File(f));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(file.hasNext()) {
				records.add(file.next());
			}
		}
		catch(NoSuchElementException elementException) {
			System.err.println("No Such Element Error");
			System.exit(1);
		}
		catch (IllegalStateException stateException) {
			System.err.println("Illegal State Error");
			System.exit(1);
		}

		return records;
	}


	public void add(String f, String score) {

		BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new FileWriter(f, true));
			bw.newLine();

			bw.write(score);
			bw.newLine();
			bw.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void close() {
		if (file!= null)
			file.close();
	}
}