package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.Date;

import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import main.NotesApp;

public class Note implements Serializable{
	
	//Serialization
	private static final long serialVersionUID = -2045366320593037212L;
	
	//Model
	private Date generationDate;
	private Date lastModifyDate;
	private String title;
	
	//TextPane
	private NotesTextPane textPane;
	
	//ToolBar
	boolean bold;
	String fontFamily = "Comic Sans MS";
	boolean italic;
	boolean underlined;

	//super things
	private NotesApp app;
	
	public Note(NotesApp app) {
		Date date = new Date();
		this.generationDate = date;
		this.setLastModifyDate(date);
		this.title = "title";
		this.textPane = getNotesTextPane();
		this.setup(app);
	}

	private NotesTextPane getNotesTextPane() {
			NotesTextPane myTextPane = new NotesTextPane(this);
			myTextPane.setBackground(Color.YELLOW);
			myTextPane.setBorder(new EmptyBorder(8, 8, 8, 8));		
			return myTextPane;
	}

	/**
	 * @return the generationDate
	 */
	public Date getGenerationDate() {
		return generationDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Note [generationDate=" + generationDate + ", title=" + title
				+ ", text=" + textPane + "]";
	}

	/**
	 * @return the lastModifyDate
	 */
	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	/**
	 * @param lastModifyDate the lastModifyDate to set
	 */
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	/**
	 * @return the textPane
	 */
	public NotesTextPane getTextPane() {
		return textPane;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isBold() {
		return bold;
	}

	public boolean isItalic() {
		return italic;
	}

	public boolean isUnderlined() {
		return underlined;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
		this.textPane.loadCurrentAttributes();
	}


	public void setItalic(boolean italic) {
		this.italic = italic;
		this.textPane.loadCurrentAttributes();
	}

	public void setUnderlined(boolean underlined) {
		this.underlined = underlined;
		this.textPane.loadCurrentAttributes();
	}

	public String getFontFamily() {
		return this.fontFamily;
	}

	/**
	 * @param fontFamily the fontFamily to set
	 */
	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
		this.textPane.loadCurrentAttributes();
	}

	public AttributeSet getAttributes() {
		SimpleAttributeSet attributes = new SimpleAttributeSet();
		StyleConstants.setFontSize(attributes, 14);
	 	StyleConstants.setBold(attributes, this.isBold());
	 	StyleConstants.setItalic(attributes,  this.isItalic());
	 	StyleConstants.setUnderline(attributes,  this.isUnderlined());
	 	StyleConstants.setFontFamily(attributes, this.getFontFamily());
	 	return attributes;
	}

	public boolean hasSelection() {
		return this.textPane.hasSelection();
	}

	public void loadCurrentAttributes() {
		this.textPane.loadCurrentAttributes();
	}

	public void saveNotes() {
		this.lastModifyDate = new Date();
		this.app.saveNotes();
	}

	public void focusTextPane() {
		this.textPane.requestFocus();
	}
	
	public void setup(NotesApp app) {
		this.app = app;
		this.textPane.setup(this);
	}

	public void updateAttributes(AttributeSet attributes) {
		bold = attributes.containsAttribute(StyleConstants.Bold, true);
	 	italic = attributes.containsAttribute(StyleConstants.Italic, true);
	 	underlined = attributes.containsAttribute(StyleConstants.Underline, true);
	 	fontFamily = (String) attributes.getAttribute(StyleConstants.FontFamily);
	 	app.getToolbarPanel().updateAttributes(bold,italic,underlined,fontFamily);
	}

}
