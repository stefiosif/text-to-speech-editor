package view;

import javax.swing.*;

import commands.CommandsFactory;
import model.Document;
import java.awt.BorderLayout;
import java.awt.Font;

public class Text2SpeechEditorView{
	
	private static Text2SpeechEditorView viewInstance;
	private Document currentDocument;
	private final JFrame mainFrame;
	private final JTextArea textArea;
	private boolean encoded;

	private Text2SpeechEditorView() {
		mainFrame = new JFrame("Text2Speech Editor");
		mainFrame.setBounds(200, 200, 800, 550);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		mainFrame.setJMenuBar(menuBar);
		
		//DOCUMENT MANAGEMENT
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		JMenuItem itemNew = new JMenuItem("New");
		menuFile.add(itemNew);
		itemNew.addActionListener(CommandsFactory.createCommand("NewDocument"));
		
		JMenuItem itemOpen = new JMenuItem("Open");
		menuFile.add(itemOpen);
		itemOpen.addActionListener(CommandsFactory.createCommand("OpenDocument"));
		
		JMenuItem itemEdit = new JMenuItem("Edit");
		menuFile.add(itemEdit);
		itemEdit.addActionListener(CommandsFactory.createCommand("EditDocument"));

		JMenuItem itemSave = new JMenuItem("Save");
		menuFile.add(itemSave);
		itemSave.addActionListener(CommandsFactory.createCommand("SaveDocument"));
		
		JMenuItem itemExit = new JMenuItem("Exit");
		menuFile.add(itemExit);
		itemExit.addActionListener(e -> mainFrame.dispose());
		
		//DOCUMENT MANAGEMENT
		JMenu menuRun = new JMenu("Transform");
		menuBar.add(menuRun);
		
		JMenuItem itemDoc2Speech = new JMenuItem("Document");
		menuRun.add(itemDoc2Speech);
		itemDoc2Speech.addActionListener(CommandsFactory.createCommand("DocumentToSpeech"));
				
		JMenuItem itemLine2Speech = new JMenuItem("Line");
		menuRun.add(itemLine2Speech);
		itemLine2Speech.addActionListener(CommandsFactory.createCommand("LineToSpeech"));
		
		//AUDIO TUNING
		JMenu menuAudio = new JMenu("Audio");
		menuBar.add(menuAudio);
		
		JMenuItem itemAudio = new JMenuItem("Tune Audio");
		menuAudio.add(itemAudio);
		itemAudio.addActionListener(CommandsFactory.createCommand("TuneAudio"));
		
		//ENCODING
		JMenu menuEncoding = new JMenu("Encoding");
		menuBar.add(menuEncoding);
		
		JMenuItem itemEncodeDoc = new JMenuItem("Encoded Contents");
		menuEncoding.add(itemEncodeDoc);
		itemEncodeDoc.addActionListener(CommandsFactory.createCommand("PlayEncodedContents"));
		
		JMenuItem itemEncodeLine = new JMenuItem("Encoded Line");
		menuEncoding.add(itemEncodeLine);
		itemEncodeLine.addActionListener(CommandsFactory.createCommand("PlayEncodedLine"));

		JMenuItem itemEncode = new JMenuItem("Tune Encoding");
		menuEncoding.add(itemEncode);
		itemEncode.addActionListener(CommandsFactory.createCommand("TuneEncoding"));
		
        Font font = new Font("Consolas",Font.PLAIN, 16);
		textArea = new JTextArea();
        textArea.setFont(font);
        textArea.setEditable(false);
        mainFrame.setLocationRelativeTo(null);
		mainFrame.getContentPane().add(textArea, BorderLayout.CENTER);
		mainFrame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		Text2SpeechEditorView.getViewInstance();
	}
	
	public static Text2SpeechEditorView getViewInstance() {
		if (viewInstance == null) {
			viewInstance = new Text2SpeechEditorView();
		}
		return viewInstance;
	}
	
	public JFrame getFrame() {
		return mainFrame;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}

	public void setCurrentDocument(Document document) {
		this.currentDocument = document;
	}
	
	public Document getCurrentDocument() {
		return currentDocument;
	}
	
	public boolean isEncoded() {
		return getIsEncoded();
	}
	
	public void setIsEncoded(boolean isEncoded) {
		this.encoded = isEncoded;
	}

	public boolean getIsEncoded() {
		return encoded;
	}
}