package tts;

import java.util.logging.Logger;

public class FakeTextToSpeechAPI implements TextToSpeechAPI {

	int volume;
	int pitch;
	int rate;

	@Override
	public void play(String string) { Logger.getLogger(string);	}

	@Override
	public void setVolume(int volume) {
		this.volume = volume;
	}

	@Override
	public void setPitch(int pitch) {
		this.pitch = pitch;
	}

	@Override
	public void setRate(int rate) {
		this.rate = rate;
	}
}
