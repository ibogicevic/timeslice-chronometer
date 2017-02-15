package data;

/**
 * A single step in a slice history, i.e. the slice letter with its timestamp.
 * @author ivan
 */
public class SliceHistoryItem {
	/** Letter (key) of the slice */
	public String triggerKey;
	/** Number of seconds from begin of measurements to slice start */
	public String timeStamp;
}
