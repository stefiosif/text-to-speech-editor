package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import encodingstrategies.EncodingStrategy;
import tts.TextToSpeechAPI;
import tts.TextToSpeechAPIFactory;

public class Document {
	private String author;
	private String title;
    private final ArrayList<Line> lines = new ArrayList<>();
	private EncodingStrategy encodingStrategy;
	private TextToSpeechAPI audioManager;
	private boolean hasSave = false;
	private LocalDateTime ldt;

	public Document(String author, String title) {
		this.author = author;
		this.title = title;
		audioManager = TextToSpeechAPIFactory.createTTSAPI("FreeTTSAdapter");
		setTTSAPI(audioManager);
	}

	public Document(String author, String title, File file) {
		this.author = author;
		this.title = title;

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String[] tmp = br.readLine().split(":");
			this.author = tmp[1];
            String[] tmp1 = br.readLine().split(":");
			this.title = tmp1[1];
		    while (br.ready()) {
		    	Line newLine = new Line(br.readLine());
		    	lines.add(newLine);
		    }
		}catch (IOException e) {
		    e.printStackTrace();
		}	
		audioManager = TextToSpeechAPIFactory.createTTSAPI("FreeTTSAdapter");
		setTTSAPI(audioManager);
	}
	
	public Document() {}

	public void playContents() {
		for (int index = 1; index < lines.size(); index++) {   
		        lines.get(index).playLine(audioManager);
		}
	}
	
	public void playLine(int lineNum) {
		lines.get(lineNum).playLine(audioManager);
	}
	
	public void playReverseContents() {
		for (int index = 1; index < lines.size(); index++) {   
	        lines.get(index).playReverseLine(audioManager);
		}
	}
	
	public void playReverseLine(int lineNum) {
		lines.get(lineNum).playReverseLine(audioManager);
	}

	public void playEncodedContents() {
		for (int index = 1; index < lines.size(); index++) {
	        lines.get(index).playEncodedLine(encodingStrategy, audioManager);
		}
	}
	
	public void playEncodedLine(int lineNum, EncodingStrategy encodingStrategy) {
		lines.get(lineNum).playEncodedLine(encodingStrategy, audioManager);
	}

	public void tuneEncodingStrategy(EncodingStrategy encodingStrategy) {
		this.encodingStrategy = encodingStrategy;
	}
	
	public EncodingStrategy getEncodingStrategy() {
		return encodingStrategy;
	}
	
	public void setTTSAPI(TextToSpeechAPI tts) {
		this.audioManager = tts;	
	}
	
	public TextToSpeechAPI getTTSAPI() {
		return audioManager;
	}
	
	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public boolean isHasSave() {
		return hasSave;
	}

	public void setHasSave(boolean hasSave) {
		this.hasSave = hasSave;
	}

	public void setLdt(LocalDateTime ldt) {
		this.ldt = ldt;
	}

}
