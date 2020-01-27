package SenderAndReceiver;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiverThread extends Thread{
	Socket socket;
	UserInfo info;
	
	 public ReceiverThread(Socket socket, UserInfo info){
		this.socket = socket;
		this.info = info;
	}
	
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			while(true) {
				info.setMessage(reader.readLine());
				if(info.getMessage() == null)
					break;
				System.out.println("¼ö½Å>" + info.getMessage());
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
