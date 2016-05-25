
public class Decoration {
	private int x;
	private int y;
	private String type;
	
	public Decoration(int type,int x,int y){
		if(type>2||type<0){
			System.out.println("Something went wrong with a decoration");
		}
		if(type ==0){
			this.type = "grass";
			this.x=x;
			this.y=y;
		}
		else if(type ==1){
			this.type = "flower";
			this.x=x;
			this.y=y;
		}
		else if(type==2){
			this.type = "tree";
			this.x=x;
			this.y=y;
		}
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
	public String getType(){
		return this.type;
	}

}
