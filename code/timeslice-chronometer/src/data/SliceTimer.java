package data;

import control.SliceHandler;
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

	@Override
	/**
	 * Start/continue the stopwatch
	 */
	public void start() {
		lastTime = System.currentTimeMillis();
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
			Label currentSliceLabel = SliceHandler.getCurrentSlice().sliceTimerLabel;
			currentSliceLabel.setText(Double.toString(time/10.));
		}
	}
};

