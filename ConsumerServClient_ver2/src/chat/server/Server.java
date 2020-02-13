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

import SenderAndReceiver.ConsumerInfo;
import SenderAndReceiver.MainMenu;
import SenderAndReceiver.Packet;
import chat.client.CliConsumerManage;



public class Server implements MainMenu {
	static int socketCnt = -1;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {
		ArrayList<Socket> sockets = new ArrayList<>();
		ServerThreading serverThread;
		ServerSocket serverSocket = null;
		Socket socket = null;
		Packet packet = new Packet();
		
	try {
		serverSocket = new ServerSocket(9001);
		System.out.println("Ŭ���̾�Ʈ�� ������ ������Դϴ�.");
		
		while(true) {
			socket = serverSocket.accept();
			socketCnt++;
			System.out.println("Ŭ���̾�Ʈ ���ӿ� �����߽��ϴ�." + socketCnt + "��°");
			sockets.add(socket);
			serverThread = new ServerThreading(socket , packet);
			
			serverThread.start();
				
			System.out.println("2");
		}
	
		
	}
	catch(SocketException e) {
		System.out.println("���� �ɸ�1");
		System.out.println(e.getMessage());
	} catch (IOException e) {
		e.printStackTrace();
	}
	System.out.println("");
	System.exit(0);
	
}
	
	
}