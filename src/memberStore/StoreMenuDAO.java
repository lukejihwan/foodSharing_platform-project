package memberStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class StoreMenuDAO {
//	StoreMenuDTO storeMenuDTO;
	
		DBManager dbManager=DBManager.getInstance();
	
	public int insert(StoreMenuDTO storeMenuDTO){ //이거는 멤버에 올릴필요없이 {}안에서 다 사용하면 될듯
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		con=dbManager.getConnection();
		
		String sql="insert into storemenu (storemember_idx, storemenu_idx, foodname, servings, deadline, memo, filename)";
		sql+=" values (?, seq_storemenu.nextval, ?,?,?,?,?)"; //내 시퀀스 값은 제일 앞에 두는게 좋을 듯..
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, storeMenuDTO.getStoreMemberDTO().getStoreMember_idx()); //여기를 채워줘야함
			pstmt.setString(2, storeMenuDTO.getFoodName());
			pstmt.setString(3, storeMenuDTO.getServings());
			pstmt.setString(4, storeMenuDTO.getDeadLine());
			pstmt.setString(5, storeMenuDTO.getMemo());
			pstmt.setString(6, storeMenuDTO.getFilename());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt);
		}
		return result;
	}
	
	public List selectAll() {
		ArrayList<StoreMenuDTO> list=new ArrayList<StoreMenuDTO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		con=dbManager.getConnection();
		
		String sql="SELECT STORENAME, ADDRESS, FOODCATEGORY, STOREMENU_IDX, m.STOREMEMBER_IDX, FOODNAME, SERVINGS, DEADLINE, MEMO, FILENAME";
		sql+=" FROM storemember s right OUTER JOIN storemenu m";
		sql+=" on s.STOREMEMBER_IDX =m.STOREMEMBER_IDX";
		
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				StoreMemberDTO storeMemberDTO=new StoreMemberDTO();
				StoreMenuDTO storeMenuDTO=new StoreMenuDTO();
				storeMenuDTO.setStoreMemberDTO(storeMemberDTO);
				
				storeMemberDTO.setStoreName(rs.getString("storename"));
				storeMemberDTO.setAddress(rs.getString("address"));
				storeMemberDTO.setFoodCategory(rs.getString("foodcategory"));
				storeMenuDTO.setStoreMenu_idx(rs.getInt("storemenu_idx"));
				storeMemberDTO.setStoreMember_idx(rs.getInt("storemember_idx")); //이 부분 맞나 확인
				storeMenuDTO.setFoodName(rs.getString("foodname"));
				storeMenuDTO.setServings(rs.getString("servings"));
				storeMenuDTO.setDeadLine(rs.getString("deadline"));
				storeMenuDTO.setMemo(rs.getString("memo"));
				storeMenuDTO.setFilename(rs.getString("filename"));
				
				list.add(storeMenuDTO);
			}
			System.out.println("나눔음식 수는 "+list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		
		return list;
	}
	
}






