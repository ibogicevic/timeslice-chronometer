package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * StatusArea at the bottom of the main window
 * @author ivan
 */
public class StatusArea extends GridPane {
	
	/** Info text that explains the key-press options */
	final private String INFOTEXT = ""
			+ "A..Z: switch to associated slice timer, increase counter\n"
			+ "SPACE: pause measurement\n"
			+ "BACKSPACE: restart measurement\n"
			+ "ENTER: save data as export.csv\n"
			+ "ESCAPE: exit application\n";
	
	/**
	 * Initialize StatusArea
	 */
	public StatusArea() {
		// pane styling
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setHgap(5);
		this.setVgap(5);
		// add info text
		this.add(new Label(INFOTEXT), 1, 1);
	}
}
