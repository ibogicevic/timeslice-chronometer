package gui;

import control.SliceHandler;
import data.Slice;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MainArea extends GridPane {
	
	public MainArea() {
		
		// table styling
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setHgap(5);
		this.setVgap(5);
		
		// demo slices
		Slice.sliceList.put("c", new Slice('c'));
		Slice.sliceList.put("w", new Slice('w'));
		Slice.sliceList.put("d", new Slice('d'));
		updateSliceList();
		SliceHandler.setCurrentSlice("d");
		SliceHandler.getCurrentSlice().sliceTimer.start();
	}
	
	public void updateSliceList() {
		// table header
		this.add(new Label("Key"), 1, 1);
		this.add(new Label("Time"), 2, 1);
		this.add(new Label("Counter"), 3, 1);
		// list of slices
		int rowIndex = 1;
		for (Slice slice : Slice.sliceList.values()) {
			rowIndex++;
			this.add(new Label(""+slice.triggerKey), 1, rowIndex);
			this.add(slice.sliceTimerLabel, 2, rowIndex);
			this.add(slice.sliceCounterLabel, 3, rowIndex);
		}
		// table footer
		// TODO: total numbers
	}
	
}
