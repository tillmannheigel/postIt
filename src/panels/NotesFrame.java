package panels;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.NotesApp;

/**
 * This class represents the root frame 
 * of the window with its panels. 
 * 
 * @author Tillmann Heigel
 *
 */
@SuppressWarnings("serial")
public class NotesFrame extends JFrame {
	
	JPanel mainPanel = new JPanel();

	public NotesFrame(NotesApp app){
				
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(false);
		
		mainPanel.setLayout(new BorderLayout());
						
		mainPanel.add(app.getToolbarPanel(),BorderLayout.PAGE_START);
		mainPanel.add(app.getPostitPanel(),BorderLayout.CENTER);
		mainPanel.add(app.getSidebarPanel(),BorderLayout.EAST);
		
		this.getContentPane().add(mainPanel);

		pack();		
		
		app.getPostitPanel().title.requestFocus() ;
	}
	

}
