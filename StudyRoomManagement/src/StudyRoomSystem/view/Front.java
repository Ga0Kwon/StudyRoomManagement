package StudyRoomSystem.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import StudyRoomSystem.controller.MController;
import StudyRoomSystem.controller.SController;
import StudyRoomSystem.model.ConnectDao;
import StudyRoomSystem.model.MemberDao;
import StudyRoomSystem.model.MemberDto;
import StudyRoomSystem.model.SeatDto;

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
		while(true) {
			try {
				System.out.println("-------------------스터디룸-------------------");
				System.out.print("1. 회원가입 2. 로그인 : ");
				int choice = scanner.nextInt();
				
				if(choice == 1) { //1. 회원 가입
					signUp();
				}else if(choice == 2) { //2.로그인
					logIn();
				}
			}catch(InputMismatchException e) {
				System.err.println("잘못 입력하셨습니다.");
				scanner = new Scanner(System.in);
			}catch(Exception e) {
				System.err.println("프로그램 오류 관리자에게 문의하세요.");
			}
		}
	}
	
	//1. 회원 가입
	public void signUp() {
		System.out.println("-------------------회원가입-------------------");
		System.out.print("아이디 : "); String id  = scanner.next();
		System.out.print("비밀번호 : "); String pw  = scanner.next();
		System.out.print("비밀번호 확인 : "); String confirmPw  = scanner.next();
		System.out.print("이름 : "); String name  = scanner.next();
		System.out.print("전화번호 : "); String tel  = scanner.next();
		
		int result = MController.getInstance().signUp(id, pw, confirmPw, name, tel);
		
		if(result == 0) {
			System.out.println("회원가입 성공!");
		}else if(result == -1){
			System.err.println("비밀번호와 비밀번호 확인이 서로 맞지 않습니다.");
		}else if(result == -2) {
			System.err.println("존재하는 아이디입니다.");
		}else if(result == -3) {
			System.err.println("[DB 에러] 회원 가입을 하실 수 없습니다. 직원에게 문의해주세요.");
		}
	}
	
	//2. 로그인
	public void logIn() {
		System.out.println("-------------------로그인--------------------");
		System.out.print("아이디 : "); String id  = scanner.next();
		System.out.print("비밀번호 : "); String pw  = scanner.next();
		
		int result = MController.getInstance().logIn(id, pw);
		
		if(result == -1) {
			System.err.println("로그인에 실패하였습니다.");
		}else {
			System.out.println("로그인 성공!");
			System.out.println(result + "번 회원님 환영합니다.");
			printSeat(); //좌석 출력
		}
	}
	
	//2-1) 좌석 출력
	public void printSeat() {
		ArrayList<SeatDto> seatDB = SController.getInstance().printSeat();
		System.out.println("-------------------좌석현황-------------------");
		for(int i = 0; i < seatDB.size(); i++) {
			if(seatDB.get(i).getStatus() == 0) { //사용가능이면
				System.out.println("----\n"
						+ "|\t\t|\n"
						+ "|\t/|"
						+ "|/\t|"
						+ "_\t\t_");
			}else {
				String name = MemberDao.getInstance().findName(seatDB.get(i).getCustomer_UID());
				
				System.out.println("----\n"
						+ "|"+seatDB.get(i).getCustomer_UID()+"|\n"
						+ "|"+ name +"|"
						+ "|/\t|"
						+ "_\t\t_");
			}
		}
	}
	
}
