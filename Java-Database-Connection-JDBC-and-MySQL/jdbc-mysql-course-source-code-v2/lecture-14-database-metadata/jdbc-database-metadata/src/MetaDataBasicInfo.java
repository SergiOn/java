import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MetaDataBasicInfo {

	public static void main(String[] args) throws SQLException {

		Connection myConn = null;

		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/demo", "student", "student");

			// 2. Get metadata
			DatabaseMetaData databaseMetaData = myConn.getMetaData();
			
			// 3. Display info about database
			System.out.println("Product name: " + databaseMetaData.getDatabaseProductName());
			System.out.println("Product version: " + databaseMetaData.getDatabaseProductVersion());
			System.out.println();
			
			// 4. Display info about JDBC Driver
			System.out.println("JDBC Driver name: " + databaseMetaData.getDriverName());
			System.out.println("JDBC Driver version: " + databaseMetaData.getDriverVersion());
			
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(myConn);
		}
	}

	private static void close(Connection myConn)
			throws SQLException {

		if (myConn != null) {
			myConn.close();
		}
	}
}