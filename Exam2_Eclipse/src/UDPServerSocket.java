import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServerSocket implements AutoCloseable {
	private DatagramSocket socket;
	private int bindPort;

	public UDPServerSocket() throws SocketException {
		bindPort = 60001;
	}

	public int getBindPort() {
		return bindPort;
	}

	public void processRequest() throws IOException {
		try (DatagramSocket socket = new DatagramSocket(getBindPort())) {
			byte[] buffer = new byte[256];
			DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
			socket.receive(receivedPacket);
			String receivedMessage = new String(receivedPacket.getData());
			DatagramPacket sendPacket;
			String sendMessage;
			if (receivedMessage.equals("W?")) {
				sendMessage = "UDPS";
			} else if (receivedMessage.equals("BYE")) {
				sendMessage = "BYE ACK";
				sendPacket = new DatagramPacket(sendMessage.getBytes(), sendMessage.getBytes().length,
						receivedPacket.getAddress(), receivedPacket.getPort());
				socket.send(sendPacket);
				socket.close();
			} else {
				sendMessage = "ACK";
			}
			sendPacket = new DatagramPacket(sendMessage.getBytes(), sendMessage.getBytes().length,
					receivedPacket.getAddress(), receivedPacket.getPort());
			socket.send(sendPacket);
		} catch (IOException e) {
			throw new IOException();
		}
	}

	@Override
	public void close() throws Exception {
		if (socket != null && !socket.isClosed()) {
			socket.close();
		}
	}

}
