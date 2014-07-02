package data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Note;

public class Save {
	
	static final String filename = "notes.bin";
	static final String directory = ".notes/";
	static final String homePath = System.getProperty("user.home")+"/";

	public static void saveNotes(ArrayList<Note> notes) {
		try {
			File file = new File(homePath + directory + filename);
			File parent = file.getParentFile();
			if (!parent.exists() && !parent.mkdirs()) {
			    throw new IllegalStateException("Couldn't create dir: " + parent);
			}
			@SuppressWarnings("resource")
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			for (Note note : notes) {
				out.writeObject(note);
			}
			System.out.println("saved");
		} catch (Exception e) {
			System.out.println("fail: "+ e.toString());
		}
	}

}
