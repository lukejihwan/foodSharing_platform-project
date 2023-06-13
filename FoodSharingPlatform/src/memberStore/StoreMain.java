package memberStore;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dao.StoreMemberDAO;
import dao.StoreMenuDAO;
import util.DBManager;

public class StoreMain extends JFrame{
	JPanel container;
	Page[] pages=new Page[3]; //페이지들을 담아줄 배열
	
	public final static int LOGINPAGE=0;
	public final static int JOINPAGE=1;
	public final static int REGISTPAGE=2;
	
	public static final Color col1=new Color(151,142,113);
	public static final Color col2=new Color(254,213,107);
	public static final Color col3=new Color(216,177,94);
	public static final Color col4=new Color(234,156,94);
	public static final Color col5=new Color(234,118,103);

	DBManager dbManager=DBManager.getInstance();
	StoreMemberDAO storeMemberDAO=new StoreMemberDAO();//모두 같은 DAO를 사용하도록 하기위함
	StoreMenuDAO storeMenuDAO=new StoreMenuDAO();//모두 같은 DAO를 사용하도록 하기위함
	
	public StoreMain() {
		container=new JPanel();
		
		pages[0]=new LoginPage(this);
		pages[1]=new JoinPage(this);
		pages[2]=new RegistPage(this);
		
		add(container);
		//페이지 부착
		for(int i=0; i<pages.length; i++) {
			container.add(pages[i]);
		}
		
		//최초실행시 로그인 페이지 출력
		showHide(LOGINPAGE);
		
		setTitle("MemberStore");
		setSize(1000, 700);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dbManager.release(dbManager.getConnection()); //이런식으로 해주어도 되나?
				//DAO가 두개 존재하는데, 두군데 다 멤버변수로 con을 빼놓고 여기서 release(con)해야하나?
				System.out.println("안전하게 DB연결끊음");
				System.exit(0);
			}
		});
	}
	
	public void showHide(int n) {
		for(int i=0; i<pages.length; i++) {
			if(n==i) {
				pages[i].setVisible(true);
			}else {
				pages[i].setVisible(false);
			}
		}
	}
	
	public static void main(String[] args) {
		new StoreMain();
	}

}
