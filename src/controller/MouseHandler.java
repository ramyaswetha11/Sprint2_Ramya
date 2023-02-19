package controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.ShapeConfig;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class MouseHandler extends MouseAdapter {

	Point p1 = new Point(0, 0);
	Point p2 = new Point(0, 0);
	int x;
	int y;
	int l;
	int w;

	private static ApplicationState appState;

	private final PaintCanvasBase paintCanvas;

	private final ShapeList shapeList;

	public MouseHandler(PaintCanvasBase paintCanvas,ShapeList shapeList){
		this.paintCanvas = paintCanvas;
		this.shapeList = shapeList;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		p1 = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		p2 = e.getPoint();

		x = Math.min(p1.x, p2.x);
		y = Math.min(p1.y, p2.y);
		w = Math.abs(p1.x - p2.x);
		l = Math.abs(p1.y - p2.y);

		ShapeConfig shapeConfig = new ShapeConfig(appState.getActivePrimaryColor(), appState.getActiveSecondaryColor(),
				appState.getActiveShapeType(), appState.getActiveShapeShadingType());

		ShapeMode.run(x, y, p1, p2, l, w, shapeConfig,paintCanvas,shapeList);
	}

	public static void getAppState(ApplicationState AppState) {
		appState = AppState;
	}

}
