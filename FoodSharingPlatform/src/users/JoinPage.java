package users;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class JoinPage extends Page{
	JLabel la_title;
	
	JPanel p_name;
	JLabel la_name;
	JTextField t_name;
	
	JPanel p_id;
	JLabel la_id;
	JTextField t_id;
	JButton bt_idCheck;
	
	JPanel p_pass;
	JLabel la_pass;
	JTextField t_pass;
	JButton bt_passCheck;
	
	JPanel p_pass2;
	JLabel la_pass2;
	JTextField t_pass2;
	
	JPanel p_birth;
	JLabel la_birth;
	JComboBox<String> box_year;
	JLabel la_year;
	JComboBox<String> box_mon;
	JLabel la_mon;
	JComboBox<String> box_day;
	JLabel la_day;
	
	JPanel p_cardNum;
	JLabel la_cardNum;
	JTextField t_cardNum;
	
	JPanel p_phoneNum;
	JLabel la_phoneNum;
	JComboBox<String> box_phoneNum;
	JTextField t_frontNum;
	JLabel la_dash;
	JTextField t_endNum;
	
	JButton bt_regist;
	JButton bt_cancel;
	
	//코드 짜면서 멤버변수로 올려야 하는 것들
	int result;
	
	public JoinPage(UserMain userMain) {
		super(userMain);
		setPreferredSize(new Dimension(500,700));
		setBackground(UserMain.COLOR3);
		la_title=new JLabel("회원정보 입력");
		
		p_name=new JPanel();
		la_name=new JLabel("＊이름");
		t_name=new JTextField();
		
		p_id=new JPanel();
		la_id=new JLabel("＊아이디");
		t_id=new JTextField();
		bt_idCheck=new JButton("중복확인");
		
		p_pass=new JPanel();
		la_pass=new JLabel("＊비밀번호");
		t_pass=new JTextField();

		p_pass2=new JPanel();
		la_pass2=new JLabel("＊비밀번호확인");
		t_pass2=new JTextField();
		
		p_birth=new JPanel();
		la_birth=new JLabel("＊생일");
		box_year=new JComboBox<String>();
		la_year=new JLabel("년");
		box_mon=new JComboBox<String>();
		la_mon=new JLabel("월");
		box_day=new JComboBox<String>();
		la_day=new JLabel("일");
		
		p_cardNum=new JPanel();
		la_cardNum=new JLabel("＊나눔카드번호");
		t_cardNum=new JTextField();
		
		p_phoneNum=new JPanel();
		la_phoneNum=new JLabel("＊휴대폰");
		box_phoneNum=new JComboBox<String>();
		t_frontNum=new JTextField();
		la_dash=new JLabel("ㅡ");
		t_endNum=new JTextField();
		bt_regist=new JButton("가입");
		bt_cancel=new JButton("취소");
		
		//디자인
		la_title.setPreferredSize(new Dimension(300, 120));
		la_title.setFont(new Font("굴림", Font.BOLD, 40));
		Dimension d=new Dimension(400, 40);
		
		p_name.setPreferredSize(d);
		la_name.setPreferredSize(new Dimension(80, 30));
		t_name.setPreferredSize(new Dimension(120,30));
		
		p_id.setPreferredSize(d);
		la_id.setPreferredSize(new Dimension(100,30));
		t_id.setPreferredSize(new Dimension(120, 30));
		
		p_pass.setPreferredSize(d);
		la_pass.setPreferredSize(new Dimension(100, 30));
		t_pass.setPreferredSize(new Dimension(120,30));
		
		p_pass2.setPreferredSize(d);
		la_pass2.setPreferredSize(new Dimension(100,30));
		t_pass2.setPreferredSize(new Dimension(120,30));
		
		p_birth.setPreferredSize(new Dimension(400,40));
		la_birth.setPreferredSize(new Dimension(50,30));
		box_year.setPreferredSize(new Dimension(60,30));
		la_year.setPreferredSize(new Dimension(30,30));
		box_mon.setPreferredSize(new Dimension(50,30));
		la_mon.setPreferredSize(new Dimension(40,30));
		box_day.setPreferredSize(new Dimension(50,30));
		la_day.setPreferredSize(new Dimension(40,30));
		
		p_cardNum.setPreferredSize(d);
		la_cardNum.setPreferredSize(new Dimension(90,30));
		t_cardNum.setPreferredSize(new Dimension(140,30));
		
		p_phoneNum.setPreferredSize(d);
		la_phoneNum.setPreferredSize(new Dimension(80,30));
		box_phoneNum.setPreferredSize(new Dimension(80,30));
		t_frontNum.setPreferredSize(new Dimension(60,30));
		la_dash.setPreferredSize(new Dimension(16,30));
		t_endNum.setPreferredSize(new Dimension(60,30));
		
		t_frontNum.setHorizontalAlignment(JTextField.CENTER);
		t_endNum.setHorizontalAlignment(JTextField.CENTER);
		t_frontNum.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField src=(JTextField)e.getSource();
				if(src.getText().length()>3) {
					e.consume();
				}
			}
		});
		t_endNum.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField src=(JTextField)e.getSource();
				if(src.getText().length()>3) {
					e.consume();
				}
			}
		});
		
		box_year.addItem("연도");
		for(int i=2023; i>1940;i--) {
			box_year.addItem(Integer.toString(i));
		}
		box_mon.addItem("월");
		for(int i=1; i<13; i++) {
			box_mon.addItem(Integer.toString(i));
		}
		box_day.addItem("일");
		for(int i=1; i<=31; i++) {
			box_day.addItem(Integer.toString(i));
		}
		box_phoneNum.addItem("번호");
		box_phoneNum.addItem("010");
		box_phoneNum.addItem("011");
		box_phoneNum.addItem("070");
		
		bt_regist.setPreferredSize(new Dimension(100,40));
		bt_cancel.setPreferredSize(new Dimension(100,40));
		
		//붙이기
		add(la_title);
		
		add(p_name);
		p_name.add(la_name);
		p_name.add(t_name);
		
		add(p_id);
		p_id.add(la_id);
		p_id.add(t_id);
		p_id.add(bt_idCheck);
		
		add(p_pass);
		p_pass.add(la_pass);
		p_pass.add(t_pass);
		
		add(p_pass2);
		p_pass2.add(la_pass2);
		p_pass2.add(t_pass2);
		
		add(p_birth);
		p_birth.add(la_birth);
		p_birth.add(box_year);
		p_birth.add(la_year);
		p_birth.add(box_mon);
		p_birth.add(la_mon);
		p_birth.add(box_day);
		p_birth.add(la_day);
		
		add(p_cardNum);
		p_cardNum.add(la_cardNum);
		p_cardNum.add(t_cardNum);
		
		add(p_phoneNum);
		p_phoneNum.add(la_phoneNum);
		p_phoneNum.add(box_phoneNum);
		p_phoneNum.add(t_frontNum);
		p_phoneNum.add(la_dash);
		p_phoneNum.add(t_endNum);
		
		add(bt_regist);
		add(bt_cancel);
		
		
		bt_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.updateComponentTreeUI(userMain);
				userMain.showHide(UserMain.LOGINPAGE);
			}
		});
		
		bt_idCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idcheck();
			}
		});
		
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
	}
	
	public void idcheck() {
		result=userMain.userMemberDAO.selectid(t_id.getText());
		if(result==1) {
			JOptionPane.showMessageDialog(userMain, "아이디가 이미 존재합니다");
			t_id.setText("");
		}else {
			JOptionPane.showMessageDialog(userMain, "사용가능한 아이디 입니다");
		}
	}
	
	public void regist() {
		UserMemberDTO userMemberDTO=new UserMemberDTO();
		String birth=(String)box_year.getSelectedItem()+"/"+(String)box_mon.getSelectedItem()+"/"+(String)box_day.getSelectedItem();
		String ph_num=(String)box_phoneNum.getSelectedItem()+t_frontNum.getText()+t_endNum.getText();

		userMemberDTO.setName(t_name.getText());
		userMemberDTO.setId(t_id.getText());
		userMemberDTO.setPass(t_pass.getText());
		userMemberDTO.setPass2(t_pass2.getText());
		userMemberDTO.setBirth(birth); //콤보박스의 값을 date형식으로 넣자 완료!!
		userMemberDTO.setCardNum(t_cardNum.getText());
		userMemberDTO.setPhoneNum(ph_num);
		System.out.println(birth);
		System.out.println(ph_num);
		
		if(t_pass.getText().equals(t_pass2.getText())) { //직접적으로 String을 비교하는 방식이 확실함
			System.out.println("비밀번호 일치");
			if(t_name.getText()!="" && t_pass.getText()!="" && t_pass2.getText()!="" && box_year.getSelectedIndex()!=0 && box_mon.getSelectedIndex()!=0 && box_day.getSelectedIndex()!=0 && t_cardNum.getText()!="" && box_phoneNum.getSelectedIndex()!=0 && t_frontNum.getText()!="" && t_endNum.getText()!="") {

				if(result==2) { //모든 조건이 충족되었을 때 insert실행 후 로그인 페이지로 돌아오기
					userMain.userMemberDAO.insert(userMemberDTO);
					System.out.println("회원가입성공");
					JOptionPane.showMessageDialog(userMain, "회원가입 성공");
					clean();
					userMain.showHide(UserMain.LOGINPAGE);
					
				}else {
					JOptionPane.showMessageDialog(userMain, "ID중복확인이 필요합니다");
				}
				
			}else {
				JOptionPane.showMessageDialog(userMain, "모두 입력해 주시기 바랍니다");
			}
			
		}else {
			System.out.println(t_pass.getText());
			System.out.println(t_pass2.getText());
			//비밀번호와 비밀번호확인이 서로 일치하지 않을때 일어나는 일
			//근데 분명 일치하게 하는데 이상하게 자꾸 비밀번호가 동일하지 않는다고 하는데 왜그럴까?
			JOptionPane.showMessageDialog(userMain, "비밀번호가 동일하지 않습니다");
			
		}
	}
	
	//회원가입 완료후 회원가입 폼 초기화
	public void clean() {
		
	}
	
}











