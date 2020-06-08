package com.main.canvas.canvas;

import java.awt.Canvas;
import java.awt.Graphics;

public class CanvasT extends Canvas{
	 int x_source =-1;
	 int y_source = -1;
	 int size = 30;
	 int grid_size = 30;
	 public void Canvas() {
		 
	 }
	 @Override
	    public void paint(Graphics g) {
	      	for (int i = 0; i < grid_size; i++) {
				for (int j = 0; j < grid_size; j++) {
						g.drawRect(i*size, j*size, size, size);
				}
			}
	    }
}
