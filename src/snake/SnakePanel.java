package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.xml.stream.events.StartDocument;

public class SnakePanel extends JPanel implements KeyListener,ActionListener {
	
	ImageIcon up = new ImageIcon("up.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon title = new ImageIcon("title.jpg");
	ImageIcon body = new ImageIcon("body.png");
	ImageIcon food = new ImageIcon("food.png");
	
	int[] snakex = new int[750];
	int[] snakey = new int[750];
	int len ;
	String direction;
	
	boolean isfailed = false;
	
	Random rand = new Random();
	int foodx = rand.nextInt(34) * 25 + 25;
	int foody = rand.nextInt(24) * 25 + 75;
	
	Timer timer = new Timer(100 , this) ; // 为什么不可以先声明，然后再new 呢
	
	
	boolean isstart = false;
	
	public SnakePanel (){
		this.setFocusable(true);
		this.addKeyListener(this);
		setup();
		timer.start();
	}
	
	public void  paint (Graphics g){
		this.setBackground(Color.BLACK); // 这个好像没有起到什么作用！！！
		title.paintIcon(this, g, 25, 11);
		g.fillRect(25, 75, 850, 650);// 画出黑框框
		
		food.paintIcon(this, g, foodx, foody);
		
		if (direction.equals("R")){
			right.paintIcon(this, g, snakex[0], snakey[0]);
		}
		else if (direction.equals("L")){
			left.paintIcon(this, g, snakex[0], snakey[0]);
		}
		else if (direction.equals("D")){
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}
		else if (direction.equals("U")){
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}
		
		for (int i = 0; i < len; i++)
		{
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		
		if (isstart == false){
			g.setColor(Color.WHITE);
			g.setFont(new Font ("arial", Font.BOLD, 30));
			g.drawString ("press space to start/pause",250, 300);
		}
		
		if (isfailed == true){
			g.setColor(Color.WHITE);
			g.setFont(new Font ("arial", Font.BOLD, 30));
			g.drawString ("you are failed, press space to restart",250, 300);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font ("arial",Font.BOLD, 25));
		g.drawString("score: " + (len - 3) * 10, 600, 50);
	}

	public void setup (){
		isfailed = false;
		isstart = false;
		len = 3;
		direction = "R";
		snakex[0] = 100;
		snakey[0] = 100;
		snakex[1] = 75;
		snakey[1] = 100;
		snakex[2] = 50;
		snakey[2] = 100;
		
	}
	
	public void eatfood (){
		if (snakex[0] == foodx && snakey[0] == foody)
		{
			len++;
			foodx = rand.nextInt(34) * 25 + 25;
			foody = rand.nextInt(24) * 25 + 75;
			
		}
	}
	public boolean isdead (){
		if (snakex[0] >= 850 || snakex[0] <= 25 || snakey[0] > 650 || snakey[0] <= 75)
			return true;
		for (int i = 1; i < len; i++){
			if (snakex[0] == snakex[i] && snakey[0] == snakey[i])
				return true;
		}
		return false;
			
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		if (keycode == KeyEvent.VK_SPACE){
			if (isfailed){
				setup();
			}else
				isstart = !isstart;
			repaint();
		} else if (keycode == KeyEvent.VK_RIGHT){
			if (direction != "L")
				direction = "R";
		} else if (keycode == KeyEvent.VK_LEFT){
			if (direction != "R")
				direction = "L";
		} else if (keycode == KeyEvent.VK_DOWN){
			if (direction != "U")
				direction = "D";
		} else if (keycode == KeyEvent.VK_UP){
			if (direction != "D")
				direction = "U";
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();
		
		if (isstart && !isfailed){
			for (int i =len ; i > 0; i--){
				snakex[i] = snakex[i - 1];
				snakey[i] = snakey[i - 1];
			}
			if (direction.equals("R")){
				snakex[0] = snakex[0] + 25;
//				if (snakex[0] > 850)
//					isfailed = true;
			}
			else if (direction.equals("L")){
				snakex[0] = snakex[0] - 25;
//				if (snakex[0] < 25)
//					snakex[0] = 850;
			}
			else if (direction.equals("D")){
				snakey[0] = snakey[0] + 25;
//				if (snakey[0] > 650)
//					snakey[0] = 75;
			}
			else if (direction.equals("U")){
				snakey[0] = snakey[0] - 25;
//				if (snakey[0] < 75)
//					snakey[0] = 650;
			}
		}
		eatfood();
		isfailed = isdead ();
		
		repaint ();
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}


}
