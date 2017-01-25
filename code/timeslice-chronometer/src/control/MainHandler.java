package control;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainHandler {

	public static void handleKeyEvent(KeyEvent event) {
		if (event.getCode().equals(KeyCode.SPACE)) {
			
		}
		if (event.getCode().isLetterKey()) {
			SliceHandler.handleLetterKey(event.getCode().toString());
		}
	}

}
