import java.util.ArrayList;
import java.util.Scanner;

public abstract class Point {
	ArrayList<ConsumerInfo> arraylist;
	ConsumerInfo tmp;
	Scanner sc = new Scanner(System.in);
	int i = 0;
	
	Point(Object m)
	{
		this.arraylist = (ArrayList<ConsumerInfo>) m;
	}
	
	void num_search(int num)
	{
		
		while(true) {
			if(i>arraylist.size() || num>arraylist.size()) {
				System.out.println("해당 정보를 찾을 수 없습니다.");
				break;
			}
			tmp = arraylist.get(i);
			if(num == tmp.getNumber()) {
				point_chk();
				break;
			}
			i++;
		}
	}
	
	public abstract void point_chk();
	

}

