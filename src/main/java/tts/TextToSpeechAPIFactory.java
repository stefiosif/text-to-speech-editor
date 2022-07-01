package tts;

public class TextToSpeechAPIFactory {

	private TextToSpeechAPIFactory() {
		throw new IllegalStateException("Utility class");
	}

	public static TextToSpeechAPI createTTSAPI(String apiType) {
		return switch (apiType) {
			case "FreeTTSAdapter" -> new FreeTTSAdapter();
			case "FakeTextToSpeechAPI" -> new FakeTextToSpeechAPI();
			default -> null;
		};
	}

}
