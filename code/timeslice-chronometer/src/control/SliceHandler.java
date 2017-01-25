package control;

import data.Slice;
import data.SliceMap;
import gui.Main;

/**
 * Stores and handles all dynamic parts associated to a slice
 * @author ivan
 */
public class SliceHandler {

	/** Currently running slice */
	private static Slice currentSlice = null;

	/**
	 * Get the current running slide
	 * @return current slide or null if no slice timer is running
	 */
	public static Slice getCurrentSlice() {
		return currentSlice;
	}
	
	/**
	 * Handle the event that the users presses a letter (start/continue/stop timer)
	 * @param key the character of the pressed key
	 */
	public static void handleLetterKey(String key) {
		// stop current slice timer
		if (currentSlice != null) {
			currentSlice.sliceTimer.stop();
		}
		currentSlice = null;
		// create new slice if it does not already exist
		if (!SliceMap.getInstance().containsKey(key)) {
			// add new slice
			SliceMap.getInstance().put(key, new Slice(key));
		}
		// reload slice (and remember it)
		currentSlice = SliceMap.getInstance().get(key);
		// start/continue associated slice timer
		currentSlice.sliceTimer.start();
		// increase associated slice counter(s)
		currentSlice.sliceCounter++;
		SliceMap.getInstance().get("sum").sliceCounter++;
		// update view
		Main.getInstance().mainArea.updateSliceListView();
	}

	
}
