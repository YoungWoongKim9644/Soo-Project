
public class Erase_Consumer extends Point {

	Erase_Consumer(Object m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void point_chk() {
		
		arraylist.remove(i);
		if(arraylist.size() == 0)
			System.out.println("표시할 고객이 없습니다.");
	}

}
