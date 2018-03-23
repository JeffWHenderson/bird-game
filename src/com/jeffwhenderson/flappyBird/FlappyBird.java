package com.jeffwhenderson.flappyBird;

import java.awt.Graphics;
import javax.swing.JFrame;

public class FlappyBird {
	public static FlappyBird flappyBird;
	public final int DEFAULT_WIDTH = 700;
	public final int DEFAULT_HEIGHT = 700;
	public Renderer renderer;
	
	public FlappyBird() {
		JFrame jframe = new JFrame();
		renderer = new Renderer();
		
		jframe.add(renderer);
		jframe.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		jframe.setVisible(1 > 0);
		// jframe.setResizable(1 < 0);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this line will close program out properly
	}
	
	public void repaint(Graphics arg0) {
		System.out.print("repaint method isn't doing much");
	}
	
	public static void main(String[] args) {
		flappyBird = new FlappyBird();
	}

}
