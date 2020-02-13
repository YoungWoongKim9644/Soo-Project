package chat.client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
	private Connection conn;
	private Statement stmt;
	ResultSet rs;

	
	
	public CliConsumerManage(ObjectInputStream in, ObjectOutputStream out) {
		CliConsumerManage.sc =new Scanner(System.in);
		this.conn = null;
		this.stmt = null;
		this.rs = null;
		this.in = in;
		this.out = out;
		this.islogIn = false;

		
	}
	
	
	/* ----------1.SingUp----------- */
	 public void signUp(Packet packet) throws SQLException {
		char sex;
		
		System.out.println("회원 이름 :"); packet.setName(sc.next()); 
		System.out.println("회원 포인트");	packet.setPoint(sc.nextInt());
		System.out.println("회원 성별 [M/F]"); packet.setGender(sc.next().charAt(0));
		sex = packet.getGender();
		while (!(sex == 'M' || sex == 'm' || sex == 'f' || sex == 'F')) {
			System.out.println(UNDEFINED_CHARACTER_MSG);
			packet.setGender(sc.next().charAt(0));
		}
		
		do
		{System.out.println("회원 번호"); packet.setNumber(sc.next());
		}
		while((isAleadNum(packet)));
		
	}
	/* ----------1.SingUp----------- */

	
	 public boolean isAleadNum(Packet packet)
		{
		 	
			try {
				System.out.println("보낼 때 : "+ packet.getNumber());
				out.writeObject(packet);
				out.reset();
				
				
				
				
				packet = (Packet)in.readObject();
				if(packet.getError() != null)
				System.out.println(packet.getError());
				
				} 
				catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			System.out.println("return " + packet.isBool());
			return packet.isBool();
		} 
	 

	public boolean adminlogin(Packet packet ) {
		
		
		if(!packet.isIslogin()){
			String adminID = null;
			String adminPW = null;
			System.out.println("admin 계정이 필요합니다.");
			System.out.println("ID : "); adminID = sc.next();
			System.out.println("PW : "); adminPW = sc.next();
			 packet.setAdminID(adminID);
			 packet.setAdminPW(adminPW);
		
			try {
				System.out.println("옵니까?");
				out.writeObject(packet);
				out.reset();
				
				
				packet = (Packet)in.readObject();
				System.out.println("옵니까2");
				
			} catch (IOException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			
			if(packet.isErrorExist())
				System.out.println(packet.getError());
			
			System.out.println("연결에 성공했습니다.");
			return  packet.isIslogin();
			
			
			
		
			}
		
			else if(packet.isIslogin()) {
				char log;
				System.out.println("이미 로그인 되어있습니다.");
				System.out.println("로그아웃 하시겠습니까? [y/n]");
				log = sc.next().charAt(0);
				if(log == 'Y' || log == 'y') {
					packet.setIslogin(false);
					return false;
				}
				else {
					return true;
				}
			}
		return false;
	}
	 
	 
	 
	 
	 
	 
	/* ----------2.Modify----------- */
	public void selection(Packet packet) throws IOException, SQLException {
		
		
		if(!adminlogin(packet)) {
			return;
		}
		
		
		System.out.println(MODIFY_CUSTOMERINFO_MSG);//고객번호입력
		String num;	num = sc.next();
		for ( String menu: MODIFYMENUS)//바꿀정보선택
		System.out.println(menu);
		int n = sc.nextInt();
		System.out.println("바꿀 정보를 입력하세요.");//바꿀 정보입력
		packet.setNumber(num);
		packet.setInfoMenu(n);
		packet.setNewInfo(sc.next());
		out.writeObject(packet);
		out.reset();
		
		
		try {
			packet = (Packet)in.readObject();
			
			if(packet.isErrorExist())
				System.out.println(packet.getError());
			
			System.out.println(packet.getSentence());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/* ----------2.Modify----------- */


		/* ----------3..PointLookUp----------- */
		public void pointChk(Packet packet) {
			try {
				out.writeObject(packet);
				out.reset();
				
				
				packet = (Packet)in.readObject();
				System.out.println("lookup : " + packet.getMenuNo());
				System.out.println(packet.getSentence());
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		/* ----------3.PointLookUp----------- */
	 
	
	/* ----------4.PointModify----------- */
	public void pointMod(Packet packet) {
		
			System.out.println( ENTER_POINT_TO_CHANGE);
			int newpoint = sc.nextInt();
			packet.setNewPoint(newpoint);
		try {
			
			out.writeObject(packet);
			out.reset();
			
			
			packet = (Packet)in.readObject();
			System.out.println(packet.getSentence());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/* ----------4.PointModify----------- */

	
	
	
	
	/* ----------5.Remove ConData---------- */
	public void remove(Packet packet) {
		try {
			out.writeObject(packet);
			out.reset();
			
			
			packet = (Packet)in.readObject();
			System.out.println(packet.getSentence());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/* ----------5.Remove ConData----------- */
	
	
	
	
	
	/* ----------6.Show All Lsit----------- */
	public void showList(Packet packet) throws SQLException, IOException {
		
		int cnt;
		int i = 0;
		try {
				out.writeObject(packet);
				out.reset();
				
				
				packet = (Packet)in.readObject();
				System.out.println("list : " + packet.getMenuNo());
				cnt = packet.getArrayList().size();
				System.out.println("[이름]"  + "\t\t" +   "[번호]"  + "\t\t" +    "[성별]"  + "\t\t" +  "[포인트]");
			do {
				
				System.out.println(packet.getArrayList().get(i++));
				cnt--;
			}while(cnt >0 );
	
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	/* ----------6.Show All List----------- */
	
	
	
	
	 
	 
	 /* ----------6.statistic sum & avg ----------- */
	 public void statistic(Packet packet)
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
	 	
	 	packet.setGender(gender);
		if(menu == 1) {
			packet.setMenuNo(1);
		}
		else if(menu == 2) {
			packet.setMenuNo(2);
		}
		
		try {
			out.writeObject(packet);
			out.reset();
			
			
			packet = (Packet)in.readObject();
			
			if(packet.isErrorExist()) {
				System.out.println(packet.getError());
			}
			else {
				packet.getSentence();	
			}
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	 }
	 
	 
	 public void deleteAll(Packet packet)
	 {	
		 System.out.println("모든 고객정보를 삭제하시겠습니까? [y/n]");
		 char answer = sc.next().charAt(0);
		 if(answer == 'y' || answer == 'Y') {
			 
			 try {

				out.writeObject(packet);
				out.reset();
				
				
				
				packet = (Packet)in.readObject();
				
				if(packet.isErrorExist()) {
					System.out.println(packet.getError());
				}
				else {
					System.out.println(packet.getSentence());
				}
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		 
		 }
		 else if (answer =='N' || answer =='n') {
			 return;
		 }
		 else {
			 System.out.println("잘못 입력하셨습니다.");
			 return;
		 }
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	public void quit(Socket socket)
	{
		try {
			System.out.println(PROGRAM_QUIT_MSG);
			stmt.close();
			conn.close();
			socket.close();
			in.close();
			out.close();
			
		} catch (SQLException | IOException e) {
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


