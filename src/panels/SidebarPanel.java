/**
 * 
 */
package panels;

import java.util.ArrayList;

import javax.swing.JPanel;

import main.NotesApp;
import model.Note;

/**
 * @author Tillmann Heigel
 *
 */
@SuppressWarnings("serial")
public class SidebarPanel extends JPanel {
	
		private ArrayList<Note> notes;
		private NotesApp app;
	
	public SidebarPanel(NotesApp myApp, ArrayList<Note> notes) {
		this.app = myApp;
		this.notes = notes;
	}



}
