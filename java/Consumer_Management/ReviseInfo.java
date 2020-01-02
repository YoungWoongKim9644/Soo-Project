
import java.util.*;

public class ReviseInfo {
	
	ArrayList<ConsumerInfo> arraylist;
	ConsumerInfo tmp;
	Scanner sc = new Scanner(System.in);
	
	ReviseInfo(Object m)
	{
		this.arraylist = (ArrayList<ConsumerInfo>) m;
	}
	
	void selection()
	{	
		int num;
		do
		{
			System.out.println("바꿀 고객의 번호를 입력하세요.");
			num = sc.nextInt()-1;
		}
		while(num>= arraylist.size());
			
		System.out.println("바꿀 정보를 선택해주세요.");
		System.out.println("1. 이름");
		System.out.println("2. 성별");
		System.out.println("3. 포인트");
		System.out.println("4. 번호");
		
		if(sc.nextInt() == 1)
		{
			System.out.println("바꿀 정보를 입력하세요.");
			revise_name(num, sc.next());
		}
		else if(sc.nextInt() == 2)
		{
			System.out.println("바꿀 정보를 입력하세요.");
			revise_sex(num, sc.next().charAt(0));
		}
		else if(sc.nextInt() == 3)
		{
			System.out.println("바꿀 정보를 입력하세요.");
			revise_point(num, sc.nextInt());
		}
		else if(sc.nextInt() == 4)
		{
			System.out.println("바꿀 정보를 입력하세요.");
			revise_number(num, sc.nextInt());
		}
		else
		{
			System.out.println("잘못 입력하셨습니다.");
		}
	}
	
	private void revise_name(int num, String name)
	{
		tmp = arraylist.get(num);
		tmp.setName(name);
		arraylist.add(num, tmp);
	}
	
	private void revise_sex(int num, char sex)
	{
		tmp = arraylist.get(num);
		tmp.setSex(sex);
		arraylist.add(num, tmp);
	}
	
	private void revise_point(int num, int point)
	{
		tmp = arraylist.get(num);
		tmp.setPoint(point);
		arraylist.add(num, tmp);
	}
	
	private void revise_number(int num, int point)
	{
		tmp = arraylist.get(num);
		tmp.setPoint(point);
		arraylist.add(num, tmp);
	}
	
	
}
