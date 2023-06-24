package users;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.StoreMemberDAO;
import dao.StoreMenuDAO;
import dao.UserMemberDAO;


public class UserMain extends JFrame{
	JPanel p_west;
	
	public static final Color COLOR1=new Color(250,162,142, 98);
	public static final Color COLOR2=new Color(227,175,129,89);
	public static final Color COLOR3=new Color(250,222,154,98);
	public static final Color COLOR4=new Color(227,217,129,89);
	public static final Color COLOR5=new Color(221,255,153,100);
	
	public static final int LOGINPAGE=0;
	public static final int JOINPAGE=1;
	public static final int RESERVEPAGE=2;
	Page[] pages=new Page[3];
	boolean flag;
	
	//코드 짜면서 멤버변수선언이 필요한 곳
	UserMemberDAO userMemberDAO=new UserMemberDAO(); //어차피 이렇게 멤버로 선언해주어도 크게 상관없을 듯
	StoreMemberDAO storeMemberDAO=new StoreMemberDAO(); //storeMain이 가지고 있는 StoreMemberDAO와는 다른 주소값인 것에 주의...
	StoreMenuDAO storeMenuDAO=new StoreMenuDAO(); //storeMain이 가지고 있는 StoreMenuDAO와는 다른 주소값인 것에 주의... 
	//Q: 두개의 주소값이 같을 필요 없지 않은가?
	
	public UserMain() {
		super("유저화면");
		JMenu m_reserve=new JMenu("예약페이지");
		JMenu m_introduce=new JMenu("소개글");
		JMenu m_inquiry=new JMenu("채팅문의");
		JMenu m_thank=new JMenu("감사글");
		JMenu m_logOut=new JMenu("로그아웃");
		
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
		
		pages[0]=new LoginPage(this);
		pages[1]=new JoinPage(this);
		pages[2]=new ReservePage(this);
		
		p_west.setBackground(COLOR3);
		c.setLayout(null); //AbsoluteLayout을 사용하려면 Container를 null로 설정해주어야함
		p_west.setBounds(0,0,500,700);
		add(p_west);
		
		pages[0].setBounds(500, 0, 500, 700);
		add(pages[0]);
		pages[1].setBounds(500, 0, 500, 700);
		add(pages[1]);
		pages[2].setBounds(0, 0, 1000, 700);
		add(pages[2]);
		System.out.println("유저화면 시작완료");
		showHide(LOGINPAGE);
		
		setSize(1000, 700);
		setVisible(true);
		
		m_logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("눌림");
				Object obj=e.getSource();
				int option=JOptionPane.showConfirmDialog(UserMain.this, "로그아웃 하시겠습니까?");
				if(option==JOptionPane.OK_OPTION) {
					showHide(LOGINPAGE);
				}
			}
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
				System.exit(0);
			}
		});
	}
	
	public void showHide(int n) {
		for(int i=0; i<3;i++) {
			if(n==i) {
				pages[n].setVisible(true);
				//로그인 버튼을 눌렀을 때 예약페이지가 열리면서 메뉴바가 활성화됨
				if(n==2) {
					flag=true;
					p_west.setVisible(false);
				}
			}else {
				pages[i].setVisible(false);
			}
		}
	}
	
	public static void main(String[] args) {
		new UserMain();
	}

}
