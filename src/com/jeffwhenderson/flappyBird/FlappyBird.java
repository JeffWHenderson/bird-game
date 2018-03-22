package com.jeffwhenderson.flappyBird;

import javax.swing.JFrame;

public class FlappyBird {
	public static FlappyBird flappyBird;
	public final int DEFAULT_WIDTH = 700;
	public final int DEFAULT_HEIGHT = 700;
	
	public FlappyBird() {
		JFrame jframe = new JFrame();
		
		jframe.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		jframe.setVisible(1 > 0);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this line will close program out properly
	}
	
	public static void main(String[] args) {
		flappyBird = new FlappyBird();
	}
}
