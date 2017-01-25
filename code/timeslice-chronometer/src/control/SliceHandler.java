package control;

import data.Slice;
import data.SliceMap;

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

	
}
