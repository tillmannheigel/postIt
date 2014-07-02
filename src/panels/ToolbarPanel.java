package panels;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.StyledDocument;

import model.Note;


@SuppressWarnings("serial")
public class ToolbarPanel extends JPanel {
	
	Note note;
	
	JToolBar toolbar = new JToolBar();
	
	private JToggleButton boldButton = new JToggleButton();
	private JToggleButton italicButton = new JToggleButton();
	private JToggleButton underlineButton = new JToggleButton();
	private JButton saveButton = new JButton();
	
	StyledDocument doc;

	JComboBox<String> fontsComboBox;
	
	String[] fonts;
	
	public ToolbarPanel(Note note){
		
		this.note = note;
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		this.setFontBox();
		this.setButtons();
		this.setToolBar();	
		
		this.add(toolbar);
		
	}

	/**
	 * This method will setup 
	 * all toolbar buttons
	 * and the fonts combobox
	 */
	private void setToolBar() {
		toolbar.setFloatable(false);
		toolbar.add(saveButton);
		toolbar.add(boldButton);
		toolbar.add(italicButton);
		toolbar.add(underlineButton);
		toolbar.add(fontsComboBox);
	}

	/**
	 * This method sets
	 */
	private void setFontBox() {
		GraphicsEnvironment enviroment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		fonts = enviroment.getAvailableFontFamilyNames();
		fontsComboBox = new JComboBox<String>(fonts);		
		fontsComboBox.addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				System.out.println("PopupListener: "+fontsComboBox.getSelectedItem().toString());
				setFont(fontsComboBox.getSelectedItem().toString());
				note.focusTextPane();
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {			
			}
		});
		
		fontsComboBox.setSelectedItem(note.getFontFamily());
	}
	
	private void setFont(String font) {
		this.note.setFontFamily(font);		
	}

	public void setButtons(){
		boldButton.setIcon(new ImageIcon("src/img/font_bold_icon&32.png"));
		italicButton.setIcon(new ImageIcon("src/img/font_italic_icon&32.png"));
		underlineButton.setIcon(new ImageIcon("src/img/font_underline_icon&32.png"));
		saveButton.setIcon(new ImageIcon("src/img/system-save-icon-small.png"));
		
		boldButton.setSelected(note.isBold());
		italicButton.setSelected(note.isItalic());
		underlineButton.setSelected(note.isUnderlined());
		
		boldButton.addItemListener(new ItemListener() {
						
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				note.setBold(e.getStateChange()==e.SELECTED);
				
				if (note.hasSelection()) {
					note.loadCurrentAttributes();
				}
				
				note.focusTextPane();
			}
		});
	
		italicButton.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				note.setItalic(e.getStateChange()==e.SELECTED);
				if (note.hasSelection()) {
					note.loadCurrentAttributes();
				}
				note.focusTextPane();

			}
		});

		underlineButton.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				note.setUnderlined(e.getStateChange()==e.SELECTED);
				if (note.hasSelection()) {
					note.loadCurrentAttributes();
				}
				note.focusTextPane();


				}
		});
		
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Action performed");
				note.saveNotes();
				note.focusTextPane();
			}
		});
	}
	
	public void setToolbarActive(Boolean active){
		boldButton.setEnabled(active);
		italicButton.setEnabled(active);
		underlineButton.setEnabled(active);
		fontsComboBox.setEnabled(active);
	}

	public void updateAttributes(boolean bold, boolean italic,
			boolean underlined, String fontFamily) {
		boldButton.setSelected(bold);
		italicButton.setSelected(italic);
		underlineButton.setSelected(underlined);
		fontsComboBox.setSelectedItem(fontFamily);
	}
}
