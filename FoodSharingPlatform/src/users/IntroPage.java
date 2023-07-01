package users;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JPanel;

import design.RoundButton;

public class IntroPage extends Page{
	JPanel p_north;
	
	RoundButton bt_reserve;
	RoundButton bt_introduce;
	RoundButton bt_chat;
	RoundButton bt_thank;
	JLabel la_user;
	JLabel la_date;
	RoundButton bt_logout;
	
	
	
	public IntroPage(UserMain userMain) {
		super(userMain);
		setPreferredSize(new Dimension(1000,700));
		bt_reserve=new RoundButton("예약페이지");
		bt_introduce=new RoundButton("플랫폼소개");
		bt_chat=new RoundButton("기부자와 채팅");
		bt_thank=new RoundButton("나눔감사 글");
		la_user=new JLabel("사용자 : ");
		la_date=new JLabel("날짜 : "+LocalDate.now());
		bt_logout=new RoundButton("로그아웃");
		
		//디자인
		Color btColor=new Color(135, 188, 212);
		bt_reserve.setBackground(btColor);
		bt_introduce.setBackground(btColor);
		bt_chat.setBackground(btColor);
		bt_thank.setBackground(btColor);
		la_user.setBounds(775, 2, 80, 30);
		la_date.setBounds(650, 2, 150, 30);
		bt_logout.setBackground(btColor);
		p_north=new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2=(Graphics2D)g;
				g2.setColor(UserMain.COLOR3);
				g2.fillRoundRect(0, 0, 970, 35, 20, 20);
				g2.setColor(Color.WHITE);
				g2.drawRoundRect(0, 0, 970, 35, 19, 19);
				//이런식으로 border를 주는것도 하나의 방법! 물론 border라기보단 그냥 빈 타원을 그리는 거지만
			}
		};
		p_north.setLayout(null);
		p_north.setPreferredSize(new Dimension(990,47));
		
		//붙이기
		p_north.add(bt_reserve);
		p_north.add(bt_introduce);
		p_north.add(bt_chat);
		p_north.add(bt_thank);
		p_north.add(la_user);
		p_north.add(la_date);
		p_north.add(bt_logout);
		add(p_north);
	}

}
