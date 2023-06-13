package design;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import users.UserMain;

public class CustomJButton extends JButton{
	String string;
	
	public CustomJButton(String string) {
		this.string=string;
		
	}
		
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(UserMain.COLOR2);
		g2.fillRoundRect(0, 0, 100, 40, 20, 20);
		
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("굴림", Font.BOLD, 20));
		g2.drawString(string	, 10, 28);
		
	}
	
	protected void paintBorder(Graphics g) {
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(Color.DARK_GRAY);
		g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
		//가장이쁜 border를 주려면 이런식으로 -1픽셀 씩 해주어야 한다
	}
	
}

