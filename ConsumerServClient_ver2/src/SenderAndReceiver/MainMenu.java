package SenderAndReceiver;

public interface MainMenu {
	public static int MENU = 0;
	public static int NAME = 1;
	public static int POINT = 2;
	public static int GENDER = 3;
	public static int NUMBER = 4;
	public static int ADMINID = 5;
	public static int ADMINPW = 6;
	public static int ISLOGIN = 7;
	public static int BOOL = 8;
	public static int SENTENCE = 9;
	public static int NEWINFO = 10;
	public static int ERRORNUM = 11;
	
	public static int MENU_NUM = 9;
	public static int SIGNUP = 1;
	public static int REVISION = 2;
	public static int POINT_REFER = 3;
	public static int POINT_MODIFY = 4;
	public static int REMOVE_INFO = 5;
	public static int TOTAL_ACCOUNT = 6;
	public static int STATISTIC_FUNC = 7;
	public static int DELETE_ALL = 8;
	public static int QUIT_PROGRAM = 9;
	
	public static int POINT_LOOKUP_NUM = 0;
	public static int POINT_MODIFY_NUM = 1;
	public static int REMOVE_INFO_NUM = 2;
	public static int MODIFY_MENU_NUM = 5;
	public static int LOOKUP_NUM = 1;
	public static int REMOVE_NUM = 2;
	public static int MODIFY_NUM = 3;
	public static final String MENUS[] = {	
			
		"메뉴를 선택하세요\n",
		"1. 회원 등록",
		"2. 회원 정보 수정",
		"3. 고객 포인트 조회",
		"4. 고객 포인트 변경",
		"5. 고객 정보 제거",
		"6. 전체 보기",
		"7. 사용자 통계",
		"8. 전체 삭제",
		"9. 프로그램 종료"
		
	};
	
	
	public static final String MODIFYMENUS[] = {
			"바꿀 정보를 선택해주세요.",
			"1. 이름",
			"2. 성별",
			"3. 포인트",
			"4. 번호"
	};
	
	public static final String STATISTICMENUS[] = {
			"조회할 정보를 선택해주세요.",
			"1. 포인트 총 합",
			"2. 포인트 평균"
	};
	
	public static final String STATISTIC_GEN_MENUS[] = {
			"조회할 정보의 성별을 선택해주세요.",
			"1. 남",
			"2. 녀",
			"3. 총계"
	};
	
	
	
	
	public static final String UNDEFINED_CHARACTER_MSG = "m 또는 f만 입력할 수 있습니다.";
	public static final String ENTER_POINT_TO_CHANGE = "변경할 포인트를 입력해주세요.";
	public static final String CONNECTION_FAILED = "연결 실패";
	public static final String NO_EXIST_CUSTOMER_MSG = "조회가능한 고객이 없습니다.";
	public static final String MODIFY_CUSTOMERINFO_MSG = "바꿀 고객님의 고객 번호를 입력하세요.";
	public static final String CANT_FIND_DATA_MSG = "해당 정보를 찾을 수 없습니다.";
	public static final String PROGRAM_QUIT_MSG = "프로그램을 저장하고 종료합니다.";
	public static final String CON_NUM_ALEADY_EXIST_MSG = "해당 번호가 이미 존재합니다. 다시 입력해주세요";
	public static final String ADMIN_LOGIN = "관리자 계정에 로그인 하셨습니다.";
	public static final String ADMIN_LOGIN_FAILED = "아이디 혹은 비밀번호가 틀렸습니다.";
	public static final String POINT_MENU[] = {
			"포인트 조회를 하시고자 하는 고객 번호를 입력해주세요",
			"포인트 변경을 하고자 하는 고객 번호를 입력해주세요. ",
			"삭제할 고객의 번호를 입력해주세요.",
			};
	public static final String CUSTOMER_TABLE_NAME = "CUSTOMER_TABLE";
	public static final String[] CUSTOMER_TABLE_ATTRS = {
			 "CUSTOMER_NAME",
			 "CUSTOMER_NO",
			 //"PHONE_NUM",
			 "GENDER",
			 "POINT_"};
}
