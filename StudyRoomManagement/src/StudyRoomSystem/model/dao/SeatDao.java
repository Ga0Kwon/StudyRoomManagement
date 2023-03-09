package StudyRoomSystem.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import StudyRoomSystem.controller.MController;
import StudyRoomSystem.model.dto.SeatDto;

public class SeatDao extends ConnectDao{
	
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
		
		String sql = "select * from seat";
		
		try {
			
			ps = con.prepareStatement(sql);
			
			rs =  ps.executeQuery();
			
			while(rs.next()) {
				SeatDto dto = new SeatDto(
						rs.getInt(1), 
						rs.getInt(2), 
						rs.getInt(3), 
						rs.getInt(4));
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
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, 1);
			ps.setInt(2, loginedMemberUid);
			ps.setInt(3, seatNo);
			
			ps.executeUpdate();
			
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
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, seatNo);
			
			rs =  ps.executeQuery();
			
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
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, memberUid);
			
			rs = ps.executeQuery();
			
			if(!rs.next()){ //들어간 자리가 없을 경우
				return 0;
			}
			
			return rs.getInt(1); // 들어간 자리가 있을 경우
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}
	//퇴실
	public boolean outSeat(int seatNo) {
		String sql = "update Seat set Status = 0, customer_uid = -1 where customer_uid = ? and seat_uid = ?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, MController.getInstance().getLogSeasion().getCustomer_UID());
			ps.setInt(2, seatNo);
			
			ps.executeUpdate();
			
			return true;
			
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//기존 자리 비우기 
	public boolean moveSeat() {
		
		//어차피 한명당 한 자리밖에 못들어가기 때문에 조건문에 customer_uid 써도 된다.
		String sql = "update Seat set Status = 0, customer_uid = -1 where customer_uid = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, MController.getInstance().getLogSeasion().getCustomer_UID());
			
			ps.executeUpdate();
			
			return true;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
}
