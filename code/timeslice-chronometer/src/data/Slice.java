package data;

import javafx.scene.control.Label;

/**
 * A Slice is a stopwatch timer with its associated data. 
 * @author ivan
 */
public class Slice {

	/** Id of the slice and keycode the users presses to continue the timer */
	public String triggerKey;
	/** The number of times the timer has ben started/restarted*/
	public long sliceCounter = 0;
	/** The UI Label for the timer (continuously updated when timer is running) */
	public Label sliceTimerLabel = new Label("./.");
	/** The stopwatch timer itself */
	public SliceTimer sliceTimer = new SliceTimer(sliceTimerLabel);
	
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
