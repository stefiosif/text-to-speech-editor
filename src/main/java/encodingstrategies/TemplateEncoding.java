package encodingstrategies;

public abstract class TemplateEncoding implements EncodingStrategy{

	public String encode(String encode) {
		StringBuilder encodedLine = new StringBuilder();
		for (int i=0; i < encode.length(); i++) {
		    char c = encode.charAt(i);    
			char retVal = mapCharacter(c);
			encodedLine.append(retVal);
		}
		return encodedLine.toString();
	}
	
	protected abstract char mapCharacter(char letter);
}
