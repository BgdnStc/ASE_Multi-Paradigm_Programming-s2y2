import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilsDAO {
	private static Connection connection;

	public static void setConnection() throws Exception {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:test.db");
			connection.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {
			throw new Exception();
		}
	}

	public static void closeConnection() throws SQLException {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new SQLException();
		}
	}

	public static String selectData() throws SQLException {
		String sqlSelect = "SELECT * FROM Phones";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sqlSelect);
		StringBuilder stringBuilder = new StringBuilder();
		while (rs.next()) {
			stringBuilder.append(rs.getInt(1));
			stringBuilder.append(":");
			stringBuilder.append(rs.getString(2));
			stringBuilder.append(":");
			stringBuilder.append(rs.getDouble(3));
			stringBuilder.append(":");
			stringBuilder.append(rs.getFloat(4));
			stringBuilder.append("\r\n");
		}
		connection.close();
		return stringBuilder.toString();
	}
}
