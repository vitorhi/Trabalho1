
public class Pokemon {
	String name;
	int hp;
	boolean dead;
	static int i=0;
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
	public void implementAtack(String nome,int atkPri,int q){
		
		
		Atack at= new Atack(nome,atkPri,q);
		atacks[(i%4)]=at;
		i++;
		
	}
	
	public Atack getAtack(int n){
		return atacks[n];
	}
	public boolean isDead(){
		if(dead==true)
			return true;
		else
			return false;
	}

}
