import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author www.luv2code.com
 *
 */
public class ReadClobDemo {

	public static void main(String[] args) throws Exception {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		Reader input = null;
		FileWriter output = null;

		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/demo", "student", "student");

			// 2. Execute statement
			myStmt = myConn.createStatement();
			String sql = "select resume from employees where email='john.doe@foo.com'";
			myRs = myStmt.executeQuery(sql);
			
			// 3. Set up a handle to the file
			File theFile = new File("resume_from_db.txt");
			output = new FileWriter(theFile);

			if (myRs.next()) {

				input = myRs.getCharacterStream("resume"); 
				System.out.println("Reading resume from database...");
				System.out.println(sql);
				
				int theChar;
				while ((theChar = input.read()) > 0) {
					output.write(theChar);
				}
				
				System.out.println("\nSaved to file: " + theFile.getAbsolutePath());
				
				System.out.println("\nCompleted successfully!");				
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (input != null) {
				input.close();
			}

			if (output != null) {
				output.close();
			}
			
			close(myConn, myStmt);
		}
	}

	private static void close(Connection myConn, Statement myStmt)
			throws SQLException {

		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}
}
