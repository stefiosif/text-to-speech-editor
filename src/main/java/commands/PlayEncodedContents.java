package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.Document;
import view.Text2SpeechEditorView;

public class PlayEncodedContents implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		  Text2SpeechEditorView view = Text2SpeechEditorView.getViewInstance();
		  Document document = view.getCurrentDocument();
		  view.getFrame().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		  try {
			  if (document.isHasSave()) {
			  	  if (view.isEncoded()) {
					  document.playEncodedContents();
					  
				  } else {
					  JOptionPane.showMessageDialog(view.getTextArea(), "You need to pick an encoding first.");
				  }
			  }else {
				  JOptionPane.showMessageDialog(view.getTextArea(), "Save your document first!");
			  }
		  } catch(NullPointerException n) {
			  JOptionPane.showMessageDialog(view.getTextArea(), "Select a document first!");
		  }
	}
}
