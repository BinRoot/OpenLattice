
public class Engine {

	public void draw(Object o) {
		if(o instanceof LineSeg) {
			LineSeg ls = (LineSeg) o;
			drawLineSeg(ls);
		}
		else {
			System.out.println("Not a valid vector.");
		}
	}
	
	public void drawLineSeg( LineSeg ls ) {
		System.out.println("DRAWING LINESEG NOW");
	}
}
