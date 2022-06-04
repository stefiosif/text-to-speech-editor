package commands;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import model.Document;
import view.Text2SpeechEditorView;

public class TuneEncoding implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {		
		JFrame frameEncoding = new JFrame("Choose Encoding Style.");
		frameEncoding.setBounds(100, 100, 400, 225);
		frameEncoding.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		JRadioButton rotThirteen = new JRadioButton("Rot-13");
		rotThirteen.setFont(new Font("Consolas", Font.PLAIN, 14));
		
		JRadioButton atBash = new JRadioButton("AtBash");
		atBash.setFont(new Font("Consolas", Font.PLAIN, 14));
		
		JRadioButton noEncodingButton = new JRadioButton("No encoding");
		noEncodingButton.setFont(new Font("Consolas", Font.PLAIN, 14));
		
		ButtonGroup group = new ButtonGroup();
		group.add(rotThirteen);
		group.add(atBash);
		group.add(noEncodingButton);
		noEncodingButton.setSelected(true);
		
		JButton cancelButton = new JButton("Cancel");		
		cancelButton.addActionListener(e1 -> frameEncoding.dispose());
		
		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(e12 -> {
			  Text2SpeechEditorView view = Text2SpeechEditorView.getViewInstance();
			  Document document = view.getCurrentDocument();
			  StrategiesFactory strategiesFactory = new StrategiesFactory();

			  try {
				  if (document.isHasSave()) {
					  boolean rot = rotThirteen.isSelected();
					  boolean bash = atBash.isSelected();
					  String retval = null;
					  if (rot)
						  retval = "Rot13Encoding";
					  else if(bash)
						  retval = "AtBashEncoding";

					  if (rot || bash) {
						  view.setIsEncoded(true);
						  EncodingStrategy enc = strategiesFactory.createStrategy(retval);
						  document.tuneEncodingStrategy(enc);
					  } else {
						  view.setIsEncoded(false);
					  }
				  }else {
					  JOptionPane.showMessageDialog(view.getTextArea(), "Save your document first!");
				  }
				  frameEncoding.dispose();
			  } catch(NullPointerException n) {
				  JOptionPane.showMessageDialog(view.getTextArea(), "Select a document first!");
				  frameEncoding.dispose();
			  }
		  });

		GroupLayout groupLayout = new GroupLayout(frameEncoding.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(continueButton, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
					.addGap(74)
					.addComponent(cancelButton)
					.addGap(55))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(atBash)
					.addGap(48)
					.addComponent(rotThirteen)
					.addGap(26)
					.addComponent(noEncodingButton)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(63)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(atBash)
						.addComponent(rotThirteen)
						.addComponent(noEncodingButton))
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(continueButton)
						.addComponent(cancelButton))
					.addGap(25))
		);
		
		frameEncoding.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frameEncoding.getContentPane().setLayout(groupLayout);
		frameEncoding.setLocationRelativeTo(null);
		frameEncoding.setVisible(true);
	}
}
