package com.rtn.drm.test.impl;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelloWorld extends JPanel
{

	public HelloWorld()
	{
		super();
		JLabel label = new JLabel("HEllo WOrld");
		this.add(label);
		JFrame frame = new JFrame();
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
	}

	public void shutdown()
	{
		System.out.println("HelloWorld dead");
	}
}
