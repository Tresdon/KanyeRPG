
public class Weapon {
	
	private int quality=15;
	private String type ="";
	private int damage= 0;
	
	public Weapon(int level){
	
		switch(level){
		case 1:
			this.type="Stick";
			this.damage=1;
			break;
		case 2:
			this.type ="Baseball Bat";
			this.damage =2;
			break;
		case 3:
			this.type = "Fire Axe";
			this.damage = 5;
			break;
		case 4:
			this.type="Mace";
			this.damage=8;
			break;
		case 5:
			this.type="Excelsior";
			this.damage=12;
			break;
		}
	}
	public Weapon(Weapon other){
		this.type=other.getType();
		this.damage = other.getDamage();
	}
	public String getType(){
		return this.type;
	}
	public int getDamage(){
		return this.damage;
	}
	public int getQuality(){
		return this.quality;
	}
	public void setType(int level){
		switch(level){
		case 1:
			this.type="Stick";
			this.damage=1;
			break;
		case 2:
			this.type ="Baseball Bat";
			this.damage =2;
			break;
		case 3:
			this.type = "Fire Axe";
			this.damage = 5;
			break;
		case 4:
			this.type="Mace";
			this.damage=8;
			break;
		case 5:
			this.type="Excelsior";
			this.damage=12;
			break;
		}
	}
	public boolean equals(Object other){
		Weapon weaponOther = (Weapon)other;
		if(weaponOther.getClass()!=this.getClass()||weaponOther==null){
			return false;
		}
		else if(this.type==weaponOther.getType()){
			return true;
		}
		else{
			return false;
		}
	}
	public String toString(){
		return "Type of Weapon: "+type+"\nDamage the weapon does: "+	damage +"\nQuality of the weapon: "+quality;
	}
}
