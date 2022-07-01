package commands;

import java.awt.event.ActionListener;

public class CommandsFactory {

	private CommandsFactory() {
		throw new IllegalStateException("Utility class");
	}
	
	public static ActionListener createCommand(String commandType) {
		return switch (commandType) {
			case "NewDocument" -> new NewDocument();
			case "OpenDocument" -> new OpenDocument();
			case "SaveDocument" -> new SaveDocument();
			case "EditDocument" -> new EditDocument();
			case "DocumentToSpeech" -> new DocumentToSpeech();
			case "LineToSpeech" -> new LineToSpeech();
			case "TuneEncoding" -> new TuneEncoding();
			case "TuneAudio" -> new TuneAudio();
			case "PlayEncodedLine" -> new PlayEncodedLine();
			case "PlayEncodedContents" -> new PlayEncodedContents();
			default -> null;
		};
	}
}
