public class Pokemon {
	String name;
	int hp;
	int max_hp;
	boolean dead;
	int i=0;
	int n_copy=0;
	public Atack[] atacks;
	
	public Pokemon (String name,int hp){
		this.name=name;
		this.hp=hp;
		this.dead=false;
		// Coloquei um HP_Max para comparar na funcao GainHp
		this.max_hp=hp;
		this.atacks=new Atack[4];
	}

	
	public void implementAtack(String nome,int atkPri,int q){
				
		Atack at= new Atack(nome,atkPri,q);
		
		
		//o indice do ataque é fornecido pela sua prioridade

		this.atacks[i++]=at;
		
		
	}
	
	
	public Atack getAtack(int n){
		
//		atacks[n].evtTime=evtime;
		if(this.atacks[n]==null)
			return this.atacks[0];
		return this.atacks[n];
	}
	public boolean isDead(){
		return this.dead;
	}

//	public static int getMax_hp() {
//		return max_hp;
//	}
//
//	public static void setMax_hp(int max_hp) {
//		Pokemon.max_hp = max_hp;
//	}

}
