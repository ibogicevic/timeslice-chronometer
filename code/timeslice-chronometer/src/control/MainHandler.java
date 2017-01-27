package control;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.input.InputEvent;
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
			// ask user to confirm reset
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirm Reset");
			alert.setHeaderText("Do you really want to reset all timers?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
			    // User says OK, so reset measurement
				SliceHandler.resetSlices();
			} else {
			    // Cancel, do nothing
			}
		}
		
		// enter key
		if (event.getCode().equals(KeyCode.ENTER)) {
			// save data
			SliceHandler.exportData();
		}

		// escape key
		if (event.getCode().equals(KeyCode.ESCAPE)) {
			// ask user to confirm exit
//			Alert alert = new Alert(AlertType.CONFIRMATION);
//			alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
//			    public void handle(DialogEvent event) {
//			    	System.out.println(event.getEventType().toString());
//			        event.consume();
//			    }});
//			alert.setTitle("Confirm Exit");
//			alert.setHeaderText("Do you really want to exit the application?");
//			Optional<ButtonType> result = alert.showAndWait();
//			if (result.get() == ButtonType.OK){
//				// User says OK, so exit application
//				System.exit(0);
//			} else {
//			    // Cancel, do nothing
//			}
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
