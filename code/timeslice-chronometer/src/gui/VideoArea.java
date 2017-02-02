package gui;

import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class VideoArea extends BorderPane {

	// TODO: Comments needed
	private MediaPlayer mediaPlayer = null;
	private MediaView mediaView = null;

	// TODO: Comments needed
	public void play() {
		if (mediaPlayer != null) {
			mediaPlayer.play();
		}
	}
	
	public void pause() {
		if (mediaPlayer != null) {
			mediaPlayer.pause();
		}
	}

	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
	}

	private void loadVideo(File videoFile) {
		// check if no file has been chosen
		if (videoFile == null) {
			return;
		}
		// init video
		String url = "file://" + videoFile.getAbsolutePath();
		Media media = new Media(url);
		mediaPlayer = new MediaPlayer(media);
		mediaView = new MediaView(mediaPlayer);
		// remove open-button
		this.getChildren().clear();
		// arrange and add view
		this.setScaleX(0.5);
		this.setScaleY(0.5);
		this.setCenter(mediaView);
		// position video when loaded
		mediaPlayer.setOnReady(new Runnable() {    
            @Override
            public void run() {
            	mediaView.setTranslateX(-media.getWidth()/4);  
            	mediaView.setTranslateY(-media.getHeight()/4);  
            }
        });
	}
	
	/**
	 * Initialize Area
	 */
	public VideoArea() {
		// add open-button
		Button openButton = new Button("Open Video");
		openButton.setOnAction((event) -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			Stage parentWindow = TimesliceChronometer.getInstance().getPrimaryStage();
			File videoFile = fileChooser.showOpenDialog(parentWindow);
			loadVideo(videoFile);
		});
		this.setTop(openButton);
	}
}
