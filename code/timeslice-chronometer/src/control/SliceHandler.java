package control;

import data.Slice;
import data.SliceMap;
import gui.TimesliceChronometer;

/**
 * Stores and handles all dynamic parts associated to a slice
 * @author ivan
 */
public class SliceHandler {

	/** Currently running slice */
	private static Slice currentSlice = null;

	/** Previously running slice (before break) */
	private static Slice lastSlice = null;

	/**
	 * Get the current running slice
	 * @return current slide or null if no slice timer is running
	 */
	public static Slice getCurrentSlice() {
		return currentSlice;
	}

	/**
	 * Stops the timer of the current running slice or continues it after a break
	 */
	public static void toggleBreak() {
		Slice sumSlice = SliceMap.getInstance().get("sum");
		if (currentSlice != null) {
			// timer is running, stop it			
			currentSlice.sliceTimer.stop();
			lastSlice = currentSlice;
			currentSlice = null;
			// stop also sum slice timer
			sumSlice.sliceTimer.stop();
		} else {
			// no timer is running, continue with last one
			currentSlice = lastSlice;
			if (currentSlice != null) {
				currentSlice.sliceTimer.start();
			}
			// start also sum slice timer
			sumSlice.sliceTimer.stop();
		}
	}

	/**
	 * Resets measurement by stopping and deleting all slices
	 */
	public static void resetSlices() {
		Slice sumSlice = SliceMap.getInstance().get("sum");
		// stop all timers
		if (currentSlice != null) {
			// timer is running, stop it			
			currentSlice.sliceTimer.stop();
			lastSlice = currentSlice;
			currentSlice = null;
			// stop also sum slice timer
			sumSlice.sliceTimer.stop();
		}
		// delete all timers
		SliceMap.reset();
		// update view
		TimesliceChronometer.getInstance().mainArea.updateSliceListView();
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
		Slice sumSlice = SliceMap.getInstance().get("sum");
		sumSlice.sliceTimer.start();
		// increase associated slice counter(s)
		currentSlice.sliceCounter++;
		sumSlice.sliceCounter++;
		// update view
		TimesliceChronometer.getInstance().mainArea.updateSliceListView();
	}
	
	

	/**
	 * Saves all slice data to a csv-file
	 */
	public static void exportData() {
		// TODO: NIY
	}

}
