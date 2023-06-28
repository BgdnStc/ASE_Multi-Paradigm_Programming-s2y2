import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {

	public static void sendMeeting(OnlineMeeting meeting) {
		try (DatagramSocket clientSocket = new DatagramSocket()) {
			String message = meeting.toString();
			byte[] content = message.getBytes();
			InetAddress address = InetAddress.getByName("localhost");
			DatagramPacket sentPacket = new DatagramPacket(content, content.length, address, 9000);
			clientSocket.send(sentPacket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		OnlineMeeting meeting = new OnlineMeeting();
		UdpClient.sendMeeting(meeting);
	}
}
