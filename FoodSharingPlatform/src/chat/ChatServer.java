package chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//서버소켓을 정의하자
public class ChatServer extends JFrame{
	JPanel p_north;
	JTextField t_port;
	JButton bt_start; //접속버튼
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket server;
	int port=9999;
	Thread serverThread; //접속자 대기를 위한 전용 쓰레드
	//메인쓰레드가 대기상태에 빠지지 않게 안전하게 실행하기 위함
	
	//접속한 클라이언트 측 메시지 전용쓰레드를 모아놓을 컬렉션
	Vector<ServerThread> vec=new Vector<ServerThread>();
	
	public ChatServer() {
		p_north=new JPanel();
		t_port=new JTextField(Integer.toString(port),9);
		bt_start=new JButton("서버가동");
		area=new JTextArea();
		scroll=new JScrollPane(area);
		
		p_north.add(t_port);
		p_north.add(bt_start);
		add(p_north, BorderLayout.NORTH);
		
		add(scroll);
		
		setSize(400, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		serverThread=new Thread() {
			public void run() {
				createServer();
			}
		};
		
		bt_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverThread.start();
				bt_start.setEnabled(false);
			}
		});
	}
	
	public void createServer(){
		// 서버를 가동한다는 것은 ServerSocket을 생성하고, accept() 동작시키는 것임
		try {
			server = new ServerSocket(port); // 서버 생성
			area.append("서버 가동\n");
			while (true) {
				Socket socket = server.accept(); // 클라이 언트가 접속할 때까지 대기 상태에 빠짐, 메인쓰레드가 동작을 못하면 안되니 메인쓰레드 말고 쓰레드를 따로 만들어줌
				// 위의 socket은 {}안에서 사라지니 따로 보관해 주어야 함
				// Ex) 10명이 들어오면 10개의 Thread가 생성되도록 함.
				// 소켓은 곧 소멸되므로, 소멸되기 전에 특정 클래스의 인스턴스를 생성하여, 그 안에
				// 보관해두자, 특히 이 인스턴스는 각각 독립적으로 실행되기 위해 쓰레드로 정의한다!!
				String ip = socket.getInetAddress().getHostAddress();
				area.append(ip + "접속자님 발견\n");

				ServerThread smt = new ServerThread(socket, this);
				smt.start(); // 쓰레드 시작!

				vec.add(smt);// 출생명부에 기록!!
				area.append("현재" + vec.size() + "명이 채팅에 참여중\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//채팅 참여자가 나갈 경우, 서버측 메시지 전용 스레드 또한 소멸시켜야 하므로,
	//별도의 메서드로 처리해 주자!
	public void removeClient(ServerThread smt) {
		//누구를 죽일지 매개변수로 받을 거임
		//넘겨받은 smt를 벡터에서 제거하자!!
		vec.remove(smt);
		area.append("현재 채팅 참여자 수는"+vec.size()+"\n");
	}

	
	public static void main(String[] args) {
		new ChatServer();
	}

}
