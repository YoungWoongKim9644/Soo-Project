package chat.server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;

import SenderAndReceiver.ConsumerInfo;
import SenderAndReceiver.MainMenu;
import SenderAndReceiver.Packet;



public class Server implements MainMenu {
	public static void main(String[] args) throws SQLException {
		ServerSocket serverSocket = null;
		Socket socket = null;
		ConsumerInfo info = null;
		ServConsumerManage con = null;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		
	try {
		serverSocket = new ServerSocket(9001);
		System.out.println("Ŭ���̾�Ʈ�� ������ ������Դϴ�.");
		socket = serverSocket.accept();
		System.out.println("Ŭ���̾�Ʈ ���ӿ� �����߽��ϴ�.");
		
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		con = new ServConsumerManage(in, out);
		
		if(con.connection()) {
			while (true) {
				Packet packet = (Packet)in.readObject();
				
				if (packet.getMenuNo() == SIGNUP) {
				con.signUp();
				} else if (packet.getMenuNo() == REVISION) {con.selection();
				//} else if (n == POINT_REFER) {manage_point(POINT_LOOKUP_NUM, con);
				//} else if (n == POINT_MODIFY) {manage_point(POINT_MODIFY_NUM, con);
				//} else if (n == REMOVE_INFO) {manage_point(REMOVE_INFO_NUM, con);
				} else if (packet.getMenuNo() == TOTAL_ACCOUNT) {con.showList();
				//} else if (n == STATISTIC_FUNC) {con.statistic();
				//} else if (n == DELETE_ALL) {con.deleteAll();
				//} else if (n == QUIT_PROGRAM ) {con.quit();return;}
			}
			
		}
		}
		socket.close();
		in.close();
		out.close();
	}
	catch(SocketException e) {
		System.out.println("���� �ɸ�1");
		System.out.println(e.getMessage());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.exit(0);
	
}
}