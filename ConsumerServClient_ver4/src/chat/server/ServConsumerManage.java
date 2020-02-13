package chat.server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import SenderAndReceiver.ConsumerInfo;
import SenderAndReceiver.MainMenu;
import SenderAndReceiver.Packet;



public class ServConsumerManage implements MainMenu {
	
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
	ConsumerInfo tmp;
	static Scanner sc; 
	int object_num;
	boolean islogIn;
	private String url;
	private String username;
	private String password;
	private String hashType;
	private Connection conn;
	private String query;
	private Statement stmt;
	ResultSet rs;
	
	public ServConsumerManage(ObjectInputStream in, ObjectOutputStream out) {
		sc = new Scanner(System.in);
		this.url = "jdbc:oracle:thin:@localhost:1521:orcl";
		this.username = null;
		this.password = null;
		this.conn = null;
		this.hashType = "SHA-256";
		this.query = null;
		this.stmt = null;
		this.rs = null;
		this.in = in;
		this.out = out;
		this.islogIn =false;
	}
	
	
	
	public boolean connection()
	{
		System.out.println("DB ID를 입력하세요 : ");
		username = sc.nextLine();
		System.out.println("DB PW를 입력하세요 : ");
		password = sc.nextLine();
		try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(this.url, this.username, this.password);
		}
		catch(SQLException e){	e.printStackTrace();
		} catch (ClassNotFoundException e) {e.printStackTrace();
		}
		if(conn == null) {
			System.out.println("DB 연결에 실패했습니다.");
			return false;
		}
		else {
			System.out.println("DB 연결에 성공했습니다.");
			return true;
		}
	}

	/* ----------1.SingUp----------- */
	
	 public void signUp(Packet packet){
	
		 System.out.println("phase : signup");
		 
				if(isAleadNum(packet)) {
					return;
				}
				
				insertIntoDB(packet);
		 }
	 
	 /* ----------1.SingUp----------- */ 
	 
	 public boolean isAleadNum(Packet packet)
		{
		 System.out.println("phase : isAleadyNum");
		 
		String number = (String)packet.getArray()[NUMBER];
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT customer_no FROM CUSTOMER_TABLE");
				while (rs.next()) {
					if (rs.getString("CUSTOMER_NO").equals(number)) {
						System.out.println("isAleadNum_overlapped");
						packet.setError(CON_NUM_ALEADY_EXIST_MSG);
						packet.setBool(true);
						return true;
					}	
					}
				} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("isAleadNum not overlapped");
			packet.setBool(false);
			return false;
			
		}
	 
	 
	 
	
		 
	 void insertIntoDB(Packet packet)  {
		 
		 	System.out.println("phase : insertIntoDB");
		 
		 query = "INSERT INTO " + CUSTOMER_TABLE_NAME + " VALUES";
		  query = query + "('" + (String)packet.getArray()[NAME] + "','"
				  + (String)packet.getArray()[NUMBER] + "','"
				  + (char)packet.getArray()[GENDER] + "',"
				  + (int)packet.getArray()[POINT]+")";
		  
				 System.out.println(query);
				try {
					stmt = conn.createStatement();
					stmt.executeUpdate(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
					
			} 
	 public void selection(Packet packet) throws IOException, SQLException {
		 System.out.println("phase : selection");
		 
		 if(packet.isIslogin()) {
			 modify(packet);
		 }else if(!packet.isIslogin()) {
			 adminlogin(packet);
		 }
	 }
	 
	 public void adminlogin(Packet packet) {
		System.out.println("phase : adminlogin");

		packet.setMenuNo(2);
		String adminID = packet.getAdminID();
		String adminPW = packet.getAdminPW();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM ADMIN_ACCOUNT");
			while (rs.next()) {
				if (rs.getString("ADMIN_ID").equals(adminID) && rs.getString("ADMIN_PW").equals(adminPW) ){
					packet.setErrorExist(false);
					packet.setIslogin(true);
				}
				else {
					packet.setErrorExist(true);
					packet.setError(ADMIN_LOGIN_FAILED);
					packet.setIslogin(false);
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return;
		}
	 
	 
	 
	 
	 
	/* ----------2.Modify----------- */
	public void modify(Packet packet) throws IOException, SQLException {
		
		System.out.println("phase : modify");
		
		String conNo = packet.getNumber();
		int menuNo = packet.getInfoMenu();
		
		if (menuNo == 1) {updateToDB(conNo,"CUSTOMER_NAME",packet.getNewInfo(),packet);
		} else if (menuNo == 2) {updateToDB(conNo,"GENDER",packet.getNewInfo(),packet);
		} else if (menuNo == 3) {updateToDB(conNo,"POINT_",packet.getNewInfo(),packet);
		} else if (menuNo == 4) {updateToDB(conNo,"CUSTOMER_NO",packet.getNewInfo(),packet);;
		} else {	packet.setErrorExist(true);
					packet.setError("잘못 입력하셨습니다.");
		}
			return;
		
	}
	/* ----------2.Modify----------- */

	
	
	 
	 void updateToDB(String customerNo, String updateAttr,String updateValue, Packet packet) throws SQLException {
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
				packet.setSentence("고객 정보 수정을 완료하였습니다.");
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	
	
	
	
	
	/* ----------(3).Numsearch----------- */
	 
	String num2query(int num) {
		return " WHERE CUSTOMER_NO =" + "'" + num + "'";
	}
	/* ----------(3).Numsearch----------- */
	
	 
	 void conNo2DB(String query, int num, Packet packet)
		{
		 String result;
		 System.out.println(query);
			try {
				stmt = conn.createStatement();
				if(num == LOOKUP_NUM) {
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					//if (rs.getString("CUSTOMER_NO").equals(customerNo))
					result = rs.getString("CUSTOMER_NAME") + "님의 잔여 포인트는 " + rs.getString("POINT_") + "입니다.";
						System.out.println(result);
						packet.setSentence(result);
				}
				}
				else if(num == REMOVE_NUM || num == MODIFY_NUM) {
					stmt.executeUpdate(query);
					if(num == REMOVE_NUM) {
						packet.setSentence("모든 데이터를 삭제하였습니다.");
					}
					else if(num == MODIFY_NUM) {
						packet.setSentence("해당 고객 정보를 수정하였습니다.");
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	

		/* ----------3..PointLookUp----------- */
	 
		public void pointChk(String querypart, Packet packet) {
			conNo2DB("SELECT POINT_ , CUSTOMER_NAME FROM " + CUSTOMER_TABLE_NAME + " " + querypart , LOOKUP_NUM , packet);
		}
		/* ----------3.PointLookUp----------- */
	 
	
	/* ----------4.PointModify----------- */
	 
	public void pointMod(String querypart , Packet packet) {
		System.out.println("querypart :" + querypart);
		conNo2DB("UPDATE CUSTOMER_TABLE SET POINT_ = "+ packet.getNewPoint() + querypart, MODIFY_NUM, packet );
	}
	/* ----------4.PointModify----------- */

	
	
	
	
	/* ----------5.Remove ConData---------- */
	
	public void remove(String querypart , Packet packet) {
		conNo2DB("DELETE" + CUSTOMER_TABLE_NAME + querypart,REMOVE_NUM, packet);	
	}
	/* ----------5.Remove ConData----------- */
	
	
	
	
	
	/* ----------6.Show All Lsit----------- */
	 
	public void showList(Packet packet) throws SQLException {
		
		try {
			rs = printCustomers(packet);
			if(rs == null) {
				return;
			}
			while (rs.next()) {
				packet.getArrayList().add(rs.getString(CUSTOMER_TABLE_ATTRS[0]) + "\t\t" + rs.getString(CUSTOMER_TABLE_ATTRS[1])
						+ "\t\t" + rs.getString(CUSTOMER_TABLE_ATTRS[2]) + "\t\t" + rs.getString(CUSTOMER_TABLE_ATTRS[3]));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	/* ----------6.Show All List----------- */
	
	 
	 public ResultSet printCustomers(Packet packet) throws IOException {
			query = "SELECT * FROM " + CUSTOMER_TABLE_NAME;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				return rs;
			} catch (SQLException e) {
				e.printStackTrace();
				
				packet.setError(CONNECTION_FAILED);
				packet.setErrorExist(true);
				return null;
			}
			
		}
	
	 
	 
	 /* ----------6.statistic sum & avg ----------- */
	 
	 public void statistic(Packet packet)
	 {	
		 
		 int menu = packet.getMenuNo();
		 char gender = packet.getGender();
		 
		if(menu == 1) {
			try {
				stmt = conn.createStatement();
				System.out.println("SELECT SUM(POINT_) FROM CUSTOMER_TABLE WHERE GENDER = " + "'" + gender + "'");
				rs = stmt.executeQuery("SELECT SUM(POINT_) FROM CUSTOMER_TABLE WHERE GENDER = " + "'" + gender + "'");
				while(rs.next()) {
					//System.out.println(gender + " : 포인트 총합은 " + rs.getString("SUM(POINT_)")+ "입니다.");
					packet.setSentence(gender + " : 포인트 총합은 " + rs.getString("SUM(POINT_)")+ "입니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				packet.setErrorExist(true);
				packet.setError("포인트를 조회할 수 없습니다.");
				System.out.println("포인트를 조회할 수 없습니다.");
			}
		}
		else if(menu == 2) {
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT AVG(POINT_) FROM CUSTOMER_TABLE WHERE GENDER = " + "'" + gender + "'");
				while(rs.next()) {
					//System.out.println(gender + " : 포인트 평균은 " + rs.getString("AVG(POINT_)")+ "입니다.");
					packet.setSentence(gender + " : 포인트 평균은 " + rs.getString("AVG(POINT_)")+ "입니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				packet.setErrorExist(true);
				packet.setError("포인트를 조회할 수 없습니다.");
				System.out.println("포인트를 조회할 수 없습니다.");
			}
		}
	 }
	 
	 
	 
	 public void deleteAll(Packet packet)
	 {	String query ="DELETE FROM" + CUSTOMER_TABLE_NAME;
		 System.out.println("모든 고객정보를 삭제하시겠습니까? [y/n]");
		 char answer = sc.next().charAt(0);
		 if(answer == 'y' || answer == 'Y') {
			 conNo2DB(query, REMOVE_NUM,packet);
		 }
		 else if (answer =='N' || answer =='n') {
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
			e.printStackTrace();
		}
		
	}
	

}













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


