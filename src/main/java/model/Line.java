package model;

import java.util.ArrayList;
import java.util.Collections;
import encodingstrategies.EncodingStrategy;
import tts.TextToSpeechAPI;

public class Line {
	private final ArrayList<String> words = new ArrayList<>();

	public Line(String line) {
		String[] splitter = line.split(" ");
		Collections.addAll(words, splitter);
	}
	
	public void playLine(TextToSpeechAPI audioManager) {
		for (String word : words) {
			audioManager.play(word + " ");
		}
	}
	
	public void playReverseLine(TextToSpeechAPI audioManager) {
		for (int index = words.size(); index > 0; index--) {
			audioManager.play(words.get(index-1) + " ");
		}
	}
	
	public void playEncodedLine(EncodingStrategy encodingStrategy, TextToSpeechAPI audioManager) {
		for (String word : words) {
			audioManager.play(encodingStrategy.encode(word) + " ");
		}
	}
}
