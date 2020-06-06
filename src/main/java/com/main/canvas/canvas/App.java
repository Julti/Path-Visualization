package com.main.canvas.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Hello world!
 *
 */
public class App implements MouseListener,KeyListener,MouseMotionListener
{
	private boolean sourceset = false;
	private boolean destset = false;
	CanvasT c;
	public App() {
		c = new CanvasT();
		draw();
	}
	ArrayList<Integer>[] G = new ArrayList[50*50];
    public static void main( String[] args )
    {
        new App();
    }
    public void draw() {
    	JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(1000,1000);
        jf.setTitle("Path Visualizer");
        jf.addKeyListener(this);
        c.addMouseListener(this);
        c.addMouseMotionListener(this);
        jf.add(c);
        jf.setVisible(true);
    }
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		c.x_source=(e.getX()/50)*50;
		c.y_source =(e.getY()/50)*50;
		Graphics g = c.getGraphics();
		if(!sourceset) {
			g.setColor(Color.ORANGE);
			sourceset=true;
			g.fill3DRect(c.x_source, c.y_source, 50, 50, true);
		}else if(!destset) {
			g.setColor(Color.GREEN);
			destset=true;
			g.fill3DRect(c.x_source, c.y_source, 50, 50, true);
		}
		
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getX());
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		c.x_source=(arg0.getX()/50)*50;
		c.y_source =(arg0.getY()/50)*50;
		Graphics g = c.getGraphics();
		g.fill3DRect(c.x_source, c.y_source, 50, 50, true);
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
}
