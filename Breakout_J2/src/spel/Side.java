package spel;
import wheels.users.*;

public class Side {
	private Rectangle r;
	
	public Side(int width, int height, int xPos, int yPos) {
		r = new Rectangle();
		r.setSize(width, height);
		r.setLocation(xPos, yPos);
		r.setColor(java.awt.Color.gray.darker());
	}
	
	public int getHeight() {
		return r.getHeight();
	}
	public int getWidth() {
		return r.getWidth();
	}
	
}
