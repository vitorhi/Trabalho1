
public class Pokemon {
	String name;
	int hp;
	static int max_hp;
	boolean dead;
	static int i=0;
	public Atack[] atacks=new Atack[4];
	
	public Pokemon (String name,int hp){
		this.name=name;
		this.hp=hp;
		dead=false;
		// Coloquei um HP_Max para comparar na funcao GainHp
		max_hp = hp;
	}
	
	public void LoseHp(Atack atk){ 
		if(atk.quantity()>hp){
			dead=true;
			return;		
		}
		else
			hp=hp-atk.quantity();	
	}

	// Para recuperar vida
	public void GainHp(UseItem heal){
		// Caso ele recupere "muito" hp
		if(hp + heal.quantity() >= hp){
			hp = max_hp;
			return;
		}
		else
			hp=hp + heal.quantity();	
	}
	
	public void implementAtack(String nome,int atkPri,int q){
		
		Atack at= new Atack(nome,atkPri,q);
		atacks[i++]=at;
		
		
	}
	
	public Atack getAtack(int i){
		return atacks[i];
	}
	public boolean isDead(){
		if(dead==true)
			return true;
		else
			return false;
	}

}
