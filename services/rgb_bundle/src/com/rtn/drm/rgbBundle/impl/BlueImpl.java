package com.rtn.drm.rgbBundle.impl;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rtn.drm.rgbBundle.IColor;

import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class BlueImpl extends JPanel implements IColor 
{

	private JFrame frame = null;

	public BlueImpl() 
	{
		super();
		setBackground(new Color(0,0,255));
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
	}

	public void draw()
	{
		frame = new JFrame();
		BlueImpl window = new BlueImpl();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(window);
		frame.setSize(1280,720);
		frame.setVisible(true);
	}

	public void close()
	{
		if(frame != null)
		{
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}
}