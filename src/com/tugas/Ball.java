package com.tugas;

import java.awt.*;
public class Ball extends Rectangle {
	float x, y;
	float speedX, speedY;
	float radius;
	private char symbol;
	private Color color;
	
	public Ball(float x, float y, float radius, float speed, 
			float angleInDegree,Color color, char simbol) {
		this.x = x;
		this.y = y;
		this.speedX = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
		this.speedY = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
		this.radius = radius;
		this.color = color;
		this.symbol = simbol;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x - radius), (int)(y - radius), 
				(int)(2 *radius), (int)(2 * radius));
		
		g.setColor(Color.BLACK);
		Font myFont = new Font ("Arial", Font.BOLD, 30);
		g.setFont(myFont);
		g.drawString(String.valueOf(this.symbol), (int) x - 8, (int) y + 8);
	}
	
	public double fungsiL(double m, double c, double x) {
		return m*x+c;
	}
	
	public void collide(Ball ball) {
		 double deltaX = Math.abs(this.x - ball.x);
	     double deltaY = Math.abs(this.y - ball.y);
	     double distance = deltaX * deltaX + deltaY * deltaY;
	        
	     if (distance < (this.radius + ball.radius) * (this.radius + ball.radius)) {
	    	 
	    	 float temp = this.speedX;
             this.speedX = ball.speedX;
             ball.speedX = temp;
             
             float temp2 = this.speedY;
             this.speedY = ball.speedY;
             ball.speedY = temp2;
           
             double m = 1;
             
             if(ball.x-this.x != 0) {
            	 m = (ball.y - this.y)/(ball.x - this.x);
             }
             else {
            	 m = (ball.y - this.y)/((ball.x - this.x)+0.0001);
             }
             
             double c = ball.y - (m*ball.x);
             double alfa = Math.atan(m);
             double a = (double)(radius * Math.cos(alfa));
             double x = this.x + (ball.x - this.x)/2;

			//  System.out.println("a: " + a);
			//  System.out.println("x: " + x);
             
             if(this.x<ball.x) {
            	 this.x = (float)(x-a);
	             this.y = (float)(fungsiL(m, c, this.x));
	             
	             ball.x = (float)(x+a);
	             ball.y = (float)(fungsiL(m, c, ball.x));
             }
             else {
            	 this.x = (float)(x+a);
	             this.y = (float)(fungsiL(m, c, this.x));
	             
	             ball.x = (float)(x-a);
	             ball.y = (float)(fungsiL(m, c, ball.x));
             }
	     }
	}
	
	public void collide(BallArea box) {
		
		float ballMinX = box.minX + radius;
		float ballMinY = box.minY + radius;
		float ballMaxX = box.maxX - radius;
		float ballMaxY = box.maxY - radius;
		
		x += speedX;
		y += speedY;
		
		if (x < ballMinX) {
			speedX = -speedX;
			x = ballMinX;
		} 
		else if (x > ballMaxX) {
			speedX = -speedX;
			x = ballMaxX;
		}
		
		if (y < ballMinY) {
			speedY = -speedY;
			y = ballMinY;
		} 
		else if (y > ballMaxY) {
			speedY = -speedY;
			y = ballMaxY;
		}
	}
}
