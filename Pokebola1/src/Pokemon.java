
public class Pokemon {
	String name;
	int hp;
	boolean dead;
	public Atack[] atacks=new Atack[4];
	
	public Pokemon (String name,int hp){
		this.name=name;
		this.hp=hp;
		dead=false;
	}
	
	public void LoseHp(Atack atk){ 
		if(atk.quantity()>hp){
			dead=true;
			return;		
		}
		else
			hp=hp-atk.quantity();
		
	}
	public boolean isDead(){
		if(dead==true)
			return true;
		else
			return false;
	}

}
