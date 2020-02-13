package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.sql.SQLException;
import java.util.Scanner;
import function.signup;

public class Client implements java.io.Serializable {
	private static String id = null;
	private static String password = null;
	
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws SQLException, IOException {
		
		
		Socket socket = null;
		CliConsumerManage con = null;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		
		try {
			socket = new Socket("localhost",9001);
			con = new CliConsumerManage();
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
			System.out.println("id 입력");
			id = sc.nextLine();
			System.out.println("pw 입력");
			password = sc.nextLine();
			signup signup = new signup(id,password);
			out.writeObject(signup);
			out.flush();
			//System.out.println("보내긴 보냈음");
		
			
			
			//socket.close();
		}
		catch(IOException e) {
			System.out.println("클라 여기 걸림");
			System.out.println(e.getMessage());
		}
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
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
		*/
		
	}
}
