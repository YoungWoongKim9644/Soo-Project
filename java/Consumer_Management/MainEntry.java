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
				break;
			case 2:
				revise.selection();
				break;
			case 3:
				System.out.println("����Ʈ ��ȸ�� �Ͻð��� �ϴ� �� ��ȣ�� �Է����ּ���");
				point = new Point_ref(arrayList); point.num_search(sc.nextInt());
				break;
			case 4:
				System.out.println("����Ʈ ������ �ϰ��� �ϴ� �� ��ȣ�� �Է����ּ���. ");
				point = new PointSetting(arrayList); point.num_search(sc.nextInt());
				break;
			case 5:
				System.out.println("������ ���� ��ȣ�� �Է����ּ���.");
				point = new Erase_Consumer(arrayList); point.num_search(sc.nextInt());
				break;
			case 6:
				for(int j = 0; j<arrayList.size();j++)
					index.show_component(j);
				
			}

		}
	}
}