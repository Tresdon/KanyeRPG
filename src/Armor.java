
public class Armor {

	private int quality=15;
	private String type ="";
	private int defense= 0;
	
	public Armor(Armor other){
		this.quality=other.getQuality();
		this.type =other.getType();
		this.defense=other.getDefense();
	}
	public Armor(int level){

		switch(level){
		case 1:
			this.type="Cloth Armor";
			this.defense=1;
			break;
		case 2:
			this.type ="Copper Armor";
			this.defense =2;
			break;
		case 3:
			this.type = "Bronze Armor";
			this.defense = 5;
			break;
		case 4:
			this.type="Silver Armor";
			this.defense=8;
			break;
		case 5:
			this.type="Titanium Armor";
			this.defense =12;
			break;
		default:
			System.out.println("Must be a value between 1 and 5");
		}
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public String getType() {
		return type;
	}
	public void setType(int level) {
		switch(level){
		case 1:
			this.type="Cloth Armor";
			this.defense=1;
			break;
		case 2:
			this.type ="Copper Armor";
			this.defense =2;
			break;
		case 3:
			this.type = "Bronze Armor";
			this.defense = 5;
			break;
		case 4:
			this.type="Silver Armor";
			this.defense=8;
			break;
		case 5:
			this.type="Titanium Armor";
			this.defense =12;
			break;
		default:
			System.out.println("Must be a value between 1 and 5");
		}
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public boolean equals(Object other){
		Armor armorOther = (Armor)other;
		if(armorOther.getClass()!=this.getClass()||armorOther==null){
			return false;
		}
		else if(getType()==armorOther.getType()){
			return true;
		}
		else{
			return false;
		}
	}
	public String toString(){
		return "Type of Armor: "+type+"\nDefense the armor provides: "+	defense +"\nQuality of the armor: "+quality;
	}
	public int amountToDefend(int n){ //The amount of damage coming through to the armor
		if(n-defense>0){
			return n-defense; //final damage going to the player.
		}
		else{
			return 1; //Must always do at least 1 damage to the player.
		}
	}
}
