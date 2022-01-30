package spel;
import wheels.users.*;

public class Brick {
	private Rectangle br;
	private int width = 40;
	private int height = 10;
	private int y = 100;
	private int hp;
	private boolean vis;
	
	
	public Brick(int k) {
		if(k == 0) {
			createBr1();
		}else if(k == 1) {
			createBr2();
		}else if(k == 2) {
			createBr3();
		}
		vis = true;
	}
	
	public void createBr1() {
		br = new Rectangle();
		br.setSize(width, height);
		br.setColor(java.awt.Color.RED);
		br.setFrameColor(java.awt.Color.BLACK);
		setHp(1);

	}

	public void createBr2() {
		br = new Rectangle();
		br.setSize(width*4/3-1, height);
		br.setColor(java.awt.Color.BLUE);
		br.setFrameColor(java.awt.Color.BLACK);
		setHp(2);

	}
	
	public void createBr3() {
		br = new Rectangle();
		br.setSize(width*9/4, height);
		br.setColor(java.awt.Color.GREEN);
		br.setFrameColor(java.awt.Color.BLACK);
		setHp(3);
	}
	
	public void setLoc(int aX, int r) {
		br.setLocation(aX, y -height*r -2*r);
	}
	
	public int getLocX() {
		return br.getXLocation();
		
	}
	public int getLocY() {
		return br.getYLocation();
	}
	public int getWidth() {
		return br.getWidth();
	}
	public int getHeight() {
		return br.getHeight();
	}
	//hur många gånger man ska slå brickarna
	public void hpDown() {
		if(getHp() != 0) {
			setHp(getHp()-1);
			if(getHp() == 0){
				setVisibleF();
			}else if(getHp() == 1) {
				br.setColor(java.awt.Color.RED);
				br.setFrameColor(java.awt.Color.BLACK);
			}else if(getHp() == 2) {
				br.setColor(java.awt.Color.BLUE);
				br.setFrameColor(java.awt.Color.BLACK);
			}else if(getHp() == 3) {
				br.setColor(java.awt.Color.GREEN);
				br.setFrameColor(java.awt.Color.BLACK);
			}
		}
		
		
	}
	public void setHp(int aHp) {
		hp = aHp;
	}
	public int getHp() {
		return hp;
	}
	public boolean isVisible() {
		return vis;
	}
	public void setVisibleF() {
		vis = false;
		br.setColor(null);
	}

}
