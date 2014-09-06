import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Coty
 *
 */
public class RectangleTest {
	
	private static int numRects = 0;
	private static ArrayList<Rectangle> rectArr;
	static Scanner keyboard = new Scanner(System.in);
	private static int xCoord, yCoord, width, height;
	private static int count = 1;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("How many rectangles would you like to input?");
		numRects = keyboard.nextInt();
		if(numRects < 1){
			System.out.println("Number of Rectangles needs to be above 0");
			System.exit(0);
		}
		rectArr = new ArrayList<Rectangle>(numRects);
		for(int i=0; i<numRects;i++){
			System.out.println("Rectangle " + count + ":");
			System.out.println("X Coordinate: ");
			xCoord = keyboard.nextInt();
			System.out.println("Y Coordinate: ");
			yCoord = keyboard.nextInt();
			System.out.println("Width: ");
			width  = keyboard.nextInt();
			System.out.println("Height: ");
			height = keyboard.nextInt();
			
			if(xCoord<0 || yCoord<0 || width<0 || height<0){
				System.out.println("No negative numbers");
				System.exit(0);
			}
			
			Rectangle rect = new Rectangle(xCoord,yCoord,width,height);
			rectArr.add(i, rect);
			count++;
		}
		count = 1;
		for(int i=0; i<rectArr.size(); i++){
			System.out.println("Rectangle " + count + ": " + rectArr.get(i).toString());
			count++;
		}
		Rectangle total = Rectangle.enclosed(rectArr);
		int numOverlaps = Rectangle.countNumOverlaps(rectArr);
		System.out.println("\nNumber of Overlaps: " + numOverlaps);
		
		if(numOverlaps != numRects -1) System.out.println("There is no common area of overlap.");
		else{
			Rectangle rect = Rectangle.commonArea(rectArr);
			System.out.println("Area in Common: " + rect.toString());
		}
		System.out.println("Smallest Rectangle that encloses all rectangles: " + total.toString());
	}

}
