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
import chat.server.ServConsumerManage;


public class Client implements MainMenu {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws SQLException, IOException {
		Socket socket = null;
		CliConsumerManage con = null;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		Packet packet = new Packet();
		
		try {
			socket = new Socket("localhost",9001);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			con = new CliConsumerManage(in, out);
			//CltSendReceive sender = new CltSendReceive(socket,info);
			

				while (true) {
					System.out.println(packet.getMenuNo());
					for (String menu : MENUS)
						System.out.println(menu);
					int n = 0;
					n = sc.nextInt();
					packet.setMenuNo(n);
					
				
					if (n == SIGNUP) {con.signUp(packet);
					} else if (n == REVISION) {con.selection(packet);
					} else if (n == POINT_REFER) {manage_point(POINT_LOOKUP_NUM, con, packet);
					} else if (n == POINT_MODIFY) {manage_point(POINT_MODIFY_NUM, con, packet);
					} else if (n == REMOVE_INFO) {manage_point(REMOVE_INFO_NUM, con, packet);
					} else if (n == TOTAL_ACCOUNT) {con.showList(packet);
					} else if (n == STATISTIC_FUNC) {con.statistic(packet);
					} else if (n == DELETE_ALL) {con.deleteAll(packet);
					} else if (n == QUIT_PROGRAM ) {con.quit(socket);return;}
				
				}
				
			
			
		}
		catch(Exception e) {
			System.out.println("여기걸리니?");
			System.out.println(e.getMessage());
		}
			
	}
	
	private static void manage_point(int i, CliConsumerManage con, Packet packet)
	{
		System.out.println(POINT_MENU[i]);
		packet.setPointNum(sc.nextInt());
		if(i == POINT_LOOKUP_NUM) { con.pointChk(packet);}
		else if(i == POINT_MODIFY_NUM) {con.pointMod(packet);}
		else if(i == REMOVE_INFO_NUM) {con.remove(packet);}
		else {System.out.println("no Data in DB");
			return;
		}
	}
}
