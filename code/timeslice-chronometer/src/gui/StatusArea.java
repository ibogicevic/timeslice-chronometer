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
			+ "A..Z: switch to slice, increase counter\n"
			+ "SPACE: pause measurement\n"
			+ "BACKSPACE: restart measurement\n"
			//+ "ENTER: save data as export.csv\n"
			+ "ESCAPE: exit application\n";
	
	/** About text that explains the tool background */
	final private String ABOUTTEXT = "(c) 2017 Dr. Ivan Bogicevic\n"
			+ "Version: 0.1\n"
			+ "Licensed under the Apache License, Version 2.0\n"
			+ "Developed as part of a research project at the University of Stuttgart,\n"
			+ "Institute of Software Technology, Software Engineering Group";	
	
	/**
	 * Initialize StatusArea
	 */
	public StatusArea() {
		// pane styling
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setHgap(10);
		this.setVgap(10);
		// add info text
		this.add(new Label(INFOTEXT), 1, 1);
		this.add(new Label(ABOUTTEXT), 2, 1);
	}
}
