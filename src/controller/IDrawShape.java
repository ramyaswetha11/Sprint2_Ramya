package controller;

import java.awt.Graphics2D;

import controller.commands.CreateShape;

public interface IDrawShape {

	public void draw(CreateShape shape, Graphics2D graphics2d);

}

