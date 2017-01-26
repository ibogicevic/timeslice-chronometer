package control;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Handles all events triggered by the user
 * @author ivan
 */
public class MainHandler {

	/**
	 * Handle a key press event
	 * @param event
	 */
	public static void handleKeyEvent(KeyEvent event) {
		
		// letter key 
		if (event.getCode().isLetterKey()) {
			// forward to slice handler
			SliceHandler.handleLetterKey(event.getCode().toString());
		}
		
		// space key
		if (event.getCode().equals(KeyCode.SPACE)) {
			// pause
			SliceHandler.toggleBreak();
		}

		// backspace key
		if (event.getCode().equals(KeyCode.BACK_SPACE)) {
			// reset measurement
			SliceHandler.resetSlices();
		}
		
		// enter key
		if (event.getCode().equals(KeyCode.ENTER)) {
			// save data
			SliceHandler.exportData();
		}

		// escape key
		if (event.getCode().equals(KeyCode.ESCAPE)) {
			// exit application
			System.exit(0);
		}
				
	}

}
