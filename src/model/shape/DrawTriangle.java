package model.shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import controller.commands.CreateShape;
import model.interfaces.IDrawShape;

public class DrawTriangle implements IDrawShape {
	@Override
	public void draw(CreateShape shape, Graphics2D graphics2d) {

		int[] X = { shape.p1.x, shape.p1.x, shape.p2.x };
		int[] Y = { shape.p1.y, shape.p2.y, shape.p2.y };

		switch (shape.shapeConfig.shadingType) {

		case OUTLINE:
			graphics2d.setColor(Color.WHITE);
			graphics2d.fillPolygon(X, Y, 3);
			graphics2d.setStroke(new BasicStroke(5));
			graphics2d.setColor(shape.shapeConfig.primaryColor);
			graphics2d.drawPolygon(X, Y, 3);
			break;

		case FILLED_IN:
			graphics2d.setColor(shape.shapeConfig.primaryColor);
			graphics2d.fillPolygon(X, Y, 3);
			graphics2d.setStroke(new BasicStroke(5));
			graphics2d.setColor(shape.shapeConfig.primaryColor);
			graphics2d.fillPolygon(X, Y, 3);
			break;

		case OUTLINE_AND_FILLED_IN:
			graphics2d.setColor(shape.shapeConfig.primaryColor);
			graphics2d.fillPolygon(X, Y, 3);
			graphics2d.setStroke(new BasicStroke(5));
			graphics2d.setColor(shape.shapeConfig.secondaryColor);
			graphics2d.drawPolygon(X, Y, 3);
			break;

		default:
			break;

		}

	}
}
