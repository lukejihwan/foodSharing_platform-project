package design;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;

public class CustomJTextField extends JTextField{
	Color color=new Color(115, 169, 222);
    
    protected void paintComponent(Graphics g) {
    	Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
    }
    
    protected void paintBorder(Graphics g) {
    	Graphics2D g2=(Graphics2D)g;
        g2.setColor(color);
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
    }
    
    
}
