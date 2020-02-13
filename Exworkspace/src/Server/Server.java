package Server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import function.signup;


public class Server {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;	
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		
	try {
		serverSocket = new ServerSocket(9001);
		System.out.println("클라이언트의 접속을 대기중입니다.");
		socket = serverSocket.accept();
		System.out.println("클라이언트 접속에 성공했습니다.");
		
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		management con = new management();
		
		//System.out.println("여긴 온다.");
		
		signup signup = (signup)in.readObject();

		System.out.println("id :" + signup.getId() + " pw : " + signup.getPassword());
		
		System.out.println("connetion : " + con.connection(signup.getId(),signup.getPassword()));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*if(con.connection()) {
			con.insertToDB();
			
			/*
			while (true) {
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
			
		}*/
	}
	catch(SocketException e) {
		System.out.println("여기 걸림1");
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





