package gui;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

/**
 * Toolbar at the top of the main window
 * @author ivan
 */
public class ToolbarArea extends ToolBar {
	
	public ToolbarArea() {
		// initialize buttons
		Button exitButton = new Button("Exit");
		// actionlisteners
		exitButton.setOnAction(e -> {Platform.exit();});
		// add all buttons
		//this.getItems().add(exitButton);
	}
}