package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

/**
 * InfoArea at the bottom of the main window
 * @author ivan
 */
public class InfoArea extends GridPane {

	/** Default rate of measurement */
	final double DEFAULT_RATE = 4;
	
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
			+ "Licensed under the Apache License, Version 2.0\n"
			+ "Developed as part of a research project at the University of Stuttgart,\n"
			+ "Institute of Software Technology, Software Engineering Group"
			+ "";

	// TODO: This should be moved to a separate ControlArea 
	/** Slider to control the rate of the measurements */
	Slider rateSlider = new Slider();


	/**
	 * Adds a slider control so that the user can rate the video and the time measurement
	 */
	private void addrateSlider() {
		// slider settings
		rateSlider.setMin(1);
		rateSlider.setMax(8);
		rateSlider.setValue(DEFAULT_RATE);
		rateSlider.setShowTickLabels(true);
		rateSlider.setMajorTickUnit(1);
		rateSlider.setBlockIncrement(1);
		// only allow whole numbers
		rateSlider.valueProperty().addListener((obs, oldval, newVal) ->
		rateSlider.setValue(Math.round(newVal.doubleValue())));
		// change video rate to slider value
		rateSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> value,
					Number oldValue, Number newValue) {
				Main.getInstance().videoArea.setRate(newValue.doubleValue());
			}
		});
		// add slider to pane
		this.add(new Label ("Rate"), 1, 1);
		this.add(rateSlider, 2, 1);
	}

	/**
	 * Returns the current rate of the measurement
	 */
	public double getRate() {
		return rateSlider.getValue();
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
		// add rate-slider
		// TODO: This should be done in a new area (ControlArea) and not in this InfoArea
		addrateSlider();
	}
}
