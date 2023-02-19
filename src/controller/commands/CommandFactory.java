package controller.commands;

import java.awt.Point;

import controller.ShapeList;
import model.ShapeConfig;
import model.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;

public class CommandFactory {

	public static ICommand drawCommand(int x, int y, Point p1, Point p2, int l, int w, ShapeConfig shapeConfig,ShapeList shapeList) {
		return new CreateShape(x, y, p1, p2, l, w, shapeConfig,shapeList);
	}

	public static ICommand selectCommand(Point p1, Point p2,PaintCanvasBase paintCanvas) {
		return new SelectShape(p1, p2,paintCanvas);
	}

	public static ICommand moveCommand(Point p1,Point p2) {
		return new MoveShape(p1,p2);
	}
}
