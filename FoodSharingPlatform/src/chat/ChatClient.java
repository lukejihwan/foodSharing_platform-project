package chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import design.RoundButton;
import design.RoundTextField;
import memberStore.StoreMenuDTO;
import users.UserMemberDTO;

public class ChatClient extends JFrame{
	JPanel p_north;
	//RoundButton bt_connect; //접속버튼
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input; //메세지 입력창
	RoundButton bt_send; //전송버튼

	int port=9999;
	ClientThread cmt;
	
	//우리가 지금까지 패키지 밖에서 이 클래스의 메서드를 호출할수 있었던 것은 public때문이었다.
	//그래서 이 클래스의 멤버변수는 public이 아니므로 접근이 불가하다.
	public ChatClient() {
		super("사업자채팅");
		p_north = new JPanel();
		//bt_connect = new RoundButton("접속");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField() {
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
		//텍스트필드안은 칠하면 안될듯하다 계속 잔상이 남음 아니 그냥 그리면 잔상이 난다
		//디자인이 제일 어려워..
		bt_send = new RoundButton("전송");
		bt_send.setBackground(new Color(127, 139, 219));
		
		
		//디자인
		//t_input.setBorder(new LineBorder(UserMain.COLOR1, 3, true));
		t_input.setPreferredSize(new Dimension(180,27));
		
		
		//p_north.add(bt_connect);
		add(p_north, BorderLayout.NORTH);
		
		add(scroll);
		
		p_south.add(t_input);
		p_south.add(bt_send);
		add(p_south, BorderLayout.SOUTH);
		
		
		setSize(300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		connect();
		
		/*bt_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect(); //에코서버에 접속하기
			}
		});*/
		
		bt_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmt.sendMsg(t_input.getText()); //서버에 메시지 보내기!
				t_input.setText(""); //입력값 초기화
			}
		});
		
		t_input.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					cmt.sendMsg(t_input.getText());
					t_input.setText(""); //입력값 초기화
				}
			}
		});
	}
	
	//소켓 객체의 인스턴스를 생성하는 순간, 지정한 서버에 접속할 수 있다
	//이 부분은 연결과 입력스트림과 출력스트림두개를 서버와 연결하는 역할을 담당!
	public void connect() {
		String ip="localhost";
		int port=9999;
		
		try {
			Socket socket = new Socket(ip, port);
			//클라이언트 측에서의 실시간 메시지 청취 및 전송용 쓰레드 생성
			//cmt가 필요한 이유는? A: 
			cmt = new ClientThread(this, socket);
			cmt.start(); //스레드 시작
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//사장님채팅창에 주문올리기
	public void sendAOrder(UserMemberDTO userMemberDTO, StoreMenuDTO storeMenuDTO) {
		String userId=userMemberDTO.getId();
		String foodName=storeMenuDTO.getFoodName();
		String servings=storeMenuDTO.getServings();
		area.append(userId+"님이\n"+"상품명 : "+foodName+"\n몇인분 : "+servings+"\n을 예약했습니다");
	}

//	public static void main(String[] args) {
//		new ChatClient();
//	}

}
