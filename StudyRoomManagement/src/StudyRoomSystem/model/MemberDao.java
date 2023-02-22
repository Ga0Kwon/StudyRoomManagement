package StudyRoomSystem.model;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDao {
	
	private static Connection conn = ConnectDao.getInstance().returnConnectDao();
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	//싱글톤
	private static MemberDao dao = new MemberDao();
	
	public MemberDao() {}
	
	public static MemberDao getInstance() {
		return dao;
	}
	
	//회원 가입
	public boolean signUp(MemberDto dto) {
		String sql = " " + "INSERT INTO customer (customer_UID, customer_ID, customer_PW, customer_NAME, customer_TEL) VALUES (Customer_Seq.NEXTVAL, ? , ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getCustomer_ID());
			pstmt.setString(2, dto.getCustomer_PW());
			pstmt.setString(3, dto.getCustomer_NAME());
			pstmt.setString(4, dto.getCustomer_TEL());
			
			pstmt.executeUpdate();
			
			return true;
		}catch(SQLException e) {
			System.err.println("DB에 해당 정보를 넣을 수 없습니다. " + e.getMessage());
			return false;
		}
	}
	
	// * 회원 정보를 모두 받아오는 함수 + 로그인
	public ArrayList<MemberDto> returnMemberDB(){
		ArrayList<MemberDto> memberDB = new ArrayList<>();
		
		String sql = "select * from customer";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs =  pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDto dto = new MemberDto(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5));
				
				memberDB.add(dto);
			}
			
			return memberDB;
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	//멤버 고유번호로 해당 고객의 이름 받아오기
	public String findName(int uid) {
		ArrayList<MemberDto> memberList = returnMemberDB();
		
		for(MemberDto dto : memberList) {
			if(dto.getCustomer_UID() == uid) {
				return dto.getCustomer_NAME();
			}
		}
		return null; //해당 멤버 번호로 찾지 못할 경우
	}
	
}
