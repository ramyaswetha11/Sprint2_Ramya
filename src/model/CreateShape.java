package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

import controller.CommandHistory;
import controller.ICommand;
import controller.IUndoable;
import controller.ShapeList;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class CreateShape implements ICommand, IUndoable {

	CreateShape shape;
	Point p1;
	Point p2;
	ShapeType shapeType;
	Color primaryColor;
	Color secondaryColor;
	ApplicationState appState;
	ShapeList shapeList;
	PaintCanvasBase paintCanvas;

	public CreateShape(Point p1, Point p2, PaintCanvasBase paintCanvas, ApplicationState appState, ShapeList shapeList) {
		this.p1 = p1;
		this.p2 = p2;
		this.appState = appState;
		this.paintCanvas = paintCanvas;
		this.shapeList = shapeList;
		this.shapeType = appState.getActiveShapeType();
		this.primaryColor = stringToColor(appState.getActivePrimaryColor().toString());
		this.secondaryColor = stringToColor(appState.getActiveSecondaryColor().toString());
	}

	public static Color stringToColor(final String value) {
		if (value == null) {
			return Color.black;
		}
		try {
			return Color.decode(value);
		} catch (NumberFormatException nfe) {
			try {
				final java.lang.reflect.Field f = Color.class.getField(value);

				return (Color) f.get(null);
			} catch (Exception ce) {
				return Color.black;
			}
		}
	}

	@Override
	public void run() {
		shape = new CreateShape(p1, p2, paintCanvas, appState, shapeList);
		shapeList.addShape(shape);
		CommandHistory.add(this);
		System.out.println(shapeList.getList());
	}

	@Override
	public void undo() {
		System.out.println(shapeList.getList());
		shapeList.removeShape(shape);
		Graphics2D graphics2d = paintCanvas.getGraphics2D();
		graphics2d.setColor(Color.WHITE);
		graphics2d.fillRect(0, 0, 2560, 1440);
		System.out.println(shapeList.getList());
		for (CreateShape shape : shapeList.getList()) {

			graphics2d.setStroke(new BasicStroke(5));
			graphics2d.setColor(shape.primaryColor);
			
			if (shape.shapeType == ShapeType.RECTANGLE) {
				int l = Math.abs(shape.p1.x - shape.p2.x);
				int w = Math.abs(shape.p1.y - shape.p2.y);
				graphics2d.fillRect(Math.min(shape.p1.x, shape.p2.x), Math.min(shape.p1.y, shape.p2.y), l, w);
			}
		}
	}

	@Override
	public void redo() {
		System.out.println(shapeList.getList());
		shapeList.addShape(shape);
		Graphics2D graphics2d = paintCanvas.getGraphics2D();
		graphics2d.setColor(Color.WHITE);
		graphics2d.fillRect(0, 0, 2560, 1440);
		System.out.println(shapeList.getList());
		for (CreateShape shape : shapeList.getList()) {
			graphics2d.setStroke(new BasicStroke(5));
			graphics2d.setColor(shape.primaryColor);
			
			if (shape.shapeType == ShapeType.RECTANGLE) {
				int l = Math.abs(shape.p1.x - shape.p2.x);
				int w = Math.abs(shape.p1.y - shape.p2.y);
				graphics2d.fillRect(Math.min(shape.p1.x, shape.p2.x), Math.min(shape.p1.y, shape.p2.y), l, w);
			}
		}
	}

}
