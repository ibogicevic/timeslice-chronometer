package data;

import java.util.SortedMap;
import java.util.TreeMap;

import javafx.scene.control.Label;

public class Slice {

	// list of all slices
	public static SortedMap<String, Slice> sliceList = new TreeMap<String, Slice>();
	
	// TODO: add comments for fields
	public char triggerKey;
	public SliceTimer sliceTimer = new SliceTimer();
	public long sliceCounter = 0;
	public Label sliceTimerLabel = new Label("-");
	public Label sliceCounterLabel = new Label("-");
	
	// private empty constructor (triggerKey must be set)
	@SuppressWarnings("unused")
	private Slice() {	
	}
	
	// constructor
	public Slice(char triggerKey) {
		this.triggerKey = triggerKey;
	}
	
	
}
