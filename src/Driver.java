import java.util.Scanner;
public class Driver {
	public static void main(String [] args) {
		
		Camera c = new Camera(2,2,2);
		Scanner kb = new Scanner(System.in);
		boolean go = true;
		while(go) {
			System.out.println("current position: "+c.positionStr());
			System.out.println("pointing in direction: "+c.tangentStr());
			System.out.println("enter [w][a][s][d] [8][2] [i][j][k][l] to move.");
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
			else{
				System.out.println("Command not recognized.");
			}
			System.out.println();
		}
	}
}
