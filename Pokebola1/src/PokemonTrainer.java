import java.util.*;
public class PokemonTrainer {
	String name;
	Queue<Pokemon> queue = new LinkedList<Pokemon>();
	Pokemon []pokemons;
	// é aqui que coloquei a prioridade
	static int _prioridade = 0;
	
	public PokemonTrainer(Pokemon[]vector_pokemon,String name){

		for(int i=0;i<vector_pokemon.length;i++){

			queue.add(vector_pokemon[i]);
		}
//		pokemons=vector_pokemon;
		this.name=name;
		// é aqui que adicionei a prioridade ao jogador
		_prioridade++;
	}
	public Pokemon getPokqueue(){
		return queue.element();
	}
	
	
}
