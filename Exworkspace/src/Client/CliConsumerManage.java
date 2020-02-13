package Client;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;




public class CliConsumerManage {
	
	
	static Scanner sc; 
	int object_num;
	int islogIn = 0;
	private String url;
	private String username;
	private String password;
	private String hashType;
	private Connection conn;
	private String query;
	private Statement stmt;
	ResultSet rs;
	
	
	public CliConsumerManage() {
		CliConsumerManage.sc =new Scanner(System.in);
		this.url = "jdbc:oracle:thin:@localhost:1521:orcl";
		this.username = null;
		this.password = null;
		this.conn = null;
		this.hashType = "SHA-256";
		this.query = null;
		this.stmt = null;
		this.rs = null;
	}
	
	public void connection()
	{
	}
	
	
	
}
	