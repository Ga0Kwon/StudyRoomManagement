package StudyRoomSystem.controller;

import java.util.ArrayList;

import StudyRoomSystem.model.SeatDao;
import StudyRoomSystem.model.SeatDto;

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
	
}
