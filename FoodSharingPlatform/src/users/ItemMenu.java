package users;

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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import chat.ChatClient;
import chat.ClientThread;
import memberStore.StoreMenuDTO;

public class ItemMenu extends JPanel{
	Image image;
	JButton bt_detail;
	JButton bt_reserve;
	StoreMenuDTO storeMenuDTO;
	
	public ItemMenu(StoreMenuDTO storeMenuDTO) {
		this.storeMenuDTO=storeMenuDTO;
		setPreferredSize(new Dimension(480,100));
		Border lineBorder=BorderFactory.createLineBorder(UserMain.COLOR2, 2); 
		setBorder(lineBorder);

		bt_detail=new JButton("상세보기");
		bt_reserve=new JButton("예약");
		
		setLayout(null); //이렇게 해줌으로서, 버튼을 배치하기 위함
		
		bt_detail.setBounds(290, 60, 90, 30);
		bt_reserve.setBounds(380, 60, 90, 30);
		
		add(bt_detail);
		add(bt_reserve);
		
		createImage();
		
		//상세보기 버튼눌렀을 때 일어나는 일
		bt_detail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDetail();
			}
		});
		
		//예약버튼을 눌렀을 때 일어나는 일
		bt_reserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reserve();
			}
		});
		
	}
	
	//예약버튼을 눌렀을 시
	public void reserve() {
		int option=JOptionPane.showConfirmDialog(this, "예약하시겠습니까?");
		if(option==JOptionPane.OK_OPTION) {
			System.out.println("확인버튼 눌림");
			
			//이부분에서 채팅창이 개설되는 것이 맞나?...쨋든 서버는24시간 가동되는 상태..
			//ChatClient client=new ChatClient(); 이부분에서 채팅창이 생기는 것은 이상함..
			//사장님쪽 채팅창은 항상켜져있어야 하고, 유저의 채팅창은 켜져있을수도 아닐수도 있기 때문에,
			//유저가 로그인 하는 타이밍에 유저의 DB를 모아둘 곳이 필요하고, 이 DB를 예약버튼을 누르면
			//그 때 사장님에게 발송하는 식이 맞는 것 같다.
			
			//디자인적으로 예약완료되면 버튼 비활성화
			bt_reserve.setText("예약완료");
			bt_reserve.setEnabled(false);
			
			//사장님에게 주문 메시지 보내기
			sendOrder(null);
		}else {
			System.out.println("안눌림");
		}
	}
	
	//사장님에게 주문보내기
	public void sendOrder(UserMemberDTO userMemberDTO) {
		//가상의 서버상의 유저가 존재한다고 하자
		ChatClient chatClient=new ChatClient();
		chatClient.sendAOrder(userMemberDTO, storeMenuDTO);
	}
	
	
	//상세보기 버튼을 눌렀을 때 호출되는 메서드
	public void showDetail() {
		//아니 그냥 이렇게만 해도 JFrame이 생성된다.. 굳이 JOptionPane.showMessageDialog할 필요없다!!
		PopUp popUp=new PopUp(storeMenuDTO);
		popUp.setLocationRelativeTo(this);
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
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(UserMain.COLOR5);
		g2.fillRoundRect(0, 0, 480, 100, 20, 20); //뒷부분이 정확히 뭘 의미하는지 정리
		
		g2.drawImage(image, 10, 10, 90, 80, this);
		
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("굴림", Font.BOLD, 15));
		
		g2.drawString("가게이름", 120, 20); 
		//g2.drawString(storeMenuDTO.getStoreMemberDTO().getStoreName(),200, 20); //상호명 나올 곳
		//이 부분이 에러남
		//DB 다시 수정
		
		g2.drawString("음식이름", 120, 50);
		g2.drawString(storeMenuDTO.getFoodName(), 200, 50);//음식이름 나올 곳
		
		g2.drawString("음식양", 120, 80);
		g2.drawString(storeMenuDTO.getServings(), 200, 80); //몇인분 나오는 곳
		
		g2.drawString("음식종류", 310, 20);
		//g2.drawString(storeMenuDTO.getStoreMemberDTO().getFoodCategory(), 400, 20); //음식종류 나오는 곳

		g2.drawString("예약마감시간", 310, 50);
		g2.drawString(storeMenuDTO.getDeadLine(), 410, 50); //예약마감시간 나오는 곳
	}
	
	
	public void main() {
		
	}
	
	
}
