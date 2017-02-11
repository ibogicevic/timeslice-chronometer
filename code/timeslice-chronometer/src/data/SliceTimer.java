package data;

import gui.Main;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

/**
 * Stopwatch of a Slice
 * @author ivan
 */
public class SliceTimer extends AnimationTimer {

	// time the timer has been started in milliseconds
	private long startTime = 0;
	// timer value in milliseconds
	private long time = 0;
	// cache timer label
	private Label timerLabelCache;
	// flag that specifies whether the slice timer is running
	private boolean running = false;

	/**
	 * Gets a timestamp as timing reference (if video is loaded from video, otherwise from system time)
	 * @return
	 */
	private long getCurrentTimeInMillis() {
		long currentTime = 0;
		if (Main.getInstance().videoArea.isVideoLoaded()) {
			// video is loaded, take video time
			currentTime = (long)(Main.getInstance().videoArea.getCurrentTime().toMillis());
		} else {
			// no video loaded, take system time
			currentTime = System.currentTimeMillis();
		}
		return currentTime;
	}

	/**
	 * Adds the duration of the current time frame to the time of this timer
	 * and updates the timer values. 
	 */
	private void updateTimer() {
		// calculate time difference
		long currentTime = getCurrentTimeInMillis();
		long delta = (currentTime - startTime);
		// speedupfactor must only be considered if no video is loaded
		long speedupFactor = 1;
		if (!Main.getInstance().videoArea.isVideoLoaded()) {
			speedupFactor = Math.round(Main.getInstance().infoArea.getSpeedupFactor());
		}
		// add time difference of interval
		time += speedupFactor * delta;
		startTime += delta;
		// update timer label (round)
		if (Main.getInstance() != null && Main.getInstance().slicesArea != null) {
			timerLabelCache.setText(getRoundedTime());
		}
	}

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
		// call parent start method
		if (!running) {
			// remember start time
			startTime = getCurrentTimeInMillis();
			super.start();
		}
		running = true;
	}

	@Override
	/**
	 * Pause the stopwatch
	 */
	public void stop() {
		if (running) {
			updateTimer();
			super.stop();
		}
		running = false;
	}

	@Override
	/**
	 * Overridden method from super class AnimationTimer, called on every frame
	 * when timer is running
	 */
	public void handle(long now) {
		updateTimer();
	}

	/**
	 * Rounds the time to 0.1s and converts it to a string
	 * @return the number of seconds the timer was running
	 */
	public String getRoundedTime() {
		long roundedTime = 100 * Math.round(time/100.);
		String roundedTimeString = Double.toString(roundedTime/1000.); 
		return roundedTimeString;
	}

	public boolean isRunning() {
		return running;
	}
};

