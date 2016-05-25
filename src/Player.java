import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
//This is the playable character so there can only be one of them
public class Player {
	
	private String name ="";
	private static boolean instance = false;
	private String[] inventory= new String[10];
	private int health =50;
	private int money = 0;
	private int x=0;
	private int y=0;
	
	public Player (String name) throws IllegalArgumentException{

		if(!instance){
		this.name =name;
		Arrays.fill(inventory,"");
		instance = true;
		}
		else{
			throw new IllegalArgumentException("A player has already been created in the world");
		}
	}
	public Player(Player other) throws IllegalArgumentException{
		if(!instance){
		name=other.getName();
		Arrays.fill(inventory, "");
		instance = true;
		}
		else{
			throw new IllegalArgumentException("A player has already been created in the world");
		}
	}
	//Can load a player's data from a file.
	public Player(File file) throws FileNotFoundException{
		Scanner fromFile = new Scanner(file);
		name =fromFile.nextLine();
		health = fromFile.nextInt();
		money = fromFile.nextInt();
		x = fromFile.nextInt();
		y = fromFile.nextInt();
		String inventoryString = fromFile.nextLine();
		inventory = inventoryString.split(",");
		
	}
	public void savePlayer() throws IOException{
		File dir = new File("saves");
		if(!dir.exists()){
			dir.mkdir();
		}
		File file = new File("saves/"+name+".txt");
		if(!file.exists()){
			file.createNewFile();
		}
		PrintWriter write = new PrintWriter(file);
		write.println(name);
		write.println(health);
		write.println(money);
		write.println(x);
		write.println(y);
		write.println(Arrays.toString(inventory).substring(1,Arrays.toString(inventory).length()-1));
		write.flush();
		write.close();
		
	}
	public void move(){
		//TODO add this in when I start reading keyboard input and using a canvas
	}
	public boolean equals(Object that){
		return false;  //The player cannot be equal to anything in the world, there is only one player. It is a singleton.
	}
	public String getName() {
		return name;
	}
	public String[] getInventory() {
		return inventory;
	}
	public void addToInventory(String item){
		for(int i =0;i<inventory.length;i++){
			if(inventory[i]==""){
				inventory[i]=item;
				break;
			}
		}
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
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
	public void attack(Weapon weapon, Enemy enemy){
		int weaponDamage = weapon.getDamage();
		enemy.setHealth(enemy.getHealth()-weaponDamage);
		if(enemy.getHealth()<=0){
			enemy = null;
		}
		
	}
	public String toString(){
		return "Name: "+name+"\nCurrent Health: "+health+"\nMoney: "+money +"$"+"\nIs Holding: "+Arrays.toString(inventory)+"\nCoordinates: ("+x+","+y+")";
		
	}
}
