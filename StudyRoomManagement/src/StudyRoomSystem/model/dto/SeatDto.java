package StudyRoomSystem.model.dto;

public class SeatDto {
	//1. 필드
	private int Seat_UID;
	private int Seat_TYPE;
	private int Status;
	private int customer_UID;
	
	//2. 생성자
	public SeatDto() {}
	
	
	public SeatDto(int seat_UID, int seat_TYPE, int status, int customer_UID) {
		Seat_UID = seat_UID;
		Seat_TYPE = seat_TYPE;
		Status = status;
		this.customer_UID = customer_UID;
	}

	//3. 메소드
	public int getSeat_UID() {
		return Seat_UID;
	}


	public void setSeat_UID(int seat_UID) {
		Seat_UID = seat_UID;
	}


	public int getSeat_TYPE() {
		return Seat_TYPE;
	}


	public void setSeat_TYPE(int seat_TYPE) {
		Seat_TYPE = seat_TYPE;
	}


	public int getStatus() {
		return Status;
	}


	public void setStatus(int status) {
		Status = status;
	}


	public int getCustomer_UID() {
		return customer_UID;
	}


	public void setCustomer_UID(int customer_UID) {
		this.customer_UID = customer_UID;
	}


	@Override
	public String toString() {
		return "SeatDto [Seat_UID=" + Seat_UID + ", Seat_TYPE=" + Seat_TYPE + ", Status=" + Status + ", customer_UID="
				+ customer_UID + "]";
	}
	
	
	
}
