
public class Pokemon {
	String name;
	int hp;
	int max_hp;
	boolean dead;
	int i=0;
	int n_copy=0;
	public Atack[] atacks=new Atack[4];

	public Pokemon (String name,int hp){
		this.name=name;
		this.hp=hp;
		dead=false;

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



}
