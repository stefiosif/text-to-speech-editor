package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import model.Document;
import view.Text2SpeechEditorView;

public class DocumentToSpeech implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Text2SpeechEditorView view = Text2SpeechEditorView.getViewInstance();
		JFrame frameReverse = new JFrame();
		frameReverse.setBounds(100, 100, 370, 170);
		frameReverse.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JRadioButton isReverse = new JRadioButton("Reverse");
		JRadioButton notReverse = new JRadioButton("Normal");
			
		 JButton btnNewButton = new JButton("Transform");

		 GroupLayout groupLayout = new GroupLayout(frameReverse.getContentPane());
		 groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(65)
					.addComponent(notReverse)
					.addPreferredGap(ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
					.addComponent(isReverse)
					.addGap(59))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(132)
					.addComponent(btnNewButton)
					.addContainerGap(133, Short.MAX_VALUE))
			  );
	  	groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(notReverse)
						.addComponent(isReverse))
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap(21, Short.MAX_VALUE))
			  );
		
		ButtonGroup group = new ButtonGroup();
		group.add(isReverse);
		group.add(notReverse);
		notReverse.setSelected(true);
		frameReverse.getContentPane().setLayout(groupLayout);
		frameReverse.setLocationRelativeTo(null);
		frameReverse.setVisible(true);
		frameReverse.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		btnNewButton.addActionListener(e1 -> {
			Document document = view.getCurrentDocument();
			try {
				boolean toReverse = isReverse.isSelected();
				if (document.isHasSave()) {
					if (toReverse) {
						document.playReverseContents();
					} else {
						document.playContents();
					}
				} else {
					JOptionPane.showMessageDialog(view.getTextArea(), "Save your document first!");
				}
				frameReverse.dispose();
			} catch(NullPointerException n) {
				JOptionPane.showMessageDialog(view.getTextArea(), "Select a document first!");
				frameReverse.dispose();
			}
		});
	  }
}

