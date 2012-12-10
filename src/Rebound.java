import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import acm.graphics.*;
import acm.util.*;

@SuppressWarnings("serial")
public class Rebound extends JPanel { 
	
	private GRect brick;
	private GOval ball;
	ImageIcon rectangle;
	static JLabel paddle;
	static JLabel ball1;
	private double vx, vy;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	public void init() {
		setLayout(new BorderLayout());
		ActionMap actionMap = getActionMap();
		int check = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap inputMap = getInputMap(check);
		
		for (Direction direction : Direction.values()) {
			inputMap.put(direction.getKeyStroke(), direction.getText());
			actionMap.put(direction.getText(), new Movement(direction.getText()));
		}
		
		rectangle = new ImageIcon(getClass().getResource("rectangle.png"));
		Image i1 = rectangle.getImage();
		Image i2 = i1.getScaledInstance(100, 20, Image.SCALE_DEFAULT);
		rectangle.setImage(i2);
		paddle = new JLabel(rectangle);
		
		//drawing the ball
		double x = getWidth()/2 - 10;
		double y = getHeight()/2 - 10;
		ball = new GOval(x, y, 10, 10);
		ball.setFilled(true);
		add(ball);
		
		add(paddle, BorderLayout.SOUTH);
	}
	
	public Rebound() {
		
		init();
		moveBall();
		
		//set velocity for the ball's x and y
		vx = 1;
		vy = 2;
	}
	
	private void moveBall() {
		double x = ball1.getX();
		double y = ball1.getY();
		
		if (x == 0 || x == getWidth() - 10)
			vx = -vx;
		if (y == 0 || y == getHeight() -10)
			vy = -vy;
		
		ball.move(vx, vy);
	}
	
	private void addBricks() {
		
	}
	
	private class Movement extends AbstractAction {
		public Movement(String text) {
			super (text);
			putValue(ACTION_COMMAND_KEY, text);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int x = paddle.getX();
			int y = paddle.getY();
			String move = e.getActionCommand();
			
			if (move.equals("Left"))
				paddle.setLocation(x-10, 741);
			if (move.equals("Right"))
				paddle.setLocation(x+10, 741);
			
			repaint();
		}
	}
	
	enum Direction {
			
		LEFT("Left", KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0)),
		RIGHT("Right", KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));

		Direction(String text, KeyStroke keyStroke) {
		    this.name = name;
		    this.keyStroke = keyStroke;
		}
		   
		private String name;
		private KeyStroke keyStroke;

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
}
