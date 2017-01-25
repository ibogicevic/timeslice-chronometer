package control;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainHandler {

	public static void handleKeyEvent(KeyEvent event) {
		
		// space key
		if (event.getCode().equals(KeyCode.SPACE)) {
			// pause
			SliceHandler.toggleBreak();
		}
		
		// escape key
		if (event.getCode().equals(KeyCode.ESCAPE)) {
			// exit application
			System.exit(0);
		}
		
		// letter key 
		if (event.getCode().isLetterKey()) {
			// forward to slice handler
			SliceHandler.handleLetterKey(event.getCode().toString());
		}
		
	}

}
