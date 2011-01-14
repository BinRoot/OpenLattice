import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;

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
		int camX1 = 0;
		int camY1 = 0;
		int camX2 = 0;
		int camY2 = 0;
		
		double t1 = c.tangent.dot(new Vector3D(ls.x1,ls.y1,ls.z1)) - c.tangent.dot(c.position);
		//projection of point A
		double px1 = ls.x1 - t1*c.tangent.getX();
		double py1 = ls.y1 - t1*c.tangent.getY();
		double pz1 = ls.z1 - t1*c.tangent.getZ();
		System.out.println("real projected point A: "+px1+", "+py1+", "+pz1);
		
		double t2 = c.tangent.dot(new Vector3D(ls.x2,ls.y2,ls.z2)) - c.tangent.dot(c.position);
		//projection of point B
		double px2 = ls.x2 - t2*c.tangent.getX();
		double py2 = ls.y2 - t2*c.tangent.getY();
		double pz2 = ls.z2 - t2*c.tangent.getZ();
		System.out.println("real projected point B: "+px2+", "+py2+", "+pz2);
		
		//disposition solution of A
		double [][] ans1 = new double[2][1];
		double [][] arr1 = new double[2][2];
		if(c.normal.getX()==0 && c.binormal.getX()==0) {
			arr1[0][0]=c.normal.getY();	arr1[0][1]=c.binormal.getY();
			arr1[1][0]=c.normal.getZ(); arr1[1][1]=c.binormal.getZ();
			
			ans1[0][0]=ls.y1-c.position.getY();
			ans1[1][0]=ls.z1-c.position.getZ();
		}
		else if(c.normal.getY()==0 && c.binormal.getY()==0) {
			arr1[0][0]=c.normal.getX();	arr1[0][1]=c.binormal.getX();
			arr1[1][0]=c.normal.getZ(); arr1[1][1]=c.binormal.getZ();
			
			ans1[0][0]=ls.x1-c.position.getX();
			ans1[1][0]=ls.z1-c.position.getZ();
		}
		else if(c.normal.getZ()==0 && c.binormal.getZ()==0) {
			arr1[0][0]=c.normal.getX();	arr1[0][1]=c.binormal.getX();
			arr1[1][0]=c.normal.getY(); arr1[1][1]=c.binormal.getY();
			
			ans1[0][0]=ls.x1-c.position.getX();
			ans1[1][0]=ls.y1-c.position.getY();
		}

		Matrix m1 = new Matrix( arr1, 2,2 );
		Matrix mAns1 = new Matrix( ans1, 2,1 );
		Matrix mSol1 = null;
		
		mSol1 = m1.inverse().multiply(mAns1);
		mSol1.print();
		
		double solArr1[][] = mSol1.getMat();
		System.out.println("lambda = "+solArr1[0][0]);
		System.out.println("mu = "+solArr1[1][0]);
		camX1 = (int) solArr1[0][0];
		camY1 = (int) solArr1[1][0];
		
		
		//disposition solution of B
		double [][] ans2 = new double[2][1];
		double [][] arr2 = new double[2][2];
		if(c.normal.getX()==0 && c.binormal.getX()==0) {
			arr2[0][0]=c.normal.getY();	arr2[0][1]=c.binormal.getY();
			arr2[1][0]=c.normal.getZ(); arr2[1][1]=c.binormal.getZ();
			
			ans2[0][0]=ls.y2-c.position.getY();
			ans2[1][0]=ls.z2-c.position.getZ();
		}
		else if(c.normal.getY()==0 && c.binormal.getY()==0) {
			arr2[0][0]=c.normal.getX();	arr2[0][1]=c.binormal.getX();
			arr2[1][0]=c.normal.getZ(); arr2[1][1]=c.binormal.getZ();
			
			ans1[0][0]=ls.x2-c.position.getX();
			ans1[1][0]=ls.z2-c.position.getZ();
		}
		else if(c.normal.getZ()==0 && c.binormal.getZ()==0) {
			arr2[0][0]=c.normal.getX();	arr2[0][1]=c.binormal.getX();
			arr2[1][0]=c.normal.getY(); arr2[1][1]=c.binormal.getY();
			
			ans1[0][0]=ls.x2-c.position.getX();
			ans1[1][0]=ls.y2-c.position.getY();
		}
		
		
		
		Matrix m2 = new Matrix( arr2, 2,2 );
		Matrix mAns2 = new Matrix( ans2, 2,1 );
		Matrix mSol2 = null;
		
		
		mSol2 = m2.inverse().multiply(mAns2);
		
		
		double solArr2[][] = mSol2.getMat();
		System.out.println("lambda2 = "+solArr2[0][0]);
		System.out.println("mu2 = "+solArr2[1][0]);
		camX2 = (int) solArr2[0][0];
		camY2 = (int) solArr2[1][0];
		
		double camPosX = c.getDimension().getWidth()/2;
		double camPosY = c.getDimension().getHeight()/2;
		
		camX1 = (int) (camPosX+camX1);
		camY1 = (int) (camPosY+camY1);
		camX2 = (int) (camPosX+camX2);
		camY2 = (int) (camPosY+camY2);
		
		Shape line = new Line2D.Double(camX1, camY1, camX2, camY2);
		return line;
	}
	
}
