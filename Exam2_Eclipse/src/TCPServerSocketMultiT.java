import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class TCPServerSocketMultiT {
	private ServerSocket serverSocket;
	private int port = 50001;
	File f;
	VectThread vt;

	public TCPServerSocketMultiT(int port) throws Exception {
		serverSocket = new ServerSocket(port);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setFileName(String newFName) {
		if (newFName != null) {
			f = new File(newFName);
		} else {
			f = null;
		}
	}

	public void startTCPServer() throws IOException {
		Socket socket = serverSocket.accept();
		while (true) {
			Runnable r = () -> {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
					vt = new VectThread(f.getAbsolutePath());
					List<ElectronicDevices> list = vt.getPhonesList();

					String line;
					while ((line = reader.readLine()) != null) {
						if (line.equals("EXIT")) {
							socket.close();
							return;
						} else if (line.equals("GETFILE")) {
							oos.writeObject(list);
							oos.flush();
						} else if (line.equals("GETJSON")) {

						} else if (line.equals("GETDB")) {

						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
			new Thread(r).start();
		}
	}

}
