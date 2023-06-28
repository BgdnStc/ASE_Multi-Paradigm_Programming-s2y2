import java.io.Serializable;
import java.util.Date;

public abstract class Meeting implements Cloneable, Serializable {
	private int id;
	private String title;
	private int duration;
	private Date startDate;

	private static int idNumber = 0;

	public Meeting() {
		this.id = -1;
		this.title = "";
		this.duration = 0;
		this.startDate = null;
	}

	public Meeting(String title, int duration, Date startDate) throws Exception {
		if (title == "" || duration < 0) {
			throw new Exception();
		} else {
			this.id = idNumber++;
			this.title = title;
			this.duration = duration;
			this.startDate = startDate;
		}
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws Exception {
		if (title == "") {
			throw new Exception();
		} else {
			this.title = title;
		}
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) throws Exception {
		if (duration < 0) {
			throw new Exception();
		} else {
			this.duration = duration;
		}
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Meeting meeting = null;
		try {
			meeting = getClass().getDeclaredConstructor(String.class).newInstance(this.title, this.duration,
					this.startDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return meeting;
	}

	@Override
	public boolean equals(Object obj) {
		Meeting meeting = (Meeting) obj;
		if (!this.title.equals(meeting.title)) {
			return false;
		} else if (this.duration != meeting.duration) {
			return false;
		} else if (!this.startDate.equals(meeting.startDate)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
