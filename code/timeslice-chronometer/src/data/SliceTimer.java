package data;

import control.SliceHandler;
import gui.TimesliceChronometer;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

public class SliceTimer extends AnimationTimer {

	private long lastTime = 0;
	private long time = 0;

	@Override
	public void start() {
		lastTime = System.currentTimeMillis();
		super.start();
	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public void handle(long now) {
		long currentTime = System.currentTimeMillis();
		long delta = (currentTime - lastTime) / 100;
		time += delta;
		lastTime += 100 * delta;
		if (TimesliceChronometer.getInstance() != null && TimesliceChronometer.getInstance().mainArea != null) {
			Label currentSliceLabel = SliceHandler.getCurrentSlice().sliceTimerLabel;
			currentSliceLabel.setText(Double.toString(time/10.));
		}
	}
};

