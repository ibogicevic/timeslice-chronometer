package gui;

import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Logger;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VideoArea extends BorderPane {

	/** Video resource */
	private Media media = null;
	/** Player for the video resource */
	private MediaPlayer mediaPlayer = null;
	/** View for the mediePlayer */
	private MediaView mediaView = null;

	private double originalWindowWidth = 0;

	/**
	 * Start playing loaded video (redirects command to mediaplayer)
	 */
	public void play() {
		if (mediaPlayer != null) {
			mediaPlayer.play();
		}
	}

	/**
	 * Break playing loaded video (redirects command to mediaplayer)
	 */
	public void pause() {
		if (mediaPlayer != null) {
			mediaPlayer.pause();
		}
	}

	/**
	 * Stop playing loaded video (redirects command to mediaplayer)
	 */
	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
	}

	/**
	 * Set the speedup factor of the video (redirects command to mediaplayer)
	 */
	public void setRate(double rate) {
		if (mediaPlayer != null) {
			mediaPlayer.setRate(rate);
		}
	}

	/**
	 * Get the current time of the video (redirects command to mediaplayer)
	 * @return the time as duration or duration 0 if no video loaded
	 */
	public Duration getCurrentTime() {
		if (mediaPlayer == null) {
			return new Duration(0);	
		}
		return mediaPlayer.getCurrentTime();
	}

	/**
	 * Checks whether a video is loaded or not
	 * @return true if a video has been loaded
	 */
	public boolean isVideoLoaded() {
		return (media != null);
	}

	/**
	 * Resize the mediaview so that it fits to the grid
	 * FIXME: calculation is wrong, video is not always is the top right corner
	 */
	public void updateSize() {
		// check if media exists
		if (media == null) {
			return;
		}
		// calc new size
		double windowWidth = Main.getInstance().getPrimaryStage().getWidth();
		double windowHeight = Main.getInstance().getPrimaryStage().getHeight();
		double scaleX =  (0.75*windowWidth) / media.getWidth();
		double scaleY = (0.75*windowHeight) / media.getHeight();
		double scale = Math.min(scaleX, scaleY);
		double translationX = -(0.25+0.75/2.)*(originalWindowWidth-20) + (0.25+0.75/2.)*windowWidth;
		// apply new size
		mediaView.setScaleX(scale);
		mediaView.setScaleY(scale);		
		mediaView.setTranslateX(translationX);
		mediaView.setTranslateY(+20);
	}

	/**
	 * Load, init and position video in mediaPlayer
	 * @param videoFile video to load
	 */
	private void loadVideo(File videoFile) {
		// check if no file has been chosen
		if (videoFile == null) {
			return;
		}
		// init video
		String url = null;
		try {
			url = videoFile.toURI().toURL().toString();
		} catch (MalformedURLException e) {
			Logger.getGlobal().warning(e.getLocalizedMessage());
		}
		media = new Media(url);
		mediaPlayer = new MediaPlayer(media);
		mediaView = new MediaView(mediaPlayer);
		// set rate from slider
		this.setRate(Main.getInstance().infoArea.getSpeedupFactor());
		// remove open-button
		this.getChildren().clear();
		// arrange and add view
		this.setTop(mediaView);
		originalWindowWidth = Main.getInstance().getPrimaryStage().getWidth();
		// position video when loaded
		mediaPlayer.setOnReady(new Runnable() {    
			@Override
			public void run() {
				updateSize();  
			}
		});
	}

	/**
	 * Initialize Area
	 */
	public VideoArea() {
		// table styling
		this.setPadding(new Insets(10, 10, 10, 10));
		// add open-button
		Button openButton = new Button("Open Video");
		openButton.setFocusTraversable(false);
		openButton.setOnAction((event) -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Video");
			Stage parentWindow = Main.getInstance().getPrimaryStage();
			File videoFile = fileChooser.showOpenDialog(parentWindow);
			loadVideo(videoFile);
		});
		this.setTop(openButton);
	}
}
