package controller;

import java.awt.Point;

import controller.commands.CommandFactory;
import model.ShapeConfig;
import model.interfaces.ICommand;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

public class ShapeMode {

	private static ApplicationState appState;
	private static ICommand shapeCommand;

	public static void run(int x, int y, Point p1, Point p2, int l, int w, ShapeConfig shapeConfig,PaintCanvasBase paintCanvas,ShapeList shapeList) {

		switch (appState.getActiveMouseMode()) {

		case DRAW:
			shapeCommand = CommandFactory.drawCommand(x, y, p1, p2, l, w, shapeConfig,shapeList);
			break;
		case SELECT:
			shapeCommand = CommandFactory.selectCommand(p1, p2,paintCanvas);
			break;
		case MOVE:
			shapeCommand = CommandFactory.moveCommand(p1,p2);
			break;

		default:
			shapeCommand = CommandFactory.selectCommand(p1, p2,paintCanvas);
		}
		
		shapeCommand.run();
	}

	public static void getAppState(ApplicationState AppState) {
		appState = AppState;
	}
}

