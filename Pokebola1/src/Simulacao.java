import java.util.Scanner;

class Battle{
	PokemonTrainer fighter1;
	PokemonTrainer fighter2;
	
	PokemonTrainer player;
	PokemonTrainer enemy;
	
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
			enemy=fighter2;
		}
		else{
			player=fighter2;
			enemy=fighter1;
		}	
		scan.close();
		System.out.println("Você escolheu "+player.name);
		Controller control= new Controller(player,enemy);
		
		long tm=System.currentTimeMillis();
		control.addEvent(player.getPokqueue().getAtack(1),enemy.getPokqueue().getAtack(1));
		player.getPokqueue().getAtack(1).atkTimeSet(tm+800);
		enemy.getPokqueue().getAtack(1).atkTimeSet(tm+1600);
		control.run();

		
		
		

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
		Pikachu.implementAtack("Vine Whip", 3, 10);
		Pikachu.implementAtack("Tackle", 2, 10);
		Pikachu.implementAtack("Razor Leaf", 1, 10);
		Pikachu.implementAtack("Solar Beam", 4, 10);

		Pokemon Charmander = new Pokemon("Charmander",50);
		Pikachu.implementAtack("Flamethrower", 4, 10);
		Pikachu.implementAtack("Rage", 2, 10);

		Pokemon Pidgeotto = new Pokemon("Pidgeotto",35);
		Pikachu.implementAtack("Sand_Attack", 2, 10);

		Pokemon Rattata = new Pokemon("Rattata",35);
		Pikachu.implementAtack("Hyper Fang", 3, 10);
		Pikachu.implementAtack("Thunderbolt", 2, 10);
		Pikachu.implementAtack("Bite", 4, 10);

		Pokemon Zubat = new Pokemon("Zubat",28);
		Pikachu.implementAtack("Supersonic", 4, 10);
		Pikachu.implementAtack("Fly", 3, 10);
		Pikachu.implementAtack("Whirlwind", 2, 10);
		Pikachu.implementAtack("Wing Attack", 4, 10);

		Pokemon Magikarpa = new Pokemon("Magikarp",30);
		Pikachu.implementAtack("Flail", 4, 10);
		Pikachu.implementAtack("Splash", 2, 10);

		Pokemon Squirtle = new Pokemon("Squirtle",30);
		Pikachu.implementAtack("Aqua Tail", 4, 10);

		Pokemon[]pokemons_Ash={Pikachu,Bulbassauro,Charmander,Pidgeotto};


		Pokemon[]pokemons_Rocket={Rattata,Zubat,Magikarpa,Squirtle};

		//criacao treinadores
		PokemonTrainer Ash= new PokemonTrainer(pokemons_Ash,"Ash");
		PokemonTrainer RocketTm= new PokemonTrainer(pokemons_Rocket,"Equipe Rocket");
		System.out.println(RocketTm.getPokqueue().atacks[0].name);;

		//inicialização da batalha
		
		Battle bt = new Battle(Ash, RocketTm);
		
		bt.Init();	

	}

}
