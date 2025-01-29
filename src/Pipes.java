//Pipe properties
public class Pipes {
	public int XCoordinate = 300;
	public int width = 50;
	public int height = 500;
	public int gap = 120;
	
	public int randomYPositionTop = (int) (-400 + (Math.random() * 401)); //sets the position between -400 and 0
	public int YPositionBottom =  randomYPositionTop + height + gap; //the Y position for the bottom pipe will depend on the top one
	
	
}
