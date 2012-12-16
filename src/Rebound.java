import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

@SuppressWarnings("serial")
public class Rebound extends JPanel { 
	
	ImageIcon rectangle;
	static JLabel paddle;
	static JPanel frame;
	final JLabel lives = new JLabel ("Lives: 3");
	final JLabel score = new JLabel ("Score: 0");
	
	public Rebound() {
		
		setLayout(new BorderLayout());
		setBackground(Color.white);
		ActionMap actionMap = getActionMap();
		int check = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap inputMap = getInputMap(check);
		
		for (paddleDirection direction : paddleDirection.values()) {
			inputMap.put(direction.getKeyStroke(), direction.getText());
			actionMap.put(direction.getText(), new Movement(direction.getText()));
		}
		
		rectangle = new ImageIcon(getClass().getResource("rectangle.png"));
		Image i1 = rectangle.getImage();
		Image i2 = i1.getScaledInstance(100, 20, Image.SCALE_DEFAULT);
		rectangle.setImage(i2);
		paddle = new JLabel(rectangle);
		
		frame = new JPanel();
		add(frame);
		frame.add(paddle);
		//frame.add(score, BorderLayout.NORTH+50);
		//frame.add(lives, BorderLayout.NORTH);
		paddle.setLocation(400, 741);
	}
	
	private class Movement extends AbstractAction {
		public Movement(String text) {
			super (text);
			putValue(ACTION_COMMAND_KEY, text);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Ball ball = new Ball(frame);
			
			int count = 0;
			int x = paddle.getX();
			String move = e.getActionCommand();
			
			if (move.equals("Left") && x >= 7)
				paddle.setLocation(x-25, 741);
			if (move.equals("Right") && x <= frame.getSize().width-90)
				paddle.setLocation(x+25, 741);
			if (move.equals("Space") && count < 1) {
				ball.start();
				count = 1;
			}
		}
	}
}

class Ball extends Thread {

	int a = Rebound.paddle.getX()+50;
	int b = Rebound.paddle.getY()-9;
	private int vx = 1, vy = 1, x = a, y = b;
	private JPanel box;
	private int d1 = 70, d2= 70, d3 = 70, d4 = 70, d5 = 70, d6 = 70;
	
	public Ball (JPanel m) {
		box = m;
	}
	
	public static Color randColor() {
		Random r = new Random();
		int a = r.nextInt(256);
		int b = r.nextInt(256);
		int c = r.nextInt(256);
		Color x = new Color(a, b, c);
		
		return x;
	}
	
	public void addBall() {
		Graphics g = box.getGraphics();
		g.fillOval(x, y, 10, 10);
		g.dispose();
	}
	
	public void addBricks() {
		Graphics g = box.getGraphics();
		
		for (int column = 0; column <= 60; column += 10) {
			for (int row = 0; row <= 930; row += 155) {
				g.fillRect(row, column, 155, 10);
			}
			g.setColor(randColor());
		}
	}
	
	public void moveBall() {
		int scoreCount = 0;
		Graphics g = box.getGraphics();
		g.setXORMode(box.getBackground());
	    g.fillOval(x, y, 10, 10);
	    x -= vx;
	    y -= vy;
	    Dimension d = box.getSize();
		    
	    if (x < 0) {
	    	x = 0;
	    	vx = -vx;
	    }
		    
	    if (x+10 >= d.width) {
	    	x = d.width - 10;
	    	vx = -vx;
	    }
		    
	   
	    	if (x <= 155) {
	    		 if (y <= d1) {
	    		    y = d1;
	    		    vy = -vy;
	    		    g.clearRect(0, d1-10, 155, 10);
	    		    d1 -= 10;
	    		    scoreCount++;
	    		    //score.setText("" + scoreCount);
	    		 }
	    	}
	    	if (x > 155 && x <= 310) {
	    		 if (y <= d2) {
	    		    	y = d2;
	    		    	vy = -vy;
	    		    	g.clearRect(155, d2-10, 155, 10);
	    		    	d2 -= 10;
	    		    	scoreCount++;
	    		    	//score.setText("" + scoreCount);
	    		 }
	    	}
	    	if (x > 310 && x <= 465) {
	    		 if (y <= d3) {
	    			 y = d3;
	    			 vy = -vy;
	    			 g.clearRect(310, d3-10, 155, 10);
	    			 d3 -= 10;
	    			 scoreCount++;
	    			 //score.setText("" + scoreCount);
	    		 }
	    	}
	    	if (x > 465 && x <= 620) {
	    		 if (y <= d4) {
	    			 y = d4;
	    			 vy = -vy;
	    			 g.clearRect(465, d4-10, 155, 10);
	    			 d4 -= 10;
	    			 scoreCount++;
	    			 //score.setText("" + scoreCount);
	    		 }
	    	}
	    	if (x > 620 && x <= 775) {
	    		 if (y <= d5) {
	    			 y = d5;
	    			 vy = -vy;
	    			 g.clearRect(775, d5-10, 155, 10);
	    			 d5 -= 10;
	    			 scoreCount++;
	    			 //score.setText("" + scoreCount);
	    		 }
	    	}
	    	if (x > 775 && x <= 930) {
	    		 if (y <= d6) {
	    			 y = d6;
	    			 vy = -vy;
	    			 g.clearRect(775, d6-10, 155, 10);
	    			 d6 -= 10;
	    			 scoreCount++;
	    			 //score.setText("" + scoreCount);
	    		 }
	    	}
		    
	    if (y >= box.getSize().height-25 && x >= Rebound.paddle.getX() && x < Rebound.paddle.getX()+100) {
	    	vy = -vy;
		}
		    
	    g.fillOval(x, y, 10, 10);
	    //g.dispose();
	}

	public void run() {
		try {
			addBall();
			addBricks();
			for (int i = 1; i > 0; i++) {
				if (y+10 >= box.getSize().height) {
					System.out.println("Game Over!");
					break;
				}
				moveBall();
				sleep(3);
			}	
		} 
		catch (InterruptedException e) {}
	}
}

enum paddleDirection {
		
	LEFT("Left", KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0)),
	RIGHT("Right", KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0)),
	SPACE("Space", KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0));
 
	private String name;
	private KeyStroke keyStroke;

	paddleDirection (String name, KeyStroke keyStroke) {
	    this.name = name;
	    this.keyStroke = keyStroke;
	}
	
	public String getText() {
		return name;
	}

	public KeyStroke getKeyStroke() {
	   return keyStroke;
	}

	@Override
	public String toString() {
		return name;
    }
}	
	
	

