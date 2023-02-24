package StudyRoomSystem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDao {
	
	private static Connection conn;
	
	//싱글톤
	private static ConnectDao dao = new ConnectDao();
		
	
	public static ConnectDao getInstance() {
		return dao;
	}
	
		
	public ConnectDao() {}


	public Connection returnConnectDao() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.219.113:1521/orcl",
					"java", //사용자2
					"oracle" //비밀번호
					);
//			System.out.println("연결 성공!");
			return conn;
			
		}catch(ClassNotFoundException e) {
			System.out.println("찾을 수 없음 " + e.getMessage());
			return null;
		}
		catch(SQLException e) {
			System.out.println("DB연동 실패 : " + e.getMessage());
			return null;
		}
	}
}
