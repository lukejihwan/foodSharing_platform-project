package users;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UserMain extends JFrame{
	JPanel p_west;
	JPanel p_east;
	JLabel la_title;
	CustomJTextField t_customId;
	CustomJTextField t_customPass;
	CustomJButton bt_join;
	CustomJButton bt_login;
	
	public static final Color color1=new Color(250,162,142, 98);
	public static final Color color2=new Color(227,175,129,89);
	public static final Color color3=new Color(250,222,154,98);
	public static final Color color4=new Color(227,217,129,89);
	public static final Color color5=new Color(221,255,153,100);
	
	public UserMain() {
		Container c=getContentPane();
		p_west=new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2=(Graphics2D)g;
				Image image=null;
				Image rainbowImage=null;
				try {
					rainbowImage=ImageIO.read(new File("src\\res\\rainbow.png"));
					image = ImageIO.read(new File("src\\res\\children.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g2.drawImage(rainbowImage, 0, -50, 500, 400, this);
				g2.drawImage(image, 40, 300, 400, 150, this);
			}
		};
		p_east=new JPanel();
		la_title=new JLabel("Welcome!");
		t_customId=new CustomJTextField(8);
		t_customPass=new CustomJTextField(8);
		bt_join=new CustomJButton("회원가입");
		bt_login=new CustomJButton("로그인");
		
		//디자인
		p_east.setLayout(null);
		p_east.setBackground(color1);
		p_west.setBackground(color3);

		la_title.setFont(new Font("굴림", Font.BOLD, 50));
		la_title.setLocation(600, 130);
		la_title.setSize(400,50);
		
		t_customId.setBounds(600, 300, 300, 40);
		t_customPass.setBounds(600, 350, 300, 40);
		
		bt_join.setBounds(600, 400, 100,40);
		bt_login.setBounds(750, 400, 100,40);
		
		c.setLayout(null); //AbsoluteLayout을 사용하려면 Container를 null로 설정해주어야함
		p_west.setBounds(0,0,500,700);
		p_east.setBounds(500, 0, 500, 700);
		
		add(la_title);
		add(t_customId);
		add(t_customPass);
		add(p_west);
		add(p_east);
		add(bt_join);
		add(bt_login);
		
		setSize(1000, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new UserMain();
	}

}
