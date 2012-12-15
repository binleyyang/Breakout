import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

@SuppressWarnings("serial")
public class Rebound extends JPanel { 
	
	ImageIcon rectangle;
	static JLabel paddle;
	static JPanel frame;
	
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
		add(frame, BorderLayout.CENTER);
		frame.add(paddle, BorderLayout.SOUTH);
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
			ball.addBricks();
			
			int count = 0;
			int x = paddle.getX();
			String move = e.getActionCommand();
			
			if (move.equals("Left") && x >= 7)
				paddle.setLocation(x-30, 741);
			if (move.equals("Right") && x <= frame.getSize().width-90)
				paddle.setLocation(x+30, 741);
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
	private int vx = 2, vy = 2, x = a, y = b;
	private JPanel box;
	
	public Ball (JPanel m) {
		box = m;
	}
	
	/*
	public static Color randColor() {
		Random r = new Random();
		int a = r.nextInt(256);
		int b = r.nextInt(256);
		int c = r.nextInt(256);
		Color x = new Color(a, b, c);
		
		return x;
	}
	*/
	
	public void addBall() {
		Graphics g = box.getGraphics();
		g.fillOval(x, y, 10, 10);
		g.dispose();
	}
	
	public void addBricks() {
		Graphics g = box.getGraphics();
		
		for (int column = 0; column <= 90; column += 10) {
			for (int row = 0; row <= 930; row += 155) {
				g.fillRect(row, column, 155, 10);
			
				if (column == 0 || column == 10) 
					g.setColor(Color.RED);
				if (column == 20 || column == 30)
					g.setColor(Color.ORANGE);
				if (column == 40 || column == 50)
					g.setColor(Color.YELLOW);
				if (column == 60 || column == 70)
					g.setColor(Color.GREEN);
				if (column == 80 || column == 90)
					g.setColor(Color.CYAN);
			}
		}
	}
	
	public void moveBall() {
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
		    
	    if (y <= 90) {
	    	y = 90;
	    	vy = -vy;
	    	if (x <= 155) {
	    		g.setColor(Color.white);
	    		g.fillRect(0, 60, 155, 10);
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
			for (int i = 1; i > 0; i++) {
				if (y+10 >= box.getSize().height) {
					System.out.println("Game Over!");
					break;
				}
				moveBall();
				sleep(4);
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
	
	

