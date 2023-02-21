package StudyRoomSystem.view;

import java.util.Scanner;

import StudyRoomSystem.controller.MController;

public class Front {
	private Scanner scanner = new Scanner(System.in);
	
	//*싱글톤
	private static Front front = new Front();
	//생성자
	private Front() {}
	//객체 반환 함수
	public static Front getInstance() {
		return front;
	}
	
	//처음보이는 페이지
	public void index() {
		System.out.println("-------------------스터디룸-------------------");
		System.out.print("1. 회원가입 2. 자리예약 : ");
		int choice = scanner.nextInt();
		
		if(choice == 1) {
			signUp();
		}else if(choice == 2) {
			
		}
	}
	
	//회원 가입
	public void signUp() {
		System.out.println("-------------------회원가입-------------------");
		System.out.print("아이디 : "); String id  = scanner.next();
		System.out.print("비밀번호 : "); String pw  = scanner.next();
		System.out.print("비밀번호 확인 : "); String confirmPw  = scanner.next();
		System.out.print("이름 : "); String name  = scanner.next();
		System.out.print("전화번호 : "); String tel  = scanner.next();
		
		boolean result = MController.getInstance().signUp(id, pw, confirmPw, name, tel);
		
		if(result) {
			System.out.println("회원가입 성공!");
		}else {
			System.err.println("비밀번호가 맞지 않거나 DB에서 문제가 생겼습니다.");
		}
	}
	
}
