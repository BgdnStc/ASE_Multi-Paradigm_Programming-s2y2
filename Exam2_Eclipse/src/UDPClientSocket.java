import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClientSocket implements AutoCloseable {
	DatagramSocket socket;

	public UDPClientSocket() throws SocketException {
		socket = new DatagramSocket();
	}

	public String sendAndReceiveMsg(String msg, String ipAddr, int port) throws UnknownHostException {
		String receivedMessage = "";
		try {
			DatagramPacket sendPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length,
					InetAddress.getByName(ipAddr), port);
			socket.send(sendPacket);

			byte[] buffer = new byte[256];
			DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
			socket.receive(receivedPacket);
			receivedMessage = new String(receivedPacket.getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return receivedMessage;
	}

	@Override
	public void close() throws Exception {
		if (socket != null && !socket.isClosed()) {
			socket.close();
		}
	}

}
