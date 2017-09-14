package snake;

import javax.swing.JFrame;

public class Snake {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame (); //按住 ctrl + shift + o 可以把包印出来
		frame.setBounds(10, 10, 900, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SnakePanel panel = new SnakePanel ();
		frame.add(panel);
		
		frame.setVisible(true);
		

	}

}
