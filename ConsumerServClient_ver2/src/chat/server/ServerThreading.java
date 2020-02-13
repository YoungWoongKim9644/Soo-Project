package chat.server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import SenderAndReceiver.MainMenu;
import SenderAndReceiver.Packet;


public class ServerThreading extends Thread implements MainMenu {
	
	Socket socket;
	Packet packet;
	ServConsumerManage con;
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	
	ServerThreading(Socket socket, Packet packet){
		this.socket = socket;
		this.packet = packet;
		try {
			this.out = new ObjectOutputStream(socket.getOutputStream());
			this.in = new ObjectInputStream(socket.getInputStream());
			this.con = new ServConsumerManage(in, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public synchronized void run() {
		System.out.println("hello");
	
		if(con.connection())
		{	
		while (true) {
			
			System.out.println("phase : receiving");
			try {
				packet = (Packet)in.readObject();
				
				System.out.println("menu : " + String.valueOf(packet.getArray()[MENU]));
				
				if ((int)packet.getArray()[MENU]== SIGNUP) {con.signUp(packet);
				} else if ((int)packet.getArray()[MENU] == REVISION) {con.selection(packet);
				} else if ((int)packet.getArray()[MENU] == POINT_REFER) {manage_point(POINT_LOOKUP_NUM, con, packet);
				} else if ((int)packet.getArray()[MENU] == POINT_MODIFY) {manage_point(POINT_MODIFY_NUM, con, packet);
				} else if ((int)packet.getArray()[MENU] == REMOVE_INFO) {manage_point(REMOVE_INFO_NUM, con, packet);
				} else if ((int)packet.getArray()[MENU] == TOTAL_ACCOUNT) {con.showList(packet);
				} else if ((int)packet.getArray()[MENU] == STATISTIC_FUNC) {con.statistic(packet);
				} else if ((int)packet.getArray()[MENU] == DELETE_ALL) {con.deleteAll(packet);
				} else if ((int)packet.getArray()[MENU] == QUIT_PROGRAM ) {con.quit();return;}
				
				System.out.println(packet.getError());
				System.out.println("phase : sending");
				out.writeObject(packet);
				out.reset();
				out.flush();
				
			} catch (ClassNotFoundException | IOException | SQLException e) {
				System.out.println("???");
				e.printStackTrace();
			}
			
	}	
		}
	}
	
	private static void manage_point(int i, ServConsumerManage con, Packet packet)
	{
		System.out.println("phase : manage_point");
		String query = con.num2query(packet.getPointNum());
		if(i == POINT_LOOKUP_NUM) { con.pointChk(query, packet);}
		else if(i == POINT_MODIFY_NUM) {con.pointMod(query, packet);}
		else if(i == REMOVE_INFO_NUM) {con.remove(query, packet);}
		
	}
	
}
