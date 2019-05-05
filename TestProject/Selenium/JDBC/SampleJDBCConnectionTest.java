package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SampleJDBCConnectionTest {

	String DBURL;
	String DBUser;
	String DBPassword;
	Connection connection;
	Statement statement;
	ResultSet resultset;
	public static void main(String[] args) {
		SampleJDBCConnectionTest object = new SampleJDBCConnectionTest();
		object.DBURL="jdbc:oracle:thin:@localhost:1521:orcl";
		object.DBUser="system";
		object.DBPassword="Tssr1993";
		object.startConnection(object.DBURL,object.DBUser,object.DBPassword);
		object.executeQuery();
		object.validateColumnData("customer_name", "sumanth");
		try {
			object.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void startConnection(String DBurl, String Username, String Password)
	{
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection= DriverManager.getConnection(DBurl,Username,Password);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void executeQuery()
	{
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultset = statement.executeQuery("select * from customers");
		} catch ( SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void validateColumnData(String ColumnName, Object ColumnValue)
	{
		try {
			resultset.beforeFirst();
			while(resultset.next())
			{
				System.out.println("inside while");
				String name = resultset.getString("customer_name");
				System.out.println("name: "+ name);
				if(name.equals(ColumnValue))
				{
					System.out.println("inside if");
					Object customerid = resultset.getInt("customer_id");
					System.out.println("customerid: "+ customerid);
					if(resultset.isLast())
					{
						resultset.first();
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	
}
