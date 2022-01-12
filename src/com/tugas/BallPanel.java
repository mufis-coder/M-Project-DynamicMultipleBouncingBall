package com.tugas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class BallPanel extends JPanel implements KeyListener {
	
	private static final int REFRESH_RATE = 30;
	private List<Ball> balls;
	private BallArea box;
	private int areaWidth;
	private int areaHeight;
	private int min;
	private int max;
	private char symbols[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 
						'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', 
						'4', '5', '6', '7', '8', '9'};
	private Color warna[] = {Color.RED, Color.GREEN, Color.YELLOW, Color.MAGENTA,
			Color.GRAY, Color.PINK, Color.ORANGE, Color.BLUE, Color.WHITE};
	
	public BallPanel(int width, int height) {
		this.min = 0;
		this.max = warna.length-1;
		this.balls = new ArrayList<Ball>();
		this.areaWidth = width; this.areaHeight = height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
		box = new BallArea(0, 0, width, height, Color.BLACK, Color.WHITE);
		
		//untuk awal ada 3 bola, bisa ditambah dengan mengetik A-Z atau 0-9 (Huruf harus kapital)
		makeBall();
		
		this.addKeyListener(this);
		this.setFocusable(true);
		
		//untuk mendapatkan ukuran area latar belakang jika frame diresize
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
			Component c = (Component)e.getSource();
			Dimension dim = c.getSize();
			areaWidth = dim.width;
			areaHeight = dim.height;
			box.set(0, 0, width, height);
			}
		});
		startThread();
	}
	
	public Color pickWarna() {
		int index = (int)(Math.random() * (max - min + 1) + min);
		return this.warna[index]; 
	}
	
	public void makeBall() {
		this.balls.add(newBall('A'));
		this.balls.add(newBall('B'));
		this.balls.add(newBall('C'));
	}	
	
	public boolean cekPosisi(int x, int y) {
		for (Ball ball : balls) {
			double deltaX = Math.abs(x - ball.x);
		     double deltaY = Math.abs(y - ball.y);
		     double distance = deltaX * deltaX + deltaY * deltaY;
		        
		     if (distance <= (ball.radius + ball.radius) * (ball.radius + ball.radius)) {
		    	 return true;
		     }
		}
		
		return false;
	}
	
	public Ball newBall(char simbol) {
		Random rand = new Random();
		int radius = 50;
		
		int x = rand.nextInt(this.areaWidth - radius * 2 - 20) + radius + 10;
		int y = rand.nextInt(this.areaHeight - radius * 2 - 20) + radius + 10;
//		while(cekPosisi(x, y)) {
//			x = rand.nextInt(this.areaWidth - radius * 2 - 20) + radius + 10;
//			y = rand.nextInt(this.areaHeight - radius * 2 - 20) + radius + 10;
//		}
		
		
		//aslinya 5
		int speed = 5;
		int angleInDegree = rand.nextInt(360);
		return new Ball(x, y, radius, speed, angleInDegree, pickWarna(), simbol);
	}
	
	public void startThread() {
		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
					for (Ball ball : balls) {
						ball.collide(box);
					}
					for (int i = 0; i < balls.size(); i++) {
						 for (int j=i+1; j<balls.size(); j++) {
							balls.get(i).collide(balls.get(j));
						 }
					}
					repaint();
					try {
						Thread.sleep(1000 / REFRESH_RATE);
					} 
					catch (InterruptedException ex) {}
				}
			}
		};
		gameThread.start();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		box.draw(g);
		for (Ball ball : balls) {
			ball.draw(g);
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		char inputSimbol = e.getKeyChar();
		
		for (char s : this.symbols) {
			if (inputSimbol == s) {
				this.balls.add(newBall(s));
				break;
			}
		}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
