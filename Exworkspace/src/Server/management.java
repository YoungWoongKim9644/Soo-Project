package Server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class management {
	
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
	
	
	public management() {
		management.sc =new Scanner(System.in);
		this.url = "jdbc:oracle:thin:@localhost:1521:orcl";
		this.username = null;
		this.password = null;
		this.conn = null;
		this.hashType = "SHA-256";
		this.query = null;
		this.stmt = null;
		this.rs = null;
	}
	
	
	
	public boolean connection(String username, String password)
	{
		
		//sender.send("사용자 이름을 입력하세요");
		//System.out.println("사용자 이름을 입력하세요");
		this.username = username; 
		//username = sender.receive();
		//sender.send("사용자 패스워드를 입력하세요");
		//System.out.println("사용자 패스워드를 입력하세요");
		this.password = password;
		
		try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(this.url, this.username, this.password);
		}
		catch(SQLException e){	e.printStackTrace();System.out.println("여기 걸림2");
		} catch (ClassNotFoundException e) {e.printStackTrace();System.out.println("여기 걸림3");
		}
		if(conn == null) {
			System.out.println("db연결에 실패했습니다.");
			//sender.send("연결에 실패했습니다.");
			return false;
		}
		else {
			System.out.println("db연결에 성공했습니다.");
			//sender.send("연결에 성공했습니다.");
			return true;
		}
	}

	
	
	

	
	 
	 
	 void insertToDB(String query)  {
				 System.out.println(query);
				try {
					stmt = conn.createStatement();
					stmt.executeUpdate(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			} 
	 
	 
	 

}



	 
	 
	 
	 
	 
	 
	 
	/* ----------2.Modify----------- */
	 /*
	public void selection() throws IOException, SQLException {
		
		if(islogIn == 0){
		String adminID = null;
		String adminPW = null;
		System.out.println("admin 계정이 필요합니다.");
		System.out.println("ID : "); adminID = sc.next();
		System.out.println("PW : "); adminPW = sc.next();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM ADMIN_ACCOUNT");
			while (rs.next()) {
				if (rs.getString("ADMIN_ID").equals(adminID) && rs.getString("ADMIN_PW").equals(adminPW) ){
					System.out.println(ADMIN_LOGIN);
					islogIn = 1;
				}
				else {
					System.out.println(ADMIN_LOGIN_FAILED);
					return;
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		}
		else if(islogIn == 1) {
			char log;
			System.out.println("이미 로그인 되어있습니다.");
			System.out.println("로그아웃 하시겠습니까? [y/n]");
			log = sc.next().charAt(0);
			if(log == 'Y' || log == 'y') {
				islogIn = 0;
				return;
			}
		}
		
		
		System.out.println(MODIFY_CUSTOMERINFO_MSG);
		int num;	num = sc.nextInt();
		for ( String menu: MODIFYMENUS)
			System.out.println(menu);

		int n = sc.nextInt();
		System.out.println("바꿀 정보를 입력하세요.");
		if (n == 1) {updateToDB(String.valueOf(num),"CUSTOMER_NAME", tmp,sc.next());
		} else if (n == 2) {updateToDB(String.valueOf(num),"GENDER", tmp,sc.next());
		} else if (n == 3) {updateToDB(String.valueOf(num),"POINT_",tmp,sc.next());
		} else if (n == 4) {updateToDB(String.valueOf(num),"CUSTOMER_NO", tmp,sc.next());;
		} else {	System.out.println("잘못 입력하셨습니다.");
		}
	}
	/* ----------2.Modify----------- */

	
	
	 /*
	 void updateToDB(String customerNo, String updateAttr, ConsumerInfo C,String updateValue) throws SQLException {
			try {
				stmt = conn.createStatement();
				if (updateAttr.equalsIgnoreCase("POINT_")) //
					{query = "UPDATE " + CUSTOMER_TABLE_NAME + " SET " + updateAttr + " = " + updateValue + " WHERE CUSTOMER_NO = '"
							+ customerNo + "'";
					System.out.println(query);
					}
				else
					{query = "UPDATE " + CUSTOMER_TABLE_NAME + " SET " + updateAttr + " = '" + updateValue + "' WHERE CUSTOMER_NO = '"
							+ customerNo + "'";
					System.out.println(query);
					}
				stmt.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	
	
	
	
	/* ----------(3).Numsearch----------- */
	 /*
	String num_search(int num) {
		return " WHERE CUSTOMER_NO =" + "'" + num + "'";
	}
	/* ----------(3).Numsearch----------- */
	
	 /*
	 void connumberToDB(String query, int num)
		{
		 System.out.println(query);
			try {
				stmt = conn.createStatement();
				if(num == LOOKUP_NUM) {
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					//if (rs.getString("CUSTOMER_NO").equals(customerNo))
						System.out.println(rs.getString("CUSTOMER_NAME") + "님의 잔여 포인트는 " + rs.getString("POINT_") + "입니다.");
				}
				}
				else if(num == REMOVE_NUM || num == MODIFY_NUM)
				stmt.executeUpdate(query);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	

		/* ----------3..PointLookUp----------- */
	 /*
		public void point_chk1(String querypart) {
			connumberToDB("SELECT POINT_ , CUSTOMER_NAME FROM " + CUSTOMER_TABLE_NAME + " " + querypart , LOOKUP_NUM);
		}
		/* ----------3.PointLookUp----------- */
	 
	
	/* ----------4.PointModify----------- */
	 /*
	public void point_chk2(String querypart) {
		System.out.println("querypart :" + querypart);
		System.out.println( ENTER_POINT_TO_CHANGE);
		int point = sc.nextInt();
		connumberToDB("UPDATE CUSTOMER_TABLE SET POINT_ = "+ point + querypart, MODIFY_NUM );
	}
	/* ----------4.PointModify----------- */

	
	
	
	
	/* ----------5.Remove ConData---------- */
	 /*
	public void point_chk3(String querypart) {
		connumberToDB("DELETE" + CUSTOMER_TABLE_NAME + querypart,REMOVE_NUM);	
	}
	/* ----------5.Remove ConData----------- */
	
	
	
	
	
	/* ----------6.Show All Lsit----------- */
	 /*
	public void show_index_component() throws SQLException {
		rs = printCustomers();
		try {
			while (rs.next()) {
				System.out.println(rs.getString(CUSTOMER_TABLE_ATTRS[0]) + "\t" + rs.getString(CUSTOMER_TABLE_ATTRS[1])
						+ "\t" + rs.getString(CUSTOMER_TABLE_ATTRS[2]) + "\t" + rs.getString(CUSTOMER_TABLE_ATTRS[3])
						/*+ "\t" + rs.getInt(CUSTOMER_TABLE_ATTRS[4]) + "hello"*//*);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/* ----------6.Show All List----------- */
	
	 /*
	 public ResultSet printCustomers() {
			query = "SELECT * FROM " + CUSTOMER_TABLE_NAME;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				return rs;
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(CONNECTION_FAILED);
				return null;
			}
			
		}
	
	 
	 
	 /* ----------6.statistic sum & avg ----------- */
	 /*
	 public void statistic()
	 {	int menu = 0;
		int genderNo;
		char gender;
		
	 	for(String statisticMenu : STATISTICMENUS) {
			System.out.println(statisticMenu);
		}
	 	menu = sc.nextInt();
	 	for(String statistiGenMenu : STATISTIC_GEN_MENUS) {
			System.out.println(statistiGenMenu);
		}
	 	genderNo = sc.nextInt();
	 	if(genderNo == 1) {gender = 'm';}
	 	else if(genderNo ==2) {gender = 'f';}
	 	else if(genderNo == 3) { gender = '*';}
	 	else {System.out.println("잘못 입력하셨습니다.");return;}
	 	
		if(menu == 1) {
			try {
				stmt = conn.createStatement();
				System.out.println("SELECT SUM(POINT_) FROM CUSTOMER_TABLE WHERE GENDER = " + "'" + gender + "'");
				rs = stmt.executeQuery("SELECT SUM(POINT_) FROM CUSTOMER_TABLE WHERE GENDER = " + "'" + gender + "'");
				while(rs.next()) {
					System.out.println(gender + " : 포인트 총합은 " + rs.getString("SUM(POINT_)")+ "입니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("포인트를 조회할 수 없습니다.");
			}
		}
		else if(menu == 2) {
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT AVG(POINT_) FROM CUSTOMER_TABLE WHERE GENDER = " + "'" + gender + "'");
				while(rs.next()) {
					System.out.println(gender + " : 포인트 평균은 " + rs.getString("AVG(POINT_)")+ "입니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("포인트를 조회할 수 없습니다.");
			}
		}
	 }
	 
	 
	 public void deleteAll()
	 {	String query ="DELETE FROM" + CUSTOMER_TABLE_NAME;
		 System.out.println("모든 고객정보를 삭제하시겠습니까? [y/n]");
		 char answer = sc.next().charAt(0);
		 if(answer == 'y' || answer == 'Y') {
			 connumberToDB(query, REMOVE_NUM);
		 }
		 else if (answer =='N' || answer =='n') {
			 return;
		 }
		 else {
			 System.out.println("잘못 입력하셨습니다.");
			 return;
		 }
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	public void quit()
	{
		try {
			System.out.println(PROGRAM_QUIT_MSG);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean isAleadNum(int number)
	{
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT customer_no FROM CUSTOMER_TABLE");
			while (rs.next()) {
				if (rs.getString("CUSTOMER_NO").equals(String.valueOf(number))) {
					System.out.println(CON_NUM_ALEADY_EXIST_MSG);
					return true;
				}
					
				}
			} catch (SQLException e) {
			e.printStackTrace();
		}
			return false;
	}
	*/













/*
public void lookupPoint() {
	String customerNo = this.inputCno(true);
	this.query = "SELECT CUSTOMER_NO, POINT_, CUSTOMER_NAME FROM " + CUSTOMER_TABLE_NAME;
	try {
		this.stmt = this.conn.createStatement();
		ResultSet rs = this.stmt.executeQuery(this.query);
		while (rs.next()) {
			if (rs.getString("CUSTOMER_NO").equals(customerNo))
				System.out.println(rs.getString("CUSTOMER_NAME") + "님의 잔여 포인트는 " + rs.getString("POINT_") + "입니다.");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}





*/


