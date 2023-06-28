import java.util.Date;

public class OnlineMeeting extends Meeting implements IMeeting {
	public enum Platform {
		Zoom, Teams, Meet;
	}

	private Platform platformUsed;

	public OnlineMeeting() {
		super();
		this.platformUsed = null;
	}

	public OnlineMeeting(String title, int duration, Date startDate, Platform platformUsed) throws Exception {
		super(title, duration, startDate);
		this.platformUsed = platformUsed;
	}

	public Platform getPlatformUsed() {
		return platformUsed;
	}

	public void setPlatformUsed(Platform platformUsed) {
		this.platformUsed = platformUsed;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String getDetails() {
		if (platformUsed.equals(null)) {
			return "";
		} else {
			return platformUsed.toString();
		}
	}

}
