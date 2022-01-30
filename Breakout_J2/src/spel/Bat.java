package spel;
import java.awt.event.MouseEvent;
import wheels.users.*;




public class Bat{
	private Rectangle bat;
	private int width = 100;
	private int height = 8;
	private int velX;
	private int vel = 2;
	private int x = Frame._dp.getWidth()/2-width/2;
	private int y = Frame._dp.getHeight()-4*height;
	private int tempeX;

	
	public Bat() {
		bat = new Rectangle();
		bat.setSize(width, height);
		bat.setLocation(x, y);
		bat.setColor(java.awt.Color.GREEN);

	}
	
	public int getX() {
		return bat.getXLocation();
	}
	public int getY() {
		return bat.getYLocation();
	}
	public int getWidth() {
		return bat.getWidth();
	}
	public int getHeight() {
		return bat.getHeight();
	}
	public void setVelX(int VelX) {
		this.velX = VelX;
	}
	public int getVelX() {
		return velX;
	}
	
	public void move() {
		if(getX()+getWidth()/2 - getTempeX() <= 1 && getX()+getWidth()/2 - getTempeX() >= -1){
			setVelX(0);
		}else if(getTempeX()<(getX()+getWidth()/2)) {
			setVelX(-vel);
		}else if(getTempeX()>(getX()+getWidth()/2)) {
			setVelX(vel);
		}
		
		bat.setLocation(bat.getXLocation()+getVelX(), bat.getYLocation());
	}
	public void mouseMove(MouseEvent e,int sideBredd, int FW) {
		if(e.getX()>=getWidth()/2+sideBredd && e.getX()<=FW-getWidth()/2-sideBredd) {
			setTempeX(e.getX());
		}else if(e.getX()<getWidth()/2+sideBredd) {
			setTempeX(getWidth()/2+sideBredd);
		}else if(e.getX()>FW-getWidth()/2-sideBredd) {
			setTempeX(FW-getWidth()/2-sideBredd);
		}
	}
	public void setTempeX(int aTempeX) {
		this.tempeX = aTempeX;
	}
	public int getTempeX() {
		return tempeX;
	}
	
}
