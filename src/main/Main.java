package main;

import controller.DrawShape;
import controller.JPaintController;
import controller.MouseHandler;
import controller.ShapeList;
import controller.ShapeMode;
import controller.commands.CreateShape;
import model.interfaces.IJPaintController;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args){
    	
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        IJPaintController controller = new JPaintController(uiModule, appState);
        
        List<CreateShape> shapeList = new ArrayList<CreateShape>();
        ShapeList ShapeList = new ShapeList(shapeList);
        DrawShape DrawShape = new DrawShape(ShapeList,paintCanvas);
        
        MouseHandler handler = new MouseHandler(paintCanvas,ShapeList);
        paintCanvas.addMouseListener(handler);
        MouseHandler.getAppState(appState);
        ShapeMode.getAppState(appState);
        
        controller.setup();
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}

