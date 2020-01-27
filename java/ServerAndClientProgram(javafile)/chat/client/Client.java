package chat.client;
import java.net.Socket;
import SenderAndReceiver.*;


public class Client {
	public static void main(String[] args) {
		Socket socket = null;
		UserInfo info = null;
		
		try {
			socket = new Socket("localhost",9001);
			Thread sender = new SenderThread(socket,info);
			Thread receiver = new ReceiverThread(socket,info);
			sender.start();
			receiver.start();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
