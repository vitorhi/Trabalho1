import java.util.Scanner;
class Battle{
	PokemonTrainer fighter1;
	PokemonTrainer fighter2;
	PokemonTrainer player;
	
	public Battle(PokemonTrainer a,PokemonTrainer b){
		fighter1=a;
		fighter2=b;
	}
	public void Init(){
		Scanner scan= new Scanner(System.in);
		
		System.out.println("Escolha seu jogador:");
		System.out.println("1)"+fighter1.name+"\n2)"+fighter2.name);
		if(scan.nextInt()==1){
			player=fighter1;
			
		}
		else
			player=fighter2;
		
		System.out.println("Você escolheu "+player.name);
		
		
		
		
		
	}
	
}



public class Simulacao {
	

	public static void main(String[] args) {
		
		
		//criacao dos pokemons
		Pokemon Pikachu = new Pokemon("Pikachu",35);
		Pikachu.implementAtack("Choque do trovão", 1, 10);
		Pikachu.implementAtack("Choque do trovão", 2, 10);
		Pikachu.implementAtack("Choque do trovão", 3, 10);
		
		Pokemon Bulbassauro = new Pokemon("Bulbassauro",40);
		Pikachu.implementAtack("Choque do trovão", 3, 10);
		Pikachu.implementAtack("Choque do trovão", 2, 10);
		Pikachu.implementAtack("Choque do trovão", 1, 10);
		Pikachu.implementAtack("Choque do trovão", 4, 10);
		
		Pokemon Charmander = new Pokemon("Charmander",50);
		Pikachu.implementAtack("Choque do trovão", 4, 10);
		Pikachu.implementAtack("Choque do trovão", 2, 10);
		
		Pokemon Pidgeotto = new Pokemon("Pidgeotto",35);
		Pikachu.implementAtack("Choque do trovão", 2, 10);
		
		Pokemon Rattata = new Pokemon("Rattata",35);
		Pikachu.implementAtack("Choque do trovão", 3, 10);
		Pikachu.implementAtack("Choque do trovão", 2, 10);
		Pikachu.implementAtack("Choque do trovão", 4, 10);
		
		Pokemon Zubat = new Pokemon("Zubat",28);
		Pikachu.implementAtack("Choque do trovão", 4, 10);
		Pikachu.implementAtack("Choque do trovão", 3, 10);
		Pikachu.implementAtack("Choque do trovão", 2, 10);
		Pikachu.implementAtack("Choque do trovão", 4, 10);
		
		
		Pokemon Magikarpa = new Pokemon("Magikarpa",30);
		Pikachu.implementAtack("Choque do trovão", 4, 10);
		Pikachu.implementAtack("Choque do trovão", 2, 10);
		
		Pokemon Squirtle = new Pokemon("Squirtle",30);
		Pikachu.implementAtack("Choque do trovão", 4, 10);
		
		Pokemon[]pokemons_Ash={Pikachu,Bulbassauro,Charmander,Pidgeotto};
		Pokemon[]pokemons_Rocket={Rattata,Zubat,Magikarpa,Squirtle};
		
		//criacao treinadores
		PokemonTrainer Ash= new PokemonTrainer(pokemons_Ash,"Ash");
		PokemonTrainer RocketTm= new PokemonTrainer(pokemons_Rocket,"Equipe Rocket");
		
		//inicialização da batalha
		Battle bt = new Battle(Ash, RocketTm);
		bt.Init();	
		
	}

}
