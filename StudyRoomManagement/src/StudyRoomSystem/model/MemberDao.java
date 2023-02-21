package StudyRoomSystem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao {
	
	private static Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//싱그톤
	private static MemberDao dao = new MemberDao();
	
	public MemberDao() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.219.113:1521/orcl",
					"java", //사용자
					"oracle" //비밀번호
					);
			
			
		}catch(ClassNotFoundException e) {
			System.out.println("찾을 수 없음 " + e.getMessage());
		}
		catch(SQLException e) {
			System.out.println("DB연동 실패 : " + e.getMessage());
		}
	}
	
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
}
