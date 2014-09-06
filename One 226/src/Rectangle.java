import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Coty
 *
 */
public class Rectangle {
	
	private int x, y, width, height;
	
	/**
	 * Constructor    
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
    public Rectangle(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    /**
     * getter for x Coord
     * @return x
     */
    public int getX(){
        return x;
    }
    
    /**
     * getter for y Coord
     * @return y
     */
    public int getY(){
        return y;
    }
    
    /**
     * getter for width
     * @return width
     */
    public int getWidth(){
        return width;
    }
    
    /**
     * getter for height
     * @return height
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * takes Rectangle object as parameter, determines whether rectangles 'this' and 'a' overlap
     * and returns true if so
     * @param a
     * @return true/false
     */
    public boolean overlaps(Rectangle a){
    	int rectW = this.getX() + this.getWidth();
    	int rectH = this.getY() + this.getHeight();
    	int secondW = a.getX() + a.getWidth();
    	int secondH = a.getY() + a.getHeight();
    	
    	if(a.getX() <= rectW && a.getY() <= rectH){
    		if(this.getX() <= secondW && this.getY() <= secondH) return true;
    		else return false;
    	}
    	else return false;
    }
    
    /**
     * accepts Rectangle object as parameter and returns a new Rectangle object
     * that is the result of the overlap of two rectangles, 'this' and 'a'
     * @param a
     * @return Rectangle object
     */
    public Rectangle intersect(Rectangle a){
    	Rectangle rect;
    	if(this.overlaps(a)){
    		int rectW = this.getX() + this.getWidth();	//determines farthest to the right X-coord of rect 'this'
        	int rectH = this.getY() + this.getHeight();	//determines highest Y-coord of rect 'this'
        	int secondW = a.getX() + a.getWidth();	//same as
        	int secondH = a.getY() + a.getHeight();	// above just for rect 'a'
        	
        	if(this.getX() < a.getX() && this.getY() > a.getY()){	//special case that won't work with if and else below
        		if(secondH == this.getY()){ //special case within the special case used for rectangles with only overlapping axis'
        			rect = new Rectangle(a.getX(), this.getY(), rectW - a.getX(),0);
        			return rect;
        		}
        		rect = new Rectangle(a.getX(),this.getY(),rectW - a.getX(), rectH - secondH);
        		return rect;
        	}
        	if(a.getX() < this.getX() && a.getY() > this.getY()){	//Similar to above special case where 'a' is above and left of 'this'
        		if(rectH == a.getY()){
        			rect = new Rectangle(this.getX(), a.getY(), secondW - this.getX(), 0);
        			return rect;
        		}
        		rect = new Rectangle(this.getX(), a.getY(), secondW - this.getX(), secondH - rectH);
        		return rect;
        	}
    		if(this.getX() <= a.getX() && this.getY() <= a.getY()){	//determines whether rectangle 'this' is lowest and farthest to the left
    			rect = new Rectangle(a.getX(),a.getY(),rectW - a.getX(),rectH - a.getY());
    			return rect;
    		}
    		else{
    			rect = new Rectangle(this.getX(),this.getY(),secondW - this.getX(),secondH - this.getY());
    			return rect;
    		}
    		
    	}
    	else return rect = new Rectangle(0,0,0,0);
    }
    
    /**
     * determines smallest possible rectangle that contains both Rectangles 'this' and 'a'
     * and returns it
     * @param a
     * @return Rectangle object
     */
    public Rectangle union(Rectangle a){
    	Rectangle rect;
    	int rectW = this.getX() + this.getWidth();	//determines farthest to the right X-coord of rect 'this'
    	int rectH = this.getY() + this.getHeight();	//determines highest Y-coord of rect 'this'
    	int secondW = a.getX() + a.getWidth();	//same as
    	int secondH = a.getY() + a.getHeight();	// above just for rect 'a'
    	
    	if(this.getX() < a.getX() && this.getY() > a.getY()){				//similar special cases used and if 
    		rect = new Rectangle(this.getX(), a.getY(), secondW, rectH);	//statements explained in intersect method
    		return rect;
    	}
    	if(a.getX() < this.getX() && a.getY() > this.getY()){
    		rect = new Rectangle(a.getX(), this.getY(), rectW, secondH);
    		return rect;
    	}
    	if(this.getX() <= a.getX() && this.getY() <= a.getY()){
    		rect = new Rectangle(this.getX(),this.getY(), secondW, secondH);
    		return rect;
    	}
    	else{
    		rect = new Rectangle(a.getX(),a.getY(), rectW, rectH);
    		return rect;
    	}
    }
    
    /**
     * Iterates through ArrayList of Rectangle objects and counts the total
     * number of overlaps then returns that int
     * @param list
     * @return int count
     */
    public static int countNumOverlaps(ArrayList<Rectangle> list){
    	int count = 0;
    	for(int i=0; i<list.size(); i++){
    		for(int j=i+1; j<list.size(); j++){
    			if(list.get(i).overlaps(list.get(j))) count++;
    		}
    	}
    	return count;
    }
    
    /**
     * accepts ArrayList of Rectangle objects as parameter and determines with the use
     * of the intersect method the common area shared between all Rectangle objects within
     * the given ArrayList
     * @param list
     * @return Rectangle
     */
    public static Rectangle commonArea(ArrayList<Rectangle> list){
    	int size = list.size();
    	while(size>1){
    		int i = 0, j = 1;
			Rectangle rect = list.get(i).intersect(list.get(j));
			list.remove(j);
			list.remove(i);
			list.add(i, rect);
			size--;    
    		
    	}
    	Rectangle last = list.get(0);
    	return last;
    }
    
    /**
     * Accepts ArrayList of Rectangle objects and determines the smallest possible
     * Rectangle object that can enclose all of them and returns that Rectangle object
     * @param list
     * @return Rectangle
     */
    public static Rectangle enclosed(ArrayList<Rectangle> list){
    	int x=0,y=0,width=0,height=0;
    	x = list.get(0).getX();		//set X and Y
    	y = list.get(0).getY();		//as respective X and Y coordinates of first Rectangle in given ArrayList
    	for(int i=0; i<list.size(); i++){
    		for(int j=i+1; j<list.size(); j++){
    			if(list.get(j).getX() < x) x = list.get(j).getX();		//Compares X with X Coord of next Rectangle
    			if(list.get(j).getY() < y) y = list.get(j).getY();		//same with Y
    		}
    		
    	}
    	int topX = list.get(0).getWidth() + list.get(0).getX();	//adds width and X coord of first Rectangle 
    	int topY = list.get(0).getHeight() + list.get(0).getY(); //adds height and Y coord of first Rectangle
    	for(int i=0; i<list.size(); i++){
    		for(int j=i+1; j<list.size(); j++){
    			if(list.get(j).getX() + list.get(j).getWidth() > topX) topX = list.get(j).getX() + list.get(j).getWidth();	//iterates through ArrayList seeing how far to the right and 
    			if(list.get(j).getY() + list.get(j).getHeight() > topY) topY = list.get(j).getY() + list.get(j).getHeight();//how high in the coordinate plane the group of rectangles reach
    		}																												//Farthest right and highest get set as topX and topY respectively
    	}
    	width = x+topX;
    	height = y+topY;
    	Rectangle rect = new Rectangle(x,y,width,height);
    	return rect;
    }
    
    /**
     * Overwritten toString returns x,y,width,height of Rectangle object
     */
    public String toString(){
        return "x=" + this.getX() + ", y=" + this.getY() + ", width=" +
        this.getWidth() + ", height=" + this.getHeight();
    }
    
}
