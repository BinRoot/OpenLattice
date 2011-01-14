
public class Matrix {

	int rows;
	int cols;
	
	double [][] mat;
	
	public Matrix(double [][] mat, int rows, int cols) {
		this.mat = mat;
		this.rows = rows;
		this.cols = cols;
	}

	public double[][] getMat() {
		return mat;
	}

	public void print() {
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				System.out.println(mat[i][j]);
			}
		}
	}
	
	public Matrix multiply(Matrix b) {
		Matrix product = null;
		Matrix a = this;
		if (a.rows==2 && a.cols==2 && b.rows==2 && b.cols==1) { //2 by 2 matrix * 2 by 1 matrix
			double [][] mats = new double[2][1];
			
			mats[0][0] = a.getVal(0,0)*b.getVal(0,0) + a.getVal(0,1)*b.getVal(1,0);
			mats[1][0] = a.getVal(1,0)*b.getVal(0,0) + a.getVal(1,1)*b.getVal(1,0);

			product = new Matrix(mats, 2, 1);
		}
		
		return product;
	}
	
	public Matrix inverse() {
		Matrix inv = null;
		if(rows == 2 && cols==2) { //2 by 2 matrix
			double [][] mats = new double[2][2];
			double f = 1/det();
			
			double a = getVal(0, 0);
			double b = getVal(0, 1);
			double c = getVal(1, 0);
			double d = getVal(1, 1);
			
			mats[0][0] = f*d;
			mats[0][1] = f*b*-1;
			mats[1][0] = f*c*-1;
			mats[1][1] = f*a;
			
			inv = new Matrix(mats, 2,2);
		}
		return inv;
	}
	
	public double det() {
		double det = 0;
		if(cols==2 && rows==2) { //2 by 2 matrix
			double a = getVal(0, 0);
			double b = getVal(0, 1);
			double c = getVal(1, 0);
			double d = getVal(1, 1);
			return (a*d - c*b);
		}
		return det;
	}
	
	public double getVal(int row, int col){
		return mat[row][col];
	}
	
	
}
