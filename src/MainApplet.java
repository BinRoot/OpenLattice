import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class MainApplet extends Applet implements KeyListener, Runnable {
	
	boolean wKey, aKey, sKey, dKey, spaceKey, cKey,
		iKey, jKey, kKey, lKey, qKey, eKey = false;
	
	Camera c = new Camera(2,2,2);
	Engine e = new Engine(c);
	
	public void init() {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(this);

		setSize(c.getDimension());
		addKeyListener(this);
		
		e.addLineSeg(new LineSeg(200,0,0, 200,200,0));
		e.addLineSeg(new LineSeg(200,200,0, 200,200,300));
		e.addLineSeg(new LineSeg(200,200,300, 200,0,0));
		e.addLineSeg(new LineSeg(200,0,0, 400,200,0));
		e.addLineSeg(new LineSeg(400,200,0, 200,200,0));
		e.addLineSeg(new LineSeg(400,200,0, 200,200,300));
		
		e.addLineSeg(new LineSeg(-200,0,0, 200,0,0)); 
		e.addLineSeg(new LineSeg(0,-200,0, 0,200,0)); 
		e.addLineSeg(new LineSeg(0,0,-200, 0,0,200)); 
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for(LineSeg ls : e.getLineSegs()) {
			//g2.draw(e.getShape(ls));
		}
		g2.drawString("P "+ c.getPositionStr(), 10, 20);
		g2.drawString("T "+ c.getTangentStr(), 10, 40);
		g2.drawString("B "+ c.getBinormalStr(), 10, 60);
		g2.drawString("N "+ c.getNormalStr(), 10, 80);
	}

	
	
	
	//Key events:
	
	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		if(e.getKeyCode()==87) { //w
			wKey=true;
		}
		if(e.getKeyCode()==65) { //a
			aKey=true;
		}
		if(e.getKeyCode()==83) { //s
			sKey=true;
		}
		if(e.getKeyCode()==68) { //d
			dKey=true;
		}
		if(e.getKeyCode()==32) { //[SPACE]
			spaceKey=true;
		}
		if(e.getKeyCode()==67) { //c
			cKey=true;
		}
		if(e.getKeyCode()==73) { //i
			iKey=true;
		}
		if(e.getKeyCode()==74) { //j
			jKey=true;
		}
		if(e.getKeyCode()==75) { //k
			kKey=true;
		}
		if(e.getKeyCode()==76) { //l
			lKey=true;
		}
		if(e.getKeyCode()==81) { //q
			qKey=true;
		}
		if(e.getKeyCode()==69) { //e
			eKey=true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==87) { //w
			wKey=false;
		}
		if(e.getKeyCode()==65) { //a
			aKey=false;
		}
		if(e.getKeyCode()==83) { //s
			sKey=false;
		}
		if(e.getKeyCode()==68) { //d
			dKey=false;
		}
		if(e.getKeyCode()==32) { //[SPACE]
			spaceKey=false;
		}
		if(e.getKeyCode()==67) { //c
			cKey=false;
		}
		if(e.getKeyCode()==73) { //i
			iKey=false;
		}
		if(e.getKeyCode()==74) { //j
			jKey=false;
		}
		if(e.getKeyCode()==75) { //k
			kKey=false;
		}
		if(e.getKeyCode()==76) { //l
			lKey=false;
		}
		if(e.getKeyCode()==81) { //q
			qKey=false;
		}
		if(e.getKeyCode()==69) { //e
			eKey=false;
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}


	public void run() {
		while(true) {
			try {
				if(wKey) {
					//System.out.println("w");
					c.moveIn();
					repaint();
				}
				if(aKey) {
					//System.out.println("a");
					c.panLeft();
					repaint();
				}
				if(sKey) {
					//System.out.println("s");
					c.moveOut();
					repaint();
				}
				if(dKey) {
					//System.out.println("d");
					c.panRight();
					repaint();
				}
				if(spaceKey) {
					//System.out.println("SPACE");
					c.panUp();
					repaint();
				}
				if(cKey) { 
					//System.out.println("c");
					c.panDown();
					repaint();
				}
				if(iKey) { 
					c.pitchUp();
					repaint();
				}
				if(jKey) { 
					c.yawLeft();
					repaint();
				}
				if(kKey) { 
					c.pitchDown();
					repaint();
				}
				if(lKey) { 
					c.yawRight();
					repaint();
				}
				if(qKey) { 
					c.rollLeft();
					repaint();
				}
				if(eKey) { 
					c.rollRight();
					repaint();
				}
				
				Thread.sleep(100);
				//System.out.println("sleep");
			} catch (InterruptedException e) {}
		}
	}
	
	
	
}
