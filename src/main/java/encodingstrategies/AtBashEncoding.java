package encodingstrategies;

public class AtBashEncoding extends TemplateEncoding{

	@Override
	protected char mapCharacter(char letter) {
		int asciiValue = letter;
		if (asciiValue >= 65 && asciiValue <= 90) {
			asciiValue = 65 - asciiValue + 90;
		} else if(asciiValue >= 97 && asciiValue <= 122) {
			asciiValue = 97 - asciiValue + 122;
		}
		return (char)asciiValue;
	}
}
