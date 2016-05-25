import java.util.Scanner;
import java.util.Random;
public class AutoRun {


	public static void main(String[] args) throws IllegalArgumentException {
		playGame();
	}
	private static void playGame() throws IllegalArgumentException{

		System.out.println("This is the Version of 'Generic RPG' where some things will be done automatically");
		System.out.println("The program will do some things on it's own to test interaction between the elements");
		System.out.println("Also in order to test whether or not the method calls work");
		System.out.println("And occasionally you the user will be asked to provide some input");
		System.out.println("Type 'y' to begin playing, anything else will terminate the program");
		Scanner in = new Scanner(System.in);
		if(!in.next().equalsIgnoreCase("y")){
			System.exit(0);
		}
		System.out.println("What do you want to name your character?");
		String playerName = in.next();
		Player player = new Player(playerName);
		System.out.println("Your character is named: "+playerName);
		System.out.println(playerName+" needs an occupation! What would you like your player to be?");
		System.out.println("Valid commands are: 'beggar','blacksmith','king' anything else will terminate the program.");
		String jobString = in.next().toLowerCase();
		if(!jobString.equals("beggar")&&!jobString.equals("blacksmith")&&!jobString.equals("king")){
			System.out.println("Invalid command, the system will now exit");
			System.exit(1);
		}
		else if(jobString.equals("king")){
			player.setMoney(player.getMoney()+100);
		}
		else if(jobString.equals("blacksmith")){
			player.setMoney(player.getMoney()+50);
		}
		else{
			player.setMoney(player.getMoney()+10);
		}
		System.out.println(playerName +" is now a "+jobString);
		System.out.println(playerName+" needs a weapon, so he goes to the weapon shop.");
		System.out.println("What weapon would you like to buy?, you have " +player.getMoney()+" Gold");
		System.out.println("Stick: 0 Gold \nBaseball Bat: 30 Gold \nMace: 90 Gold");
		System.out.println("Valid commands are 'stick', 'bat', and 'mace', anything else will terminate the program.");
		String weaponString;
		while(true){
			weaponString = in.next().toLowerCase().trim();
			if(weaponString.equals("stick")){
				break;
			}
			if(weaponString.equals("bat")){
				if(player.getMoney()>=30){
					player.setMoney(player.getMoney()-30);
					break;
				}
				else{
					System.out.println("You do not have enough money for that! What would you like to buy?");

				}
			}
			if(weaponString.equals("mace")){
				if(player.getMoney()>=90){
					player.setMoney(player.getMoney()-90);
					break;
				}
				else{
					System.out.println("You do not have enough money for that! What would you like to buy?");
				}
			}
		}
		Weapon weapon = new Weapon(1);
		if(!weaponString.equalsIgnoreCase("stick")&&!weaponString.equalsIgnoreCase("bat")&&!weaponString.equalsIgnoreCase("mace")){
			System.out.println("Invalid Command, the program will now exit");

			System.exit(1);
		}
		else if(weaponString.equalsIgnoreCase("stick")){
			weapon = new Weapon(1);
			player.addToInventory(weapon.getType());
		}
		else if(weaponString.equalsIgnoreCase("bat")){
			weapon = new Weapon(2);
			player.addToInventory(weapon.getType());
		}
		else if(weaponString.equalsIgnoreCase("mace")){
			weapon = new Weapon(4);
			player.addToInventory(weapon.getType());
		}
		System.out.println("The current state of your character is: \n"+player.toString()+"\n");
		System.out.println("Type 'y' to continue the game,  anything else will terminate the program.");
		if(!in.next().toLowerCase().trim().equals("y")){
			System.out.println("The game will now exit.");
			System.exit(1);
		}
		System.out.println("Oh no, an enemy has appeared!");
		Random rand = new Random();
		Enemy enemy = new Enemy(1+rand.nextInt(3));
		System.out.println("The enemy is a: "+enemy.getName());
		System.out.println("The details of the "+enemy.getName()+" are: \n"+enemy.toString());
		System.out.println("You attack the enemy with your weapon.");
		System.out.println("Your "+weapon.getType()+" does "+weapon.getDamage()+" damage");
		player.attack(weapon, enemy);
		System.out.println("The "+enemy.getName()+" now has "+enemy.getHealth()+" health left.");
		System.out.println("The "+enemy.getName()+" attacks you back!");
		enemy.attack(player);
		System.out.println("Your health is now: "+player.getHealth());
		System.out.println("Would you like to keep fighting or would you like to run from the "+enemy.getName());
		System.out.println("Valid commands here are 'run' and 'fight' ");
		String runString =in.next();
		if(!runString.equalsIgnoreCase("run")&&!runString.equalsIgnoreCase("fight")){
			System.out.println("Invalid input, Program will now exit");
			System.exit(1);
		}
		else if(runString.equalsIgnoreCase("run")){
			System.out.println("You just barely got out of that one!");
		}
		else{
			while(player.getHealth()>0&&enemy.getHealth()>0){
				player.attack(weapon, enemy);
				if(enemy.getHealth()>0){
					enemy.attack(player);
					if(player.getHealth()<=0){
						System.out.println("You have died! Game over.");
						System.exit(0);
					}
				}
				else{
					System.out.println("You have slain the enemy!");
					System.out.println("You receive 30 gold for killing that "+enemy.getName());
					player.setMoney(player.getMoney()+30);
					break;
				}

			}

		}
		System.out.println("Your health now is: "+player.getHealth());
		System.out.println("Time to continue your adventure. Press 'y' to continue, anything else will terminate the program.");
		if(!in.next().trim().equalsIgnoreCase("y")){
			System.exit(0);
		}
		System.out.println("Time to go to an armor shop");
		System.out.println("What kind of armor would you like?");
		System.out.println("Cloth: 0 Gold\nCopper:20 Gold\nSilver:40 Gold");
		System.out.println("You have "+player.getMoney()+" Gold");
		System.out.println("valid commands here are 'cloth', 'copper', and 'silver'");
		String armorString = in.next().trim().toLowerCase();
		Armor armor = new Armor(1);
		if(armorString.equals("cloth")){
			player.addToInventory("Cloth Armor");
			armor = new Armor(1);
		}
		else if(armorString.equals("copper")){
			if(player.getMoney()>=20){
				armor = new Armor(2);
				player.addToInventory("Copper Armor");
				player.setMoney(player.getMoney()-20);
			}
			else{
				System.out.println("You can't afford that, you only have "+player.getMoney()+" Gold");
			}
		}
		else if(armorString.equals("silver")){
			if(player.getMoney()>=40){
				armor = new Armor(4);
				player.addToInventory("Silver Armor");
				player.setMoney(player.getMoney()-40);
			}
			else{
				System.out.println("You can't afford that, you only have "+player.getMoney()+" Gold");
			}
		}
		else{
			System.out.println("Invalid command, the program will now exit.");
			System.exit(0);
		}
		System.out.println("Here is what your character currently looks like: \n"+player.toString()+"\n");
		System.out.println("Would you like to continue the game? 'y' to continue, anything else will terminate");
		if(!in.next().trim().equalsIgnoreCase("y")){
			System.exit(0);
		}
		System.out.println("ANOTHER ENEMY APPEARS!");
		Enemy enemy2 = new Enemy(4);
		System.out.println("Here are the details of the enemy:\n"+enemy2.toString());
		System.out.println("The "+enemy2.getName()+" comes straight at you! There is nowhere to hide, you must fight!\n");
		
		while(player.getHealth()>0&&enemy2.getHealth()>0){
			player.attack(weapon, enemy2);
			if(enemy2.getHealth()>0){
				enemy2.attack(armor,player);
				if(player.getHealth()<=0){
					System.out.println("You have died! Game over.");
					System.exit(0);
				}
			}
			else{
				System.out.println("You have slain the enemy!");
				break;
			}

		}
		System.out.println("You lived with: "+player.getHealth()+" health");
		System.out.println("Congratulations! you won the game!");
		System.out.println("This is the end of the adventure, now feel free to use any command you wish for the rest of the time");
		System.out.println("Type 'help' to get a list of commands, type 'quit' to exit");
		while(true){

			String line = in.nextLine().trim().toLowerCase();
			if(line.equals("quit")){
				System.exit(0);
			}
			else if(line.equals("help")){
				System.out.println("Help: Shows this menu\nQuit: Exits the program\nEnemy: Shows the details of the enemies fought.\nPlayer: Shows the details of the current player"
						+ "\nArmor: Shows the details of the current armor being worn\nWeapon:Shows the current weapon that is equipped");
			}
			else if(line.equals("player")){
				System.out.println(player.toString());
			}
			else if(line.equals("enemy")){
				System.out.println(enemy.toString());
				System.out.println(enemy2.toString());
			}
			else if(line.equals("armor")){
				System.out.println(armor.toString());
			}
			else if(line.equals("weapon")){
				System.out.println(weapon.toString());
			}
		}
	}

}
