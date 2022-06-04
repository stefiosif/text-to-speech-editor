package commands;

import model.Document;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import java.awt.Font;
import view.Text2SpeechEditorView;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class NewDocument implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Text2SpeechEditorView view = Text2SpeechEditorView.getViewInstance();
		
		JFrame inputFrame = new JFrame("Type author and title.");
		inputFrame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		inputFrame.setBounds(100, 100, 350, 250);
		inputFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		JLabel labelAuthor = new JLabel("Author");
		labelAuthor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel labelTitle = new JLabel("Title");
		labelTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTextField textAuthor = new JTextField();
		textAuthor.setColumns(10);
		
		JTextField textTitle = new JTextField();
		textTitle.setColumns(10);
		
		JButton buttonNew = new JButton("Create");
		
		GroupLayout groupLayout = new GroupLayout(inputFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(buttonNew))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(58)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(labelAuthor)
								.addComponent(labelTitle))
							.addGap(55)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(textTitle, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
								.addComponent(textAuthor, Alignment.LEADING))))
					.addGap(49))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textAuthor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelAuthor))
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelTitle)
						.addComponent(textTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addComponent(buttonNew)
					.addGap(28))
		);
		inputFrame.setLocationRelativeTo(null);
		inputFrame.getContentPane().setLayout(groupLayout);
		inputFrame.setVisible(true);
		
		buttonNew.addActionListener(e1 -> {
			view.getTextArea().setText("Title: " + textTitle.getText() + "\nAuthor: " + textAuthor.getText() + "\n\n");
			Document document = new Document(textAuthor.getText(),textTitle.getText());
			  LocalDateTime ldt = LocalDateTime.now();

			try {
				view.setCurrentDocument(document);
				document.setLdt(ldt);  /*local date time*/

			} catch(NullPointerException n) {
				JOptionPane.showMessageDialog(view.getTextArea(), "Select a document first!");
			}
			inputFrame.dispose();
			view.getTextArea().setEditable(true);
		});
	}
	
}
