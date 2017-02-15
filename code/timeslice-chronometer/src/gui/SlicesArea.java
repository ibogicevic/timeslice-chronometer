package gui;

import data.Slice;
import data.SliceMap;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * GridPane containing the slice data
 * @author ivan
 */
public class SlicesArea extends GridPane {
	
	private Label videoTimeLabel = new Label("-");

	/**
	 * Initialize Area
	 */
	public SlicesArea() {
		// table styling
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setHgap(10);
		this.setVgap(5);
		// show initial view
		updateSliceListView();
	}
	
	/**
	 * Reload and show new slice data
	 */
	public void updateSliceListView() {
		// clear current view
		this.getChildren().clear();
		// write table header
		this.add(new Label("Key –"), 1, 1);
		this.add(new Label("Time –"), 2, 1);
		this.add(new Label("Counter"), 3, 1);
		// list all slices (including table footer)
		int rowIndex = 1;
		for (Slice slice : SliceMap.getInstance().values()) {
			rowIndex++;
			// key
			this.add(new Label("" + slice.triggerKey), 1, rowIndex);
			// timer
			this.add(slice.sliceTimerLabel, 2, rowIndex);
			// counter
			this.add(new Label(Long.toString(slice.sliceCounter)), 3, rowIndex);
		}
		// show also current video time
		rowIndex++;
		this.add(new Label("vid"), 1, rowIndex);
		this.add(videoTimeLabel, 2, rowIndex);
		AnimationTimer videoTime = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (Main.getInstance() == null) {
					return;
				}
				Duration currentTime = Main.getInstance().videoArea.getCurrentTime();
				double roundedTime = Math.round(currentTime.toSeconds()*10.);
				String roundedTimeString = Double.toString(roundedTime/10.);
				videoTimeLabel.setText(roundedTimeString);
			}
		};
		videoTime.start();
	}
	
}
