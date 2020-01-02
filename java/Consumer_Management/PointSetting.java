
public class PointSetting extends Point {

	PointSetting(Object m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	public void point_chk()
	{
		int point=0;
		System.out.println("변경할 포인트를 입력해주세요.");
		point = sc.nextInt();
		tmp.setPoint(point);
	}
}
