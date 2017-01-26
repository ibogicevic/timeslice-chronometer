package control;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Handles all events triggered by the user
 * @author ivan
 */
public class MainHandler {

	private static Set<String> unreleasedKeys = new HashSet<String>();
	
	/**
	 * Handle a key press event
	 * @param event
	 */
	public static void handleKeyPressed(KeyEvent event) {
		
		// letter key 
		if (event.getCode().isLetterKey()) {
			// forward to slice handler if just pressed
			if (!unreleasedKeys.contains(event.getCode().toString())) {
				SliceHandler.handleLetterKey(event.getCode().toString());
			}
			// remember that key has been pressed
			unreleasedKeys.add(event.getCode().toString());
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
	
	/**
	 * Handle a key release event
	 * @param event
	 */
	public static void handleKeyReleased(KeyEvent event) {
		// remember that key has been released
		if (event.getCode().isLetterKey()) {
			unreleasedKeys.remove(event.getCode().toString());
		}
	}

}
