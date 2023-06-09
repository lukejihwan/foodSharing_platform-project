package memberStore;

public class StoreMemberDTO {
	private int storeMember_idx;
	private int businessNumber;
	private String id;
	private String pass;
	private String storeName;
	private String address;
	private String foodCategory;
	
	public int getStoreMember_idx() {
		return storeMember_idx;
	}
	public void setStoreMember_idx(int storeMember_idx) {
		this.storeMember_idx = storeMember_idx;
	}
	public int getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(int businessNumber) {
		this.businessNumber = businessNumber;
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
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFoodCategory() {
		return foodCategory;
	}
	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}
	
}
