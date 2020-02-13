package chat.client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


import SenderAndReceiver.*;


public class CliConsumerManage implements MainMenu {
	
	ConsumerInfo tmp;
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
	static Scanner sc; 
	int object_num;
	boolean islogIn;
	private String url;
	private String hashType;
	private Connection conn;
	private String query;
	private Statement stmt;
	ResultSet rs;
	Packet packet;
	
	
	public CliConsumerManage(ObjectInputStream in, ObjectOutputStream out, Packet packet) {
		CliConsumerManage.sc =new Scanner(System.in);
		this.packet =packet;
		this.url = "jdbc:oracle:thin:@localhost:1521:orcl";
		this.conn = null;
		this.hashType = "SHA-256";
		this.query = null;
		this.stmt = null;
		this.rs = null;
		this.in = in;
		this.out = out;
		this.islogIn = false;
		packet = new Packet();
		
	}
	
	
	public boolean connection()
	{
		try {
			System.out.println("사용자 이름을 입력하세요");  packet.setId(sc.nextLine());
			System.out.println("사용자 패스워드를 입력하세요"); packet.setPassword( sc.nextLine());
			out.writeObject(packet);
			out.flush();
			
			packet = (Packet)in.readObject();
			System.out.println(packet.getSentence());
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return packet.getBool();
		
	}
	
	/* ----------1.SingUp----------- */
	 public void signUp() throws SQLException {
		String name;
		int point;
		char sex;
		int number;
		
		System.out.println("회원 이름 :"); packet.setName(sc.next()); 
		System.out.println("회원 포인트");	packet.setPoint(sc.nextInt());
		System.out.println("회원 성별 [M/F]"); packet.setGender(sc.next().charAt(0));
		sex = packet.getGender();
		while (!(sex == 'M' || sex == 'm' || sex == 'f' || sex == 'F')) {
			System.out.println(UNDEFINED_CHARACTER_MSG);
			packet.setGender(sc.next().charAt(0));
		}
		try {
			
		do
		{System.out.println("회원 번호"); packet.setNumber(sc.next());
		}
		while((isAleadNum(packet)));
		
		
			out.writeObject(packet);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/* ----------1.SingUp----------- */

	
	 public boolean isAleadNum(Packet packet)
		{
		 	
			try {
				System.out.println("보낼 때 : "+ packet.getNumber());
				out.writeObject(packet);
				out.flush();
				
				packet = (Packet)in.readObject();
				} 
				catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			System.out.println("return" + packet.getBool());
			return packet.getBool();
		} 
	 

	public boolean adminlogin( ) {
		
		if(!islogIn){
			String adminID = null;
			String adminPW = null;
			System.out.println("admin 계정이 필요합니다.");
			System.out.println("ID : "); adminID = sc.next();
			System.out.println("PW : "); adminPW = sc.next();
			packet.setAdminID(adminID);
			packet.setAdminPW(adminPW);
			packet.setIslogin(islogIn);
			

			
			try {
				System.out.println("옵니까?");
				out.writeObject(packet);
				out.flush();
				
				packet = (Packet)in.readObject();
				System.out.println("옵니까2");
				
			} catch (IOException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println(packet.getSentence());
			return packet.Islogin();
			
			
			
		
			}
		
			else if(islogIn) {
				char log;
				System.out.println("이미 로그인 되어있습니다.");
				System.out.println("로그아웃 하시겠습니까? [y/n]");
				log = sc.next().charAt(0);
				if(log == 'Y' || log == 'y') {
					islogIn = false;
					return false;
				}
				else {
					return true;
				}
			}
		return false;
	}
	 
	 
	 
	 
	 
	 
	/* ----------2.Modify----------- */
	public void selection() throws IOException, SQLException {
		
		
		if(!adminlogin()) {
			return;
		}
		
		
		System.out.println(MODIFY_CUSTOMERINFO_MSG);//고객번호입력
		String num;	num = sc.next();
		for ( String menu: MODIFYMENUS)//바꿀정보선택
		System.out.println(menu);
		int n = sc.nextInt();
		System.out.println("바꿀 정보를 입력하세요.");//바꿀 정보입력
		packet.setNumber(num);
		packet.setMenuNo(n);
		packet.setNewInfo(sc.next());
		out.writeObject(packet);
		out.flush();
		try {
			packet = (Packet)in.readObject();
			System.out.println(packet.getSentence());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	
	
	
	
	/* ----------(3).Numsearch----------- */
	String num_search(int num) {
		return " WHERE CUSTOMER_NO =" + "'" + num + "'";
	}
	/* ----------(3).Numsearch----------- */
	
	
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
		public void point_chk1(String querypart) {
			connumberToDB("SELECT POINT_ , CUSTOMER_NAME FROM " + CUSTOMER_TABLE_NAME + " " + querypart , LOOKUP_NUM);
		}
		/* ----------3.PointLookUp----------- */
	 
	
	/* ----------4.PointModify----------- */
	public void point_chk2(String querypart) {
		System.out.println("querypart :" + querypart);
		System.out.println( ENTER_POINT_TO_CHANGE);
		int point = sc.nextInt();
		connumberToDB("UPDATE CUSTOMER_TABLE SET POINT_ = "+ point + querypart, MODIFY_NUM );
	}
	/* ----------4.PointModify----------- */

	
	
	
	
	/* ----------5.Remove ConData---------- */
	public void point_chk3(String querypart) {
		connumberToDB("DELETE" + CUSTOMER_TABLE_NAME + querypart,REMOVE_NUM);	
	}
	/* ----------5.Remove ConData----------- */
	
	
	
	
	
	/* ----------6.Show All Lsit----------- */
	public void showList() throws SQLException, IOException {
		
			if(in.readBoolean()){
				in.readUTF();
				return;
			}
			
		try {
			do {
				packet = (Packet)in.readObject();
				System.out.println(packet.getSentence());
			}while(packet.getBool());
	
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	/* ----------6.Show All List----------- */
	
	
	
	
	 
	 
	 /* ----------6.statistic sum & avg ----------- */
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


