package controller.commands;

import controller.*;
import controller.DrawShape;
import model.interfaces.ICommand;
import model.interfaces.ISelectedShapesList;
import model.interfaces.IUndoable;
import view.interfaces.PaintCanvasBase;
import java.awt.*;
import java.util.LinkedList;

public class MoveShape implements ICommand, IUndoable, ISelectedShapesList {

	private PaintCanvasBase paintCanvas;
	private int xDelta;
	private int yDelta;
	private final Point startPoint;
	private final Point endPoint;
	private static boolean undoSelected = false;
	private static boolean redoSelected = false;
	private final LinkedList<CreateShape> tempMoveList;
	private final LinkedList<CreateShape> tempRemoveList;

	public MoveShape(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		tempMoveList = new LinkedList<>();
		tempRemoveList = new LinkedList<>();
	}
	@Override
	public void run() {
		undoSelected = false;
		redoSelected = false;

		xDelta = endPoint.x - startPoint.x;
		yDelta = endPoint.y - startPoint.y;

		for (CreateShape selectedShape : selectedShapes) {
			for (CreateShape drawnShape : ShapeList.getList()) {
				if (selectedShape.equals(drawnShape)) {
					tempRemoveList.add(drawnShape);
					selectedShape.x = selectedShape.x+xDelta;
					selectedShape.y =  selectedShape.y+yDelta;
					selectedShape.p1 = new Point(selectedShape.p1.x+xDelta,selectedShape.p1.y+yDelta);
					selectedShape.p2 = new Point(selectedShape.p2.x+xDelta,selectedShape.p2.y+yDelta);
					tempMoveList.add(selectedShape);
				}
			}
		}

		for (CreateShape temp1 : tempRemoveList) {
			selectedShapes.remove(temp1);
			ShapeList.removeShape(temp1);
		}
		for (CreateShape temp1 : tempMoveList) {
			selectedShapes.add(temp1);
			ShapeList.addShape(temp1);
		}
		DrawShape.update();
		CommandHistory.add(this);
	}

	@Override
	public void undo() {
		redoSelected = false;
		undoSelected = tempMoveList.size() > 0;
		xDelta = endPoint.x - startPoint.x;
		yDelta = endPoint.y - startPoint.y;
		for (CreateShape temp1 : tempMoveList) {
			selectedShapes.remove(temp1);
			ShapeList.removeShape(temp1);
		}
		for (CreateShape temp1 : tempRemoveList) {
			temp1.x = temp1.x-xDelta;
			temp1.y = temp1.y-yDelta;
			temp1.p1 = new Point(temp1.p1.x-xDelta,temp1.p1.y-yDelta);
			temp1.p2 = new Point(temp1.p2.x-xDelta,temp1.p2.y-yDelta);
			selectedShapes.add(temp1);
			ShapeList.addShape(temp1);
		}

		if (paintCanvas != null) {
			DrawShape.update();
		}
	}

	@Override
	public void redo() {
		xDelta = endPoint.x - startPoint.x;
		yDelta = endPoint.y - startPoint.y;
		undoSelected = false;
		redoSelected = tempMoveList.size() > 0;

		for (CreateShape temp1 : tempRemoveList) {
			selectedShapes.remove(temp1);
			ShapeList.removeShape(temp1);
		}
		for (CreateShape temp1 : tempMoveList) {
			temp1.x = temp1.x+xDelta;
			temp1.y = temp1.y+yDelta;
			temp1.p1 = new Point(temp1.p1.x+xDelta,temp1.p1.y+yDelta);
			temp1.p2 = new Point(temp1.p2.x+xDelta,temp1.p2.y+yDelta);
			selectedShapes.add(temp1);
			ShapeList.addShape(temp1);
		}
		if (paintCanvas != null) {
			DrawShape.update();
		}
	}
}