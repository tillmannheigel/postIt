package main;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import model.Note;
import panels.PostItPanel;
import panels.SidebarPanel;
import panels.ToolbarPanel;
import data.Load;
import data.Save;

/**
 * This is the main class
 * everything can be reached 
 * with this class
 * 
 * @author Tillmann Heigel
 * @version 1.0
 * 
 */
public class NotesApp implements Serializable{

	//Serialisierung
	private static final long serialVersionUID = 6206292154572335611L;

	/**
	 * ToolbarPanel is used for displaying buttons.
	 */
	private ToolbarPanel toolbarPanel;
	
	/**
	 * PostitPanel is used for displaying the 
	 * current postit.
	 */
	private PostItPanel postitPanel;
	
	
	/**
	 * SidebarPandel is used for displaying the list of
	 * postit's.
	 */
	private SidebarPanel sidebarPanel;
	
	/**
	 * List of all Notes from this user
	 */
	private ArrayList<Note> notes;
	
	private Note currentNote;


	/**
	 * Constructs a new NotesApp with the current Date.
	 * @param date
	 */
	public NotesApp(Date date) {
		
		//get model
		this.notes = this.loadNotes();	
		this.currentNote = this.getCurrentNote();
		
		//set view
		this.postitPanel = new PostItPanel(currentNote);
		this.toolbarPanel = new ToolbarPanel(currentNote);
		this.sidebarPanel =  new SidebarPanel(this, notes);
	}

	private Note getCurrentNote() {
		Note note = notes.get(0);
		note.setup(this);
		return note;
	}

	private ArrayList<Note> loadNotes() {
		ArrayList<Note> myNotes = Load.loadNotes();
		System.out.println("Länge: "+myNotes.size());
		for (Note note : myNotes) {
			System.out.println(note.toString());
		}
		if (myNotes.isEmpty()) {
			Note note = new Note(this);
			myNotes.add(note);
			Save.saveNotes(myNotes);
		}
		System.out.println("Länge: "+myNotes.size());
		return myNotes;
	}
	
	public void saveNotes(){
		System.out.println("Save notes: ");
		System.out.println(notes);
		Save.saveNotes(this.notes);
	}

	/**
	 * Getter for getting toolbar
	 * @return the toolbar panel
	 */
	public ToolbarPanel getToolbarPanel() {
		return toolbarPanel;
	}

	/**
	 * Getter for getting postit
	 * @return the postit panel
	 */
	public PostItPanel getPostitPanel() {
		return postitPanel;
	}

	/**
	 * @return the sidebarPanel
	 */
	public SidebarPanel getSidebarPanel() {
		return sidebarPanel;
	}

	/**
	 * @return the notes
	 */
	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	
}
