public class SmartPhone extends Phone {
	private int batteryDuration;

	public int getBatteryDuration() {
		return batteryDuration;
	}

	public void setBatteryDuration(int batteryDuration) throws Exception {
		if (batteryDuration <= 0) {
			throw new Exception();
		} else {
			this.batteryDuration = batteryDuration;
		}
	}

	@Override
	public String infoDevice() {
		return ((Integer) batteryDuration).toString();
	}

}
