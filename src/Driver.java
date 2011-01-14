import java.util.Scanner;
public class Driver {
	public static void main(String [] args) {
		
		Camera c = new Camera(2,2,2);
		Scanner kb = new Scanner(System.in);
		
		double mat1[][] = { 
				{2,0}, 
				{3,1}};
		Matrix m1 = new Matrix(mat1, 2, 2);

		double mat2[][] = { 
				{3}, 
				{1}};
		Matrix m2 = new Matrix(mat2, 2, 1);
		
		m1.multiply(m2).print();
		
		boolean go = true;
		while(go) {
			System.out.println(c.toString());
			System.out.println("enter [w][a][s][d] [8][2] [i][j][k][l] [u][o] to move.");
			String ans = kb.nextLine();
			if(ans.equals("q")) {
				go=false;
			}
			else if(ans.equals("a")) {
				c.panLeft();
			}
			else if(ans.equals("d")) {
				c.panRight();
			}
			else if(ans.equals("w")) {
				c.moveIn();
			}
			else if(ans.equals("s")) {
				c.moveOut();
			}
			else if(ans.equals("8")) {
				c.panUp();
			}
			else if(ans.equals("2")) {
				c.panDown();
			}
			else if(ans.equals("i")) {
				c.pitchUp();
			}
			else if(ans.equals("j")) {
				c.yawLeft();
			}
			else if(ans.equals("k")) {
				c.pitchDown();
			}
			else if(ans.equals("l")) {
				c.yawRight();
			}
			else if(ans.equals("u")) {
				c.rollLeft();
			}
			else if(ans.equals("o")) {
				c.rollRight();
			}
			else{
				System.out.println("Command not recognized.");
			}
			System.out.println();
		}
	}
}
