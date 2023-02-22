package StudyRoomSystem.model;

public class MemberDto {
	//1. 필드
	private int customer_UID;
	private String customer_ID;
	private String customer_PW;
	private String customer_NAME;
	private String customer_TEL;
	
	
	//2. 생성자
	public MemberDto() {}

	
	
	public MemberDto(String customer_ID, String customer_PW, String customer_NAME,
			String customer_TEL) {
		this.customer_ID = customer_ID;
		this.customer_PW = customer_PW;
		this.customer_NAME = customer_NAME;
		this.customer_TEL = customer_TEL;
	}

	public MemberDto(int customer_UID, String customer_ID, String customer_PW, String customer_NAME,
			String customer_TEL) {
		this.customer_UID = customer_UID;
		this.customer_ID = customer_ID;
		this.customer_PW = customer_PW;
		this.customer_NAME = customer_NAME;
		this.customer_TEL = customer_TEL;
	}



	//3. 메소드
	public int getCustomer_UID() {
		return customer_UID;
	}

	public void setCustomer_UID(int customer_UID) {
		this.customer_UID = customer_UID;
	}

	public String getCustomer_ID() {
		return customer_ID;
	}

	public void setCustomer_ID(String customer_ID) {
		this.customer_ID = customer_ID;
	}

	public String getCustomer_PW() {
		return customer_PW;
	}

	public void setCustomer_PW(String customer_PW) {
		this.customer_PW = customer_PW;
	}

	public String getCustomer_NAME() {
		return customer_NAME;
	}

	public void setCustomer_NAME(String customer_NAME) {
		this.customer_NAME = customer_NAME;
	}

	public String getCustomer_TEL() {
		return customer_TEL;
	}

	public void setCustomer_TEL(String customer_TEL) {
		this.customer_TEL = customer_TEL;
	}

	@Override
	public String toString() {
		return "MemberDto [customer_UID=" + customer_UID + ", customer_ID=" + customer_ID + ", customer_PW="
				+ customer_PW + ", customer_NAME=" + customer_NAME + ", customer_TEL=" + customer_TEL + "]";
	}

	
	
		
}
