package data;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * List of all slices including footer slice (total numbers)
 * @author ivan
 */
@SuppressWarnings("serial")
public class SliceMap extends TreeMap<String, Slice> {

	/** Singleton-Pattern, triggerKey is mapped to Slice */
	private static SortedMap<String, Slice> map = null;
	
	/** Hidden default constructor */
	private SliceMap() {
	}
	
	/** Get singleton instance */
	public static synchronized SortedMap<String, Slice> getInstance() {
		if (map == null) {
			// map does not exist yet
			map = new TreeMap<String, Slice>();
			// add footer entry (total values)
			map.put("sum", new Slice("sum"));
		}
		return map;
	}
	
	/** Delete all slices */
	public static void reset() {
		// delete map
		map = null;
	}
	
}
