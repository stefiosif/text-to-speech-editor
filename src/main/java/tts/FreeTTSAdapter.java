package tts;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSAdapter implements TextToSpeechAPI {
	
	int volume = 50;
	int pitch = 75;
	int rate = 150;
	
	@Override
	public void play(String string) {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin");
        if (voice != null) {
            voice.allocate();
        } try {
			assert voice != null;
			voice.setRate(this.rate);
        	voice.setPitch(this.pitch);
        	voice.setVolume(this.volume);
        	voice.speak(string);	
        } catch (Exception e1) {
            e1.printStackTrace();
        }
	}

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
