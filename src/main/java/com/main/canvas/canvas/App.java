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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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
	int source = 0;
	int destination = 0;
	CanvasT c;
	ArrayList<Integer>[] G ;
	HashSet<Integer> prohibited = new HashSet<Integer>();
	public App() {
		c = new CanvasT();
		G= new ArrayList[20*20];
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
        jf.setSize(1100,1100);
        jf.setTitle("Path Visualizer");
        c.addKeyListener(this);
        c.addMouseListener(this);
        c.addMouseMotionListener(this);
        jf.add(c,BorderLayout.CENTER);
        jf.setVisible(true);
      
    }
    private void buildGraph() {
    	for (int i = 0; i < G.length; i++) {
    		G[i]=new ArrayList<Integer>();
    		if(!prohibited.contains(i)) {
    			if(i>=20&&i%20!=0&&i%20!=19&&i<20*19) {
    				if(!prohibited.contains(i+1))G[i].add(i+1);
    				if(!prohibited.contains(i-1))G[i].add(i-1);
    				if(!prohibited.contains(i+20))G[i].add(i+20);
    				if(!prohibited.contains(i-20))G[i].add(i-20);
    			}else if(i<20) {
    				if(!prohibited.contains(i+20))G[i].add(i+20);
    				if(i!=0) {
    					if(!prohibited.contains(i-1))G[i].add(i-1);
    				}
    				if(i!=19) {
    					if(!prohibited.contains(i+1))G[i].add(i+1);
    				}
    			}else if(i>=20*19) {
    				if(!prohibited.contains(i-20))G[i].add(i-20);
    				if(i!=20*19) {
    					if(!prohibited.contains(i-1))G[i].add(i-1);
    				}
    				if(i!=((20*20)-1)) {
    					if(!prohibited.contains(i+1))G[i].add(i+1);
    				}
    			}else if(i%20==0) {
    				if(!prohibited.contains(i+1))G[i].add(i+1);
    				if(!prohibited.contains(i+20))G[i].add(i+20);
    				if(!prohibited.contains(i-20))G[i].add(i-20);
    			}else if(i%20==19) {
    				if(!prohibited.contains(i+1))G[i].add(i-1);
    				if(!prohibited.contains(i+20))G[i].add(i+20);
    				if(!prohibited.contains(i-20))G[i].add(i-20);
    			}
    		}
			
		}
    	dfs(source);
    }
    HashSet<Integer> visited = new HashSet<Integer>();
    boolean hasfin = false;
	public void dfs(int origin) {
		System.out.println(origin+"<<");
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!visited.contains(origin)&&!hasfin) {
			visited.add(origin);
			if(origin!=source&&origin!=destination)recolor(origin);
			if(origin==destination)hasfin=true;
			ArrayList<Integer> x = G[origin];
			for (int i = 0; i < x.size(); i++) {
				if(!visited.contains(x.get(i))&&!prohibited.contains(x.get(i))) {
					dfs(x.get(i));
				}
			}
		}
	}
	public void recolor(int b) {
		Graphics g = c.getGraphics();
		g.setColor(Color.CYAN);
		int y = b/20;
		int x = b-(y*20);
		System.out.println(x+"-"+y);
		g.fill3DRect(x*50,y*50, 50, 50, true);
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
			source =(e.getY()/50*20)+(e.getX()/50);
			g.fill3DRect(c.x_source, c.y_source, 50, 50, true);
		}else if(!destset) {
			g.setColor(Color.GREEN);
			destset=true;
			destination =(e.getY()/50*20)+(e.getX()/50);
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
		if(e.getExtendedKeyCode()==e.VK_ENTER) {
			buildGraph();
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		c.x_source=(arg0.getX()/50)*50;
		c.y_source =(arg0.getY()/50)*50;
		int point =(arg0.getY()/50*20)+(arg0.getX()/50);
		if(!prohibited.contains(point))prohibited.add(point);
		Graphics g = c.getGraphics();
		g.fill3DRect(c.x_source, c.y_source, 50, 50, true);
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
}
