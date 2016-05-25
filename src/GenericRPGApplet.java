import javax.swing.JApplet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.TextField;
import java.util.Random;


public class GenericRPGApplet extends JApplet implements Runnable,MouseListener {
	private Player player;
	private Thread t=null;
	private Image bg=null ;
	private TextField text;
	Enemy enemy1,enemy2,enemy3,enemy4;
	Random rand = new Random();
	Weapon weapon = new Weapon(1);
	Boolean ended = false;
	Decoration dec1,dec2,dec3,dec4,dec5,dec6;
	Armor armor;

//ANY METHOD THAT IS A DRAW METHOD SIMPLY DRAWS THAT IMAGE/TEXT/SHAPE AT A CERTAIN LOCATION	(e.g. "drawPlayer()")
	
	public void init(){
		resize(1100,600);
		try {
			player = new Player("");
			player.setX(300);
			player.setY(300);

		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		}
		addMouseListener(this);
		try {
			initializeEnemies();
			initializeDecorations();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}



	public void paint(Graphics canvas){

		if(!ended){
			drawBackground(canvas);
			drawDecorations(canvas);
			drawItems(canvas);
			drawMoney(canvas);

			drawHealth(canvas);
			drawPlayer(canvas);
			synchronized(this){
				drawEnemy(canvas);
			}
			if(player.getHealth()<=0){
				drawGameOver(canvas);

			}
			if(player.getMoney()>=200){
				drawWinScreen(canvas);
			}
		}

	}
	public void start(){
		if ( t == null ) {
			t = new Thread(this);
			t.start();
		}
	}

	public void run() {

		while(true){
			synchronized(this){
				try {
					handleEnemies();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				}

			}
			repaint();
			try {
				Thread.sleep(200);
			}
			catch(InterruptedException e){
				e.printStackTrace();
				break;
			}
		}

	}

	private void drawWinScreen(Graphics canvas){

		Font font = new Font("Verdana",Font.BOLD,22);
		canvas.setFont(font);
		canvas.setColor(Color.gray);
		canvas.fillRect(0,0,2000,2000);
		canvas.setColor(Color.red);
		canvas.drawString("YOU ARE A GOD.",getWidth()/2-100,getHeight()/2);
		ended=true;


	}
	private void handleEnemies() throws IllegalArgumentException{
		//method that spawns a new enemy if one of the enemies dies.
		if(enemy1.getHealth()<=0){
			enemy1 = new Enemy(1);
			enemy1.setX(rand.nextInt(900));
			enemy1.setY(rand.nextInt(500));
		}
		if(enemy2.getHealth()<=0){
			enemy2 = new Enemy(1);
			enemy2.setX(rand.nextInt(900));
			enemy2.setY(rand.nextInt(500));
		}
		if(enemy3.getHealth()<=0){
			enemy3 = new Enemy(2);
			enemy3.setX(rand.nextInt(900));
			enemy3.setY(rand.nextInt(500));
		}
		if(enemy4.getHealth()<=0){
			enemy4 = new Enemy(2);
			enemy4.setX(rand.nextInt(900));
			enemy4.setY(rand.nextInt(500));
		}
	}
	private void drawBackground(Graphics canvas){
		Color ourGreen = new Color(36,119,41);
		canvas.setColor(ourGreen);
		canvas.fillRect(0, 0, 2000,2000 );

	}
	//Puts the enemies in the game initially.
	private void initializeEnemies() throws IllegalArgumentException{
		enemy1 = new Enemy(1);
		enemy2 = new Enemy(1);
		enemy3 = new Enemy(2);
		enemy4 = new Enemy(2);
		enemy1.setX(100+rand.nextInt(800));
		enemy1.setY(100+rand.nextInt(400));
		enemy2.setX(100+rand.nextInt(800));
		enemy2.setY(100+rand.nextInt(400));
		enemy3.setX(100+rand.nextInt(800));
		enemy3.setY(100+rand.nextInt(400));
		enemy4.setX(100+rand.nextInt(800));
		enemy4.setY(100+rand.nextInt(400));

	}
	private void drawPlayer(Graphics canvas){
		canvas.setColor(Color.black);
		Image playerImg = null;
		playerImg= getImage(getDocumentBase(),"player.png");
		canvas.drawImage(playerImg,player.getX()-30,player.getY()-40,this);

	}
	private void drawEnemy(Graphics canvas){
		Image enemy1Img = getImage(enemy1);
		Image enemy2Img = getImage(enemy2);
		Image enemy3Img = getImage(enemy3);
		Image enemy4Img = getImage(enemy4);
		canvas.drawImage(enemy1Img,enemy1.getX(),enemy1.getY(),this);
		canvas.drawImage(enemy2Img,enemy2.getX(),enemy2.getY(),this);
		canvas.drawImage(enemy3Img,enemy3.getX(),enemy3.getY(),this);
		canvas.drawImage(enemy4Img,enemy4.getX(),enemy4.getY(),this);
	}
	private void drawHealth(Graphics canvas){
		canvas.setColor(Color.black);
		canvas.fillRect(275,this.getHeight()-25,300,this.getHeight());
		if(player.getHealth()>35){
			canvas.setColor(Color.green);
		}
		else if(player.getHealth()>15&&player.getHealth()<=35){
			canvas.setColor(Color.orange);
		}
		else{
			canvas.setColor(Color.red);
		}
		canvas.fillRect(275,this.getHeight()-25,player.getHealth()*6,this.getHeight());
		canvas.setColor(Color.BLACK);
		Font font = new Font("Verdana",Font.BOLD,18);
		canvas.setFont(font);
		canvas.drawString("Health: "+player.getHealth()+"/50",300,this.getHeight()-35);
	}
	private void drawDecorations(Graphics canvas){
		Image grass = getImage(getDocumentBase(),"grass.png");
		Image flower = getImage(getDocumentBase(),"flower.png");
		Image tree = getImage(getDocumentBase(),"tree.png");
		canvas.drawImage(grass,dec1.getX(),dec1.getY(),this);
		canvas.drawImage(grass,dec4.getX(),dec4.getY(),this);
		canvas.drawImage(flower,dec2.getX(),dec2.getY(),this);
		canvas.drawImage(flower,dec5.getX(),dec5.getY(),this);
		canvas.drawImage(tree,dec3.getX(),dec3.getY(),this);
		canvas.drawImage(tree,dec6.getX(),dec6.getY(),this);
	}
	private void drawMoney(Graphics canvas){
		canvas.setColor(Color.black);
		Font font = new Font("Verdana",Font.BOLD,16);
		canvas.setFont(font);
		canvas.drawString("Money: "+player.getMoney()+"$",10,this.getHeight()-10);
		canvas.drawString("Current Weapon: "+weapon.getType(), this.getWidth()-510,this.getHeight()-30 );
		if(armor!=null){
			canvas.drawString("Current Armor: "+armor.getType(), this.getWidth()-510, this.getHeight()-15);
		}
		else{
			canvas.drawString("Current Armor: None", this.getWidth()-510, this.getHeight()-15);
		}
	}
	private void drawGameOver(Graphics canvas){
		this.stop();
		Image img = getImage(getDocumentBase(),"boss.png");
		Font font = new Font("Verdana",Font.BOLD,20);
		canvas.setFont(font);
		canvas.setColor(Color.gray);
		canvas.fillRect(0,0,2000,2000);
		canvas.setColor(Color.red);
		canvas.drawString("You're not a god, sorry.",getWidth()/2-100,getHeight()/2);
		canvas.drawImage(img, getWidth()/2-200, getHeight()/2-40,this);
		ended= true;
	}
	private void drawItems(Graphics canvas){
		Font font = new Font("Serif",Font.PLAIN,10);
		canvas.setColor(Color.BLACK);
		canvas.setFont(font);
		Image potion = getImage(getDocumentBase(),"potion.png");
		Image bat = getImage(getDocumentBase(),"bat.png");
		Image axe = getImage(getDocumentBase(),"axe.png");
		Image clothArmor = getImage(getDocumentBase(),"clotharmor.png");
		Image copperArmor = getImage(getDocumentBase(),"copperarmor.png");
		canvas.drawImage(potion,getWidth()-40,100,this);
		canvas.drawString("10$", getWidth()-30,150);
		canvas.drawImage(bat,getWidth()-55,200,this);
		canvas.drawString("30$", getWidth()-30,250);
		canvas.drawImage(axe,getWidth()-50,300,this);
		canvas.drawString("50$", getWidth()-30,350);
		canvas.drawImage(clothArmor,getWidth()-50,400,this);
		canvas.drawString("50$", getWidth()-30,460);
		canvas.drawImage(copperArmor,getWidth()-40,500,this);
		canvas.drawString("100$", getWidth()-30,550);
	}
	//Puts the decorations in the game world
	private void initializeDecorations(){
		dec1 = new Decoration(0,this.getWidth()-300-rand.nextInt(600),this.getHeight()-rand.nextInt(600));
		dec2 = new Decoration(1,this.getWidth()-300-rand.nextInt(600),this.getHeight()-rand.nextInt(600));
		dec3 = new Decoration(2,this.getWidth()-300-rand.nextInt(600),this.getHeight()-rand.nextInt(600));
		dec4 = new Decoration(0,this.getWidth()-300-rand.nextInt(600),this.getHeight()-rand.nextInt(600));
		dec5 = new Decoration(1,this.getWidth()-300-rand.nextInt(600),this.getHeight()-rand.nextInt(600));
		dec6 = new Decoration(2,this.getWidth()-300-rand.nextInt(600),this.getHeight()-rand.nextInt(600));
	}

	public void mouseClicked(MouseEvent e) {
		//Sets the player's coordinates on the map.
		player.setX(e.getX());
		player.setY(e.getY());
		//Enables the player to pick up items.
		if(e.getX()>getWidth()-50&&e.getY()>100&&e.getY()<150&&player.getMoney()>=10){
			player.setHealth(50);
			player.setMoney(player.getMoney()-10);
		}
		if(e.getX()>getWidth()-50&&e.getY()>200&&e.getY()<250&&player.getMoney()>=30){
			weapon.setType(2);
			player.setMoney(player.getMoney()-30);
		}
		if(e.getX()>getWidth()-50&&e.getY()>300&&e.getY()<350&&player.getMoney()>=50){
			weapon.setType(3);
			player.setMoney(player.getMoney()-50);
		}
		if(e.getX()>getWidth()-50&&e.getY()>400&&e.getY()<450&&player.getMoney()>=50){
			if(armor==null){
				armor = new Armor(1);
			}
			else{
				armor.setType(1);
			}
			player.setMoney(player.getMoney()-50);
		}
		if(e.getX()>getWidth()-50&&e.getY()>500&&e.getY()<550&&player.getMoney()>=100){
			if(armor==null){
				armor = new Armor(2);
			}
			else{
				armor.setType(2);
			}
			player.setMoney(player.getMoney()-100);
		}
		//Enables the player to fight enemies
		if(e.getX()>enemy1.getX()-10&&e.getX()<enemy1.getX()+50&&e.getY()>enemy1.getY()-10&&e.getY()<enemy1.getY()+50){
			fight(enemy1);
		}
		else if(e.getX()>enemy2.getX()-10&&e.getX()<enemy2.getX()+50&&e.getY()>enemy2.getY()-10&&e.getY()<enemy2.getY()+50){
			fight(enemy2);
		}
		else if(e.getX()>enemy3.getX()-10&&e.getX()<enemy3.getX()+50&&e.getY()>enemy3.getY()-10&&e.getY()<enemy3.getY()+50){
			fight(enemy3);
		}
		else if(e.getX()>enemy4.getX()-10&&e.getX()<enemy4.getX()+50&&e.getY()>enemy4.getY()-10&&e.getY()<enemy4.getY()+50){
			fight(enemy4);
		}

		repaint();
		e.consume();

	}

	public void mouseEntered(MouseEvent arg0) {
	}
	private Image getImage(Enemy e){
		//Helper method to get the image for the given enemy
		Image image = null;
		if(e.getName().equals("Pig")){
			image = getImage(getDocumentBase(),"Pig.gif");
		}
		else if(e.getName().equals("Hornet")){
			image = getImage(getDocumentBase(),"Hornet.png");
		}
		else if(e.getName().equals("Rat")){
			image = getImage(getDocumentBase(),"Rat.gif");
		}
		else if(e.getName().equals("Spider")){
			image = getImage(getDocumentBase(),"Spider.png");
		}
		else if(e.getName().equals("Lizard")){
			image = getImage(getDocumentBase(),"frog.png");
		}
		return image;
	}
	private void fight(Enemy e){
		//The logic behind the player and enemy fighting
		while(player.getHealth()>0&&e.getHealth()>0){
			player.attack(weapon, e);
			//How to attack player if they have armor
			if(e.getHealth()>0&&armor!=null){
				e.attack(armor,player);

			}
			//If player doesn't have armor, how to fight
			else if(e.getHealth()>0&&armor==null){
				e.attack(player);
			}
			else{
				//Giving the amount of gold to the player relative to what they killed
				if(e.getName().equals("Lizard")||e.getName().equals("Rat")||e.getName().equals("Spider")){
					player.setMoney(player.getMoney()+20);
				}
				else{
					player.setMoney(player.getMoney()+50);
				}
				break;
			}
		}
	}
	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

}
