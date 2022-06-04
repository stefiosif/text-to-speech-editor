package tts;

public interface TextToSpeechAPI {

	public void play(String string);
	
	public void setVolume(int volume);
	
	public void setPitch(int pitch);
	
	public void setRate(int rate);
}
