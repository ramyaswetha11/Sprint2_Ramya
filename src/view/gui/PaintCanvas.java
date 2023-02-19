package view.gui;

import view.interfaces.PaintCanvasBase;

import javax.swing.JComponent;
import java.awt.*;

public class PaintCanvas extends PaintCanvasBase {

    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2d = (Graphics2D)g;
    }
}
