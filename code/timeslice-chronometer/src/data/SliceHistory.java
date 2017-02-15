package data;

import java.util.ArrayList;
import java.util.List;

/**
 * A sequence of slices with timetamps
 * @author ivan
 */
@SuppressWarnings("serial")
public class SliceHistory extends ArrayList<SliceHistoryItem> {
	
	/** Internal private instance */
	private static ArrayList<SliceHistoryItem> history = new ArrayList<SliceHistoryItem>();
	
	/** Hidden default constructor */
	private SliceHistory() {
	}
	
	/** Get singleton instance */
	public static synchronized ArrayList<SliceHistoryItem> getInstance() {
		if (history == null) {
			// history does not exist yet
			history = new SliceHistory();
		}
		return history;
	}
	
	/** Add an item to the history
	 * TODO: param comments
	 */
	public static void addItem(String triggerKey, String timeStamp) {
		// transform from slice to sliceHistoryItem
		SliceHistoryItem shi = new SliceHistoryItem();
		shi.triggerKey = triggerKey;
		shi.timeStamp = timeStamp;
		// remember item
		history.add(shi);
	}
	
	/** Get the history as csv-text */
	public static String getCsv() {
		String csv = "";
		// generate csv line by line
		for (SliceHistoryItem item: history) {
			csv += item.triggerKey + ";" + item.timeStamp + ";\n";
		}
		return csv;
	}

}