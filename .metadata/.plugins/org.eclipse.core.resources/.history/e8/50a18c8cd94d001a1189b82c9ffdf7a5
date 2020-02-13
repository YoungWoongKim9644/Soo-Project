package chat.server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import SenderAndReceiver.MainMenu;
import SenderAndReceiver.Packet;




public class Server implements MainMenu {
	static int socketCnt = -1;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {
		
		ArrayList<Socket> sockets = new ArrayList<>();
		ServerSocket serverSocket = null;
		Socket socket = null;
		ServConsumerManage con = null;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		
	try {
		serverSocket = new ServerSocket(9001);
		System.out.println("클라이언트의 접속을 대기중입니다.");
		socket = serverSocket.accept();
		System.out.println("클라이언트 접속에 성공했습니다.");
		sockets.add(socket);
		
		
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		con = new ServConsumerManage(in, out);
		
		
			
			if(con.connection()) {
				while (true) {
					
					System.out.println("phase : receiving");
						
						Packet packet = (Packet)in.readObject();
						
						System.out.println("menu : " + packet.getMenuNo());
						System.out.println(packet.hashCode());
						
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
						
						out.reset();
						out.flush();
						out.writeObject(packet);
						out.flush();
						out.reset();
						
					
			}	
			}
			
			socket.close();
			in.close();
			out.close();
			
		} catch(SocketException e) {
		System.out.println("여기 걸림1");
		System.out.println(e.getMessage());
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} System.exit(0);
	
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
