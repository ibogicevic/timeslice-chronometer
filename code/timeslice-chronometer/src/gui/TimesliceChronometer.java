package gui;

import control.MainHandler;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Startup JavaFX frame
 * @author ivan
 */
public class TimesliceChronometer extends Application {

	/** Name of the application **/
	public final static String APPNAME = "Timeslice-Chronometer";
	
	// singleton
	private static TimesliceChronometer instance;
	public static TimesliceChronometer getInstance() {
		return instance;
	}
	
    // gui areas
	public CenterArea centerArea = new CenterArea();
	public StatusArea statusArea = new StatusArea();

	// remember stage for subwindows
	private Stage primaryStage;
	public Stage getPrimaryStage() {
		return this.primaryStage;
	}
	
	@Override
	public void start(Stage primaryStage) {
		// remember singleton instance (instantiated by javafx)
		TimesliceChronometer.instance = this;
		
		// remember stage for subwindows
		this.primaryStage = primaryStage;
				
		// add all areas
		BorderPane mainPane = new BorderPane();
		mainPane.setCenter(centerArea);
		mainPane.setBottom(statusArea);

		// show main pane
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		Scene scene = new Scene(mainPane, screenBounds.getWidth(), screenBounds.getHeight(), true);
		primaryStage.setTitle(APPNAME);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// redirect keypress events
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			MainHandler.handleKeyEvent(event);
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
