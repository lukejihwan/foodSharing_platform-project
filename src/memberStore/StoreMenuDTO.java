package memberStore;

public class StoreMenuDTO {
	private StoreMemberDTO storeMemberDTO;
	private int storeMenu_idx;
	private String foodName;
	private String servings;
	private String deadLine;
	private String memo;
	private String filename;

	public StoreMemberDTO getStoreMemberDTO() {
		return storeMemberDTO;
	}
	public void setStoreMemberDTO(StoreMemberDTO storeMemberDTO) {
		this.storeMemberDTO = storeMemberDTO;
	}
	public int getStoreMenu_idx() {
		return storeMenu_idx;
	}
	public void setStoreMenu_idx(int storeMenu_idx) {
		this.storeMenu_idx = storeMenu_idx;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getServings() {
		return servings;
	}
	public void setServings(String servings) {
		this.servings = servings;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
