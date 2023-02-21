package StudyRoomSystem.controller;

import java.util.ArrayList;

import StudyRoomSystem.model.MemberDao;
import StudyRoomSystem.model.MemberDto;


public class MController {
	//* 싱글톤
	private static MController mc = new MController();
	private MController() {};
	public static MController getInstance() {
		return mc;
	}
	
	private ArrayList<MemberDao> memberDB = new ArrayList<>();
	
	//회원가입
	public boolean signUp(String id, String pw, String confirmPw, String name, String tel) {
		if(pw.equals(confirmPw)) {
			MemberDto dto = new MemberDto(id, pw, name, tel);
			return  MemberDao.getInstance().signUp(dto);
		}
		return false; //비밀번호와 비밀번호확인 값과 맞지 않다면
		
	}
}
