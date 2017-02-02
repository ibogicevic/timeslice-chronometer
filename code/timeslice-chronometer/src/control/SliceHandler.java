package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.Slice;
import data.SliceMap;
import gui.TimesliceChronometer;
import javafx.stage.FileChooser;

/**
 * Stores and handles all dynamic parts associated to a slice
 * @author ivan
 */
public class SliceHandler {

	/** Currently running slice */
	private static Slice currentSlice = null;

	/** Previously running slice (before break) */
	private static Slice lastSlice = null;

	/**
	 * Get the current running slice
	 * @return current slide or null if no slice timer is running
	 */
	public static Slice getCurrentSlice() {
		return currentSlice;
	}

	/**
	 * Stops the timer of the current running slice or continues it after a break
	 */
	public static void toggleBreak() {
		Slice sumSlice = SliceMap.getInstance().get("sum");
		if (currentSlice != null) {
			// timer is running, stop it			
			currentSlice.sliceTimer.stop();
			lastSlice = currentSlice;
			currentSlice = null;
			// stop sum slice timer
			sumSlice.sliceTimer.stop();
			// stop video
			TimesliceChronometer.getInstance().videoArea.pause();
		} else {
			// no timer is running, continue with last one
			currentSlice = lastSlice;
			if (currentSlice != null) {
				currentSlice.sliceTimer.start();
			}
			// start sum slice timer
			sumSlice.sliceTimer.start();
			// continue video
			TimesliceChronometer.getInstance().videoArea.play();			
		}
	}

	/**
	 * Resets measurement by stopping and deleting all slices
	 */
	public static void resetSlices() {
		Slice sumSlice = SliceMap.getInstance().get("sum");
		// stop all timers
		if (currentSlice != null) {
			// timer is running, stop it			
			currentSlice.sliceTimer.stop();
			lastSlice = currentSlice;
			currentSlice = null;
			// stop also sum slice timer
			sumSlice.sliceTimer.stop();
		}
		// delete all timers
		SliceMap.reset();
		// update view
		TimesliceChronometer.getInstance().slicesArea.updateSliceListView();
	}

	/**
	 * Handle the event that the users presses a letter (start/continue/stop timer)
	 * @param key the character of the pressed key
	 */
	public static void handleLetterKey(String key) {
		// stop current slice timer
		if (currentSlice != null) {
			currentSlice.sliceTimer.stop();
		}
		currentSlice = null;
		// create new slice if it does not already exist
		if (!SliceMap.getInstance().containsKey(key)) {
			// add new slice
			SliceMap.getInstance().put(key, new Slice(key));
		}
		// reload slice (and remember it)
		currentSlice = SliceMap.getInstance().get(key);
		// start/continue associated slice timer
		currentSlice.sliceTimer.start();
		Slice sumSlice = SliceMap.getInstance().get("sum");
		sumSlice.sliceTimer.start();
		// increase associated slice counter(s)
		currentSlice.sliceCounter++;
		sumSlice.sliceCounter++;
		// continue video
		TimesliceChronometer.getInstance().videoArea.play();
		// update view
		TimesliceChronometer.getInstance().slicesArea.updateSliceListView();
	}

	/**
	 * Saves all slice data to a csv-file
	 */
	public static void exportData() {
		// generate path names and file name
		String homeDir = System.getProperty("user.home");
		File homeDirFile = new File(homeDir);
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		String fileName = "timeslices.csv";
		String fullFileName = timeStamp + "_" + fileName;
		String filePath = homeDir + File.separator + fullFileName;
		// save as dialog
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save as CSV-File");
		fileChooser.setInitialDirectory(homeDirFile);
		fileChooser.setInitialFileName(fullFileName);
		File file = fileChooser.showSaveDialog(TimesliceChronometer.getInstance().getPrimaryStage());
		// cancel if no file is chosen
		if (file == null) {
			return;
		}
		// file has been chosen
		try {
			// initialize writers
			FileWriter fileWriter = new FileWriter(filePath);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			// write data of all slices
			for (Slice slice : SliceMap.getInstance().values()) {
				// write slice data
				writer.write(slice.triggerKey);
				writer.write(";");
				writer.write(slice.sliceTimer.getRoundedTime());
				writer.write(";");
				writer.write(Long.toString(slice.sliceCounter));
				writer.write(";");
			}
			// close writer
			writer.close();
		} catch (IOException e) {
			// write error
			e.printStackTrace();
		}
	}

}
