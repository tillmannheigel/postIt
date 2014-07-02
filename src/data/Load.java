package data;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import model.Note;


public class Load {
	
	static final String filename = "notes.bin";
	static final String directory = ".notes/";
	static final String homePath = System.getProperty("user.home")+"/";
	
	public static ArrayList<Note> loadNotes(){
		ArrayList<Note> myNotes = new ArrayList<Note>();
		
		try {
			@SuppressWarnings("resource")
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(homePath + directory + filename));
			System.out.println("loaded");
			Object obj = in.readObject();
			while (true) {
				myNotes.add((Note)obj);
				obj = in.readObject();
			}
		} catch (Exception e) {
			if (e instanceof EOFException) {
				System.out.println("reached file end");
				return myNotes;
			}
		}
		return myNotes;
	}
}
