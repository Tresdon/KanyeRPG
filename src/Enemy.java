import java.util.Random;

public class Enemy {
	
	private int health;
	private String name = "";
	private int damage;
	private int x=0;
	private int y=0;
	
	public Enemy(int level) throws IllegalArgumentException{
		Random rand = new Random();
		int numToUse;
		switch(level){
		
		case 1:
			this.health=10;
			this.damage=3;
			
			numToUse = rand.nextInt(3);
			if(numToUse==0){
				this.name = "Spider";
			}
			if(numToUse==1){
				this.name = "Lizard";
			}
			if(numToUse==2){
				this.name = "Rat";
			}
			break;
		case 2:
			this.health=20;
			this.damage=4 ;
			numToUse = rand.nextInt(2);
			if(numToUse==0){
				this.name = "Hornet";
			}
			if(numToUse==1){
				this.name = "Pig";
			}
			break;
		case 3:
			this.health=35;
			this.damage=6;
			numToUse = rand.nextInt(2);
			if(numToUse==0){
				this.name = "Wolf";
			}
			if(numToUse==1){
				this.name = "Lion";
			}
			break;
		case 4:
			this.health=50;
			this.damage=8;
			numToUse = rand.nextInt(2);
			if(numToUse==0){
				this.name = "Wraith";
			}
			if(numToUse==1){
				this.name = "Ghoul";
			}
			break;
		case 5:
			this.health=75;
			this.damage=12;
			numToUse = rand.nextInt(2);
			if(numToUse==0){
				this.name = "Fire Golem";
			}
			if(numToUse==1){
				this.name = "Razorbeak";
			}
			break;
			//Special Bosses
		case 10:
			this.health=40;
			this.damage=16;
			this.name = "Gromel";
			break;
		case 11:
			this.health=200;
			this.damage=4;
			this.name = "Arachnos";
			break;
		case 12:
			this.health=200;
			this.damage = 30;
			this.name = "The Destroyer";
			break;
		default:
			throw new IllegalArgumentException("Must insert a value between 1-5 or 10-12");
		}
	}
	//Copy Constructor
	public Enemy(Enemy other){
		this.name=other.getName();
		this.damage=other.getDamage();
		this.health=other.getHealth();
	}
	//getters and setters
	
	
	public int getHealth(){
		return this.health;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getName(){
		return this.name;
	}
	public int getDamage(){
		return this.damage;
	}
	public void setHealth(int n){
		this.health = n;
	}
	public void attack(Player player){  //Attacking a player with no armor
		player.setHealth(player.getHealth()-damage);
	}
	public void attack(Armor armor,Player player){
		player.setHealth(player.getHealth()-armor.amountToDefend(damage));
	}
	public boolean equals(Object other){
		Enemy enemyOther = (Enemy)other;
		if(enemyOther.getClass()!=this.getClass()||enemyOther==null){
			return false;
		}
		else if(this.name==enemyOther.getName()){
			return true;
		}
		else{
			return false;
		}
	}
	public String toString(){
		return "Name: "+name+"\nHealth: "+health+"\nDamage: "+damage;
	}
	
}
