import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class MainApplet extends Applet implements KeyListener, Runnable {
	
	boolean wKey, aKey, sKey, dKey = false;
	Camera c = new Camera(2,2,2);
	Engine e = new Engine(c);
	
	public void init() {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(this);

		setSize(c.getDimension());
		addKeyListener(this);
		
		e.addLineSeg(new LineSeg(5,10,3, 100,200,200)); //4,5,-1
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for(LineSeg ls : e.getLineSegs()) {
			g2.draw(e.getShape(ls));
		}
	}

	
	
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
		
		repaint();
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
		repaint();
	}

	public void keyTyped(KeyEvent e) {
		
	}


	public void run() {
		while(true) {
			try {
				if(wKey) {
					System.out.println("w");
					c.moveIn();
				}
				if(aKey) {
					System.out.println("a");
					c.panLeft();
				}
				if(sKey) {
					System.out.println("s");
					c.moveOut();
				}
				if(dKey) {
					System.out.println("d");
					c.panRight();
				}
				
				Thread.sleep(100);
				//System.out.println("sleep");
			} catch (InterruptedException e) {}
		}
	}
	
	
	
}
