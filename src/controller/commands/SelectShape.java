package controller.commands;


import controller.DrawShape;
import model.ShapeType;
import model.interfaces.ICommand;
import controller.SelectedShapeList;
import controller.ShapeList;
import model.interfaces.ISelectedShapesList;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class SelectShape implements ICommand, ISelectedShapesList {
	private final Point startPoint;
	private final Point endPoint;
	private Point minimum;
	private int width;
	private int height;
	private ShapeList shapeList;

	private PaintCanvasBase paintCanvas;

	public SelectShape(Point startPoint, Point endPoint,PaintCanvasBase paintCanvas) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.paintCanvas = paintCanvas;
	}
	@Override
	public void run() {

		minimum = new Point(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y));

		width = Math.abs(startPoint.x - endPoint.x);

		height = Math.abs(startPoint.y - endPoint.y);

		selectedShapes.clear();

		for (CreateShape shape : ShapeList.getList()) {

			if (shape.p1.x < minimum.x + width &&
					shape.p1.x + (Math.abs(shape.p1.x - shape.p2.x)) > minimum.x &&
					shape.p1.y < minimum.y + height &&
					shape.p1.y + (Math.abs(shape.p1.y - shape.p2.y)) > minimum.y) {
				selectedShapes.add(shape);
			}
		}
		System.out.println("Selected ShapeList : " + selectedShapes);
		DrawShape.update();

		Graphics2D graphics2d = paintCanvas.getGraphics2D();

		for (CreateShape drawShape : ShapeList.getList()) {
			for (CreateShape selectShape : selectedShapes) {
				if (selectShape.equals(drawShape)) {

					if (selectShape.shapeConfig.shapeType == ShapeType.RECTANGLE) {
						Stroke stroke = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
						graphics2d.setStroke(stroke);
						graphics2d.setColor(Color.BLACK);
						graphics2d.drawRect(selectShape.x, selectShape.y,selectShape.w, selectShape.l);
					}
					else if (selectShape.shapeConfig.shapeType == ShapeType.ELLIPSE) {
						Stroke stroke = new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
						graphics2d.setStroke(stroke);
						graphics2d.setColor(Color.BLACK);
						graphics2d.drawRect(selectShape.x, selectShape.y,selectShape.w, selectShape.l);
					}
					else if (selectShape.shapeConfig.shapeType == ShapeType.TRIANGLE){
						Stroke stroke = new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
						graphics2d.setStroke(stroke);
						graphics2d.setColor(Color.BLACK);
						int[] arrayX = {selectShape.p1.x, selectShape.p1.x, selectShape.p2.x};
						int[] arrayY = {selectShape.p1.y, selectShape.p2.y, selectShape.p2.y};
						graphics2d.drawPolygon(arrayX,arrayY,3);

					}

				}
			}
		}

	}
}
