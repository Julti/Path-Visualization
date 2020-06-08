package com.main.canvas.canvas;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.main.structure.Graph;

/**
 * Hello world!
 *
 */
public class App implements MouseListener,KeyListener,MouseMotionListener,ActionListener
{
	private boolean sourceset = false;
	private boolean destset = false;
	int source = 0;
	int destination = 0;
	int square_size = 30;
	int grid_size = 30;
	CanvasT c;
	ArrayList<Integer>[] G ;
	HashSet<Integer> prohibited = new HashSet<Integer>();
	public App() {
		c = new CanvasT();
		
		draw();
	}

    public static void main( String[] args )
    {
        new App();
    }
    public void draw() {
    	JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(new BorderLayout());
        jf.setSize(1000,1000);
        jf.setTitle("Path Visualizer");
        c.addKeyListener(this);
        c.addMouseListener(this);
        c.addMouseMotionListener(this);
        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);
        bottom.setLayout(new GridLayout(1,4));
        JButton dfs = new JButton("DFS");
        dfs.setActionCommand("dfs");
        dfs.addActionListener(this);
        JButton bfs = new JButton("BFS");
        bfs.setActionCommand("bfs");
        bfs.addActionListener(this);
        bottom.add(new JPanel());
        bottom.add(dfs);
        bottom.add(bfs);
        bottom.add(new JPanel());
        jf.add(c,BorderLayout.CENTER);
        jf.add(bottom,BorderLayout.SOUTH);
        jf.setVisible(true);
    }
   
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		c.x_source=(e.getX()/square_size)*square_size;
		c.y_source =(e.getY()/square_size)*square_size;
		Graphics g = c.getGraphics();
		if(!sourceset) {
			g.setColor(Color.ORANGE);
			sourceset=true;
			source =(e.getY()/square_size*grid_size)+(e.getX()/square_size);
			g.fill3DRect(c.x_source, c.y_source, square_size, square_size, true);
		}else if(!destset) {
			g.setColor(Color.GREEN);
			destset=true;
			destination =(e.getY()/square_size*grid_size)+(e.getX()/square_size);
			g.fill3DRect(c.x_source, c.y_source, square_size, square_size, true);
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
		System.out.println();
		if(e.getExtendedKeyCode()==e.VK_0) {
			Graph g = new Graph(c, source, destination, square_size, grid_size, prohibited);
			g.buildGraph(0);
		}else if(e.getExtendedKeyCode()==e.VK_1) {
			Graph g = new Graph(c, source, destination, square_size, grid_size, prohibited);
			g.buildGraph(1);
		}else if(e.getExtendedKeyCode()==e.VK_2) {
			Graph g = new Graph(c, source, destination, square_size, grid_size, prohibited);
			g.buildGraph(2);
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		if(sourceset&&destset) {
			c.x_source=(arg0.getX()/square_size)*square_size;
			c.y_source =(arg0.getY()/square_size)*square_size;
			int point =(arg0.getY()/square_size*grid_size)+(arg0.getX()/square_size);
			if(!prohibited.contains(point))prohibited.add(point);
			Graphics g = c.getGraphics();
			g.fill3DRect(c.x_source, c.y_source, square_size, square_size, true);
		}
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Graph g = new Graph(c, source, destination, square_size, grid_size, prohibited);
		
		switch (e.getActionCommand()) {
		case "dfs":
			g.buildGraph(0);
			break;
		case "bfs":
			g.buildGraph(1);
			break;
		default:
			break;
		}
		
	}
}
