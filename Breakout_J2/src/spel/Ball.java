package spel;

import wheels.users.*;

public class Ball{
	private Ellipse b;
	private int size = 14;
	private int velX, velY;
	private int x = 350;
	private int y = 125;
	private int health;
	private boolean collide = false;

	
	public Ball() {
		b = new Ellipse();
		b.setSize(size,size);
		b.setColor(java.awt.Color.BLACK);
		b.setLocation(x, y);
		setVelY(1);
		setVelX(0);
		setHealth(3);
		
	}
	
	public int getDia() {
		return b.getWidth();
	}
	
	public void setLocStart() {
		b.setLocation(x, y);
	}
	
	public int getX() {
		return b.getXLocation();
	}
	public int getY() {
		return b.getYLocation();
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	public int getVelX() {
		return velX;
	}
	
	public int getVelY() {
		return velY;
	}
	public void setHealth(int h) {
		health = h;
	}
	public int getHealth() {
		return health;
	}
	
	public void move() {
		b.setLocation(b.getXLocation()+getVelX(), b.getYLocation()+getVelY());
	}
	
	
}
