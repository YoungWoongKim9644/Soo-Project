
import java.util.*;

public class ReviseInfo {

	ArrayList<ConsumerInfo> arraylist;
	ConsumerInfo tmp;
	Scanner sc = new Scanner(System.in);
	int object_num;

	ReviseInfo(Object m) {
		this.arraylist = (ArrayList<ConsumerInfo>) m;
	}

	void selection() {
		int i = 0;
		int num;
		System.out.println("바꿀 고객의 번호를 입력하세요.");
		num = sc.nextInt();
		do {
			if (arraylist.size() == 0 || i >= arraylist.size()) {
				System.out.println("조회가능한 고객이 없습니다.");
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
		if (n == 1) {
			System.out.println("바꿀 정보를 입력하세요.");
			revise_name(object_num, sc.next());
		} else if (n == 2) {
			System.out.println("바꿀 정보를 입력하세요.");
			revise_sex(object_num, sc.next().charAt(0));
		} else if (n== 3) {
			System.out.println("바꿀 정보를 입력하세요.");
			revise_point(object_num, sc.nextInt());
		} else if (n == 4) {
			System.out.println("바꿀 정보를 입력하세요.");
			revise_number(object_num, sc.nextInt());
		} else {
			System.out.println("잘못 입력하셨습니다.");
		}
	}

	private void revise_name(int object_num, String name) {
		tmp = arraylist.get(object_num);
		arraylist.remove(object_num);
		tmp.setName(name);
		arraylist.add(object_num, tmp);
	}

	private void revise_sex(int object_num, char sex) {
		tmp = arraylist.get(object_num);
		arraylist.remove(object_num);
		tmp.setSex(sex);
		arraylist.add(object_num, tmp);
	}

	private void revise_point(int object_num, int point) {
		tmp = arraylist.get(object_num);
		arraylist.remove(object_num);
		tmp.setPoint(point);
		arraylist.add(object_num, tmp);
	}

	private void revise_number(int object_num, int point) {
		tmp = arraylist.get(object_num);
		arraylist.remove(object_num);
		tmp.setPoint(point);
		arraylist.add(object_num, tmp);
	}

}
