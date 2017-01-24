package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MainArea extends GridPane {

	// demo timer
	private SliceTimer timer = new SliceTimer();
	public Label timerLabel = new Label();

	public MainArea() {
		this.add(timerLabel, 1, 1);
		// example run
		timer.start();
	}
}
