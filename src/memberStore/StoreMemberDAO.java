package memberStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class StoreMemberDAO {
	DBManager dbManager=DBManager.getInstance();
	Connection con=null;
	
	public int insert(StoreMemberDTO storeMemberDTO) {
		int result=0;
		PreparedStatement pstmt=null;
		
		con=dbManager.getConnection();
		
		//콤보박스에 있는 값 어떻게 가져올 건지 고민하기!!
		String sql="insert into storemember (storemember_idx, businessnumber, id, pass, storename, address, foodcategory)";
		sql+=" values (seq_storemember.nextval, ?,?,?,?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, storeMemberDTO.getBusinessNumber());
			pstmt.setString(2, storeMemberDTO.getId());
			pstmt.setString(3, storeMemberDTO.getPass());
			pstmt.setString(4, storeMemberDTO.getStoreName());
			pstmt.setString(5, storeMemberDTO.getAddress());
			pstmt.setString(6, storeMemberDTO.getFoodCategory());
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt);
		}
		return result;
	}
	
	//반환형을.. 한개의 자료가 나오니, DTO형을 반환하는게 나중에 쓸데 더 나을 것 같다 
	//LoginPage에서 로그인 성공했을 때 해당 사업자의 정보가 StoreMemberDTO에 담겨있어,
	//StoreMenuDTO와 같이 엮어서 오른쪽 영역에 패널 정보에 표시 할 수 있게 되어있다
	public StoreMemberDTO login(String id, String pass) {
		StoreMemberDTO storeMemberDTO=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		con=dbManager.getConnection();
		
		String sql="select * from storemember where id=? and pass=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs=pstmt.executeQuery();
			
			//여기서 한가지 의문..아이디가 같으면 결과가 두개가 나오는데, 그러면 자신의 정보가
			//아닌 다른사람의 아이디로 로그인 될 수 있다... 이것을 방지 하는 것을 만들어야 하는데..
			if(rs.next()) { //논리적으로 생각해봐라..값이 있을때만 DTO를 생성하는게 맞지..
				storeMemberDTO = new StoreMemberDTO();
				storeMemberDTO.setStoreMember_idx(rs.getInt("storemember_idx"));
				storeMemberDTO.setId(rs.getString("id"));
				storeMemberDTO.setPass(rs.getString("pass"));
				storeMemberDTO.setStoreName(rs.getString("storename"));
				storeMemberDTO.setAddress(rs.getString("address"));
				storeMemberDTO.setFoodCategory(rs.getString("foodcategory"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		return storeMemberDTO;
	}
	
	//아이디 중복생성을 방지하는 CRUD
	public List idCheck(String id) { 
		ArrayList<StoreMemberDTO> list=new ArrayList<StoreMemberDTO>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		con=dbManager.getConnection();
		
		String sql="select * from storemember where id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			//여기서 한가지 의문..아이디가 같으면 결과가 두개가 나오는데, 그러면 자신의 정보가
			//아닌 다른사람의 아이디로 로그인 될 수 있다... 이것을 방지 하는 것을 만들어야 하는데..
			while(rs.next()) { //논리적으로 생각해봐라..값이 있을때만 DTO를 생성하는게 맞지..
				StoreMemberDTO storeMemberDTO = new StoreMemberDTO();
				list.add(storeMemberDTO);
			}
			System.out.println("이미"+list.size()+"개의 아이디가 존재함");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt, rs);
		}
		return list;
	}
	
	
	
}













