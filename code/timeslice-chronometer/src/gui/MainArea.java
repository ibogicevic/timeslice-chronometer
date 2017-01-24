package gui;

import data.SliceTimer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MainArea extends GridPane {

	// demo timer
	private SliceTimer timer = new SliceTimer();
	public Label timerLabel = new Label();

	public MainArea() {
		
		// table styling
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setHgap(5);
		this.setVgap(5);
		
		// table header
		this.add(new Label("Key"), 1, 1);
		this.add(new Label("Time"), 2, 1);
		this.add(new Label("Counter"), 3, 1);

		// demo timer
		this.add(timerLabel, 2, 2);
		// example run
		timer.start();
	}
}
