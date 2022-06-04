package commands;

import view.Text2SpeechEditorView;
import model.Document;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LineToSpeech implements ActionListener {

	private int lineNum;
	private JRadioButton isReverse;
	private JRadioButton notReverse;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Text2SpeechEditorView view = Text2SpeechEditorView.getViewInstance();
		
		JFrame frameLineNum = new JFrame("Pick line to transform.");
		frameLineNum.setBounds(100, 100, 370, 170);
		frameLineNum.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
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
		
		
		transformButton.addActionListener(e1 -> {
			   frameLineNum.dispose();

			   JFrame frameReverse = new JFrame();
			   frameReverse.setBounds(100, 100, 370, 170);
			   frameReverse.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			   isReverse = new JRadioButton("Reverse");
			   notReverse = new JRadioButton("Normal");

			   JButton btnNewButton = new JButton("Transform");

			   GroupLayout groupLayout1 = new GroupLayout(frameReverse.getContentPane());
			   groupLayout1.setHorizontalGroup(
				  groupLayout1.createParallelGroup(Alignment.LEADING)
					  .addGroup(groupLayout1.createSequentialGroup()
						  .addGap(65)
						  .addComponent(notReverse)
						  .addPreferredGap(ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
						  .addComponent(isReverse)
						  .addGap(59))
					  .addGroup(groupLayout1.createSequentialGroup()
						  .addGap(132)
						  .addComponent(btnNewButton)
						  .addContainerGap(133, Short.MAX_VALUE))
					);
				groupLayout1.setVerticalGroup(
				  groupLayout1.createParallelGroup(Alignment.LEADING)
					  .addGroup(groupLayout1.createSequentialGroup()
						  .addGap(46)
						  .addGroup(groupLayout1.createParallelGroup(Alignment.BASELINE)
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
			  frameReverse.getContentPane().setLayout(groupLayout1);


			  frameReverse.getContentPane().setLayout(groupLayout1);
			  frameReverse.setLocationRelativeTo(null);
			  frameReverse.setVisible(true);
			  frameReverse.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

			  btnNewButton.addActionListener(e11 -> {
				  Document document = view.getCurrentDocument();
					  try {
						  if (document.isHasSave()) {
							  lineNum = Integer.parseInt(textLine.getText());
							  boolean toReverse = isReverse.isSelected();

							  if (toReverse) {
								  document.playReverseLine(lineNum);
							  } else {
								  document.playLine(lineNum);
							  }
						  } else {
							  JOptionPane.showMessageDialog(view.getTextArea(), "Save your document first!");
						  }
						  frameReverse.dispose();
					  } catch(NullPointerException n) {
						  JOptionPane.showMessageDialog(view.getTextArea(), "Select a document first!");
					  }
			  });
		});
	}

}
