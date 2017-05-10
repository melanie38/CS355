package cs355.solution;

import cs355.GUIFunctions;
import cs355.controller.Controller;
import cs355.model.drawing.Drawing;
import cs355.view.View;

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

	    //Drawing model = new Drawing();

		Controller appController = new Controller();
		View appView = new View();

        //model.inst();


		// Fill in the parameters below with your controller and view.
		GUIFunctions.createCS355Frame(appController, appView);

		GUIFunctions.refresh();
	}
}
