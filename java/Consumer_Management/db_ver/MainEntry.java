import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainEntry implements MainMenu {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException, SQLException {
		int i = 0;	
		ConsumerManage con = new ConsumerManage();
		

		
		
		
		if(con.connection()) {
				
		while (true) {
			for (String menu : MENUS)
				System.out.println(menu);
			int n = 0;
			n = sc.nextInt();
			if (n == SIGNUP) {con.signUp();
			} else if (n == REVISION) {con.selection();
			} else if (n == POINT_REFER) {manage_point(POINT_LOOKUP_NUM, con);
			} else if (n == POINT_MODIFY) {manage_point(POINT_MODIFY_NUM, con);
			} else if (n == REMOVE_INFO) {manage_point(REMOVE_INFO_NUM, con);
			} else if (n == TOTAL_ACCOUNT) {con.show_index_component();
			} else if (n == STATISTIC_FUNC) {con.statistic();
			} else if (n == DELETE_ALL) {con.deleteAll();
			} else if (n == QUIT_PROGRAM ) {con.quit();return;}
		}

		}
	
	
	
	}	
	
	private static void manage_point(int i, ConsumerManage con)
	{
		System.out.println(POINT_MENU[i]);
		String query = con.num_search(sc.nextInt());
		if(i == POINT_LOOKUP_NUM) { con.point_chk1(query);}
		else if(i == POINT_MODIFY_NUM) {con.point_chk2(query);}
		else if(i == REMOVE_INFO_NUM) {con.point_chk3(query);}
		else {
			System.out.println("no Data in DB");
			return;
		}
	}
	
}