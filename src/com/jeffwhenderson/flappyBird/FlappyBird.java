package com.jeffwhenderson.flappyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird implements ActionListener, MouseListener {
	// TODO: add key listener so spacebar can be clicked for the jump action
	// TODO: add a score counter for the number of pipes cleared
	// TODO: add graphics for bird and possibly pipes
	// TODO: WRITE TESTS
	// TODO: refactor. this code is really sloppy and basic. 
	
	public static FlappyBird flappyBird;
	public final int DEFAULT_WIDTH = 700;
	public final int DEFAULT_HEIGHT = 700;
	public Renderer renderer;
	public Rectangle bird;
	public ArrayList<Rectangle> columns;
	public Random rand;
	public boolean gameOver;
	public boolean started;
	public int speed;
	
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
		jframe.addMouseListener(this);
//		jframe.setResizable(1 < 0);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this line will close program out properly
		
		bird = new Rectangle(DEFAULT_WIDTH/2 - 10, DEFAULT_HEIGHT/2 - 10, 20, 20);
		columns = new ArrayList<Rectangle>();
		
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
		
		timer.start();
	}
	
	public void jump() {
		if(gameOver) {
			columns.clear();
			bird = new Rectangle(DEFAULT_WIDTH/2 - 10, DEFAULT_HEIGHT/2 - 10, 20, 20);
			
			yMotion = 0;
			speed = 0;
			
			addColumn(true);
			addColumn(true);
			addColumn(true);
			
			gameOver = false; 
		}
		if(!started) {
			started = true;
		} else if(!gameOver) {
			if(yMotion > 0) {
				yMotion = 0;
			}
			yMotion -= 10;
		}
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
		int speed = 10;
		ticks ++;
		if(started) {
			for(int i =  0; i < columns.size(); i ++) {
				Rectangle column = columns.get(i);
				
				column.x -= speed;
			}
			
			for(int i =  0; i < columns.size(); i ++) {
				Rectangle column = columns.get(i);
				
				if(column.x + column.width < 0) {
					columns.remove(column);
					
					if(column.y== 0) { // question this logic
						addColumn(false);
					}
				}
			}
			
			if(ticks % 2 == 0 && yMotion < 15) {
				yMotion += 2;
			}
			
			bird.y += yMotion;
			
			for(Rectangle column: columns) {
				if(bird.intersects(column)) {
					bird.x = column.x - bird.width;
					gameOver = true;
				}
			}
			if(bird.y > DEFAULT_HEIGHT - 120 - bird.width || bird.y < 0) {
				bird.y = DEFAULT_HEIGHT - 120 - bird.width;
				gameOver = true;
			}
				
		}
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
		
		for(Rectangle column:columns) {
			paintColumn(g, column);
		}
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 100));
		
		if(!started) { // make this game started ????
			g.drawString("Click to Start", 75, DEFAULT_HEIGHT/2 - 50);
		}
		
		if(gameOver) {
			g.drawString("Game Over", 100, DEFAULT_HEIGHT/2 - 50);
		}
	}
	
	public static void main(String[] args) {
		flappyBird = new FlappyBird();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		jump();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
