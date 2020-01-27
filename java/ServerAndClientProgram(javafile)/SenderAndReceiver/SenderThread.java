package SenderAndReceiver;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SenderThread extends Thread {
	Socket socket;
	UserInfo info;

	public SenderThread(Socket socket, UserInfo info) {
		this.socket = socket;
		this.info = info;
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			
			//BufferedReader reader = new BufferedReader(
			//new InputStreamReader(socket.getInputStream()));
			
			while (true) {
				info.setMessage(reader.readLine());
				if (info.getMessage().equals("bye"))
					break;
				writer.println(info.getMessage());
				writer.flush();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				socket.close();
			} catch (Exception ignored) {
			}
		}

	}
}