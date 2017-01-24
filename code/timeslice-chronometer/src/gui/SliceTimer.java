package gui;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

class SliceTimer extends AnimationTimer {

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
		long delta = (currentTime - lastTime) / 1000;
		time += delta;
		lastTime += 100 * delta;
		if (Main.getInstance() != null && Main.getInstance().mainArea != null) {
			Label timerLabel = Main.getInstance().mainArea.timerLabel;
			timerLabel.setText(Double.toString(time/10.));
		}
	}
};

