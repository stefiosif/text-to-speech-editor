package encodingstrategies;

public class StrategiesFactory{

	private StrategiesFactory() {
		throw new IllegalStateException("Utility class");
	}

	public static EncodingStrategy createStrategy(String strategyType) {
		return switch(strategyType){
			case "Rot13Encoding" -> new Rot13Encoding();
			case "AtBashEncoding" -> new AtBashEncoding();
			default -> null;
		};
	}
}
