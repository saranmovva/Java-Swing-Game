package edu.ben.groupproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * GameLog Class
 * 
 * @author Jeff
 *
 */
public class GameLog {
	private static boolean isNew = true;

	/**
	 * Writes the game logs to the Game Log text file
	 * 
	 * @param text
	 */
	public static void fileWrite(String text) {
		try {
			ArrayList<String> logs = new ArrayList<>();
			File file = new File("Game Log.txt");
			// If the file is for a new game, delete the old contents.
			if (isNew) {
				file.delete();
				isNew = false;
			} else if (file.exists()) {
				// Get the file contents
				FileReader fr = new FileReader("Game Log.txt");
				BufferedReader br = new BufferedReader(fr);
				String str;
				while ((str = br.readLine()) != null) {
					logs.add(str);
				}
				fr.close();
			}
			// Add the new contents
			logs.add(text);
			PrintWriter gameLog = new PrintWriter(file);
			for (String line : logs) {
				gameLog.println(line);
			}
			gameLog.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
