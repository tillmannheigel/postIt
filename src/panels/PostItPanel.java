package panels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledDocument;

import main.NotesApp;
import model.Note;
import model.NotesTextPane;

/**
 * This class represents the complete
 * postIt panel without
 * @author Tillmann Heigel
 *
 */
@SuppressWarnings("serial")
public class PostItPanel extends JPanel {
	
	Note			note;
	NotesTextPane	textPane;
	StyledDocument	styledDocument;
	JTextField		title;
	JLabel 			dateLabel;
	JScrollPane		scrollPane;
	JPanel 			headerPanel;
	
	public PostItPanel(Note myNote){
		
		note = myNote;
				
		this.setLayout(new BorderLayout());
			
		//dateLabel = this.getHeader(notesApp.getGenerationDate());
		
		headerPanel = this.getHeaderPanel(note);
		textPane = note.getTextPane();
		scrollPane = this.getScrollPaneForTextPane(textPane);

		this.add(scrollPane, BorderLayout.CENTER);
		this.add(headerPanel, BorderLayout.PAGE_START);	
		
		title.requestFocus();
	}

	private JScrollPane getScrollPaneForTextPane(NotesTextPane myTextPane) {
		JScrollPane myScrollPane = new JScrollPane(myTextPane);
		myScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		return myScrollPane;
	}

	private JPanel getHeaderPanel(Note note) {
		JPanel myHeaderPanel = new JPanel();
		myHeaderPanel.setLayout(new BorderLayout());
		title = this.getTitle();
		title.setText(note.getTitle());
		title.setFont(new Font("Dialog", Font.BOLD, 12));
		title.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				title.setCaretPosition(0);
				//app.getToolbarPanel().setToolbarActive(true);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				title.selectAll();
				//app.getToolbarPanel().setToolbarActive(false);
				
			}
		});
		dateLabel = this.getHeader(note.getGenerationDate());
		myHeaderPanel.add(title, BorderLayout.CENTER);
		myHeaderPanel.add(dateLabel, BorderLayout.EAST);
		title.requestFocus();
		return myHeaderPanel;
	}

	private JTextField getTitle() {
		final JTextField myTextfield = new JTextField();
		myTextfield.setOpaque(true);
		myTextfield.setBackground(Color.YELLOW);
		myTextfield.setBorder(new EmptyBorder(5,5,5,5));
		myTextfield.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				note.setTitle(myTextfield.getText()+e.getKeyChar());
			}
			
			@Override
			public void keyReleased(KeyEvent e) {				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		return myTextfield;
	}
	

	private JLabel getHeader(Date date) {
		JLabel myHeader = new JLabel(formattedDate(date));
		myHeader.setBorder(new EmptyBorder(5, 5, 5, 5));
		myHeader.setOpaque(true);
		myHeader.setBackground(Color.YELLOW);
		myHeader.setHorizontalAlignment(JLabel.RIGHT);
		return myHeader;
	}

	private String formattedDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		return dateFormat.format(date);
	}
	
	public NotesTextPane getNotesTextPane(){
		return this.textPane;
	}

}
