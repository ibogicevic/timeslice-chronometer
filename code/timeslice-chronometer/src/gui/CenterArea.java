package gui;

import data.Slice;
import data.SliceMap;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * GridPane for the center area of the screen containing the slice data
 * @author ivan
 */
public class CenterArea extends GridPane {
	
	/**
	 * Initialize Area
	 */
	public CenterArea() {
		// table styling
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setHgap(5);
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
	}
	
}
