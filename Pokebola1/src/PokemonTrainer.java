import java.util.*;
public class PokemonTrainer {
	String name;
	Queue<Pokemon> queue = new LinkedList<Pokemon>();
	
	
	
	
	// é aqui que coloquei a prioridade
	static int _prioridade = 0;
	
	public PokemonTrainer(Pokemon[]vector_pokemon,String name){

		for(int i=0;i<vector_pokemon.length;i++){
//			System.out.println(vector_pokemon[i].name);
			queue.add(vector_pokemon[i]);
//			System.out.println(queue.peek().name);

		}

		this.name=name;
		
		// é aqui que adicionei a prioridade ao jogador
		_prioridade++;
		
	}
	public Pokemon getPokqueue(){
		

		 if(queue.peek() == null){
			 System.out.println("Todos os pokemons morreram!");
			 System.out.println("Game Over");
			 Runtime.getRuntime().exit(0);			 			 
			 return null;			 
		 }	 
		return queue.peek();
	}

	public Pokemon getSELVAGEMPokqueue(){
		

		 if(queue.peek() == null){
			 System.out.println("O pokemon morreu!");
//			 Runtime.getRuntime().exit(0);			 
			 return null;			 
		 }	 
		return queue.peek();
	}
	
	public SwapPokemon PmonSP(){
		SwapPokemon SP=new SwapPokemon();
		return SP;
	}
	public UseItem PmonUI(){
		UseItem		UI=new UseItem();
		return UI;
	}
	public RunAway PmonRA(){
		RunAway	RA=new RunAway();
		return RA;
	}
	public Morte PmonMorte(){
		Morte _Morte = new Morte();
		return _Morte;
	}
}
