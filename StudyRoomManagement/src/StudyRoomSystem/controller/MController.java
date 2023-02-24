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
	
	//로그인한 Member 객체를 담기 위해
	private MemberDto logSeasion = null;

	public MemberDto getLogSeasion() {
		return logSeasion;
	}
	
	//회원가입
	public int signUp(String id, String pw, String confirmPw, String name, String tel) {
		if(checkId(id)) {
			if(pw.equals(confirmPw)) {
				MemberDto dto = new MemberDto(id, pw, name, tel);
				boolean result = MemberDao.getInstance().signUp(dto);
				if(result) {
					return 0;
				}else {
					return -3; //DB에러
				}
			}
			return -1; //비밀번호와 비밀번호확인 값과 맞지 않다면
		}
		return -2; //있는 아이디이면
	}
	
	//아이디 중복 검사
	public boolean checkId(String id) {
		ArrayList<MemberDto> memberDB = MemberDao.getInstance().returnMemberDB();
		
		for(MemberDto dto : memberDB) {
			if(dto.getCustomer_ID().equals(id)) { //동일한 아이디가 있을 경우
				return false;
			}
		}
		return true; //유효성검사를 통과했을 경우
	}
	
	//로그인
	public int logIn(String id, String pw) {
		ArrayList<MemberDto> memberDB = MemberDao.getInstance().returnMemberDB();
		
		for(int i = 0; i < memberDB.size(); i++) {
			if((memberDB.get(i).getCustomer_ID().equals(id))&&(memberDB.get(i).getCustomer_PW().equals(pw))){
				logSeasion = memberDB.get(i);
				return memberDB.get(i).getCustomer_UID();
			}
		}
		return -1;
	}
}
