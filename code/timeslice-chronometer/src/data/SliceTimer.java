package data;

import gui.TimesliceChronometer;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

/**
 * Stopwatch of a Slice
 * @author ivan
 */
public class SliceTimer extends AnimationTimer {

	// last saved timer value in milliseconds
	private long lastTime = 0;
	// timer value in milliseconds
	private long time = 0;
	// cache timer label
	private Label timerLabelCache;

	/** Private default constructor because label must be filled in cache */
	@SuppressWarnings("unused")
	private SliceTimer() {
	}
	
	public SliceTimer(Label timerLabel) {
		// fill timer label cache
		timerLabelCache = timerLabel;
	}
	
	@Override
	/**
	 * Start/continue the stopwatch
	 */
	public void start() {
		lastTime = System.currentTimeMillis();
		// call parent start method
		super.start();
	}

	@Override
	/**
	 * Pause the stopwatch
	 */
	public void stop() {
		super.stop();
	}

	@Override
	/**
	 * Overridden method from super class AnimationTimer, called on every frame
	 * when timer is running
	 */
	public void handle(long now) {
		long currentTime = System.currentTimeMillis();
		long delta = (currentTime - lastTime) / 100;
		time += delta;
		lastTime += 100 * delta;
		if (TimesliceChronometer.getInstance() != null
				&& TimesliceChronometer.getInstance().centerArea != null) {
			// update timer label
			timerLabelCache.setText(Double.toString(time/10.));
		}
	}
};

