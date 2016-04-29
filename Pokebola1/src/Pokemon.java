
public class Pokemon {
	String name;
	int hp;
	private int max_hp;
	boolean dead;
	protected int i=0;
	public Atack[] atacks=new Atack[4];
	
	public Pokemon (String name,int hp){
		this.name=name;
		this.hp=hp;
		dead=false;
		// Coloquei um HP_Max para comparar na funcao GainHp
		setMax_hp(hp);
	}

	//falta resolver o problema do i ser estatico para todos os objetos
	public void implementAtack(String nome,int atkPri,int q){
				
		Atack at= new Atack(nome,atkPri,q);
		System.out.println(i+" "+i%4);
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

	public static int getMax_hp() {
		return max_hp;
	}

	public static void setMax_hp(int max_hp) {
		Pokemon.max_hp = max_hp;
	}

}
