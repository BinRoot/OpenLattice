import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import Jampack.JampackException;
import Jampack.*;


public class Engine {

	private static ArrayList<LineSeg> lineSegs;
	Camera c = null;
	
	public Engine(Camera c) {
		lineSegs = new ArrayList<LineSeg>();
		this.c = c;
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

		
		double t1 = c.tangent.dot(new Vector3D(ls.x1,ls.y1,ls.z1)) - c.tangent.dot(c.position);
		//projection of point A
		double px1 = ls.x1 - t1*c.tangent.getX();
		double py1 = ls.y1 - t1*c.tangent.getY();
		double pz1 = ls.z1 - t1*c.tangent.getZ();
		
		double t2 = c.tangent.dot(new Vector3D(ls.x2,ls.y2,ls.z2)) - c.tangent.dot(c.position);
		//projection of point B
		double px2 = ls.x2 - t2*c.tangent.getX();
		double py2 = ls.y2 - t2*c.tangent.getY();
		double pz2 = ls.z2 - t2*c.tangent.getZ();
		
		//disposition solution of A
		double [][] arr1 = {
				{c.normal.getY(),c.binormal.getY()},
				{c.normal.getZ(),c.binormal.getZ()}
			};
		double [][] ans1 = {
				{ls.x1-c.position.getY()},
				{ls.y1-c.position.getZ()}
			};
		Zmat m1 = new Zmat( arr1 );
		Zmat mAns1 = new Zmat( ans1 );
		Zmat mSol1 = null;
		
		try {
			mSol1 = Solve.aib(m1, mAns1);
		} catch (JampackException e) {
			System.out.println("Jampack didn't work");
			e.printStackTrace();
		}
		
		double solArr1[][] = mSol1.getRe();
		System.out.println("lambda = "+solArr1[0][0]);
		System.out.println("mu = "+solArr1[1][0]);
		

		
		//System.out.println("solution of lambda and mu: "+mSol1.toString());

		Shape line = new Line2D.Double(0, 0, 0, 0);
		return line;
	}
	
}
