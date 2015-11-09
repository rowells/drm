package com.rtn.drm.test.dateuser.impl;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rtn.drm.test.date.service.DateServiceIF;

public class DateServiceUser extends JPanel
{
	private DateServiceIF dateService = null;
	private JFrame frame = null;
	private GroupLayout layout = null;
	public DateServiceUser()
	{
		super();
		System.out.println(this.getClass().getSimpleName() + " CTOR");
		
	}

	public void setDateService(DateServiceIF dateService)
	{
		System.out.println(this.getClass().getSimpleName() + " SET DATE SERVICE");
		this.dateService = dateService;
	}
	public void init()
	{
		System.out.println(this.getClass().getSimpleName() + " INIT");
		JButton dateButton = new JButton("GET DATE: ");
		JLabel dateLabel = new JLabel();

		dateButton.addActionListener(new ActionListener(){

			@Override
         public void actionPerformed(ActionEvent arg0)
         {
				EventQueue.invokeLater(new Runnable(){

					@Override
               public void run()
               {
						if (dateService == null)
			         {
			         	dateLabel.setText("ERROR");
			         }
						else
						{
							dateLabel.setText(dateService.getCurrentDate().toLocaleString());
						}
						dateLabel.repaint();
	               
               }});
	         
         }});
		layout = new GroupLayout(this);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		this.setLayout(layout);

		layout.setHorizontalGroup(layout.createSequentialGroup()
		      .addComponent(dateButton).addComponent(dateLabel, 100, 100, 100));
		layout.setVerticalGroup(layout.createParallelGroup()
		      .addComponent(dateButton).addComponent(dateLabel));
		frame = new JFrame();
		frame.getContentPane().add(this);
		frame.setSize(new Dimension(500,240));
		System.out.println(this.getClass().getSimpleName() + " initted, setting frame visible");
		frame.setVisible(true);
	}
	public void shutdown()
	{
		this.setDateService(null);
		frame = null;
		layout = null;
	}
}
