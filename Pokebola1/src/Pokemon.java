
public class Pokemon {
	String name;
	long hp;
	boolean dead;
	public Event[] atacks=new Event[4];
	
	public Pokemon (String name,long hp){
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
	

}
