package StudyRoomSystem.controller;

import java.util.ArrayList;

import StudyRoomSystem.model.dao.SeatDao;
import StudyRoomSystem.model.dto.SeatDto;

public class SController {
	//* 싱글톤
	private static SController sc = new SController();
	private SController() {};
	public static SController getInstance() {
		return sc;
	}
	
	//좌석 출력
	public ArrayList<SeatDto> printSeat(){
		return SeatDao.getInstance().printSeat();
	}
	
	//좌석 선택 
	public int selectSeat(int seatNo) {
		
		// 좌석에 누가 앉아있을 경우[-2]
		return SeatDao.getInstance().selectSeat(seatNo);
	}
	
	// 현재 로그인한 상대가 들어간 자리를 출력하는 함수 or 들어간 자리가 없을 경우 판단
	public int checkSeat(int memberUid) {
		return SeatDao.getInstance().alreadySelected(memberUid);
	}
	
	//퇴실
	public boolean outSeat(int seatNo) {
		return SeatDao.getInstance().outSeat(seatNo);
	}
	
}
