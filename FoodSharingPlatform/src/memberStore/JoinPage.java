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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JoinPage extends Page{
	//북쪽영역
	JLabel forBusiness_name;
	JPanel p_nameLogin;
	JLabel la_brandandName;
	JLabel la_join;
	JPanel p_brandImage;
	
	//아래쪽 영역
	JPanel p_west;
	JPanel p_login;
	JLabel la_title;
	JPanel p_textContainer;
	JTextField t_businessNumber;
	JTextField t_id;
	JTextField t_pass;
	JTextField t_storeName;
	JTextField t_address;
	JComboBox<String> foodCategory=new JComboBox<String>();
	JButton bt_join;
	JButton bt_login;
	JPanel p_east;

	public JoinPage(StoreMain storeMain) {
		super(storeMain);
		//북쪽 패널 영역
		forBusiness_name=new JLabel("사업자용", JLabel.CENTER);
		p_nameLogin=new JPanel(); //'한끼나눔'과 '가맹점 로그인'을 담을 패널
		la_brandandName=new JLabel("한끼 나눔");
		la_join=new JLabel("사업자 회원가입");
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
		la_title=new JLabel("<html><body style='text-align:left;'><p style='font-size:13px;'>사업자 번호<br /><br />ID<br /><br />PW<br /><br />상호명<br /><br />주소</p></body></html>");
		p_textContainer=new JPanel();
		t_businessNumber=new JTextField();
		t_id=new JTextField();
		t_pass=new JTextField();
		t_storeName=new JTextField();
		t_address=new JTextField();

		foodCategory.addItem("업종선택▼");
		foodCategory.addItem("한식");
		foodCategory.addItem("중식");
		foodCategory.addItem("일식");
		foodCategory.addItem("양식");
		foodCategory.addItem("분식");
		foodCategory.addItem("베이커리");
		foodCategory.addItem("패스트푸드");
		foodCategory.addItem("기타");
		
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
		la_brandandName.setBounds(210, 80, 200, 50);
		la_join.setFont(new Font("굴림", Font.BOLD, 30));
		la_join.setBounds(170, 150, 250, 50);
		p_brandImage.setPreferredSize(new Dimension(200,200));
		p_brandImage.setBackground(Color.darkGray);
		
		//로그인 패널 영역 디자인
		Dimension d=new Dimension(390,42);
		//p_west.setBackground(Color.yellow);
		p_west.setPreferredSize(new Dimension(230, 450));
		//p_login.setBackground(Color.WHITE);
		p_login.setPreferredSize(new Dimension(500,300));
		la_title.setPreferredSize(new Dimension(100, 240));
		la_title.setBackground(storeMain.col4);
		la_title.setOpaque(true);
		p_textContainer.setPreferredSize(new Dimension(390, 245));
		t_businessNumber.setPreferredSize(d);
		t_id.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		t_storeName.setPreferredSize(d);
		t_address.setPreferredSize(d);
		foodCategory.setPreferredSize(new Dimension(250,30));
		
		//p_east.setBackground(Color.yellow);
		p_east.setPreferredSize(new Dimension(230, 450));
		
		//북쪽영역 붙이기
		p_nameLogin.add(la_brandandName);
		p_nameLogin.add(la_join);
		add(forBusiness_name);
		add(p_nameLogin);
		add(p_brandImage);
		
		//로그인 패널 영역 붙이기
		add(p_west);
		p_login.add(la_title);
		p_textContainer.add(t_businessNumber);
		p_textContainer.add(t_id);
		p_textContainer.add(t_pass);
		p_textContainer.add(t_storeName);
		p_textContainer.add(t_address);
		p_login.add(p_textContainer);
		p_login.add(foodCategory);
		p_login.add(bt_login);
		p_login.add(bt_join);
		add(p_login);
		add(p_east);

		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storeMain.showHide(StoreMain.LOGINPAGE);
			}
		});
		
		bt_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});

	}
	
	
	public void regist() {
		//정보들 모두 취합해서 insert DAO에서 날리기
		StoreMemberDTO storeMemberDTO=null; //try catch 문에서 안보이기 때문에 지역변수 선언
		
		//아이디 중복체크 메서드
		List list=storeMain.storeMemberDAO.idCheck(t_id.getText());
		if(list.size()>0) {
			JOptionPane.showMessageDialog(this, "이미 사용중인 아이디 입니다");
			return;
		}
		
		try {
			storeMemberDTO=new StoreMemberDTO();
			storeMemberDTO.setBusinessNumber(t_businessNumber.getText());
			storeMemberDTO.setId(t_id.getText());
			storeMemberDTO.setPass(t_pass.getText());
			storeMemberDTO.setStoreName(t_storeName.getText());
			storeMemberDTO.setAddress(t_address.getText());
			storeMemberDTO.setFoodCategory((String)foodCategory.getSelectedItem());//이부분을 JComboBox에서 가져와야 함...
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(storeMain, "사업자번호를 숫자로 적어주세요"); //이부분에서 사업자번호를 분명 숫자로 적었는데 오류가 난다 왜그럴까?
			return; //MessageDialong가 두번 나오는 것을 방지하기 위해 여기서 끊어줌
		}

		if(t_businessNumber.getText()=="" || t_id.getText()=="" || t_pass.getText()=="" || t_storeName.getText()=="" || t_address.getText()=="" || foodCategory.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(storeMain, "모든 내용을 다 작성해주세요");
		}else {
			int result=storeMain.storeMemberDAO.insert(storeMemberDTO);
			
			//회원가입 완료뜨게 하기
			if(result>0) {
				JOptionPane.showMessageDialog(storeMain, "회원가입을 축하합니다");
				t_businessNumber.setText("");
				t_id.setText("");
				t_pass.setText("");
				t_storeName.setText("");
				t_address.setText("");
				foodCategory.setSelectedIndex(0);
			}
		}
	}
	
	
	
}
