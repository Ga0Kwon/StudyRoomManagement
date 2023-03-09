package StudyRoomSystem.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import StudyRoomSystem.controller.MController;
import StudyRoomSystem.controller.SController;
import StudyRoomSystem.model.dao.ConnectDao;
import StudyRoomSystem.model.dao.MemberDao;
import StudyRoomSystem.model.dto.MemberDto;
import StudyRoomSystem.model.dto.SeatDto;

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
				System.out.println("--------------------스터디룸--------------------");
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
				System.out.println(e.getMessage());
				System.err.println("프로그램 오류 관리자에게 문의하세요.");
			}
		}
	}
	
	//1. 회원 가입
	public void signUp() {
		System.out.println("--------------------회원가입--------------------");
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
		System.out.println("--------------------로그인---------------------");
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
		try {
			while(true) {
				ArrayList<SeatDto> seatDB = SController.getInstance().printSeat();
				System.out.println("--------------------좌석현황--------------------");
				int seaction = 1;
				for(int i = 0; i < seatDB.size(); i++) {
					if(seatDB.get(i).getStatus() == 0) { //사용가능이면
						System.out.printf("[\t좌석:%d\t]", seatDB.get(i).getSeat_UID());
						
						if(seaction%4 == 0) {
							System.out.println();
						}
						seaction++;
					}else {
						String name = MemberDao.getInstance().findName(seatDB.get(i).getCustomer_UID());
						
						System.out.printf("[   좌석:%d %s  ]", seatDB.get(i).getSeat_UID(), name);
						
						if(seaction%4 == 0) {
							System.out.println();
						}
						
						seaction++;
					}
				}
				
				System.out.println("--------------------------------------------");
				
				int checkSeat = SController.getInstance().checkSeat(MController.getInstance().getLogSeasion().getCustomer_UID());
				
				if(checkSeat == 0) {
					System.out.print("1. 1일권 결제 2. 정기권 결제 3. 로그아웃 : ");
					int choice = scanner.nextInt();
					if(choice == 1) { //1. 1일권 결제
						/*1일권 결제*/
						System.out.print("좌석 선택 : ");
						int select = scanner.nextInt();
						
						selectSeat(select); //좌석 선택 함수
						
					}else if(choice == 2) {
						/*정기권 결제*/
						System.out.print("좌석 선택 : ");
						int select = scanner.nextInt();
						selectSeat(select); //좌석 선택 함수
						
					}else if(choice == 3) {
						return;
					}
					
				}else {
					System.out.print("1. 퇴실 2. 좌석 이동 3. 외출 4. 로그아웃 ");
					int select = scanner.nextInt();
					if(select == 1) {
						int seatNo = SController.getInstance().checkSeat(MController.getInstance().getLogSeasion().getCustomer_UID());
						
						System.out.printf("[확인] %d번 자리에서 퇴실하시겠습니까?  1. Yes  2 No", seatNo);
						int ch = scanner.nextInt();
						if(ch == 1) {
							boolean result = SController.getInstance().outSeat(seatNo);
							
							if(result) {
								System.out.println("퇴실완료 되었습니다.");
							}else {
								System.out.println("퇴실실패하였습니다. 관리자에게 문의해주세요.");
							}
						}if(ch == 2) {
							return;
						}
					}else if(select == 2) { //2. 좌석 이동
						System.out.print("[좌석 선택] 몇번 자리로 이동하시겠습니까?");
						int changeSeatNo = scanner.nextInt();
						
						int result = SController.getInstance().checkSeat(changeSeatNo);
						
						if(result == -2) {
							System.err.println("사용중인 자리입니다.");
						}else if(result == 0){
							System.out.printf("%d번 자리로 이동하였습니다.\n", changeSeatNo);
						}else {
							System.err.println("[에러]관리자에게 문의해주세요.");
						}
						
					}else if(select == 3) { //3. 외출 
						
					}else if(select == 4){
						return;
					}
					
				}
			}
		}catch (InputMismatchException e){
			System.err.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		}catch (Exception e) {
			System.out.println("[에러] 관리자에게 문의하세요.");
		}
		
	}
	
	// 좌석 선택
	public void selectSeat(int selectSeatNo) {
		int result = SController.getInstance().selectSeat(selectSeatNo);
		if(result == -2 ){ // 현재 로그인한 사용자 들어간 자리가 따로 있을 경우
			System.err.println("[안내] 현재 사용중인 자리입니다.");
		}else if(result == 0) { //사용처리가 된 경우
			System.out.println("[" + selectSeatNo+ "번 좌석] 자리가 예약되었습니다. 입장가능합니다.");
		}else if(result == -1) {
			System.err.println("[DB에러]관리자에게 문의해주세요.");
		}
	}
}
