package com.jeffwhenderson.flappyBird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird implements ActionListener {
	public static FlappyBird flappyBird;
	public final int DEFAULT_WIDTH = 700;
	public final int DEFAULT_HEIGHT = 700;
	public Renderer renderer;
	public Rectangle bird;
	public ArrayList<Rectangle> columns;
	public Random rand;
	
	public int ticks;
	public int yMotion;
	
	public FlappyBird() {
		JFrame jframe = new JFrame();
		Timer timer = new Timer(40, this);
		
		renderer = new Renderer();
		rand = new Random();
		
		jframe.add(renderer);
		jframe.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		jframe.setTitle("Bird that flaps game");
		jframe.setVisible(1 > 0);
		jframe.setResizable(1 < 0);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this line will close program out properly
		
		bird = new Rectangle(DEFAULT_WIDTH/2 - 10, DEFAULT_HEIGHT/2 - 10, 20, 20);
		columns = new ArrayList<Rectangle>();
		
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
		
		timer.start();
	}
	
	public void addColumn(boolean start) {
		int space = 300;
		int width = 100;
		int height = 50 + rand.nextInt(300);
		if(start) {
			columns.add(new Rectangle(DEFAULT_WIDTH + width + columns.size() * 300, DEFAULT_HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(DEFAULT_WIDTH + width + (columns.size() - 1) * 300, 0, width, DEFAULT_HEIGHT - height - space));
		} else {
			columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, DEFAULT_HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, DEFAULT_HEIGHT - height - space));
		}
	}
	
	public void paintColumn(Graphics g, Rectangle column) {
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ticks ++;
		
		if(ticks % 2 == 0 && yMotion < 15) {
			yMotion += 2;
		}
		
		bird.y += yMotion;
		
		renderer.repaint(); // double buffering.googleIt
	}
	
	public void repaint(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		g.setColor(Color.ORANGE);
		g.fillRect(0, DEFAULT_HEIGHT - 120, DEFAULT_WIDTH, 120);
		
		g.setColor(Color.green);
		g.fillRect(0, DEFAULT_HEIGHT - 120, DEFAULT_WIDTH, 20);
		
		g.setColor(Color.red);
		g.fillRect(bird.x, bird.y, bird.width, bird.height);
	}
	
	public static void main(String[] args) {
		flappyBird = new FlappyBird();
	}

}
