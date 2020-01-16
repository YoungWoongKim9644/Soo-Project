/*import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLclass implements MainMenu{
	 Connection conn;
	private String query;
	private Statement stmt;
	
	SQLclass(Connection conn) throws SQLException
	{
		this.conn = conn;
		this.query = null;
		this.stmt = null;
	}
	
	
	 void insertToDB(ConsumerInfo C)  {
	//	query = "INSERT INTO " + CUSTOMER_TABLE_NAME + " VALUES";
	//   query = query + "('" + C.getName() + "','" + String.valueOf(C.getNumber()) + "','" + C.getSex() + "'," + C.getPoint()+");";
		
		 query = "INSERT INTO CUSTOMER_TABLE VALUES ('hello','hello2', NULL, NULL);";
		 System.out.println(query);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	 void updateToDB(String customerNo, String updateAttr, ConsumerInfo C,String updateValue) throws SQLException {
		try {
			stmt = conn.createStatement();
			if (updateAttr.equalsIgnoreCase("POINT_")) //
				query = "UPDATE " + CUSTOMER_TABLE_NAME + " SET " + updateAttr + " = " + updateValue + " WHERE CUSTOMER_NO = '"
						+ customerNo + "'";
			else
				query = "UPDATE " + CUSTOMER_TABLE_NAME + " SET " + updateAttr + " = '" + updateValue + "' WHERE CUSTOMER_NO = '"
						+ customerNo + "'";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	 void connumberToDB(String query)
	{
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	 public ResultSet printCustomers() {
		query = "SELECT * FROM " + CUSTOMER_TABLE_NAME;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println(rs.next());
			while (rs.next()) {
				System.out.println(rs.getString(CUSTOMER_TABLE_ATTRS[0]) + "\t" + rs.getString(CUSTOMER_TABLE_ATTRS[1])
						+ "\t" + rs.getString(CUSTOMER_TABLE_ATTRS[2]) + "\t" + rs.getString(CUSTOMER_TABLE_ATTRS[3])
						+ "\t" + rs.getInt(CUSTOMER_TABLE_ATTRS[4]) + "hello");
			}
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("연결 실패");
			return null;
		}
		
	}
	
}

*/