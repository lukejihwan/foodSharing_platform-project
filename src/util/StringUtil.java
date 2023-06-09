package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
	
	//넘겨받은 숫자가 1자리수 이면, 앞에 0붙이기
	//누군가가 이 메서드를 호출하면, 처리결과를 반환받는다
	public static String getNumString(int num) {
		String str=null;
		if(num<10) { //한자리수
			str="0"+num;
		}else { //두자리수
			str=Integer.toString(num); //Wrapper적용 이 부분 복습하고 익히기
		}
		return str;
	}
	
	//확장자 추출하여 반환받기
	public static String getExtend(String filename) {
		int lastIndex=filename.lastIndexOf(".");
		System.out.println(lastIndex);
		return filename.substring(lastIndex+1, filename.length());
		//시작위치는 포함되고, 끝위치는 비포함 이기 때문에, 위의 규칙이 적용됨!
	}
	
	//비밀번호 암호화
	//자바의 보안과 관련된 기능을 지원하는 api모여 있는 패키지가
	//java.security 이다
	public static String getConvertedPassword(String pass) {
		//암호화 객체
		StringBuffer hexString=null;
		
		try {
			hexString=new StringBuffer(); //String과 StringBuffer는 다름. 
			MessageDigest digest=MessageDigest.getInstance("SHA-256");
			byte[] hash=digest.digest(pass.getBytes("UTF-8"));//String클래스를 byte로 변환해야 함! 반환형은 byte배열형
			
			//String은 불변이다! 따라서 그 값이 변경될 수 없다
			//따라서 String 객체는 반복문 횟수가 클때는 절대
			//누적식을 사용해서는 안된다!
			//해결책) 변경가능한 문자열 객체를 지원하는 StringBuffer
			//StringBuilder 등을 활용하자!
			
			
			for(int i=0; i<hash.length; i++) { //16진수로 변환할 것임(많이 쓰임)
				String hex=Integer.toHexString(0xff& hash[i]); //16진수로 바꾸는 중...
				//System.out.println(hex);
				if(hex.length()==1) {
					hexString.append("0"); //64자가 나와야 되는데 어떤것은 63자가 나온다 이럴때 그 한자리수에 0을 붙여야한다
				}
				hexString.append(hex); //16진수를 하나의 64자로 모아줌. 굉장히 중요!!! 메모리 절약가능!
			}
			//System.out.println(hexString.toString());
			//System.out.println(hexString.length());
			
		} catch (NoSuchAlgorithmException e) {//그런알고리즘 없으면 어떡할꺼야? 라는 의미
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return hexString.toString();
	}
	
	
	
	/*
	public static void main(String[] args) {
		String result=getConvertedPassword("minzino");
		System.out.println(result);
		System.out.println(result.length());
	}
	*/
	
}
