import java.util.ArrayList;
import java.util.Scanner;

public class MainEntry {
	public static void main(String[] args) {
		int i = 0;
		Scanner sc = new Scanner(System.in);
		ArrayList<ConsumerInfo> arrayList = new ArrayList<>();
		ReviseInfo revise = new ReviseInfo(arrayList);
		Index index = new Index(arrayList);
		Point point;
		
		while (true) {
			
			MainMenu.menu();
			
			int n = 0;
			n = sc.nextInt();
			switch (n) {
			case 1:
				SignUp.signUp(arrayList);
				System.out.println(i + 1 + "��° ȸ��");
				i++;
				break;
			case 2:
				revise.selection();
				break;
			case 3:
				System.out.println("����Ʈ ��ȸ�� �Ͻð��� �ϴ� �� ��ȣ�� �Է����ּ���");
				point = new Point_ref(arrayList);
				point.num_search(sc.nextInt());
				break;
			case 4:
				System.out.println("����Ʈ ������ �ϰ��� �ϴ� �� ��ȣ�� �Է����ּ���. ");
				point = new PointSetting(arrayList);
				point.num_search(sc.nextInt());
			case 5:
				System.out.println("������ ���� ��ȣ�� �Է����ּ���.");
				point = new Erase_Consumer(arrayList);
				point.num_search(sc.nextInt());
			case 6:
				for(int i1 = 0; i1<arrayList.size();i1++)
				{
					index.show_component(i1);
				}
				
			}

		}
	}
}