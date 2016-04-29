
public class Pokemon {
	String name;
	int hp;
	int max_hp;
	boolean dead;
	int i=0;
	public Atack[] atacks=new Atack[4];
	
	public Pokemon (String name,int hp){
		this.name=name;
		this.hp=hp;
		dead=false;
		// Coloquei um HP_Max para comparar na funcao GainHp
		max_hp=hp;
	}

	
	public void implementAtack(String nome,int atkPri,int q){
				
		Atack at= new Atack(nome,atkPri,q);
		
		
		//o indice do ataque é fornecido pela sua prioridade

		atacks[i++]=at;
		
		
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

//	public static int getMax_hp() {
//		return max_hp;
//	}
//
//	public static void setMax_hp(int max_hp) {
//		Pokemon.max_hp = max_hp;
//	}

}
