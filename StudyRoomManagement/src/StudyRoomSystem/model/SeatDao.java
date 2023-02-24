package StudyRoomSystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import StudyRoomSystem.controller.MController;

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
			
			rs =  pstmt.executeQuery();
			
//			System.out.println(rs.next());
			
			while(rs.next()) {
				SeatDto dto = new SeatDto(
						rs.getInt(1), 
						rs.getInt(2), 
						rs.getInt(3), 
						rs.getInt(4));
				
//				System.out.println(rs.getInt(1));
				seatDB.add(dto);
			}
			
			return seatDB; //좌석 테이블 내용 전부 리턴
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	//좌석 선택
	public int selectSeat(int seatNo) {
		String sql = "update Seat set Status = ?, customer_UID = ? where Seat_UID = ?";
		//현재 로그인한 멤버 uid
		int loginedMemberUid = MController.getInstance().getLogSeasion().getCustomer_UID();
		
		//선택한 자리가 현재 이용가능한 자리인지 체크
		int result1 = whoSelected(seatNo);
		
		
		if(result1 != 0) {
			return result1;
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 1);
			pstmt.setInt(2, loginedMemberUid);
			pstmt.setInt(3, seatNo);
			
			pstmt.executeUpdate();
			
			return 0;
			
		}catch(SQLException e) {
			
			System.out.println(e.getMessage());
			return -1;
		}
	}
	/* 좌석 유효성 검사 */
	
	//1. 이미 누가 선택한 좌석인지
	public int whoSelected(int seatNo) {
		String sql = "select * from Seat where Seat_UID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, seatNo);
			
			rs =  pstmt.executeQuery();
			
			rs.next();
			
			//status가 3번째 컬럼
			if (rs.getInt(3) != 0){
				return -2; //현재 자리가 사용중일 경우
			}
			
			return 0;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}
	
	//2. 사용자가 이미 들어간 자리가 있는지
	public int alreadySelected(int memberUid) {
		String sql = "select * from Seat where customer_UID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberUid);
			
			rs = pstmt.executeQuery();
			
			if(!rs.next()){ //들어간 자리가 없을 경우
				return 0;
			}
			
			return rs.getInt(1); // 들어간 자리가 있을 경우
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}
}
