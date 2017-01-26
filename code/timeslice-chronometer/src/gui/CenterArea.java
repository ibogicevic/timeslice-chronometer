package gui;

import data.Slice;
import data.SliceMap;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CenterArea extends GridPane {
	
	public CenterArea() {
		// table styling
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setHgap(5);
		this.setVgap(5);
		// show initial view
		updateSliceListView();
	}
	
	public void updateSliceListView() {
		// clear current view
		this.getChildren().clear();
		// table header
		this.add(new Label("Key –"), 1, 1);
		this.add(new Label("Time –"), 2, 1);
		this.add(new Label("Counter"), 3, 1);
		// list of slices (including table footer)
		int rowIndex = 1;
		for (Slice slice : SliceMap.getInstance().values()) {
			rowIndex++;
			this.add(new Label("" + slice.triggerKey), 1, rowIndex);
			this.add(slice.sliceTimerLabel, 2, rowIndex);
			this.add(new Label(Long.toString(slice.sliceCounter)), 3, rowIndex);
		}
	}
	
}
