import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainEntry implements MainMenu {
	static Scanner sc = new Scanner(System.in);
	static ArrayList<ConsumerInfo> arrayList = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		int i = 0;	
		ConsumerManage revise = new ConsumerManage(arrayList);
		
		while (true) {

			for (i = 0; i < MENU_NUM; i++)
				System.out.println(menu[i]);

			int n = 0;
			n = sc.nextInt();
			if (n == SIGNUP) {ConsumerManage.signUp(arrayList);
			} else if (n == REVISION) {revise.selection();
			} else if (n == POINT_REFER) {manage_point(POINT_LOOKUP_NUM, new ConsumerManage(arrayList));
			} else if (n == POINT_MODIFY) {manage_point(POINT_MODIFY_NUM, new ConsumerManage(arrayList));
			} else if (n == REMOVE_INFO) {manage_point(REMOVE_INFO_NUM, new ConsumerManage(arrayList));
			} else if (n == TOTAL_ACCOUNT) {
				for (int j = 0; j < arrayList.size(); j++)
					revise.show_index_component(j);
			}
		}

	}
	private static void manage_point(int i, ConsumerManage revise)
	{
		System.out.println(POINT_MENU[i]);
		int num = revise.num_search(sc.nextInt());
		if(num == -1) { return;}
		if(i == POINT_LOOKUP_NUM) { revise.point_chk1(num);}
		else if(i == POINT_MODIFY_NUM) {revise.point_chk1(num);}
		else if(i == REMOVE_INFO_NUM) {revise.point_chk1(num);}
	}
}