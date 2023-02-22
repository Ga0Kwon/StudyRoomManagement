package StudyRoomSystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeatDao {
	
	private static Connection conn = ConnectDao.getInstance().returnConnectDao();
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//*싱글톤
	private static SeatDao seatDao = new SeatDao();
	//생성자
	private SeatDao() {}
	//객체 반환 함수
	public static SeatDao getInstance() {
		return seatDao;
	}
	
	//좌석 출력
	public ArrayList<SeatDto> printSeat(){
		ArrayList<SeatDto> seatDB = new ArrayList<>();
		
		String sql = "select * from Seat";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			System.out.println(rs.next());
			
			while(rs.next()) {
				SeatDto dto = new SeatDto(
						rs.getInt(1), 
						rs.getInt(2), 
						rs.getInt(3), 
						rs.getInt(4));
				
				System.out.println(rs.getInt(1));
				seatDB.add(dto);
			}
			return seatDB; //좌석 테이블 내용 전부 리턴
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
