
public class Vector3D {
	double x;
	double y;
	double z;
	
	public Vector3D() {
		this(0, 0, 0);
	}
	
	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	//--end of constructor

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	//--end of getters and setters
	
	public Vector3D cross(Vector3D b) {
		Vector3D product = new Vector3D();
		
		Vector3D a = this;
		
		product.setX( a.getY()*b.getZ() - a.getZ()*b.getY() );
		product.setY( a.getZ()*b.getX() - a.getX()*b.getZ() );
		product.setZ( a.getX()*b.getY() - a.getY()*b.getX() );
		
		return product;
	}
}
