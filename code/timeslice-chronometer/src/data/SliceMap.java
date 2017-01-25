package data;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * List of all slices including footer slice (total numbers)
 * @author ivan
 */
@SuppressWarnings("serial")
public class SliceMap extends TreeMap<String, Slice> {

	/** Singleton-Pattern */
	private static SortedMap<String, Slice> map = null;
	
	/** Hidden default constructor */
	private SliceMap() {
	}
	
	/** Get singleton instance */
	public static SortedMap<String, Slice> getInstance() {
		if (map == null) {
			map = new TreeMap<String, Slice>();
		}
		return map;
	}
	
	
	
}
