package com.main.structure;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import com.main.canvas.canvas.CanvasT;

public class Graph {
	ArrayList<Integer>[] G ;
	int source = 0;
	int destination = 0;
	int square_size = 30;
	int grid_size = 30;
	HashSet<Integer> prohibited = new HashSet<Integer>();
	CanvasT c;
	public  Graph(CanvasT c, int source, int destination, int square_size,int grid_size,HashSet<Integer> prohibited) {
		G= new ArrayList[grid_size*grid_size];
		this.source = source;
		this.destination = destination;
		this.square_size = square_size;
		this.grid_size = grid_size;
		this.prohibited = prohibited;
		this.c=c;
	}
	public void buildGraph(int ty) {
    	for (int i = 0; i < G.length; i++) {
    		G[i]=new ArrayList<Integer>();
    		if(!prohibited.contains(i)) {
    			System.out.println(i+"-"+(i>=grid_size)+"-"+(i%grid_size!=0)+"-"+(i%grid_size!=grid_size-1)+"-"+(i<(grid_size*(grid_size-1))));
    			if((i>=grid_size)&&(i%grid_size!=0)&&(i%grid_size!=grid_size-1)&&(i<(grid_size*(grid_size-1)))) {
    				if(!prohibited.contains(i+1))G[i].add(i+1);
    				if(!prohibited.contains(i-1))G[i].add(i-1);
    				if(!prohibited.contains(i+grid_size))G[i].add(i+grid_size);
    				if(!prohibited.contains(i-grid_size))G[i].add(i-grid_size);
    			}else if(i<grid_size) {
    				if(!prohibited.contains(i+grid_size))G[i].add(i+grid_size);
    				if(i!=0) {
    					if(!prohibited.contains(i-1))G[i].add(i-1);
    				}
    				if(i!=grid_size-1) {
    					if(!prohibited.contains(i+1))G[i].add(i+1);
    				}
    			}else if(i>=(grid_size*(grid_size-1))) {
    				if(!prohibited.contains(i-grid_size))G[i].add(i-grid_size);
    				if(i!=grid_size*(grid_size-1)) {
    					if(!prohibited.contains(i-1))G[i].add(i-1);
    				}
    				if(i!=((grid_size*grid_size)-1)) {
    					if(!prohibited.contains(i+1))G[i].add(i+1);
    				}
    			}else if(i%grid_size==0) {
    				if(!prohibited.contains(i+1))G[i].add(i+1);
    				if(!prohibited.contains(i+grid_size))G[i].add(i+grid_size);
    				if(!prohibited.contains(i-grid_size))G[i].add(i-grid_size);
    			}else if(i%grid_size==grid_size-1) {
    				if(!prohibited.contains(i+1))G[i].add(i-1);
    				if(!prohibited.contains(i+grid_size))G[i].add(i+grid_size);
    				if(!prohibited.contains(i-grid_size))G[i].add(i-grid_size);
    			}
    		}
			
		}
    	if(ty==0) {
    		dfs(source);
    	}else if(ty==1) {
    		bfs(source);
    	}else if(ty==2) {
    		bfs(source);
    		bfs(destination);
    	}
    	
    }
	HashSet<Integer> visited = new HashSet<Integer>();
    boolean hasfin = false;
	public void dfs(int origin) {
		System.out.println(origin+"<<");
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (Exception e) {
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
	public void bfs(int origin) {
		Queue<Integer> q = new LinkedList();
		q.add(origin);
		while(!q.isEmpty()&&!hasfin) {
			int t = q.poll();
			if(!visited.contains(t)) {
				 try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) {  e.printStackTrace(); }
				if (t != source && t != destination && !visited.contains(t))
					recolor(t);
				if (t == destination)
					hasfin = true;
				visited.add(t);
				ArrayList<Integer> x = G[t];
				for (int i = 0; i < x.size(); i++) {
					if (!visited.contains(x.get(i)) && !prohibited.contains(x.get(i))) {
						q.add(x.get(i));
					}
				}
			}
			
		}
	}
	public void recolor(int b) {
		Graphics g = c.getGraphics();
		g.setColor(Color.CYAN);
		int y = b/grid_size;
		int x = b-(y*grid_size);
		System.out.println(x+"-"+y);
		g.fill3DRect(x*square_size,y*square_size, square_size, square_size, true);
	}
}
