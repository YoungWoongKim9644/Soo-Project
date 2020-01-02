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
		System.out.print("이름 : "+ arraylist.get(i).getName());
		System.out.print(" 성벌 : "+ arraylist.get(i).getSex());
		System.out.print(" 포인트 : "+ arraylist.get(i).getPoint());
		System.out.print(" 번호 : "+ arraylist.get(i).getNumber());
		System.out.println();
	}
}


