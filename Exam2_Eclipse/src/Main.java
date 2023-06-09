public class Main {
	public static void main(String[] args) {
		SmartPhone smartPhone = new SmartPhone();
		try {
			Utils.createPhones(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Utils.readPhones(null);
	}
}
