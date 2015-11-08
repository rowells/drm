package com.rtn.drm.simpleGraphics.impl;

import com.rtn.drm.simpleGraphics.ISimpleGraphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JLabel;
//import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.Color;
//import java.awt.BorderLayout; 

public class simpleGraphicsImpl extends JPanel implements ISimpleGraphics {
	
	private JFrame frame = null;
	
	public simpleGraphicsImpl() {
		super();
		setBackground(new Color(255,255,255));
	}
	
	public void paintComponent(Graphics graphics) {
		//int window_width = getWidth();
		//int window_height = getHeight();
		super.paintComponent(graphics);
		
		// Draw Something
		graphics.setColor(new Color(50,100,255));
		graphics.drawOval(270, 206, 100, 100);
	}
	
	public void draw() {
		frame = new JFrame();
		simpleGraphicsImpl window = new simpleGraphicsImpl();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(window);
		frame.setSize(640,512);
		frame.setVisible(true);
	}
	
	public void close() {
		if(frame != null) {
			// Behaves as if the window's "X" was clicked.
			frame.dispatchEvent(
					new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}
}
