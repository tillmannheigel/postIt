package main;
import java.util.Date;

import panels.NotesFrame;

/**
 * This is the main class.
 * Everything starts here.
 * You can change the size of
 * the window, if you change the 
 * values of x and y.
 * 
 * @author Tillmann Heigel
 *
 */
public class NotesMain{
	
	static int x = 420;
	static int y = 320;
	
	public static void main(String[] args) {
		NotesApp app = new NotesApp(new Date());
		
		NotesFrame frame = new NotesFrame(app);
		
		frame.setSize(x, y);
		
		frame.setVisible(true);
	}

}
