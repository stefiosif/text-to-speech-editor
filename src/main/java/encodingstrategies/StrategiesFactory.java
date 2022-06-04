package encodingstrategies;

public class StrategiesFactory{
	
	public EncodingStrategy createStrategy(String strategyType) {
		if (strategyType.equals("Rot13Encoding")){
			return new Rot13Encoding();
		} else if (strategyType.equals("AtBashEncoding")) {
			return new AtBashEncoding();
		} else {
			return null;
		}
	}
}
