package encodingstrategies;

public class Rot13Encoding extends TemplateEncoding{

	@Override
	protected char mapCharacter(char letter) {
		int asciiValue = letter;
		if (asciiValue >= 65 && asciiValue <= 90) {
			asciiValue = 65 + (((asciiValue - 65) + 13 + 26) % 26);
		} else if(asciiValue >= 97 && asciiValue <= 122) {
			asciiValue = 97 + (((asciiValue - 97) + 13 + 26) % 26);
		}
		return (char)asciiValue;
	}
}
