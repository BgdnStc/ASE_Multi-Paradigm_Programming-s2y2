import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public class UdpServer extends Thread {

	public static OnlineMeeting receiveMeeting() {
		int port = 9000;
		OnlineMeeting meeting = new OnlineMeeting();

		try (DatagramSocket serverSocket = new DatagramSocket(port)) {
			byte[] buffer = new byte[256];
			DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
			serverSocket.receive(receivedPacket);
			String data = receivedPacket.getData().toString();
			// System.out.println(data);
			String[] items = data.split(" ");
			// meeting = new OnlineMeeting(items[0], Integer.parseInt(items[1]),
			// Date.parse(items[2]), (Platform)items[3]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return meeting;
	}

	public static void main(String[] args) {
		UdpServer.receiveMeeting();
	}
}
