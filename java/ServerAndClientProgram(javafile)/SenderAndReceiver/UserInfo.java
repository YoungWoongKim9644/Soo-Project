package SenderAndReceiver;

public class UserInfo {
	
	private String name;
	private String number;
	private String gender;
	private String point;
	private String message;
	
	public void UserInfo(String name, String number, String gender, String point, String message) {
		this.name = name;
		this.number = number;
		this.gender = gender;
		this.point = point;
		this.message = message;
	}

	
	
	/*---- GETTER & SETTER ----*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	/*---- GETTER & SETTER ----*/
	
}
