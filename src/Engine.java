import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;


public class Engine {

	private static ArrayList<LineSeg> lineSegs;
	
	public Engine() {
		lineSegs = new ArrayList<LineSeg>();
	}
	
	public void addLineSeg(LineSeg ls) {
		if(!lineSegs.contains(ls)) {
			lineSegs.add(ls);
		}
	}
	
	public void removeLineSeg(LineSeg ls) {
		if(!lineSegs.contains(ls)) {
			lineSegs.remove(ls);
		}
	}
	
	public ArrayList<LineSeg> getLineSegs() {
		return lineSegs;
	}
	
	
	public Shape getShape(Object o) {
		if(o instanceof LineSeg) {
			LineSeg ls = (LineSeg) o;
			if(!lineSegs.contains(ls)) {
				lineSegs.add(ls);
			}
			return getShapeOfLineSeg(ls);
		}
		else {
			System.out.println("Not a valid vector.");
			return null;
		}
	}
	
	//Algorithm for vector projection and matrix manipulation goes here:
	public Shape getShapeOfLineSeg( LineSeg ls ) {
		int x1 = ls.x1;
		int y1 = ls.y1;
		int z1 = ls.z1;
		int x2 = ls.x2;
		int y2 = ls.y2;
		int z2 = ls.z2;
		
		

		Shape line = new Line2D.Double(x1, y1, x2, y2);
		return line;
	}
	
}
