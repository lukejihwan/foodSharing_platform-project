package design;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class RoundButton extends JButton{
	Color CustomedColor1=new Color(115, 169, 222); //파스텔파란색계역
	Color CustomedColor2=new Color(250,222,154,98);
	Color CustomedColor3=new Color(122, 18, 201); //보라색계열
	Color CustomedColor4=new Color(242, 237, 94); //머스타드계열
	Color CustomedColor5=new Color(227,175,140); //베이지색계열
	
	
	public RoundButton() {
        super();
        decorate();
    }

    public RoundButton(String text) {
        super(text);
        decorate();
    }

    public RoundButton(Action action) {
        super(action);
        decorate();
    }

    public RoundButton(Icon icon) {
        super(icon);
        decorate();
    }

    public RoundButton(String text, Icon icon) {
        super(text, icon);
        decorate();
    }

    protected void decorate() {
        setBorderPainted(false);
        setOpaque(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        Graphics2D graphics = (Graphics2D) g;

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            graphics.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            graphics.setColor(getBackground().brighter());
        } else {
        	//여기에 색상을 넣어버리면 외부에서 색을 넣어도 바꿀수가 없게 된다 주의!
            graphics.setColor(getBackground());
        }

        graphics.fillRoundRect(0, 0, width, height, 20, 20);

        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();

        int textX = (width - stringBounds.width) / 2;
        int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();

        //아래Color, Font, String들을 보니, 이말은 이 클래스에서 정의하는 것이 아닌, 외부에서 개발자 마음대로
        //정의할수 있는 것 같다
        graphics.setColor(getForeground());
        graphics.setFont(getFont());
        graphics.drawString(getText(), textX, textY);
        graphics.dispose();

        super.paintComponent(g);
    }
}
