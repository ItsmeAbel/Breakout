package spel;
import wheels.users.*;
import java.awt.event.*;
import javax.swing.Timer;


public class GameBoard extends Frame implements ActionListener, MouseMotionListener{
	private Timer t = new Timer(5,this);
	private Ball boll;
	private Side sideV, sideH, top;
	private Bat bat;
	private Brick[] bricks;
	
	private int sideBredd = 10;
	private int FH = Frame._dp.getHeight();
	private int FW = Frame._dp.getWidth();
	private int nPR = 17;
	private int nPR1 = 12;
	private int nPR2 = 6;
	private int numB = nPR*2 + nPR1+nPR2;
	private int aNPR, k, BhpTot;
	private int BhpCount = 0;
	private boolean game = true;
	private boolean won = false;
	private ConversationBubble HP, EndGame, WinGame;

	

	
	public GameBoard() {
		//Frame._dp.setBackground(java.awt.Color.cyan);
		boll = new Ball();
		sideV = new Side(sideBredd,FH,0,0);
		sideH = new Side(sideBredd,FH,FW-sideBredd,0);
		top = new Side(FW,sideBredd,0,0);
		bat = new Bat();
		bricks = new Brick[numB];
		createBricks();
		HP = new ConversationBubble("Life: " + boll.getHealth());
		HP.setLocation(590, 2);
		HP.setSize(100, 75);
		HP.setFrameColor(null);
		
		BhpTot = bricks[nPR-1].getHp()*nPR*2 + bricks[nPR*2 +nPR1-1].getHp()*nPR1 + bricks[nPR*2 +nPR1 + nPR2-1].getHp()*nPR2;
		
		Frame._dp.addMouseMotionListener(this);
		if(t.isRunning()) {
			t.restart();
		}else {
			t.start();
		}
		//BhpCount= nPR*2 + nPR1*2+nPR2*3;
		
		
		

		
	}
	
	public static void Game() {
		GameBoard breakout = new GameBoard();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(game != false) {
			
			boll.move();
			check();

			bat.move();
			Frame._dp.repaint();
		}
			
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		if (game != false){
		bat.mouseMove(e, sideBredd, FW);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void createBricks() {
		for(int i=0; i<nPR; i++) {
			bricks[i] = new Brick(0);	
			if(i==0) {
				bricks[i].setLoc(sideBredd, 0);
			}else {
				bricks[i].setLoc(bricks[i-1].getLocX()+ bricks[i].getWidth(), 0);
			}
	
			bricks[i+nPR] = new Brick(0);
			if(i==0) {
				bricks[i+nPR].setLoc(sideBredd, 1);
			}else {
				bricks[i+nPR].setLoc(bricks[i-1].getLocX()+ bricks[i+nPR].getWidth(), 1);			
			}
			//bricks[i].setVisibleF();
			//bricks[i+nPR].setVisibleF();
			
		}
		
		for(int i=0; i<nPR1; i++) {
			bricks[i+nPR*2] = new Brick(1);
			if(i==0) {
				bricks[i+nPR*2].setLoc(sideBredd+28, 2);
			}else {
				bricks[i+nPR*2].setLoc(bricks[i+nPR*2-1].getLocX()+ bricks[i+nPR*2].getWidth(), 2);			
			}
			//bricks[i+nPR*2].setVisibleF();
			
		}
		for(int i=0; i<nPR2; i++) {
			bricks[i+nPR*2+nPR1] = new Brick(2);
			if(i==0) {
				bricks[i+nPR*2+nPR1].setLoc(sideBredd+bricks[i+nPR*2+nPR1].getWidth()-26, 3);
			}else {
				bricks[i+nPR*2+nPR1].setLoc(bricks[i+nPR*2+nPR1-1].getLocX()+ bricks[i+nPR*2+nPR1].getWidth(), 3);
			}
			//bricks[i+nPR*2+nPR1].setVisibleF();
		}
	}
	
	
	public void check() {
		checkSideCol();
		checkBatCol();
		checkBrickCol();
		if(won == false) {
			win();
		}else {
			moveWG();
		}
		
		
	}
	
	public void hpControl(){
		if (boll.getHealth() == 0) {
			t.stop();
			game = false;
			
		} else {
			boll.setHealth(boll.getHealth()-1);	
		}
			
	}
	public void checkSideCol() {
		if (boll.getY() > FH-boll.getDia()){
			if(BhpCount != BhpTot) {
				hpControl();
				HP.setText("Life: " + boll.getHealth());
				HP.setFrameColor(null);
				if (boll.getHealth() != 0){
					boll.setLocStart();
					boll.setVelX(0);
					boll.setVelY(1);
				} else {
					EndGame = new ConversationBubble ("GAME OVER");	
					EndGame.setLocation(280, 50);
					EndGame.setColor(java.awt.Color.cyan);
					EndGame.setFrameColor(null);
					EndGame.setColor(null);
					EndGame.setSize(200, 500);
				}
			}else {
				boll.setVelY(-boll.getVelY());
			}
			
			
		}
		if (boll.getX() > FW-boll.getDia()-sideBredd || boll.getX() < 0+sideBredd){
			boll.setVelX(-boll.getVelX());
		}
		if(boll.getY() < 0+sideBredd) {
			boll.setVelY(-boll.getVelY());
		}
	}
	
	public void checkBrickCol() {
		//brickcollide
		for(int j=0; j<3 ; j++) {
			if(j==0) {
				aNPR = nPR*2;
			}else if(j==1) {
				aNPR = nPR1;
			}else if(j==2) {
				aNPR = nPR2;
			}
			for(int i=0; i<aNPR; i++) {
				if(j==0) {
					k = i;
				}else if(j==1) {
					k = i+nPR*2;
				}else if(j==2) {
					k = i+nPR*2 + nPR1;
				}
				if(bricks[k].isVisible()){
					if((boll.getY()+boll.getDia()>=bricks[k].getLocY() && boll.getY()<=bricks[k].getLocY()+bricks[k].getHeight())) {
						if((((boll.getX()+boll.getDia() == bricks[k].getLocX()) && boll.getVelX()>0)||((boll.getX() == bricks[k].getLocX()+bricks[k].getWidth()) && boll.getVelX()<0))) {
							boll.setVelX(-boll.getVelX());
							bricks[k].hpDown();
							BhpCount++;
						}
						
						
					}if((boll.getX()+boll.getDia()>bricks[k].getLocX() && boll.getX()<bricks[k].getLocX()+bricks[k].getWidth()) && (((boll.getY()+boll.getDia() == bricks[k].getLocY()) && boll.getVelY()>0)||((boll.getY() == bricks[k].getLocY()+bricks[k].getHeight()) && boll.getVelY() < 0))) {
						boll.setVelY(-boll.getVelY());
						bricks[k].hpDown();
						BhpCount++;
					}
				}				
					
			}
		}
		//slut på brickcollide
	}
	
	public void checkBatCol() {
		//batcollide
		if((boll.getY()+boll.getDia()>bat.getY()&& boll.getY()<bat.getY()+bat.getHeight()) && (((boll.getX()+boll.getDia() == bat.getX())&& boll.getVelX()>0)||((boll.getX() == bat.getX()+bat.getWidth())&& boll.getVelX()<0) )) {
			boll.setVelX(-boll.getVelX());
		}
		
		if((boll.getX()+boll.getDia()>=bat.getX()&& boll.getX()<=bat.getX()+bat.getWidth()) && (boll.getY()+boll.getDia()== bat.getY())) {
			if(boll.getX()+boll.getDia()<bat.getX()+bat.getWidth()/2) {
				if(boll.getX()+boll.getDia()<bat.getX()+bat.getWidth()/4) {
					boll.setVelY(-1);
					boll.setVelX(-2);
				}else {
					boll.setVelY(-2);
					boll.setVelX(-1);
				}
			}else if(boll.getX()+boll.getDia()>bat.getX()+bat.getWidth()/2) {
				if(boll.getX()+boll.getDia()>bat.getX()+bat.getWidth()*3/4) {
					boll.setVelY(-1);
					boll.setVelX(2);
				}else {
					boll.setVelY(-2);
					boll.setVelX(1);
				}			
			}else {
				boll.setVelY(-boll.getVelY());
			}
		}
		//Slut pï¿½ batcollide
	}
	
	public void win() {
		if(BhpCount == BhpTot) {
			won = true;
			WinGame = new ConversationBubble ("YOU WON!");	
			WinGame.setLocation(boll.getX()-WinGame.getWidth()/2+40, boll.getY()-32);
			WinGame.setColor(java.awt.Color.cyan);
			WinGame.setFrameColor(null);
			WinGame.setColor(null);
			WinGame.setSize(125, 80);
			
		}
		
	}
	
	private void moveWG() {
		WinGame.setLocation(WinGame.getXLocation()+boll.getVelX(), WinGame.getYLocation()+boll.getVelY() );
	}
}
