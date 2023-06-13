package chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//접속한 클라이언트마다 1:1 대응하여 대화를 주고받는 스레드
//이 스레드는 대화를 주고 받기 위해서 Stream이 필요하고,
//Stream을 얻기 위해서는 Socket이 필요하다!
//이 스레드의 역할 및 방식 정리및 이해!!
public class ServerThread extends Thread{
	//서버를 두개 띄우면 포트번호가 이미 사용하고 있기 때문에 충돌이 난다
	Socket socket;
	BufferedReader buffr; //이 스트림들이 끊기는 순간은 사용자가 화면을 닫는 순간이라는 것을 유의해야한다
	BufferedWriter buffw; //이 부분 정리 및 이해!!
	//inputStream같은 것을 멤버로 안올리는 이유는?
	boolean flag=true; //이 쓰레드의 생사 여부를 결정한다
	ChatServer chatServer; //서버에 로그 남기기 위해(area사용)
	
	public ServerThread( Socket socket, ChatServer chatServer) {
		this.chatServer=chatServer;
		this.socket=socket;
		
		try {
			buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//듣기
	public void listen() {
		String msg=null;
		try {
			msg=buffr.readLine(); //json문자열로 받아짐, 따라서 분석하려면 파싱할 필요가 있음
			
			
			//sendMsg(msg);//서버에서 들었으면 다시 Client에게 돌려줌
			
			//멀티캐스팅을 실현하는 부분! 중요
			//broadCasting : 다수의 사용자에 동시에 메시지를 보낸 것
			for(int i=0; i<chatServer.vec.size(); i++) {
				ServerThread smt=chatServer.vec.get(i); //벡터에서 한 요소를 꺼낸다
				smt.sendMsg(msg);//vector를 사용해서실현하자
				//이 부분 다시 정리 및 이해하기!
			}
			
			//로그 남기기
			chatServer.area.append(msg+"\n");
			
		} catch (IOException e) { //전혀 다른 사람이 접근을 해야만 한다는데, 왜 그렇지?
			//사용자가 대화 중단하고 나갔으므로, 추후 명단에서 제거할 것이고
			//현재 스레드는 소멸시켜야 한다!
			flag=false; //이 부분 정리 및 이해하기! 왜 위치가 여기일까?
			chatServer.removeClient(this);
			//chatServer.area.append("현재 참여중인 회원 수 : "+chatServer.vec.size()+"\n");
			//e.printStackTrace();
		
		}
		
	}
	
	//말하기
	public void sendMsg(String msg) {
		try {
			buffw.write(msg+"\n"); //버퍼처리되었기 때문에 한 줄의 끝임을 알려주어야 됨
			buffw.flush(); //flush의 필요성과 존재이유 정리!
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//죽지않고, 지속적으로 듣고 말하고를 반복한다
	//단, 사용자가 접속을 끊고 나가면 이 쓰레드는 죽어야 한다. 따라서 while()문 실행여부를
	//결정짓는 논리값이 필요하다
	public void run() {
		while(flag) {
			listen(); //듣는 것만 
		}
	}
}










