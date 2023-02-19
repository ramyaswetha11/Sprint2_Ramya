package model.interfaces;


import controller.commands.CreateShape;

import java.util.LinkedList;

public interface ISelectedShapesList {
        LinkedList<CreateShape> selectedShapes = new LinkedList<>();
    }

