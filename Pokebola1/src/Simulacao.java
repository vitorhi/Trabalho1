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
		System.out.println();
		Controller control= new Controller(player,enemy);
		
		long tm=System.currentTimeMillis();

		control.addEvent(player.getPokqueue().getAtack(0,tm),enemy.getPokqueue().getAtack(0,tm+800));

//		player.getPokqueue().getAtack(1).atkTimeSet(tm);
//		enemy.getPokqueue().getAtack(1).atkTimeSet(tm+800);
		
		control.addEvent(player.getPokqueue().getAtack(1,tm+1600),enemy.getPokqueue().getAtack(2,tm+3200));

//		player.getPokqueue().getAtack(1).atkTimeSet(tm+1600);
//		enemy.getPokqueue().getAtack(0).atkTimeSet(tm+2400);
		control.addEvent(player.PmonSP(tm+8000),enemy.getPokqueue().getAtack(2,tm+10000));
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

	}

}
