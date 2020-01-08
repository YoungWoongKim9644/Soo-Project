import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ConsumerManage implements MainMenu {

	ArrayList<ConsumerInfo> arraylist;
	ConsumerInfo tmp;
	static Scanner sc = new Scanner(System.in);
	int object_num;
	static FileWriter fout = null;
	static FileReader fin = null;
	static File file = new File("C:\\Sample.txt");
	
	ConsumerManage(Object m) {
		this.arraylist = (ArrayList<ConsumerInfo>) m;
			}

	static void signUp(ArrayList<ConsumerInfo> arraylist) {
		String name;
		int point;
		char sex;
		int number;
		System.out.println("회원 이름 :");
		name = sc.next();
		System.out.println("회원 포인트");
		point = sc.nextInt();
		System.out.println("회원 성별 [M/F]");
		sex = sc.next().charAt(0);
		while (!(sex == 'M' || sex == 'm' || sex == 'f' || sex == 'F')) {
			System.out.println(UNDEFINED_CHARACTER_MSG);
			sex = sc.next().charAt(0);
		}

		System.out.println("회원 번호");
		number = sc.nextInt();
		
		arraylist.add(new ConsumerInfo(number, name, sex, point));
		try {
			fout = new FileWriter(file,true);
			//fout.write("hello");
			fout.write(FileManagement.ObjtoStr(new ConsumerInfo(number, name, sex, point)));
			fout.write("\n");
			fout.close();
		}
		catch(IOException e) {
			System.out.println("입출력 오류");
		}

	}

	void selection() throws IOException {
		int i = 0;
		int num;
		System.out.println(MODIFY_CUSTOMERINFO_MSG);
		num = sc.nextInt();
		do {
			if (arraylist.size() == 0 || i >= arraylist.size()) {
				System.out.println(NO_EXIST_CUSTOMER_MSG);
				return;
			}
		} while (num != arraylist.get(i++).getNumber());
		this.object_num = i - 1;

		System.out.println("바꿀 정보를 선택해주세요.");
		System.out.println("1. 이름");
		System.out.println("2. 성별");
		System.out.println("3. 포인트");
		System.out.println("4. 번호");

		int n = sc.nextInt();
		System.out.println("바꿀 정보를 입력하세요.");
		if (n == 1) {
			revise_name(object_num, sc.next());
		} else if (n == 2) {
			revise_sex(object_num, sc.next().charAt(0));
		} else if (n == 3) {
			revise_point(object_num, sc.nextInt());
		} else if (n == 4) {
			revise_number(object_num, sc.nextInt());
		} else {
			System.out.println("잘못 입력하셨습니다.");
		}
	}

	private void revise_name(int object_num, String name) throws IOException {
		arraylist.get(object_num).setName(name);
		FileManagement.FLineRW(arraylist, object_num);
	}

	private void revise_sex(int object_num, char sex) throws IOException {
		arraylist.get(object_num).setSex(sex);
		FileManagement.FLineRW(arraylist, object_num);
	}

	private void revise_point(int object_num, int point) throws IOException {
		arraylist.get(object_num).setPoint(point);
		FileManagement.FLineRW(arraylist, object_num);
	}

	private void revise_number(int object_num, int point) throws IOException {
		arraylist.get(object_num).setNumber(point);
		FileManagement.FLineRW(arraylist, object_num);
	}

	public void show_index_component(int i) {
		System.out.print("이름 : " + arraylist.get(i).getName());
		System.out.print(" 성벌 : " + arraylist.get(i).getSex());
		System.out.print(" 포인트 : " + arraylist.get(i).getPoint());
		System.out.print(" 번호 : " + arraylist.get(i).getNumber());
		System.out.println();
	}

	int num_search(int num) {
		int i = 0;
		while (true) {
			if (i > arraylist.size() || arraylist.size() == 0) {
				System.out.println("해당 정보를 찾을 수 없습니다.");
				return -1;
			}
			tmp = arraylist.get(i);
			if (num == tmp.getNumber()) {
				return arraylist.indexOf(tmp);
			}
			i++;
		}
	}

	public void point_chk1(int i) {
		System.out.println("변경할 포인트를 입력해주세요.");
		arraylist.get(i).setPoint(sc.nextInt());
	}

	public void point_chk2(int i) {
		System.out.println("고객님의 포인트는 " + arraylist.get(i).getPoint() + "입니다.");
	}

	public void point_chk3(int i) {
		arraylist.remove(i);
		if (arraylist.size() == 0)
			System.out.println("표시할 고객이 없습니다.");
	}

}
