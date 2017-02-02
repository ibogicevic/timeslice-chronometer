package gui;

import control.MainHandler;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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
	public SlicesArea slicesArea = new SlicesArea();
	public InfoArea infoArea = new InfoArea();
	public VideoArea videoArea = new VideoArea();

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
		GridPane mainPane = new GridPane();
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
		RowConstraints row1 = new RowConstraints();
		RowConstraints row2 = new RowConstraints();
		col1.setPercentWidth(25);
		col2.setPercentWidth(75);
		row1.setPercentHeight(75);
		row2.setPercentHeight(25);
		mainPane.getColumnConstraints().addAll(col1,col2);
		mainPane.getRowConstraints().addAll(row1, row2);
		mainPane.setGridLinesVisible(true);
		mainPane.add(slicesArea, 0, 0);
		mainPane.add(videoArea, 1, 0);
		mainPane.add(infoArea, 0, 1, 2, 1);

		// show main pane
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		Scene scene = new Scene(mainPane, screenBounds.getWidth(), screenBounds.getHeight(), true);
		primaryStage.setTitle(APPNAME);
		primaryStage.setScene(scene);
		primaryStage.show();

		// redirect keypress events
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			MainHandler.handleKeyPressed(event);
		});
		scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
			MainHandler.handleKeyReleased(event);
		});

		// redirect close event
		this.primaryStage.setOnCloseRequest(event -> {
			MainHandler.handleCloseRequest(event);
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
