package commands;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import model.Document;
import view.Text2SpeechEditorView;

public class PlayEncodedLine implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame frameLineNum = new JFrame("Pick line to transform.");
		frameLineNum.setBounds(100, 100, 370, 170);
		frameLineNum.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Line index");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTextField textLine = new JTextField();
		textLine.setColumns(10);
		
		JButton transformButton = new JButton("Continue");
		GroupLayout groupLayout = new GroupLayout(frameLineNum.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(114)
					.addComponent(transformButton)
					.addContainerGap(139, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
					.addComponent(textLine, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addGap(77))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textLine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addComponent(transformButton)
					.addContainerGap())
		);
		
		
		frameLineNum.getContentPane().setLayout(groupLayout);
		frameLineNum.setLocationRelativeTo(null);
		frameLineNum.setVisible(true);
		frameLineNum.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		
		transformButton.addActionListener(e1 -> {
			frameLineNum.dispose();
			Text2SpeechEditorView view = Text2SpeechEditorView.getViewInstance();
			Document document = view.getCurrentDocument();
			try {
				if (document.isHasSave()) {
					int lineNum = Integer.parseInt(textLine.getText());
					if (view.isEncoded()) {
						document.playEncodedLine(lineNum, document.getEncodingStrategy());
					} else {
						JOptionPane.showMessageDialog(view.getTextArea(), "You have to pick an encoding first.");
					}
				}else {
					JOptionPane.showMessageDialog(view.getTextArea(), "Save your document first!");
				}
			} catch(NullPointerException n) {
				JOptionPane.showMessageDialog(view.getTextArea(), "Select a document first!");
				frameLineNum.dispose();
			}
		});
	}
}
