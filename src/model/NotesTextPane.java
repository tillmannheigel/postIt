package model;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Document;


@SuppressWarnings("serial")
public class NotesTextPane extends JTextPane {

	//size
	 boolean wrap=true ;
	 
	 //selection
	 private int start = 0;
	 private int stop = 0;
	 
	 //cursor
	 private int currentPosition = 0;
	 private int oldLength = 0;
	 private int newLength = 0;
	 
	 //the note to which
	 //this textPane belongs
	 private Note myNote;
	 
	public NotesTextPane(Note note){
			System.out.println("GENERATED: new NotesTextPane(Note)");
		 	this.setup(note);
		 	
	 }
	/**
	 *	sets up the note.
	 *	set
	 *	- keyListener
	 *	- caretListener
	 *	load current Attributes
	 */
	public void setup(Note note) {
		this.myNote = note;
	 
		this.addCaretListener(new CaretListener() {
			
			public void caretUpdate(CaretEvent e) {
				if (e.getDot() - e.getMark() > 0) {
					start = e.getMark(); 
					stop = e.getDot();
					setPosition(e.getDot());
					//panel.app.getToolbarPanel().deselectAllButtons();
					
				} else if (e.getMark() - e.getDot() > 0) {
					start = e.getDot(); 
					stop = e.getMark();
					setPosition(e.getDot());
					//panel.app.getToolbarPanel().deselectAllButtons();
				} else {
					start = 0; 
					stop = 0;
					setPosition(e.getDot());
					if (e.getDot()>0) {
						updateAttributesWithPosition(e.getDot()-1);
					}
				}
			}

		});		
		
		this.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				loadCurrentAttributes();
			}
		});
		this.loadCurrentAttributes();
	}

	/**
	 * This method loads attributes 
	 * from myNote and sets characterAttributes.
	 */
	public void loadCurrentAttributes() {
		this.setCharacterAttributes(this.myNote.getAttributes(), false); 
	}
	
	private void updateAttributesWithPosition(int dot) {
		myNote.updateAttributes(this.getStyledDocument().getCharacterElement(dot).getAttributes());
	}
		
	/**
	 * super.getScrollableTrackViewportWidth()
	 * if wrap is true
	 * @return 
	 */
	public boolean getScrollableTracksViewportWidth(){
	      if (wrap)
	         return super.getScrollableTracksViewportWidth() ;
	      else
	         return false ;
	   }

	   //Sets the line-wrapping policy of the JTextPane
	   //By default this property is true, d.h. Zeilenumbruch
	   void setLineWrap(boolean wrap)
	   {
	      setVisible(false);  // alten Zustand verschwinden lassen (notwendig)
	      this.wrap = wrap ;
	      setVisible(true);   // neuen Zustand anzuzeigen (notwendig)
	   }
	   
	   public Boolean hasSelection(){
		return (stop-start)>0;  
	   }
	   
	   public int getStart() {
		    System.out.println(start);
			return start;
		}

		public int getStop() {
			return stop;
		}

		public int getPosition() {
			return currentPosition;
		}


		private void setPosition(int dot) {
			this.currentPosition = dot;
		}

			/* (non-Javadoc)
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				Document doc = this.getDocument();
				String text;
				try {
					text = doc.getText(0, doc.getLength());
				} catch (Exception e) {
					text = "error " + e;
				}
				return text;
			}

			public void setSize(Dimension d)
			   {
			      if(!wrap)
			      {
			         if (d.width < getParent().getSize().width)
			            d.width = getParent().getSize().width;
			      }
			      super.setSize(d);
			   }

	}


