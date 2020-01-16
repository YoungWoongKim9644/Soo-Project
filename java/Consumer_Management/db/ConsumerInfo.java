
public class ConsumerInfo {
	private int point;
	private int number;
	private String name;
	private char sex;
	
	ConsumerInfo(int number, String name, char sex, int point)
	{
		this.number = number;
		this.name = name;
		this.sex = sex;
		this.point = point;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}
	
	
}
