package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.*;

import model.Document;
import view.Text2SpeechEditorView;

public class OpenDocument implements ActionListener {
	private String author;
	private String title;

	@Override
	public void actionPerformed(ActionEvent e) {
		Text2SpeechEditorView view = Text2SpeechEditorView.getViewInstance();
		
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(view.getFrame());
		chooser.setCurrentDirectory(new File(chooser.getSelectedFile().getAbsolutePath()));
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			view.getTextArea().setEditable(false);

			try {
				String aLine = "";
				String text = "";
	            FileReader fr = new FileReader(selectedFile); 
	            BufferedReader br = new BufferedReader(fr); 

	            text = br.readLine();
	            String[] split1 = text.split(":");
	            title = split1[1];
	            String temp = br.readLine();
	            String[] split2 = temp.split(":");
	            author = split2[1];
	            text = text + "\n" + temp + br.readLine() + "\n";
	            while ((aLine = br.readLine()) != null) {
	            	text = text + "\n" + aLine;
	            }
	            view.getTextArea().setText(text);
	            br.close();
	           
			} catch (Exception evt) {
				JOptionPane.showMessageDialog(view.getFrame(), evt.getMessage()); 
			}
			
			Document document = new Document(author, title, selectedFile);
			view.setCurrentDocument(document);
			document.setHasSave(true);
			view.getFrame().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			view.getFrame().add(view.getTextArea());
			view.getFrame().setVisible(true);
		}
	}
}


