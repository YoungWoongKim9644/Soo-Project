import java.util.ArrayList;
import java.util.Scanner;

public class Index {
	
	ArrayList<ConsumerInfo> arraylist;
	ConsumerInfo tmp;
	
	Index(Object m)
	{
		this.arraylist = (ArrayList<ConsumerInfo>) m;
	}
	
	public void show_component(int i)
	{
		System.out.print("�̸� : "+ arraylist.get(i).getName());
		System.out.print(" ���� : "+ arraylist.get(i).getSex());
		System.out.print(" ����Ʈ : "+ arraylist.get(i).getPoint());
		System.out.print(" ��ȣ : "+ arraylist.get(i).getNumber());
		System.out.println();
	}
}


