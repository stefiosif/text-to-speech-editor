package tts;

public interface TextToSpeechAPI {

	void play(String string);
	
	void setVolume(int volume);
	
	void setPitch(int pitch);
	
	void setRate(int rate);
}
