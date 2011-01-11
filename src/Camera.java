import java.awt.Dimension;


public class Camera {

	Vector3D position;
	Vector3D tangent; //camera movement in and out
	Vector3D normal; //camera movement side to side
	Vector3D binormal; //camera orientation up
	
	int width = 640;
	int height = 480;
	
	public Camera() {
		this(0,0,0);
	}
	
	public Camera(int x, int y, int z) {
		position = new Vector3D(x,y,z);
		
		//tangent and binormal must be perpendicular!
		tangent = new Vector3D(1, 0, 0); //looking through x-axis
		binormal = new Vector3D(0, 0, 1); //oriented up as z-axis
		normal = binormal.cross(tangent); //(0,1,0)
	}
	
	//--end of constructors.
	
	public void setDimension(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public Dimension getDimension() {
		Dimension d = new Dimension(width, height);
		return d;
	}
	
	public String toString() {
		String info = "position: "+position.toString() + "\n" +
				"tangent: "+tangent.toString() + "\n" +
				"binormal: "+binormal.toString() + "\n" +
				"normal: "+normal.toString() + "\n"; 
			
		return info;
	}
	public String positionStr() {
		return position.toString();
	}
	public String tangentStr() {
		return tangent.toString();
	}
	
	
	public void panLeft() {
		position.setX( position.getX() + normal.getX() );
		position.setY( position.getY() + normal.getY() );
		position.setZ( position.getZ() + normal.getZ() );
	}
	
	public void panRight() {
		position.setX( position.getX() - normal.getX() );
		position.setY( position.getY() - normal.getY() );
		position.setZ( position.getZ() - normal.getZ() );
	}
	
	public void panUp() {
		position.setX( position.getX() + binormal.getX() );
		position.setY( position.getY() + binormal.getY() );
		position.setZ( position.getZ() + binormal.getZ() );
	}
	
	public void panDown() {
		position.setX( position.getX() - binormal.getX() );
		position.setY( position.getY() - binormal.getY() );
		position.setZ( position.getZ() - binormal.getZ() );
	}
	
	public void moveIn() {
		position.setX( position.getX() + tangent.getX() );
		position.setY( position.getY() + tangent.getY() );
		position.setZ( position.getZ() + tangent.getZ() );
	}
	
	public void moveOut() {
		position.setX( position.getX() - tangent.getX() );
		position.setY( position.getY() - tangent.getY() );
		position.setZ( position.getZ() - tangent.getZ() );
	}
	
	public void yawLeft() {
		Vector3D TN = new Vector3D(	normal.getX()-tangent.getX(),
									normal.getY()-tangent.getY(),
									normal.getZ()-tangent.getZ());
		TN.divide(10);
		
		tangent.add(TN);
		tangent.makeUnitVector();
		normal.setVector( binormal.cross(tangent) );
		normal.makeUnitVector();
	}
	
	public void yawRight() {
		Vector3D TN = new Vector3D(	(-1*normal.getX())-tangent.getX(),
									(-1*normal.getY())-tangent.getY(),
									(-1*normal.getZ())-tangent.getZ());
		TN.divide(10);

		tangent.add(TN);
		tangent.makeUnitVector();
		normal.setVector( binormal.cross(tangent) );
		normal.makeUnitVector();
	}
	
	public void pitchUp() {
		Vector3D TB = new Vector3D(	binormal.getX()-tangent.getX(),
									binormal.getY()-tangent.getY(),
									binormal.getZ()-tangent.getZ());
		TB.divide(10);

		tangent.add(TB);
		tangent.makeUnitVector();
		binormal.setVector( tangent.cross(normal) );
		binormal.makeUnitVector(); //optional
	}
	
	public void pitchDown() {
		Vector3D TB = new Vector3D(	(-1*binormal.getX())-tangent.getX(),
									(-1*binormal.getY())-tangent.getY(),
									(-1*binormal.getZ())-tangent.getZ());
		TB.divide(10);

		tangent.add(TB);
		tangent.makeUnitVector();
		binormal.setVector( tangent.cross(normal) );
		binormal.makeUnitVector(); //optional
	}
	
	public void rollLeft() {
		Vector3D BN = new Vector3D(	normal.getX()-binormal.getX(),
									normal.getY()-binormal.getY(),
									normal.getZ()-binormal.getZ());
		BN.divide(10);

		binormal.add(BN);
		binormal.makeUnitVector();
		normal.setVector( binormal.cross(tangent) );
	}
	
	public void rollRight() {
		Vector3D BN = new Vector3D(	(-1*normal.getX())-binormal.getX(),
									(-1*normal.getY())-binormal.getY(),
									(-1*normal.getZ())-binormal.getZ());
		BN.divide(10);

		binormal.add(BN);
		binormal.makeUnitVector();
		normal.setVector( binormal.cross(tangent) );
	}
	
}
