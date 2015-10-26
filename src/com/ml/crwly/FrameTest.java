package com.ml.crwly;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FrameTest extends JDialog {


	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane, closePanel, main;
	
	private Point initialClick;
	
	private ImageIcon closeNormal, closeHover, closePress;
	
	private JLabel closeLabel;
	
	public FrameTest (){
		
		super();
		
		initialize();
		
		showWindow();
		
		installListeners();
				
	}
	
	private void initialize(){
//		remove title bar
		setUndecorated(true);
		
//		create the new ContentPane
		contentPane = new JPanel(); setContentPane(contentPane);
		
//		if main has not already created - create it
		if (main == null)
		{
			main = new JPanel();
		}
		
//		Create a panel for close button
		closePanel = new JPanel(new BorderLayout());
		
//		Create point to catch initial mouse click coordinates
		initialClick = new Point();
				
	}
	
	public JPanel getContentPane(){
		return main;
	}
	
	public Component add(Component comp){
		return main.add(comp);
	}
	
	public void setLayout(LayoutManager manager){
		if (main == null)
		{
			main = new JPanel();
			main.setLayout(new FlowLayout());
		}
		else
		{
			main.setLayout(manager);
		}
		
		if (!(getLayout() instanceof BorderLayout))
		{			
			super.setRootPaneCheckingEnabled(false);
			super.setLayout(new BorderLayout());
			super.setRootPane(super.getRootPane());
			super.setRootPaneCheckingEnabled(true);			
		}
		
	}
	
	private void showWindow(){
		
//		if not set, set default to FlowLayout
		if (main.getLayout() == null)
		{
			setLayout(new FlowLayout());
		}
		
//		close "button" - show this image by default
		closeNormal = new ImageIcon("");
		
//		close "button" - show this image when mouse enter is detected
		closeHover = new ImageIcon("");
		
//		close "button" - show this image when mouse press is detected
		closePress = new ImageIcon("");
		
		closeLabel = new JLabel(closeNormal);
		
		closePanel.add(closeLabel, BorderLayout.EAST);
		
		contentPane.setLayout(new BorderLayout());
		contentPane.add(closePanel, BorderLayout.NORTH);
		contentPane.add(main, BorderLayout.CENTER);
		
		contentPane.setBorder(BorderFactory.createRaisedBevelBorder());
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(
				screenSize.width / 2 - (getWidth() / 2),
				screenSize.height / 2 - (getHeight() / 2));
		
		setAlwaysOnTop(true);
	}
	
	private void installListeners()
	{
		addMouseListener( new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				initialClick = e.getPoint(); getComponentAt(initialClick);
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				int thisX = getLocation().x; int thisY = getLocation().y;
				
				int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
				int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);
				
				
				
				int x = thisX + xMoved; int y = thisY+ yMoved; setLocation(x, y);
		
			}
		});
		
		closeLabel.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e)
			{
				closeLabel.setIcon(closePress);
			}
			public void mouseReleased(MouseEvent e)
			{
				closeLabel.setIcon(closeNormal);
			}
			public void mouseEntered(MouseEvent e)
			{
				closeLabel.setIcon(closeHover);
			}
			public void mouseExited(MouseEvent e){
				closeLabel.setIcon(closeNormal);
			}
			public void mouseClicked(MouseEvent e)
			{
				close();
			}
		});
	}
	
	public void close()
	{
		setVisible (false); dispose();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	

}
