import java.util.*;

public class SignUp {
	
	static Scanner sc = new Scanner(System.in);
	
	static void signUp(ArrayList<ConsumerInfo> arraylist)
	{	
		String name;
		int point;
		char sex;
		int number;
		System.out.println("회원 이름 :");
		name = sc.next();
		System.out.println("회원 포인트");
		point = sc.nextInt();
		System.out.println("회원 성별 [M/F]");
		sex = sc.next().charAt(0);
		System.out.println("회원 번호");
		number = sc.nextInt();
		
		arraylist.add(new ConsumerInfo(number, name, sex, point));
	}
	/*static void signUp(ArrayList<ConsumerInfo> arraylist,int i)
	{
		arraylist.add(i,new ConsumerInfo(0,null,0,0));
	}*/
}
