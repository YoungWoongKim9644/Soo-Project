
public class Strategy {
	private Weapon weapon = null;
	
	void doAttack() {
		if(weapon == null) System.out.println("맨손공격");
		else weapon.attack();
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}	
}
