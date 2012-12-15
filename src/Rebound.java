import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
			
			int x = paddle.getX();
			String move = e.getActionCommand();
			
			if (move.equals("Left"))
				paddle.setLocation(x-20, 741);
			if (move.equals("Right"))
				paddle.setLocation(x+20, 741);
			if (move.equals("Space")) {
				Ball ball = new Ball(frame);
				ball.start();
			}
		}
	}
}

class Ball extends Thread {

	private int vx = 2, vy = 2, x = 400, y = 400;
	private JPanel box;
	
	public Ball (JPanel m) {
		box = m;
	}
	
	public void addBall() {
		Graphics g = box.getGraphics();
		int a = Rebound.paddle.getX();
		int b = Rebound.paddle.getY();
		g.fillOval(x, y, 10, 10);
		g.dispose();
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
		    
	    if (y < 0) {
	    	y = 0;
	    	vy = -vy;
	    }
		    
	    if (y+10 >= d.height) {
	    	y = d.height - 10;
	    	vy = -vy;
		}
		    
	    g.fillOval(x, y, 10, 10);
	    g.dispose();
	}

	public void run() {
		try {
			addBall();
			for (int i = 1; i > 0; i++) {
				  moveBall();
				  sleep(5);
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
	
	

