package chat.server;


import java.net.ServerSocket;
import java.net.Socket;
import SenderAndReceiver.ReceiverThread;
import SenderAndReceiver.SenderThread;
import SenderAndReceiver.UserInfo;



public class Server {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		UserInfo info = null;
	try {
		serverSocket = new ServerSocket(9001);
		socket = serverSocket.accept();
		Thread sender = new SenderThread(socket,info);
		Thread receiver = new ReceiverThread(socket,info);
		sender.start();
		receiver.start();
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
	}
	finally {
		try {
			serverSocket.close();
		}catch(Exception ignored) {
			
		}
	}
}
}
