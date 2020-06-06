package com.main.canvas.canvas;

import java.awt.Canvas;
import java.awt.Graphics;

public class CanvasT extends Canvas{
	 int x_source =-1;
	 int y_source = -1;
	 @Override
	    public void paint(Graphics g) {
	      	for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
						g.drawRect(i*50, j*50, 50, 50);
				}
			}
	    }
}
