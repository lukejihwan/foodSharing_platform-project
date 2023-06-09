package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



//오라클과 mysql 둘이 처리하는 DB매니저 정의
public class DBManager {
	String oracle_driver="oracle.jdbc.driver.OracleDriver";
	String oracle_url="jdbc:oracle:thin:@localhost:1521:XE";
	String oracle_user="javase";
	String oracle_pass="1234";
	
	String mysql_driver="com.mysql.jdbc.Driver";//?useSSL=false&
	String mysql_url="jdbc:mysql://localhost:3306/javase?useSSL=false&characterEncoding=utf-8";//:3306/javase?characterEncoding=utf-8";
	//url부분에 발생했던 SSL 방식 문제는 SSL=false를 줌으로써 해결 됨. 그러나 이 방식은 안전하지 않으니 나중에 오픈소스 openSSL 인증서를
	//그리고 javase와 java같은 user에게 권한을 주지 않으면 access denied와 같은 오류가 발생하니 권한을 꼭 주자.
	//발급받는 것이 안전함
	//mysql_url부분 이해하고 외우기!
	String mysql_user="javase";
	String mysql_pass="1234";
	
	private static DBManager instance; //null
	private Connection connection;
	
	private DBManager () { //얘의 호출되는 시점은?
		connectOracle(); //두 메서드 중 하나 선택해서 실행하면돼
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	//오라클 접속
	public void connectOracle() {
		try {
			Class.forName(oracle_driver);
			
			connection=DriverManager.getConnection(oracle_url, oracle_user, oracle_pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//mysql 접속
	public void connectMysql() {
		try {
			Class.forName(mysql_driver);
			System.out.println("드라이버 검색성공!");
			connection=DriverManager.getConnection(mysql_url, mysql_user, mysql_pass);
			System.out.println("접속성공"+connection);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 검색 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		}
	}
	
	public static DBManager getInstance() {
		if(instance==null) {
			instance = new DBManager();
		}
	return instance;
	}
	
	public void release(Connection connection) {
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void release(PreparedStatement pstmt) {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void release(PreparedStatement pstmt, ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
