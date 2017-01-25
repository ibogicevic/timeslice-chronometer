package control;

import data.Slice;
import data.SliceMap;
import gui.Main;

// TODO: class docu
public class SliceHandler {

	// currently running slice
	private static Slice currentSlice = null;

	// TODO: method docu
	public static void setCurrentSlice(String triggerKey) {
		currentSlice = SliceMap.getInstance().get(triggerKey);
	}

	// TODO: method docu
	public static Slice getCurrentSlice() {
		return currentSlice;
	}
	
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
		// reload slice
		currentSlice = SliceMap.getInstance().get(key);
		// start/continue associated slice timer
		currentSlice.sliceTimer.start();
		// increase associated slice counter
		currentSlice.sliceCounter++;
		// update view
		Main.getInstance().mainArea.updateSliceListView();
	}

	
}
