//Vítor Hideki Ishikura			9344921
//Henrrique Spadim			9373441


import java.util.Scanner;
import java.util.Random;


class Battle{
	PokemonTrainer fighter1;
	PokemonTrainer fighter2;
	
	PokemonTrainer player;
	PokemonTrainer enemy;
	
	public Battle(PokemonTrainer a,PokemonTrainer b){
		this.fighter1=a;
		this.fighter2=b;
	}
	public void Init(){
		Scanner scan= new Scanner(System.in);

		System.out.println("Escolha seu jogador:");
		System.out.println("1)"+fighter1.name+"\n2)"+fighter2.name);
		if(scan.nextInt()==1){
			player=fighter1;
			enemy=fighter2;
		}
		else{
			player=fighter2;
			enemy=fighter1;
		}	
		scan.close();
		System.out.println("Você escolheu "+player.name);
		System.out.println();
		Controller control= new Controller(player,enemy);
		
		if(true){
			control.addEvent(player.getPokqueue().getAtack(0),enemy.PmonRA());
	
			control.addEvent(player.getPokqueue().getAtack(0),enemy.getPokqueue().getAtack(0));
			control.addEvent(player.getPokqueue().getAtack(0),enemy.getPokqueue().getAtack(0));
	
			
			control.addEvent(player.getPokqueue().getAtack(1),enemy.getPokqueue().getAtack(2));
	
			control.addEvent(player.PmonSP(),enemy.getPokqueue().getAtack(2));
			control.addEvent(player.getPokqueue().getAtack(1),enemy.getPokqueue().getAtack(2));
			
			System.out.println(control.run()+" é o vencedor!");
		}
	}

	
	public void Init_Selvagem(){

		player=fighter1;
		enemy=fighter2;
		Controller control= new Controller(player,enemy);
		
		// Para gerar os ataques aleatorios
		Random gerador = new Random();
		
		if(true){
			control.addEvent(player.getPokqueue().getAtack(0),
							 enemy.getSELVAGEMPokqueue().getAtack(gerador.nextInt(4))
							 );
	
			
			control.addEvent(player.PmonUI(),enemy.getSELVAGEMPokqueue().getAtack(gerador.nextInt(4)));
	
			control.addEvent(player.getPokqueue().getAtack(0),enemy.getSELVAGEMPokqueue().getAtack(gerador.nextInt(4)));
			control.run();
		}
	}

}



public class Simulacao {


	public static void main(String[] args) {

		
		//criacao dos pokemons
		Pokemon Pikachu = new Pokemon("Pikachu",35);
		Pikachu.implementAtack("Electro Ball", 1, 10);
		Pikachu.implementAtack("Thunderbolt", 2, 10);
		Pikachu.implementAtack("Volt Tackle", 3, 10);

		Pokemon Bulbassauro = new Pokemon("Bulbassauro",40);
		Bulbassauro.implementAtack("Vine Whip", 3, 10);
		Bulbassauro.implementAtack("Tackle", 2, 10);
		Bulbassauro.implementAtack("Razor Leaf", 1, 10);
		Bulbassauro.implementAtack("Solar Beam", 4, 10);

		Pokemon Charmander = new Pokemon("Charmander",50);
		Charmander.implementAtack("Flamethrower", 4, 10);
		Charmander.implementAtack("Rage", 2, 10);

		Pokemon Pidgeotto = new Pokemon("Pidgeotto",35);
		Pidgeotto.implementAtack("Sand_Attack", 2, 10);

		Pokemon Rattata = new Pokemon("Rattata",35);
		Rattata.implementAtack("Hyper Fang", 3, 10);
		Rattata.implementAtack("Thunderbolt", 2, 10);
		Rattata.implementAtack("Bite", 4, 10);

		Pokemon Zubat = new Pokemon("Zubat",28);
		Zubat.implementAtack("Supersonic", 4, 10);
		Zubat.implementAtack("Fly", 3, 10);
		Zubat.implementAtack("Whirlwind", 2, 10);
		Zubat.implementAtack("Wing Attack", 4, 10);

		Pokemon Magikarpa = new Pokemon("Magikarp",30);
		Magikarpa.implementAtack("Flail", 4, 10);
		Magikarpa.implementAtack("Splash", 2, 10);

		Pokemon Squirtle = new Pokemon("Squirtle",30);
		Squirtle.implementAtack("Aqua Tail", 4, 10);
		
		Pokemon[]pokemons_Ash={Pikachu,Bulbassauro,Charmander,Pidgeotto};		
		Pokemon[]pokemons_Rocket={Rattata,Zubat,Magikarpa,Squirtle};
		
		//criacao treinadores
		PokemonTrainer Ash= new PokemonTrainer(pokemons_Ash,"Ash");
		
		
		PokemonTrainer RocketTm= new PokemonTrainer(pokemons_Rocket,"Equipe Rocket");
		
		//inicialização da batalha

		Battle bt = new Battle(Ash, RocketTm);

		bt.Init();
		
		// Posicao do personagem é 'P', o mato é 'X', e chao é ' '
		//
		//  __________
		// |XXX   X  |
		// |XXX  X   |
		// |XXXX   P |
		// |X X    X |
		// |_________|
		//
		// 'a' -> esquerda
		// 's' -> para baixo
		// 'd' -> direita
		// 'w' -> para cima
		//
	
		char[][] Mapa= { {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_'},
						 {'|', 'X', 'X', 'X', ' ', ' ', ' ', 'X', ' ', '|'}, 
						 {'|', 'X', 'X', 'X', ' ', ' ', 'X', ' ', ' ', '|'}, 
						 {'|', 'X', 'X', 'X', 'X', ' ', ' ', ' ', 'P', '|'}, 
						 {'|', 'X', ' ', 'X', ' ', ' ', ' ', ' ', 'X', '|'},
						 {'|', '_', '_', '_', '_', '_', '_', '_', '_', '|'}};
	
		// Quando o "Personagem" se move, se a posicao na frente dele nao for uma parede('|' ou '_')
		// precisamos recolocar o que havia debaixo dele.
		// A fim de saber onde e quando recolocar o "Mato(X)" ou "Espaco( )" no lugar, eu criei
		// o char 'guardador_de_espacos'.
		// Supus que o espaco debaixo do "Personagem" é um "Espaco( )"
		char guardador_de_espacos = ' ';
		
		char[] andar = {'d', 'a', 'a', 'w', 'a', 'a', 'a', 'a', 's', 'w', 's', 'w', 'd', 's', 's', 'd', 's', 'd'};
		int i = 3;
		int j = 8;

		Exercicio_2 mapa_Ex2 = new Exercicio_2(Mapa, guardador_de_espacos, andar, i, j);
		
		Pokemon Pikachu1 = new Pokemon("Pikachu",35);
		Pikachu1.implementAtack("Electro Ball", 1, 10);
		Pikachu1.implementAtack("Thunderbolt", 2, 10);
		Pikachu1.implementAtack("Volt Tackle", 3, 10);

		Pokemon Bulbassauro1 = new Pokemon("Bulbassauro",40);
		Bulbassauro1.implementAtack("Vine Whip", 3, 10);
		Bulbassauro1.implementAtack("Tackle", 2, 10);
		Bulbassauro1.implementAtack("Razor Leaf", 1, 10);
		Bulbassauro1.implementAtack("Solar Beam", 4, 10);

		Pokemon Charmander1 = new Pokemon("Charmander",50);
		Charmander1.implementAtack("Flamethrower", 4, 10);
		Charmander1.implementAtack("Rage", 2, 10);

		Pokemon Pidgeotto1 = new Pokemon("Pidgeotto",35);
		Pidgeotto1.implementAtack("Sand_Attack", 2, 10);
		
		Pokemon Rattata1 = new Pokemon("Rattata",35);
		Rattata1.implementAtack("Hyper Fang", 3, 10);
		Rattata1.implementAtack("Thunderbolt", 2, 10);
		Rattata1.implementAtack("Bite", 4, 10);

		
		//inicialização da batalha no Mapa
		Pokemon[] _pokemon_Selvagem={Rattata1};
		PokemonTrainer pokemonSelvagem= new PokemonTrainer(_pokemon_Selvagem,"Rattata");

		Pokemon[]pokemons_Red={Pikachu1,Bulbassauro1,Charmander1,Pidgeotto1};
		PokemonTrainer Red= new PokemonTrainer(pokemons_Red,"Red");
		
		Battle _bt = new Battle(Red, pokemonSelvagem);
		
		for(int k = 0; k < 18; k++){
			if(mapa_Ex2.andador(andar[k])){
				_bt.Init_Selvagem();
			}
		}
	
	}

}

class Exercicio_2{

	char[][] Mapa;
	char guardador_de_espacos;
	char[] andar;
	int i;
	int j;
	
	public Exercicio_2(char[][] Mapa, char guardador_de_espacos, char[] andar, int i, int j){
		this.Mapa = Mapa;
		this.guardador_de_espacos = guardador_de_espacos;
		this.andar = andar;
		this.i = i;
		this.j = j;
	}

	boolean andador(char andar){
		
		System.out.println();		
		System.out.println("Voce é o Personagem 'P' !");
		System.out.println();
	
		for(int aux_i = 0; aux_i < 6; aux_i++){
			for(int aux_j = 0; aux_j < 10; aux_j++){
				System.out.print(this.Mapa[aux_i][aux_j]);
			}
			System.out.println();
		}
		
		System.out.println();
				
		if(andar == 'a'){ // esquerda
		
			if(this.Mapa[i-1][j] == '_' || this.Mapa[i-1][j] == '|'){
				System.out.println("Tolinho... Voce nao pode atravessar paredes!");
			}
			
			else{
				this.Mapa[i][j] = this.guardador_de_espacos;
				this.i -= 1;
			}
		}

		else if(andar == 's'){ // para baixo
		
			if(this.Mapa[i][j+1] == '_' || this.Mapa[i][j+1] == '|'){
				System.out.println("Tolinho... Voce nao pode atravessar paredes!");
			}
			
			else{
				this.Mapa[i][j] = this.guardador_de_espacos;
				this.j += 1;
			}
		}

		else if(andar == 'd'){ // direita
		
			if(this.Mapa[i+1][j] == '_' || this.Mapa[i+1][j] == '|'){
				System.out.println("Tolinho... Voce nao pode atravessar paredes!");
			}
		
			else{
				this.Mapa[i][j] = this.guardador_de_espacos;
				this.i += 1;
			}
		}

		else if(andar == 'w'){ // para cima
		
			if(this.Mapa[i][j-1] == '_' || this.Mapa[i][j-1] == '|'){
				System.out.println("Tolinho... Voce nao pode atravessar paredes!");
			}
			
			else{
				this.Mapa[i][j] = this.guardador_de_espacos;
				this.j -= 1;
			}
		}
		return substituidor_de_espacos();
	}

	// Pega o mapa, e substitui aquele espaco pelo seu personagem
	// Aqui dentro há  a funcao identificador_de_batalhas
	
	boolean substituidor_de_espacos(){

		if(this.Mapa[i][j] == 'X'){
			this.guardador_de_espacos = 'X';
			this.Mapa[i][j] = 'P';
			return identificador_de_batalhas();
		}
		
		else if(this.Mapa[i][j] == ' '){
			this.guardador_de_espacos = ' ';	
			this.Mapa[i][j] = 'P';
		}	
	
		return false;
	}

	// Identifica o mato ou o inimigo, e ve  se vc entra em uma batalha pokemon ou nao
	// Se 'gerador' for menor que        0.5, nao entra em uma batalha
	// Se 'gerador' for maior ou igual a 0.5,     entra em uma batalha

	boolean identificador_de_batalhas(){
		Random gerador = new Random();
		if(gerador.nextFloat() < 0.5){
			return true;	
		}
		return false;
	}
}

