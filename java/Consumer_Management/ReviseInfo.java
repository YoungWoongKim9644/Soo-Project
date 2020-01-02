
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
			System.out.println("�ٲ� ���� ��ȣ�� �Է��ϼ���.");
			num = sc.nextInt()-1;
		}
		while(num>= arraylist.size());
			
		System.out.println("�ٲ� ������ �������ּ���.");
		System.out.println("1. �̸�");
		System.out.println("2. ����");
		System.out.println("3. ����Ʈ");
		System.out.println("4. ��ȣ");
		
		if(sc.nextInt() == 1)
		{
			System.out.println("�ٲ� ������ �Է��ϼ���.");
			revise_name(num, sc.next());
		}
		else if(sc.nextInt() == 2)
		{
			System.out.println("�ٲ� ������ �Է��ϼ���.");
			revise_sex(num, sc.next().charAt(0));
		}
		else if(sc.nextInt() == 3)
		{
			System.out.println("�ٲ� ������ �Է��ϼ���.");
			revise_point(num, sc.nextInt());
		}
		else if(sc.nextInt() == 4)
		{
			System.out.println("�ٲ� ������ �Է��ϼ���.");
			revise_number(num, sc.nextInt());
		}
		else
		{
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
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
