import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		// instantiate at least 3 OnlineMeeting objects
		// add them to a list, set, map at your choice
		// calculate the average duration of all the meetings and print it out
		// create a database connection and a prepared statement

		try {
			OnlineMeeting meeting1 = new OnlineMeeting("Meeting1", 30, null, null);
			OnlineMeeting meeting2 = new OnlineMeeting("Meeting2", 40, null, null);
			OnlineMeeting meeting3 = new OnlineMeeting("Meeting3", 60, null, null);

			HashSet<OnlineMeeting> hSet = new HashSet<>();

			hSet.add(meeting1);
			hSet.add(meeting2);
			hSet.add(meeting3);

			int total = 0;
			int noElements = 0;
			for (OnlineMeeting meeting : hSet) {
				total += meeting.getDuration();
				noElements++;
			}
			System.out.println("Average of durations is:" + total / noElements + "minutes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void selectData(Connection connection) throws SQLException {
		String sqlSelect = "SELECT * FROM employees";

		Statement statement = connection.createStatement();

		// asked for this this prepared statement
		PreparedStatement pStatement = connection.prepareStatement(sqlSelect);

		ResultSet rs = statement.executeQuery(sqlSelect);
		while (rs.next()) {
			int id = rs.getInt(0);
			String title = rs.getString(2);
			Date startDate = rs.getDate(3);
			String platform = rs.getString(4);

			System.out.println(id + " " + title + " " + startDate + " " + platform);
		}
		rs.close();
		statement.close();
	}
}
