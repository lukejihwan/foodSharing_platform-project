package users;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import design.RoundButton;
import design.RoundTextField;

public class LoginPage extends Page{
	JLabel la_title;
	JLabel la_brandName;
	JPanel p_id;
	JLabel la_id;
	JTextField t_customId;
	JPanel p_pass;
	JLabel la_pass;
	JTextField t_customPass;
	RoundButton bt_join;
	RoundButton bt_login;

	public LoginPage(UserMain userMain) {
		super(userMain);
		la_title=new JLabel("Welcome!");
		la_brandName=new JLabel("한끼 나눔");
		p_id=new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2=(Graphics2D)g;
				g2.setColor(UserMain.COLOR2);
				g2.fillRoundRect(0, 0, 470, 100, 50, 50);
			}
		};
		la_id=new JLabel("ID");
		t_customId=new JTextField() {
			protected void paintComponent(Graphics g) {
				if (!isOpaque() && getBorder() instanceof RoundTextField) {
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setPaint(getBackground());
					g2.fill(((RoundTextField) getBorder()).getBorderShape(0, 0, getWidth() - 1, getHeight() - 1));
					g2.dispose();
				}
				super.paintComponent(g);
			}
			public void updateUI() {
				super.updateUI();
				setOpaque(false);
				setBorder(new RoundTextField());
			}
		};
		p_pass=new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2=(Graphics2D)g;
				g2.setColor(UserMain.COLOR2);
				g2.fillRoundRect(0, 0, 470, 100, 50, 50);
			}
		};
		la_pass=new JLabel("PW");
		t_customPass=new JTextField() {
			protected void paintComponent(Graphics g) {
				if (!isOpaque() && getBorder() instanceof RoundTextField) {
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setPaint(getBackground());
					g2.fill(((RoundTextField) getBorder()).getBorderShape(0, 0, getWidth() - 1, getHeight() - 1));
					g2.dispose();
				}
				super.paintComponent(g);
			}
			public void updateUI() {
				super.updateUI();
				setOpaque(false);
				setBorder(new RoundTextField());
			}
		};
		bt_join=new RoundButton("회원가입");
		bt_login=new RoundButton("로그인");
		
		//디자인
		setBackground(UserMain.COLOR4);
		la_title.setFont(new Font("굴림", Font.BOLD, 50));
		la_title.setPreferredSize(new Dimension(480,100));
		la_brandName.setHorizontalAlignment(JLabel.CENTER);
		la_brandName.setFont(new Font("굴림", Font.BOLD, 40));
		la_brandName.setPreferredSize(new Dimension(480,60));
		
		Dimension panels=new Dimension(480,100);
		Dimension d=new Dimension(60,50);
		Dimension d1=new Dimension(200,40);
		Dimension d2=new Dimension(100,40);
		p_id.setBackground(UserMain.COLOR4);
		p_pass.setBackground(UserMain.COLOR4);
		p_id.setPreferredSize(panels);
		p_pass.setPreferredSize(panels);
		
		Font font=new Font("굴림", Font.BOLD, 20);
		la_id.setPreferredSize(d);
		la_id.setFont(font);
		la_pass.setFont(font);
		t_customId.setPreferredSize(d1);
		la_pass.setPreferredSize(d);
		t_customPass.setPreferredSize(d1);
		bt_join.setPreferredSize(d2);
		bt_login.setPreferredSize(d2);
		bt_join.setBackground(UserMain.COLOR4);
		bt_login.setBackground(UserMain.COLOR4);
		bt_join.setFont(font);
		bt_login.setFont(font);
		
		//붙이기
		add(la_title);
		add(la_brandName);
		p_id.add(la_id);
		p_id.add(t_customId);
		p_pass.add(la_pass);
		p_pass.add(t_customPass);
		
		add(p_id);
		add(p_pass);
		add(bt_join);
		add(bt_login);
		
		
		bt_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.updateComponentTreeUI(userMain);
				userMain.showHide(UserMain.JOINPAGE);
			}
		});
		
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.updateComponentTreeUI(userMain);
				loginCheck();
			}
		});
		
		bt_join.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				System.out.println("반응");
			}
			public void mouseExited(MouseEvent e) { //버튼의 잔상을 해결하기 위함인데 해결 안됨..
				System.out.println("무반응");
			}
		});
		
		
		
		bt_login.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				System.out.println("반응");
			}
			public void mouseExited(MouseEvent e) { //버튼의 잔상을 해결하기 위함
				System.out.println("무반응");
			}
		});
	}
	
	//로그인체크
	public void loginCheck() {
		UserMemberDTO userMemberDTO=userMain.userMemberDAO.login(t_customId.getText(), t_customPass.getText());
		
		//로그인성공
		if(userMemberDTO!=null) {
			//로그인 성공시 해야할일
			//1) selectAll()해서 예약목록 볼수있도록
			//이것을 ReservePage에서 해주는 것이 더 일관성이 있을 듯하다.
			//ReservePage reservePage=(ReservePage)userMain.pages[2];
			//reservePage.createMenus();
			
			//2) 화면전환
			ReservePage reservePage=(ReservePage)userMain.pages[2];
			reservePage.setId(userMemberDTO.getId());
			userMain.showHide(UserMain.RESERVEPAGE);
			
			//3) 사장님께 주문보내기
			
			//4) 아이디 비번 초기화
			t_customId.setText("");
			t_customPass.setText("");
		}else {
			JOptionPane.showMessageDialog(userMain, "아이디 또는 비밀번호가 \n 일치하지 않습니다");
		}
	}
	
	
}





