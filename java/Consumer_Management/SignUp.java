import java.util.*;

public class SignUp {
	
	static Scanner sc = new Scanner(System.in);
	
	static void signUp(ArrayList<ConsumerInfo> arraylist)
	{	
		String name;
		int point;
		char sex;
		int number;
		System.out.println("ȸ�� �̸� :");
		name = sc.next();
		System.out.println("ȸ�� ����Ʈ");
		point = sc.nextInt();
		System.out.println("ȸ�� ���� [M/F]");
		sex = sc.next().charAt(0);
		System.out.println("ȸ�� ��ȣ");
		number = sc.nextInt();
		
		arraylist.add(new ConsumerInfo(number, name, sex, point));
	}
	/*static void signUp(ArrayList<ConsumerInfo> arraylist,int i)
	{
		arraylist.add(i,new ConsumerInfo(0,null,0,0));
	}*/
}
