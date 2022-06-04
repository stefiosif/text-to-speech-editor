package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import javax.swing.*;

import model.Document;
import view.Text2SpeechEditorView;

public class SaveDocument implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Text2SpeechEditorView view = Text2SpeechEditorView.getViewInstance();
		view.getFrame().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showSaveDialog(view.getFrame());
		chooser.setCurrentDirectory(new File(chooser.getCurrentDirectory().getAbsolutePath()));
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = new File(chooser.getSelectedFile().getAbsolutePath()); 
            LocalDateTime ldt = LocalDateTime.now();
            try {
            	FileWriter fw = new FileWriter(selectedFile, false); 
                BufferedWriter bw = new BufferedWriter(fw); 
                bw.write(view.getTextArea().getText()); 
                bw.flush(); 
                bw.close(); 
                
                String author = view.getCurrentDocument().getAuthor();
                String title = view.getCurrentDocument().getTitle();
                Document document = new Document(author, title, selectedFile);
    			document.setHasSave(true);
    			document.setLdt(ldt);  /*local date time*/
                view.setCurrentDocument(document);
            } 
            catch (Exception evt) {
                JOptionPane.showMessageDialog(view.getTextArea(), evt.getMessage());
            }
        }
	}
}
