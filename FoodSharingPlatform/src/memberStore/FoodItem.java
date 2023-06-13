package memberStore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import design.RoundButton;

public class FoodItem extends JPanel{
	StoreMenuDTO storeMenuDTO;
	Image image;
	RoundButton bt_del;
	
	StoreMain storeMain;
	
	public FoodItem(StoreMenuDTO storeMenuDTO, StoreMain storeMain) {
		this.storeMenuDTO=storeMenuDTO;
		this.storeMain=storeMain;
		
		bt_del=new RoundButton("삭제");
		
		setPreferredSize(new Dimension(710, 100));
		setLayout(null);
		bt_del.setBounds(600, 10, 80, 30);
		
		add(bt_del);
		
		createImage();
		
		
		bt_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		

	}
	
	
	//사업자가 삭제버튼을 눌렀을 때 삭제 될수 있도록
	//중요!! 이 버튼이 비활성화되어야 하는 시점을 생각해보면 이 비활성화 버튼은 누를때 동작하는 것이 아닌, 로그인 되자마자 비활성화 되어야한다!!
	//이 시점때문에 고민을 엄청했다!
	public void closeButton(String idForDelete) {
		//DB에서 삭제 주의! 내가 올린 것만 삭제 할 수 있어야함. 다른 id로 입력된것은 버튼 비활성화 하기!
		//1)자신의 사업자가 아닌 버튼은 비활성화
		//로그인된 사용자가 음식을 올린 사용자와 불일치시 버튼 비활성화
//		LoginPage loginPage=(LoginPage)storeMain.pages[UserMain.LOGINPAGE];
		//여기서 nullpointerException이 나는 이유는 login을 해야 StoreMemberDTO가 생기는데, 그 전에 여기가 생성자에 들어가 있으니 그렇다...
		if(!idForDelete.equals(storeMenuDTO.getStoreMemberDTO().getId())) {
			System.out.println("항목별에 들어있는 id값은 : "+ storeMenuDTO.getStoreMemberDTO().getId());
			bt_del.setBackground(Color.BLACK);
			bt_del.setEnabled(false);
		}else {
			System.out.println("로그인 한 사람과 일치하는 내 버튼은 활성화 되었어요");
			bt_del.setBackground(Color.white);
			bt_del.setEnabled(true);
		}

	}
	
	//진짜로 삭제를 실행할 부분
	public void delete() {
		//1)DB에서 삭제하기
		int option=JOptionPane.showConfirmDialog(this, "정말 삭제하시겠습니까?");
		RegistPage registPage=null;
		if(option==JOptionPane.OK_OPTION) {
			LoginPage loginPage=(LoginPage)storeMain.pages[0];
			registPage=(RegistPage)storeMain.pages[2];
			int result=storeMain.storeMenuDAO.delete(storeMenuDTO.getStoreMenu_idx());
			
			//2)디자인적으로 삭제하기
			//원래는 여기서 디자인 정보를 가지고 왔어야하나,
			//이제 그럴필요가 없는게, getList()를 호출할때마다 다 지워지고 다시 생성되게 했음
			registPage.getList(); 
			loginPage.setButtonActive();
			
			System.out.println("한건이 삭제됨");
		}
		
	}
	
	public void createImage() {
		File file=new File(storeMenuDTO.getFilename());
		try {
			image=ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2=(Graphics2D) g;
		
		g2.setColor(StoreMain.col2);
		g2.fillRoundRect(0, 0, 710, 100, 20, 20);
		
		g2.drawImage(image, 20, 10, 90, 80, this);
		
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("굴림", Font.BOLD, 15));
		
		//setBorder();
		
		g2.drawString("가게이름", 120, 20);
		g2.drawString("두끼",200, 20); //상호명 나올 곳
		//storeMenuDTO.getStoreMemberDTO().getStoreName()
		
		g2.drawString("음식이름", 120, 50);
		g2.drawString(storeMenuDTO.getFoodName(), 200, 50);//음식이름 나올 곳
		
		g2.drawString("음식양", 120, 80);
		g2.drawString(storeMenuDTO.getServings(), 200, 80); //몇인분 나오는 곳
		
		g2.drawString("음식종류", 350, 20);
		g2.drawString("양식", 450, 20); //음식종류 나오는 곳
		//storeMenuDTO.getStoreMemberDTO().getFoodCategory()

		g2.drawString("예약마감시간", 350, 50);
		g2.drawString(storeMenuDTO.getDeadLine(), 450, 50); //예약마감시간 나오는 곳
		
		g2.drawString("주소", 350, 80);
		g2.drawString("토당로140", 450, 80); //가게 주소 나오는 곳
		//storeMenuDTO.getStoreMemberDTO().getAddress()
	}
	
}
