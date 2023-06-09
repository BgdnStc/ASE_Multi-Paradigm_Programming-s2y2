import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Utils {
	private static List<ElectronicDevices> listED;

	public static List<ElectronicDevices> createPhones(int n) throws Exception {
		listED = new ArrayList<>(n);
		return listED;
	}

	public static List<ElectronicDevices> readPhones(String file) {
		try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
			while (true) {
				try {
					Phone phone = new Phone();
					phone.setWeight(Float.parseFloat(raf.readLine()));
					phone.setDiagonal(Double.parseDouble(raf.readLine()));
					phone.setProducer(raf.readLine());
					listED.add(phone);
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listED;
	}

	public void writeBinaryPhones(String file, List<ElectronicDevices> listP) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			for (ElectronicDevices phone : listP) {
				oos.writeObject(phone);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<ElectronicDevices> readBinaryPhones(String file) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			while (true) {
				try {
					listED.add((ElectronicDevices) ois.readObject());
				} catch (IOException | ClassNotFoundException e) {
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listED;
	}
}
