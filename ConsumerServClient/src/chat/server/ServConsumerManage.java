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
	Packet packet;
	
	public ServConsumerManage(ObjectInputStream in, ObjectOutputStream out) {
		Scanner sc =new Scanner(System.in);
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
		try {
			packet = (Packet)in.readObject();
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		username = packet.getId();
		password = packet.getPassword();
		try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(this.url, this.username, this.password);
		}
		catch(SQLException e){	e.printStackTrace();
		} catch (ClassNotFoundException e) {e.printStackTrace();
		}
		if(conn == null) {
			packet.setSentence("���ῡ �����߽��ϴ�.");
			packet.setBool(false);
			try {
				out.writeObject(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		else {
			packet.setSentence("���ῡ �����߽��ϴ�.");
			packet.setBool(true);
			try {
				out.writeObject(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
	}

	/* ----------1.SingUp----------- */
	
	 public void signUp(){
		 try {
				
				while((isAleadNum((Packet)in.readObject())));
				
				
				packet = (Packet)in.readObject();
				insertIntoDB(packet);
				
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		 
		 }
	 
	 /* ----------1.SingUp----------- */ 
	 
	 public boolean isAleadNum(Packet packet)
		{
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT customer_no FROM CUSTOMER_TABLE");
				while (rs.next()) {
					if (rs.getString("CUSTOMER_NO").equals(number)) {
						System.out.println(CON_NUM_ALEADY_EXIST_MSG);
						packet.setBool(true);
						return true;
					}
						
					}
				} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
			
		}
	 
	 
	 
	
		 
	 void insertIntoDB(Packet packet)  {
		 query = "INSERT INTO " + CUSTOMER_TABLE_NAME + " VALUES";
		  query = query + "('" + packet.getName() + "','" + String.valueOf(packet.getNumber()) + "','" + packet.getGender() + "'," + packet.getPoint()+")";
				 System.out.println(query);
				try {
					stmt = conn.createStatement();
					stmt.executeUpdate(query);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			} 
	 	 
	 
	 public boolean adminlogin( ) {
			
		try {
			System.out.println("��Ŷ�ޱ�");
			packet = (Packet)in.readObject();
			System.out.println("�޾���");
			
			String adminID = packet.getAdminID();
			String adminPW = packet.getAdminPW();
			
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT * FROM ADMIN_ACCOUNT");
				while (rs.next()) {
					if (rs.getString("ADMIN_ID").equals(adminID) && rs.getString("ADMIN_PW").equals(adminPW) ){
						packet.setSentence(ADMIN_LOGIN);
						packet.setIslogin(true);
						out.writeObject(packet);
						System.out.println("������");
						return true;
					}
					else {
						packet.setSentence(ADMIN_LOGIN_FAILED);
						packet.setIslogin(false);
						out.writeObject(packet);
						System.out.println("������");
						return false;
					}
				}			
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}	
			return false;
		}
	 
	 
	 
	 
	 
	/* ----------2.Modify----------- */
	public void selection() throws IOException, SQLException {
		
		if(!adminlogin()) {
			return;
		}
		
	
		try {
			packet = (Packet)in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String conNo = packet.getNumber();
		int menuNo = packet.getMenuNo();
		
		if (menuNo == 1) {updateToDB(conNo,"CUSTOMER_NAME",packet.getNewInfo());
		} else if (menuNo == 2) {updateToDB(conNo,"GENDER",packet.getNewInfo());
		} else if (menuNo == 3) {updateToDB(conNo,"POINT_",packet.getNewInfo());
		} else if (menuNo == 4) {updateToDB(conNo,"CUSTOMER_NO",packet.getNewInfo());;
		} else {	packet.setSentence("�߸� �Է��ϼ̽��ϴ�.");
		out.writeObject(packet);
		}
	
		
	}
	/* ----------2.Modify----------- */

	
	
	 
	 void updateToDB(String customerNo, String updateAttr,String updateValue) throws SQLException {
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
				packet.setSentence("�� ���� ������ �Ϸ��Ͽ����ϴ�.");
				out.writeObject(packet);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
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
						System.out.println(rs.getString("CUSTOMER_NAME") + "���� �ܿ� ����Ʈ�� " + rs.getString("POINT_") + "�Դϴ�.");
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
	 
	public void showList() throws SQLException {
		
		
		try {
			rs = printCustomers();
			while (rs.next()) {
				packet.setSentence(rs.getString(CUSTOMER_TABLE_ATTRS[0]) + "\t" + rs.getString(CUSTOMER_TABLE_ATTRS[1])
						+ "\t" + rs.getString(CUSTOMER_TABLE_ATTRS[2]) + "\t" + rs.getString(CUSTOMER_TABLE_ATTRS[3]));
				packet.setBool(true);
				out.writeObject(packet);
				out.flush();
			}
				packet.setBool(false);
				out.writeObject(packet);
				out.flush();
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	/* ----------6.Show All List----------- */
	
	 
	 public ResultSet printCustomers() throws IOException {
			query = "SELECT * FROM " + CUSTOMER_TABLE_NAME;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				out.writeBoolean(true);
				return rs;
			} catch (SQLException e) {
				e.printStackTrace();
				out.writeBytes(CONNECTION_FAILED);
				out.writeBoolean(false);
				out.flush();
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
	 	else {System.out.println("�߸� �Է��ϼ̽��ϴ�.");return;}
	 	
		if(menu == 1) {
			try {
				stmt = conn.createStatement();
				System.out.println("SELECT SUM(POINT_) FROM CUSTOMER_TABLE WHERE GENDER = " + "'" + gender + "'");
				rs = stmt.executeQuery("SELECT SUM(POINT_) FROM CUSTOMER_TABLE WHERE GENDER = " + "'" + gender + "'");
				while(rs.next()) {
					System.out.println(gender + " : ����Ʈ ������ " + rs.getString("SUM(POINT_)")+ "�Դϴ�.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("����Ʈ�� ��ȸ�� �� �����ϴ�.");
			}
		}
		else if(menu == 2) {
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT AVG(POINT_) FROM CUSTOMER_TABLE WHERE GENDER = " + "'" + gender + "'");
				while(rs.next()) {
					System.out.println(gender + " : ����Ʈ ����� " + rs.getString("AVG(POINT_)")+ "�Դϴ�.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("����Ʈ�� ��ȸ�� �� �����ϴ�.");
			}
		}
	 }
	 
	 
	 public void deleteAll()
	 {	String query ="DELETE FROM" + CUSTOMER_TABLE_NAME;
		 System.out.println("��� �������� �����Ͻðڽ��ϱ�? [y/n]");
		 char answer = sc.next().charAt(0);
		 if(answer == 'y' || answer == 'Y') {
			 connumberToDB(query, REMOVE_NUM);
		 }
		 else if (answer =='N' || answer =='n') {
			 return;
		 }
		 else {
			 System.out.println("�߸� �Է��ϼ̽��ϴ�.");
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
	
	*/
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
				System.out.println(rs.getString("CUSTOMER_NAME") + "���� �ܿ� ����Ʈ�� " + rs.getString("POINT_") + "�Դϴ�.");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}





*/


