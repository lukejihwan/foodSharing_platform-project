package design;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.AbstractBorder;

import users.UserMain;

public class RoundTextField extends AbstractBorder{
	private static final Color ALPHA_ZERO = new Color(0x0, true);
	Color myColor1=new Color(122, 18, 201); //보라색계열
	Color myColor2=new Color(115, 169, 222);//파스텔파란색계열
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Shape border = getBorderShape(x, y, width - 1, height - 1);
		g2.setPaint(ALPHA_ZERO);
		Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
		corner.subtract(new Area(border));
		g2.fill(corner);
		g2.setPaint(myColor2);
		g2.draw(border);
		g2.dispose();
	}

	public Shape getBorderShape(int x, int y, int w, int h) {
		int r = h; // h / 2;
		return new RoundRectangle2D.Double(x, y, w, h, r, r);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(4, 8, 4, 8);
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		insets.set(4, 8, 4, 8);
		return insets;
	}
}
