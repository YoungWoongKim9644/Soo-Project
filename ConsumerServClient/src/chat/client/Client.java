package chat.client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

import SenderAndReceiver.ConsumerInfo;
import SenderAndReceiver.MainMenu;
import SenderAndReceiver.Packet;


public class Client implements MainMenu {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws SQLException, IOException {
		Socket socket = null;
		ConsumerInfo info = null;
		CliConsumerManage con = null;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		Packet packet = new Packet();
		
		try {
			socket = new Socket("localhost",9001);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			con = new CliConsumerManage(in, out, packet);
			//CltSendReceive sender = new CltSendReceive(socket,info);
			if(con.connection())
			{
				while (true) {
					for (String menu : MENUS)
						System.out.println(menu);
					int n = 0;
					n = sc.nextInt();
					packet.setMenuNo(n);
					out.writeObject(packet);
					out.flush();
					if (n == SIGNUP) {con.signUp();
					} else if (n == REVISION) {con.selection();
					} else if (n == POINT_REFER) {manage_point(POINT_LOOKUP_NUM, con);
					} else if (n == POINT_MODIFY) {manage_point(POINT_MODIFY_NUM, con);
					} else if (n == REMOVE_INFO) {manage_point(REMOVE_INFO_NUM, con);
					} else if (n == TOTAL_ACCOUNT) {con.showList();
					} else if (n == STATISTIC_FUNC) {con.statistic();
					} else if (n == DELETE_ALL) {con.deleteAll();
					} else if (n == QUIT_PROGRAM ) {con.quit();return;}
				}
				
			}
			
			socket.close();
			in.close();
			out.close();
		}
		catch(Exception e) {
			System.out.println("����ɸ���?");
			System.out.println(e.getMessage());
		}
			
	}
	
	private static void manage_point(int i, CliConsumerManage con)
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