import javax.swing.*;
import java.io.*;
import java.util.*;

public class HighScore extends JPanel {
	
	private Scanner file;
	static JFrame frame;
	static JLabel info;
	static String f = "highscore.txt";

	static HighScore app = new HighScore();

	public static void main(String[] args){
		Window();
	}

	static void Window(){	
		frame = new JFrame ("HighScores");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.getContentPane().add(new HighScore());
		frame.pack();
		frame.setVisible(true);
		frame.setSize(930,840);		
	}

	public HighScore() {
		JLabel info = new JLabel("");
		ArrayList records = new ArrayList();
		records = read(f);

		for (int i = 0; i < records.size(); i++) {
			info.setText(info.getText() + " \n" + records.get(i));
		}
		add(info);
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


	public void add(String f, String message) {

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(f, true));
			bw.newLine();

			bw.write(message);
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