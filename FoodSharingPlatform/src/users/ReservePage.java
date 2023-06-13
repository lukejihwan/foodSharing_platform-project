package users;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import design.RoundButton;
import memberStore.StoreMenuDTO;

public class ReservePage extends Page{
	RoundButton bt_reserve;
	RoundButton bt_introduce;
	RoundButton bt_chat;
	RoundButton bt_thank;
	JLabel la_user;
	RoundButton bt_logout;
	
	JPanel p_north;
	JPanel p_search;
	JComboBox<String> box_search=new JComboBox<String>();
	JTextField t_search;
	JButton bt_search;
	JPanel p_center;
	JScrollPane scroll;
	
	//코드 짜면서 추가한 것들
	String[][] boxCategory= {{"음식종류", "foodCategory"},{"상호명", "storename"},{"주소", "address"}};
	
	public ReservePage(UserMain userMain) {
		super(userMain);
		setPreferredSize(new Dimension(1000,700));
		bt_reserve=new RoundButton("예약페이지");
		bt_introduce=new RoundButton("플랫폼소개");
		bt_chat=new RoundButton("기부자와 채팅");
		bt_thank=new RoundButton("나눔감사 글");
		la_user=new JLabel("사용자 : ");
		bt_logout=new RoundButton("로그아웃");
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
		p_search=new JPanel();
		box_search=new JComboBox<String>();
		t_search=new JTextField();
		bt_search=new JButton("검색");
		p_center=new JPanel();
		scroll=new JScrollPane();
		scroll.setViewportView(p_center);
		
		//디자인
		Color btColor=new Color(135, 188, 212);
		bt_reserve.setBackground(btColor);
		bt_introduce.setBackground(btColor);
		bt_chat.setBackground(btColor);
		bt_thank.setBackground(btColor);
		bt_logout.setBackground(btColor);
		bt_reserve.setBounds(5, 2, 80, 30);
		bt_introduce.setBounds(90, 2, 80, 30);
		bt_chat.setBounds(175, 2, 80, 30);
		bt_thank.setBounds(260, 2, 80, 30);
		la_user.setBounds(775, 2, 80, 30);
		bt_logout.setBounds(890, 2, 70, 30);
		p_north.setPreferredSize(new Dimension(990,47));
		p_search.setPreferredSize(new Dimension(990,50));
		box_search.setPreferredSize(new Dimension(100,30));
		t_search.setPreferredSize(new Dimension(150,30));
		bt_search.setPreferredSize(new Dimension(80,30));
		p_center.setPreferredSize(new Dimension(970, 1800));
		scroll.setPreferredSize(new Dimension(970,580));
		
		//붙이기
		p_north.add(bt_reserve);
		p_north.add(bt_introduce);
		p_north.add(bt_chat);
		p_north.add(bt_thank);
		p_north.add(la_user);
		p_north.add(bt_logout);
		add(p_north);
		add(p_search);
		p_search.add(box_search);
		p_search.add(t_search);
		p_search.add(bt_search);
		add(scroll);
		
		box_search.addItem("검색목록▼");
		for(int i=0; i<boxCategory.length; i++) {
			box_search.addItem(boxCategory[i][0]);
		};
		System.out.println("검색combobox의 크기는 "+boxCategory.length+" 개");
		
		createMenus();
		
		bt_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		
		bt_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option=JOptionPane.showConfirmDialog(userMain, "로그아웃 하시겠습니까?");
				if(option==JOptionPane.OK_OPTION) {
					userMain.showHide(UserMain.LOGINPAGE);
				}
			}
		});
	}
	
	public void setId(String id) {
		la_user.setText("사용자 : "+id);
	}
	
	//검색버튼 눌렀을 때 검색하기
	public void search() {
		int selectedIndex=box_search.getSelectedIndex()-1;
		if(selectedIndex==-1) {
			JOptionPane.showMessageDialog(this, "검색목록을 선택해주세요");
			return;
		}
		String columnName=boxCategory[selectedIndex][1];
		System.out.println("search메서드를 호출할때 들어갈 컬럼명은 : "+columnName);
		List<StoreMenuDTO> list=userMain.userMemberDAO.search(columnName, t_search.getText());
		
		//화면상에 표시해 주기
		System.out.println("검색된 내용을 포함하는 메뉴들의 갯수는 : "+list.size());
		p_center.removeAll();
		for(int i=0; i<list.size(); i++) {
			StoreMenuDTO storeMenuDTO=list.get(i);
			ItemMenu itemMenu=new ItemMenu(storeMenuDTO);
			p_center.add(itemMenu);
		}
		updateUI();
		
	}
	
	public void createMenus() {
		List menuList=userMain.storeMenuDAO.selectAll();
		System.out.println("menu리스트의 크기는 "+menuList.size());
		for(int i=0; i<menuList.size(); i++) {
			StoreMenuDTO storeMenuDTO=(StoreMenuDTO)menuList.get(i);
			ItemMenu itemMenu=new ItemMenu(storeMenuDTO);
			p_center.add(itemMenu);
		}
	}

}
