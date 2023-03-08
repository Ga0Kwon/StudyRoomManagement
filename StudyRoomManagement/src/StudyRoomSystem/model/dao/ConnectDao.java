package StudyRoomSystem.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDao {
	
	//1. JDBC 인터페이스 3개
	public Connection con; // DB 연동 인터페이스
	public PreparedStatement ps; // SQL 조작 인터페이스
	public ResultSet rs; // SQL 결과 조작 인터페이스
		

	public ConnectDao() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.219.113:1521/orcl",
					"java", //사용자2
					"oracle" //비밀번호
					);
			System.out.println("[연동 성공] ");
		}catch (Exception e) {
			System.err.println("[연동 실패] " + e.getMessage());
		}
	}
}
