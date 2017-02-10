package gui;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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

	// TODO: This should be moved to a separate ControlArea 
	/** Slider to control the speed of the measurements */
	Slider speedupSlider = new Slider();

	
	/**
	 * Adds a slider control so that the user can speedup the video and the time measurement
	 */
	private void addSpeedupSlider() {
		// slider settings
		speedupSlider.setMin(1);
		speedupSlider.setMax(8);
		speedupSlider.setValue(4);
		speedupSlider.setShowTickLabels(true);
		speedupSlider.setMajorTickUnit(1);
		speedupSlider.setBlockIncrement(1);
		// only allow whole numbers
		speedupSlider.valueProperty().addListener((obs, oldval, newVal) ->
	    speedupSlider.setValue(Math.round(newVal.doubleValue())));
		// add slider to pane
		this.add(new Label ("Speedup Factor"), 1, 1);
		this.add(speedupSlider, 2, 1);
	}

	/**
	 * Returns the current speedup factor of the measurement
	 */
	public DoubleProperty getSpeedUpFactor() {
		return speedupSlider.valueProperty();
	}
	
	/**
	 * Initialize InfoArea
	 */
	public InfoArea() {
		// pane styling
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setHgap(10);
		this.setVgap(10);
		// add info text
		this.add(new Label(INFOTEXT), 1, 2, 2, 1);
		this.add(new Label(ABOUTTEXT), 1, 3, 2, 1);
		// add speedup-slider
		// TODO: This should be done in a new area (ControlArea) and not in this InfoArea
		addSpeedupSlider();
	}
}
