import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author www.luv2code.com
 *
 */
public class WriteBlobDemo {

	public static void main(String[] args) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		FileInputStream input = null;
		
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/demo", "student", "student");

			// 2. Prepare statement
			String sql = "update employees set resume=? where email='john.doe@foo.com'";
			myStmt = myConn.prepareStatement(sql);
			
			// 3. Set parameter for resume file name
			File theFile = new File("sample_resume.pdf");
			input = new FileInputStream(theFile);
			myStmt.setBinaryStream(1, input);
			
			System.out.println("Reading input file: " + theFile.getAbsolutePath());
			
			// 4. Execute statement
			System.out.println("\nStoring resume in database: " + theFile);
			System.out.println(sql);
			
			myStmt.executeUpdate();
			
			System.out.println("\nCompleted successfully!");
			
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {			
			if (input != null) {
				input.close();
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
