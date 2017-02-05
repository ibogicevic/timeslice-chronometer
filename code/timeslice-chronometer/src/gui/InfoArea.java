package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * InfoArea at the bottom of the main window
 * @author ivan
 */
public class InfoArea extends GridPane {

	/** Info text that explains the key-press options */
	final private String INFOTEXT = ""
			+ "A..Z: switch to slice, increase counter\n"
			+ "SPACE: pause measurement\n"
			+ "BACKSPACE: restart measurement\n"
			+ "ENTER: save data as csv-file\n"
			+ "";
	
	/** About text that explains the tool background */
	final private String ABOUTTEXT = "(c) 2017 Dr. Ivan Bogicevic\n"
			+ "Contributors: Kai Mindermann M.Sc.\n"
			+ "Version: 0.2\n"
			+ "Licensed under the Apache License, Version 2.0\n"
			+ "Developed as part of a research project at the University of Stuttgart,\n"
			+ "Institute of Software Technology, Software Engineering Group"
			+ "";
	
	/**
	 * Initialize InfoArea
	 */
	public InfoArea() {
		// pane styling
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setHgap(10);
		this.setVgap(10);
		// add info text
		this.add(new Label(INFOTEXT), 1, 1);
		this.add(new Label(ABOUTTEXT), 1, 2);
	}
}
