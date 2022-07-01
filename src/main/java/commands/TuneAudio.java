package commands;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import model.Document;
import tts.TextToSpeechAPI;
import view.Text2SpeechEditorView;

public class TuneAudio implements ActionListener {

	private int volume = 50;
	private int rate = 75;
	private int pitch = 150;
	static final int MIN = 0;
	static final int MAX = 200;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame audioFrame = new JFrame("Adjust sound.");
		audioFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		audioFrame.setBounds(150, 150, 400, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		audioFrame.setContentPane(contentPane);
		
		Font font = new Font("Consolas", Font.ITALIC, 12);
		JSlider sliderVolume = new JSlider(SwingConstants.HORIZONTAL, MIN, MAX, volume);
		JSlider sliderSpeechRate = new JSlider(SwingConstants.HORIZONTAL, MIN, MAX, rate);
		JSlider sliderPitch = new JSlider(SwingConstants.HORIZONTAL, MIN, MAX, pitch);
		
		sliderVolume.setMajorTickSpacing(40);
		sliderVolume.setMinorTickSpacing(20);
		sliderVolume.setPaintTicks(true);
		sliderVolume.setPaintLabels(true);
		sliderVolume.setFont(font);
		sliderVolume.addChangeListener(e1 -> volume = sliderVolume.getValue());
		
		sliderSpeechRate.setMajorTickSpacing(40);
		sliderSpeechRate.setMinorTickSpacing(20);
		sliderSpeechRate.setPaintTicks(true);
		sliderSpeechRate.setPaintLabels(true);
		sliderSpeechRate.setFont(font);
		sliderSpeechRate.addChangeListener(e12 -> rate = sliderSpeechRate.getValue());

		sliderPitch.setMajorTickSpacing(40);
		sliderPitch.setMinorTickSpacing(20);
		sliderPitch.setPaintTicks(true);
		sliderPitch.setPaintLabels(true);
		sliderPitch.setFont(font);
		sliderPitch.addChangeListener(e13 -> pitch = sliderPitch.getValue());
		
		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(e14 -> audioFrame.dispose());
		
		JButton buttonSave = new JButton("Save changes");
		buttonSave.addActionListener(e15 -> {
			audioFrame.dispose();
			Text2SpeechEditorView view = Text2SpeechEditorView.getViewInstance();
			try {
				Document document = view.getCurrentDocument();
				TextToSpeechAPI tts = document.getTTSAPI();

				tts.setPitch(pitch);
				tts.setRate(rate);
				tts.setVolume(volume);
				document.setTTSAPI(tts);
			} catch(NullPointerException n) {
				JOptionPane.showMessageDialog(view.getTextArea(), "Select a document first!");
			}
		});
		
		JLabel labelVolume = new JLabel("Volume");
		labelVolume.setFont(new Font("Consolas", Font.PLAIN, 13));
		JLabel labelSpeechRate = new JLabel("Speech Rate");
		labelSpeechRate.setFont(new Font("Consolas", Font.PLAIN, 13));
		JLabel labelPitch = new JLabel("Pitch");
		labelPitch.setFont(new Font("Consolas", Font.PLAIN, 13));
		
		GroupLayout glContentPane = new GroupLayout(contentPane);
		glContentPane.setHorizontalGroup(
			glContentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(glContentPane.createSequentialGroup()
					.addGroup(glContentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(glContentPane.createSequentialGroup()
							.addGap(32)
							.addComponent(buttonCancel)
							.addGap(4))
						.addGroup(glContentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelSpeechRate))
						.addGroup(glContentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelPitch))
						.addGroup(glContentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelVolume)))
					.addGroup(glContentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(glContentPane.createSequentialGroup()
							.addGap(12)
							.addGroup(glContentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(sliderVolume, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(sliderSpeechRate, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(sliderPitch, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, glContentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
							.addComponent(buttonSave)
							.addGap(51))))
		);
		glContentPane.setVerticalGroup(
			glContentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(glContentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(glContentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(sliderVolume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelVolume))
					.addGap(27)
					.addGroup(glContentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(sliderSpeechRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelSpeechRate))
					.addGap(18)
					.addGroup(glContentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(sliderPitch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelPitch))
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addGroup(glContentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonCancel)
						.addComponent(buttonSave))
					.addContainerGap())
		);
		contentPane.setLayout(glContentPane);
		audioFrame.setLocationRelativeTo(null);
		audioFrame.setVisible(true);

	}

}
