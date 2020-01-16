
public interface MainMenu {
	
	public static int MENU_NUM = 7;
	public static int SIGNUP = 1;
	public static int REVISION = 2;
	public static int POINT_REFER = 3;
	public static int POINT_MODIFY = 4;
	public static int REMOVE_INFO = 5;
	public static int TOTAL_ACCOUNT = 6;
	public static int POINT_LOOKUP_NUM = 0;
	public static int POINT_MODIFY_NUM = 1;
	public static int REMOVE_INFO_NUM = 2;
	
	public static final String menu[] = {	
			
		"메뉴를 선택하세요\n",
		"1. 회원 등록",
		"2. 회원 정보 수정",
		"3. 고객 포인트 조회",
		"4. 고객 포인트 변경",
		"5. 고객 정보 제거",
		"6. 전체 보기",
	};
	public static final String UNDEFINED_CHARACTER_MSG = "m 또는 f만 입력할 수 있습니다.";
	public static final String NO_EXIST_CUSTOMER_MSG = "조회가능한 고객이 없습니다.";
	public static final String MODIFY_CUSTOMERINFO_MSG = "바꿀 고객님의 고객 번호를 입력하세요.";
	public static final String POINT_MENU[] = {
			"포인트 조회를 하시고자 하는 고객 번호를 입력해주세요",
			"포인트 변경을 하고자 하는 고객 번호를 입력해주세요. ",
			"삭제할 고객의 번호를 입력해주세요.",
			};
	
}
