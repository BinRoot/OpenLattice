
public class Camera {

	Vector3D position;
	Vector3D tangent; //camera movement in and out
	Vector3D normal; //camera movement side to side
	Vector3D binormal; //camera orientation up
	
	public Camera() {
		this(0,0,0);
	}
	
	public Camera(int x, int y, int z) {
		position = new Vector3D(x,y,z);
		
		//tangent and binormal must be perpendicular!
		tangent = new Vector3D(1, 0, 0); //looking through x-axis
		binormal = new Vector3D(0, 0, 1); //oriented up as z-axis
		normal = binormal.cross(tangent);
		System.out.println("the normal vector is "+normal.getX()+" "+normal.getY()+" "+normal.getZ());
	}
	
	//--end of constructors.
	
	public String toString() {
		return position.getX()+"  "+position.getY()+"  "+position.getZ();
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
	
}
