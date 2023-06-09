import java.util.List;

public class VectThread implements Runnable {
	private List<ElectronicDevices> phonesList;
	private double avgWeight;

	public VectThread(String filename) {
		Utils.readBinaryPhones(filename);
	}

	public List<ElectronicDevices> getPhonesList() {
		return phonesList;
	}

	public double getAvgWeight() {
		return avgWeight;
	}

	@Override
	public void run() {
		float totalWeights = 0;
		for (ElectronicDevices device : phonesList) {
			Phone phone = (Phone) device;
			totalWeights += phone.getWeight();
		}
		avgWeight = totalWeights / phonesList.size();
	}

}
