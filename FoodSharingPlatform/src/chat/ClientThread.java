package chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class ClientThread extends Thread{
	//스트림
	Socket socket;
	
	InputStream is; //바이트 기반 스트림
	InputStreamReader reader; //문자기반 입력 스트림
	BufferedReader buffr; //버퍼처리된 문자기반 입력스트림
	
	OutputStream out; //바이트 기반 스트림
	OutputStreamWriter writer; //문자기반 출력 스트림
	BufferedWriter buffw; //버퍼처리된 문자기반 출력스트림
	ChatClient chatClient;

	//Client가 태어날 때 얘가 태어나야 한다
	public ClientThread(ChatClient chatClient, Socket socket) {
		this.chatClient=chatClient;
		this.socket=socket;
		
		try {
			//듣기용
			is=socket.getInputStream();
			reader = new InputStreamReader(is);
			buffr = new BufferedReader(reader);
			
			//말하기용
			out=socket.getOutputStream();
			writer = new OutputStreamWriter(out);
			buffw = new BufferedWriter(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	//서버로부터 전송되어 온 메시지 듣기(입력)
	public void listen() {
		String msg=null;
		try {
			msg=buffr.readLine();
			//로그 남기기
			chatClient.area.append(msg+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//서버에 메시지 전송하기(출력)
	public void sendMsg(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			listen();
		}
	}

}
