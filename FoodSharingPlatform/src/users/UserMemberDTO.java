package users;

public class UserMemberDTO {
	private int usermember_idx;
	private String name;
	private String id;
	private String pass;
	private String pass2; //존재이유는?
	private String birth; //이부분은 콤보박스값을 넣을 것이므로 주의..
	private String cardNum;
	private String phoneNum; //이 부분도 콤보박스값을 넣을 것이므로 주의..
	public int getUsermember_idx() {
		return usermember_idx;
	}
	public void setUsermember_idx(int usermember_idx) {
		this.usermember_idx = usermember_idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPass2() {
		return pass2;
	}
	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
}
