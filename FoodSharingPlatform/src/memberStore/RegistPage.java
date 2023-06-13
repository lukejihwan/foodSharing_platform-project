package memberStore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import design.RoundButton;

public class RegistPage extends Page{
	//서쪽영역
	JPanel p_west;
	
	JPanel p_foodName;
	JLabel la_foodName;
	JTextField t_foodName;
	
	JComboBox<String> combo;
	
	JPanel p_reserveTime;
	JLabel la_reserveTime;
	JTextField t_reserveTime;
	
	JTextArea area;
	JScrollPane areascroll;
	JPanel p_preview;
	JButton bt_down;
	JFileChooser chooser;
	JButton bt_regist;
	
	//센터영역
	JPanel p_east;
	JScrollPane scroll;
	
	//북쪽
	JPanel p_north;
	JLabel la_memberId;
	RoundButton[] bt=new RoundButton[2]; //버튼 2개 들어갈 배열 만들기
	
	//코드 짜면서 멤버변수로 선언한 것
	Image image; //storeMenuDTO로 다 보낼거라서 멤버로 받을 필요없음 그러나 p_preview한테는 필요함
	String filename; //이미지 다운받을때 절대경로를 담아둘 멤버변수
	
	//FoodItem의 버튼을 제어하기 위한 리스트
	List<StoreMenuDTO> list;
	ArrayList<FoodItem> foodList=new ArrayList<FoodItem>();
	
	public RegistPage(StoreMain storeMain) {
		super(storeMain);
		//서쪽영역----------------
		p_west=new JPanel();
		p_foodName=new JPanel();
		la_foodName=new JLabel("음식명");
		t_foodName=new JTextField();
		combo=new JComboBox<String>();
		p_reserveTime=new JPanel();
		la_reserveTime=new JLabel("예약마감시간");
		t_reserveTime=new JTextField();
		area = new JTextArea("메모해 주세요");
		areascroll=new JScrollPane(area);
		p_preview=new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2=(Graphics2D)g;

				g2.setColor(StoreMain.col4);
				g2.fillRect(0, 0, 200, 150);
				
				g2.setColor(Color.black);
				g2.drawString("이미지를 첨부해주세요", 40, 80);
				
				if(image!=null) {
					g2.drawImage(image, 0, 0, 200, 180, storeMain);
				}
			}
		};
		chooser = new JFileChooser("C:/java_data/FoodImage");
		bt_down=new JButton("이미지 등록");
		bt_regist=new JButton("등록");
		
		//센터영역--------------
		p_east=new JPanel();
		scroll=new JScrollPane(p_east);
		
		//북쪽영역---------------
		p_north=new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2=(Graphics2D)g;
				g2.setColor(getBackground());
				g2.fillRoundRect(0, 0, 980, getHeight(), 20, 20);
				g2.setColor(Color.WHITE);
				g2.drawRoundRect(0, 0, 980, getHeight(), 19, 19);
				//이런식으로 border를 주는것도 하나의 방법! 물론 border라기보단 그냥 빈 타원을 그리는 거지만
			}
		};
		p_north.setLayout(null);
		p_north.setPreferredSize(new Dimension(990,40));
		p_north.setBackground(StoreMain.col3);
		//p_north.setBorder(new LineBorder(Color.WHITE, 1, true));
		
		la_memberId=new JLabel("기부자 : ");
		la_memberId.setBounds(750, 5, 100, 30);
		
		bt[0]=new RoundButton("내가 올린 나눔");
		bt[0].setBackground(new Color(224, 207, 195));
		bt[0].setBounds(7, 5, 140, 30);
		
		bt[1]=new RoundButton("로그아웃");
		bt[1].setBackground(new Color(224, 207, 195));
		bt[1].setBounds(875, 5, 100, 30);
		
		p_north.add(bt[0]);
		p_north.add(la_memberId);
		p_north.add(bt[1]);
		
		//-------------디자인-------------
		Dimension tf=new Dimension(150, 30);//텍스트필드의 크기
		Dimension la=new Dimension(80, 30); //라벨의 크기
		Dimension pn=new Dimension(250, 40); //패널의 크기
		p_west.setPreferredSize(new Dimension(250, 650));
		
		p_foodName.setPreferredSize(pn);
		la_foodName.setPreferredSize(la);
		t_foodName.setPreferredSize(tf);
		
		combo.setPreferredSize(new Dimension(230, 30));
		combo.addItem("몇인분");
		for(int i=1; i<=10; i++) {
			combo.addItem(Integer.toString(i)+"인분");
		}
		
		//combo.add(this); 이게 뭐니? 헐... combo를 이상한데다가 부모Panel에 쳐 넣으니까 그러지!!!
		p_reserveTime.setPreferredSize(pn);
		la_reserveTime.setPreferredSize(la);
		t_reserveTime.setPreferredSize(tf);
		
		areascroll.setPreferredSize(new Dimension(200, 100));
		p_preview.setPreferredSize(new Dimension(200, 180));
		
		bt_down.setPreferredSize(new Dimension(240,30));
		
		setLayout(new BorderLayout());
		
		//센터영역 디자인
		p_east.setPreferredSize(new Dimension(700, 30000));
		scroll.setPreferredSize(new Dimension(700, 700));
		p_east.setBackground(StoreMain.col5);
		
		//--------------------붙이기----------------
		p_foodName.add(la_foodName);
		p_foodName.add(t_foodName);
		p_west.add(p_foodName);//라벨과 텍스트필드를 가지고 있는 음식명 패널
		
		p_west.add(combo); //콤보박스
		
		p_reserveTime.add(la_reserveTime);
		p_reserveTime.add(t_reserveTime);
		p_west.add(p_reserveTime); //라벨과 텍스트필드를 가지고 있는 예약시간 패널
		
		p_west.add(areascroll);
		p_west.add(p_preview); 
		p_west.add(bt_down);
		p_west.add(bt_regist);
		
		add(p_west, BorderLayout.WEST);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		bt_down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registImage();
			}
		});
		
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
		
		bt[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		
		getList();
	}
	
	//등록페이지에 현재접속자 ID보여주기
	public void showId(String id) {
		la_memberId.setText("기부자 : "+id);
	}
	
	//이미지등록 버튼을 눌렀을 때
	public void registImage() {
		int result=chooser.showOpenDialog(this);
		
		if(result==JFileChooser.APPROVE_OPTION) {
			File file=chooser.getSelectedFile();
			filename=file.getAbsolutePath(); //나중에 이 파일경로로 DB에 저장했다가 보여주기 위해
			try {
				image=ImageIO.read(file); //멤버변수로 이미지 올리기
				updateUI();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//로그아웃!
	public void logout() {
		storeMain.showHide(StoreMain.LOGINPAGE);
	}
	
	public void getList() {
		//막 가입한 회원이 로그인하면 계속 오류났던 부분
		p_east.removeAll(); //항상 지워주고 다시 만들기!
		list=storeMain.storeMenuDAO.selectAll(); //로그인 성공시 오른쪽 영역에 떠있어야할 정보들이 list에 담겨있음

		System.out.println("현재 사업자에게 보여질 패널의 갯수는 : "+list.size()+" 개");
		for(int i=0; i<list.size(); i++) {
			StoreMenuDTO storeMenuDTO=(StoreMenuDTO)list.get(i);
			FoodItem foodItem=new FoodItem(storeMenuDTO, storeMain);
			p_east.add(foodItem);
			//foodItem의 해당 사업자가 아닌 버튼은 비활성화 시키기 위해 배열에 담아주겠다
			foodList.add(foodItem); //어디 리스트에 담는지 좀 확인하자!!!
			
		}
		updateUI();
	}
	
	//음식등록
	public void regist() {
		//1)DB에 insert하기
		StoreMenuDTO storeMenuDTO=new StoreMenuDTO();
		
		//회원가입시 넣어두었던 를 로그인시 가져와서 등록할 때   
		//storeMenuDTO와 storeMemberDTO를 오른쪽영역에 붙일 패널에 넣어줌 
		LoginPage loginPage=(LoginPage)storeMain.pages[StoreMain.LOGINPAGE];
		StoreMemberDTO storeMemberDTO=loginPage.storeMemberDTO;
		
		//새로 생성된 storeMemberDTO에는 현재 아무값도 없다..어떻게 idx를 포함한 DTO가 되게 하지?
		storeMenuDTO.setStoreMemberDTO(storeMemberDTO);  
		storeMenuDTO.setFoodName(t_foodName.getText());
		storeMenuDTO.setServings((String)combo.getSelectedItem()); 
		storeMenuDTO.setDeadLine(t_reserveTime.getText());
		storeMenuDTO.setMemo(area.getText());
		storeMenuDTO.setFilename(filename);
		
		System.out.println(storeMemberDTO+"-> storeMemberDTO의 주소값");
		System.out.println("음식등록할 storemember_idx : "+storeMenuDTO.getStoreMemberDTO().getStoreMember_idx());
		
		int result=storeMain.storeMenuDAO.insert(storeMenuDTO);
		if(result>0) {
			System.out.println("DB등록 성공");
		}
		
		//2)패널에 표시하기
		//p_east.add(new FoodItem(storeMenuDTO));
		getList();
		
		//3)다 등록되면 비우기
		cleanRegistBox();
		
		//4)버튼 비활성화 실행해주기
		loginPage.setButtonActive();
	}
	
	//등록완료 후 좌측영역 비워주는 메서드
	public void cleanRegistBox() {
		t_foodName.setText("");
		combo.setSelectedIndex(0);
		t_reserveTime.setText("");
		area.setText("메모해 주세요");
		image=null;
	}
	
		

	
	
	
}







