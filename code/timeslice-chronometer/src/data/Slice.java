package data;

import javafx.scene.control.Label;

public class Slice {

	// TODO: add comments for fields
	public String triggerKey;
	public SliceTimer sliceTimer = new SliceTimer();
	public long sliceCounter = 0;
	public Label sliceTimerLabel = new Label("./.");
	
	/** Private empty constructor (triggerKey must be set) */
	@SuppressWarnings("unused")
	private Slice() {
	}
	
	/**
	 * Constructor for a new slice
	 * @param triggerKey An id of the slide, typically the key pressed when activating the slice
	 */
	public Slice(String triggerKey) {
		// remember key
		this.triggerKey = triggerKey;
	}
		
}
