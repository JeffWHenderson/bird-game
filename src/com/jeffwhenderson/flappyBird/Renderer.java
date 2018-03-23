package com.jeffwhenderson.flappyBird;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Renderer extends JPanel {
	//Normally I'd just suppress the serialize warning but i was curious
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		FlappyBird.flappyBird.repaint(arg0);
	}
}
