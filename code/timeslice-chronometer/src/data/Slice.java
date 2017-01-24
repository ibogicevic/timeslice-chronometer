package data;

public class Slice {

	// currently running slice
	static Slice currentSlice = null;
	
	// TODO: add comments for fields
	public char triggerKey;
	public SliceTimer sliceTimer;
	public long sliceCounter = 0;
	
	// private empty constructor (triggerKey must be set)
	@SuppressWarnings("unused")
	private Slice() {	
	}
	
	// constructor
	public Slice(char triggerKey) {
		this.triggerKey = triggerKey;
	}
	
}
