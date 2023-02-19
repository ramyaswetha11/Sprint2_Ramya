package model;

import java.awt.Color;

public class ShapeConfig {
	public Color primaryColor;
	public Color secondaryColor;
	public ShapeType shapeType;
	public ShapeShadingType shadingType;

	public ShapeConfig(ShapeColor primaryColor, ShapeColor secondaryColor, ShapeType shapeType,
			ShapeShadingType shadingType) {

		this.primaryColor = ColorConvert.getColor(primaryColor.toString());
		this.secondaryColor = ColorConvert.getColor(secondaryColor.toString());
		this.shapeType = shapeType;
		this.shadingType = shadingType;
	}
}