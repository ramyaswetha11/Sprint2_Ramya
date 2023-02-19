package controller;

import java.util.List;

import controller.commands.CreateShape;

public class ShapeList {

	private static List<CreateShape> shapeList;

	public ShapeList(List<CreateShape> shapeList) {
		ShapeList.shapeList = shapeList;
	}

	public static void addShape(CreateShape shape) {
		shapeList.add(shape);
		DrawShape.update();
	}

	public static void removeShape(CreateShape shape) {
		shapeList.remove(shape);
		DrawShape.update();
	}

	public static List<CreateShape> getList() {
		return shapeList;
	}
}

