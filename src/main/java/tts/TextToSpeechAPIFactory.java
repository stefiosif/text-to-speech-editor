package tts;

public class TextToSpeechAPIFactory{
	
	public TextToSpeechAPI createTTSAPI(String APIType) {
		if (APIType.equals("FreeTTSAdapter")){
			return new FreeTTSAdapter();
		} else if (APIType.equals("FakeTextToSpeechAPI")) {
			return new FakeTextToSpeechAPI();
		} else {
			return null;
		}
	}

}
