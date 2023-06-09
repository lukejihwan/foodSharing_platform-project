package memberStore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPage extends Page{
	JLabel forBusiness_name;
	JPanel p_nameLogin;
	JLabel la_brandandName;
	JLabel la_login;
	JPanel p_brandImage;
	
	JPanel p_west;
	JPanel p_login;
	JLabel la_id;
	JTextField t_id;
	JLabel la_pass;
	JTextField t_pass;
	JButton bt_join;
	JButton bt_login;
	JPanel p_east;
	
	//코드 짜면서 멤버변수로 선언한 곳
	StoreMemberDTO storeMemberDTO;
	
	public LoginPage(StoreMain storeMain) {
		super(storeMain);
		//북쪽 패널 영역
		forBusiness_name=new JLabel("사업자용", JLabel.CENTER);
		//글자를 위쪽으로 붙이려면 결국 JPanel에다가 붙이는 법밖에 없을듯
		p_nameLogin=new JPanel(); //'한끼나눔'과 '가맹점 로그인'을 담을 패널
		la_brandandName=new JLabel("한끼 나눔");
		la_login=new JLabel("사업자 로그인");
		p_brandImage=new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2=(Graphics2D)g;
				
				g2.clearRect(0, 0, p_brandImage.getWidth(), p_brandImage.getHeight());
				g2.drawImage(image, 45, 45, 120, 100, this);
				updateUI();
			}
		};
		
		//로그인 패널 영역
		p_west=new JPanel();
		p_login=new JPanel();
		la_id=new JLabel("ID");
		t_id=new JTextField();
		la_pass=new JLabel("PW");
		t_pass=new JTextField();
		bt_join=new JButton("회원가입");
		bt_login=new JButton("로그인");
		p_east=new JPanel();
		
		//북쪽영역 디자인
		forBusiness_name.setBackground(Color.WHITE);
		forBusiness_name.setOpaque(true);
		forBusiness_name.setPreferredSize(new Dimension(200,200));
		forBusiness_name.setFont(new Font("굴림",Font.BOLD, 30));
		p_nameLogin.setBackground(storeMain.col5);
		p_nameLogin.setPreferredSize(new Dimension(550, 200));
		p_nameLogin.setLayout(null);
		la_brandandName.setFont(new Font("굴림", Font.BOLD, 35));
		la_brandandName.setBounds(190, 80, 200, 50);
		la_login.setFont(new Font("굴림", Font.BOLD, 30));
		la_login.setBounds(170, 150, 250, 50);
		p_brandImage.setPreferredSize(new Dimension(200,200));
		//p_brandImage.setBackground(Color.darkGray); paintComponent때문에 안먹음
		
		//로그인 패널 영역 디자인
		Dimension tSize=new Dimension(430,40);
		Dimension laSize=new Dimension(40,40);
		//p_west.setBackground(Color.yellow);
		p_west.setPreferredSize(new Dimension(230, 450));
		//p_login.setBackground(Color.WHITE);
		p_login.setPreferredSize(new Dimension(500,450));
		la_id.setPreferredSize(laSize);
		t_id.setPreferredSize(tSize);
		la_pass.setPreferredSize(laSize);
		t_pass.setPreferredSize(tSize);
		//p_east.setBackground(Color.yellow);
		p_east.setPreferredSize(new Dimension(230, 450));
		
		//북쪽영역 붙이기
		p_nameLogin.add(la_brandandName);
		p_nameLogin.add(la_login);
		add(forBusiness_name);
		add(p_nameLogin);
		add(p_brandImage);
		
		//로그인 패널 영역 붙이기
		add(p_west);
		p_login.add(la_id);
		p_login.add(t_id);
		p_login.add(la_pass);
		p_login.add(t_pass);
		p_login.add(bt_join);
		p_login.add(bt_login);
		add(p_login);
		add(p_east);
		
		bt_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storeMain.showHide(StoreMain.JOINPAGE);
			}
		});
		
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginCheck();
			}
		});
	}
	
	public void loginCheck(){
		//DB에서 로그인 조건 확인하기
		storeMemberDTO=storeMain.storeMemberDAO.login(t_id.getText(), t_pass.getText());
		if(storeMemberDTO==null) {
			JOptionPane.showMessageDialog(this, "로그인 실패");
		}else {
			
			JOptionPane.showMessageDialog(this, "로그인 성공");
			storeMain.showHide(StoreMain.REGISTPAGE);
		}
		
	}

	
	
}













