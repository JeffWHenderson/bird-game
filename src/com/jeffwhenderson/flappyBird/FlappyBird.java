package com.jeffwhenderson.flappyBird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird implements ActionListener {
	public static FlappyBird flappyBird;
	public final int DEFAULT_WIDTH = 700;
	public final int DEFAULT_HEIGHT = 700;
	public Renderer renderer;
	public Rectangle bird;
	
	public FlappyBird() {
		JFrame jframe = new JFrame();
		Timer timer = new Timer(40, this);
		
		renderer = new Renderer();
		
		jframe.add(renderer);
		jframe.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		jframe.setVisible(1 > 0);
		// jframe.setResizable(1 < 0);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this line will close program out properly
		
		bird = new Rectangle(DEFAULT_WIDTH/2 - 10, DEFAULT_HEIGHT/2 - 10, 20, 20);
		
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		renderer.repaint(); // double buffering.googleIt
		
	}
	
	public void repaint(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		g.setColor(Color.ORANGE);
		g.fillRect(0, DEFAULT_HEIGHT - 150, DEFAULT_WIDTH, 150);
		
		g.setColor(Color.green);
		g.fillRect(0, DEFAULT_HEIGHT - 150, DEFAULT_WIDTH, 20);
		
		g.setColor(Color.red);
		g.fillRect(bird.x, bird.y, bird.width, bird.height);
	}
	
	public static void main(String[] args) {
		flappyBird = new FlappyBird();
	}

}
