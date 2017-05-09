package cs355.solution;

import cs355.GUIFunctions;
import cs355.controller.Controller;

/**
 * This is the main class. The program starts here.
 * Make you add code below to initialize your model,
 * view, and controller and give them to the app.
 */
public class CS355 {

	/**
	 * This is where it starts.
	 * @param args = the command line arguments
	 */
	public static void main(String[] args) {

		Controller appController = new Controller();

		// Fill in the parameters below with your controller and view.
		GUIFunctions.createCS355Frame(appController, null);

		GUIFunctions.refresh();
	}
}
