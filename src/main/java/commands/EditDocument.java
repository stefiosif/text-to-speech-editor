package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import view.Text2SpeechEditorView;

public class EditDocument implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Text2SpeechEditorView view = Text2SpeechEditorView.getViewInstance();

		try {
			view.getTextArea().setEditable(true);
		} catch(NullPointerException n) {
			  JOptionPane.showMessageDialog(view.getTextArea(), "Select a document first!");
		} 
		view.getFrame().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}
