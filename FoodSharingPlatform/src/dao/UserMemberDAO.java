package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import memberStore.StoreMemberDTO;
import memberStore.StoreMenuDTO;
import users.UserMemberDTO;
import util.DBManager;

public class UserMemberDAO {
	DBManager dbManager=DBManager.getInstance();
	
	
	public int selectid(String id) {
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;	
		con=dbManager.getConnection();
		
		String sql="select * from usermember where id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=1; //이런식으로 해주어도 되나? 이것또한 로직인가?
			}else {
				result=2; //이런식으로 해주어도 되나? 이것또한 로직인가?
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		return result;
	}
	
	public int insert(UserMemberDTO userMemberDTO) {
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		con=dbManager.getConnection();
		
		String sql="insert into usermember (usermember_idx, name, id, pass, pass2, birth, cardnum, phonenum)";
		sql+=" values (seq_usermember.nextval, ?,?,?,?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userMemberDTO.getName());
			pstmt.setString(2, userMemberDTO.getId());
			pstmt.setString(3, userMemberDTO.getPass());
			pstmt.setString(4, userMemberDTO.getPass2());
			
			System.out.println("회원가입하는 사람의 생일은 "+userMemberDTO.getBirth());
			
			pstmt.setString(5, userMemberDTO.getBirth());
			pstmt.setString(6, userMemberDTO.getCardNum());
			pstmt.setString(7, userMemberDTO.getPhoneNum());
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt);
		}
		return result;
	}
	
	public UserMemberDTO login(String id, String pass) {
		UserMemberDTO userMemberDTO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		con=dbManager.getConnection();
		
		String sql="select * from usermember where id=? and pass=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				userMemberDTO=new UserMemberDTO();
				userMemberDTO.setName(rs.getString("name"));
				userMemberDTO.setId(rs.getString("id"));
				userMemberDTO.setPhoneNum(rs.getString("phonenum"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		
		return userMemberDTO;
	}
	
	public List<StoreMenuDTO> search(String column, String content) {
		StringBuilder sb=new StringBuilder();
		ArrayList<StoreMenuDTO> list=new ArrayList<StoreMenuDTO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		con=dbManager.getConnection();
		
		System.out.println("검색어는 : "+content);
		
		sb.append("SELECT ID,PASS,STORENAME,ADDRESS,FOODCATEGORY, STOREMENU_IDX, m.STOREMEMBER_IDX, FOODNAME, SERVINGS, DEADLINE, MEMO, FILENAME");
		sb.append(" FROM storemember s LEFT outer JOIN storemenu m");
		sb.append(" on s.STOREMEMBER_IDX =m.STOREMEMBER_IDX");
		sb.append(" WHERE "+column+" LIKE '%"+content+"%'");
		String sql=sb.toString();
		System.out.println(sql);
		try {
			pstmt=con.prepareStatement(sql);
			//pstmt.setString(1, column); where뒤에 바인드변수 못쓰나??
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				StoreMemberDTO storeMemberDTO=new StoreMemberDTO();
				StoreMenuDTO storeMenuDTO=new StoreMenuDTO();
				storeMenuDTO.setStoreMemberDTO(storeMemberDTO);
				
				storeMemberDTO.setId(rs.getString("id"));
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
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		return list;
	}
	
	
	
}









