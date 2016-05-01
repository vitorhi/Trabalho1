
import java.util.concurrent.TimeUnit;
abstract public class Event {

	int priority;
	
	public PokemonTrainer Trainer,Enemy;
	public String name;
	protected long evtTime;
	
	//variaveis para evento tipo atack
	int atkPriority;
	int quant;
	
	
	public boolean ready() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		return true;
//		return System.currentTimeMillis() >= evtTime;
	}
		
	public Event(String name){
		
		this.name=name;
	}
//	public Event(long eventTime){
//		evtTime = eventTime;
//		
//	}
	
	//construtor para evento tipo atack
	public Event(String name,int atkPriority,int q ) {
		this.name=name;
		quant= q;
		priority=1;
		this.atkPriority=atkPriority;

	}
	
	public void setTrainer(PokemonTrainer Trainer,PokemonTrainer Enemy){
		this.Enemy=Enemy;
		
		this.Trainer=Trainer;
	}
	
	public String eventName(){
		return name;
	}
	
	abstract public void action();
	abstract public String getClassName();

}

class EventSet {
	private Event[] events = new Event[50];
	private int i = 0;
	private int next = 0;
	public PokemonTrainer TrainerSet;
	public PokemonTrainer EnemySet;
	
	public EventSet(PokemonTrainer a,PokemonTrainer b){
		this.TrainerSet=a;
		
		this.EnemySet=b;
	}
	public void add(Event e) {
		
		e.setTrainer(TrainerSet,EnemySet);
//		System.out.println("2");
		if(i >= this.events.length)
			return;
		else{
			this.events[i] = e;
			i++;

		}

	}
	public Event getNext() {
		boolean looped = false;
		int start = this.next;
		do {
			this.next = (this.next + 1) % this.events.length;
			// See if it has looped to the beginning:
			if(start == this.next) looped = true;
			// If it loops past start, the list
			// is empty:
			if((this.next == (start + 1) % this.events.length)&& looped)
				return null;
		} while(this.events[this.next] == null);
		return this.events[this.next];
	}
	public void removeCurrent() {
		this.events[this.next] = null;
	}
}
class Controller {
	private EventSet es_a; 	
	private EventSet es_b;
	
	public Controller(PokemonTrainer x, PokemonTrainer y){
		this.es_a = new EventSet(x,y);	
		this.es_b = new EventSet(y,x);
	}
	
	public void addEvent(Event a,Event b) {
//		System.out.println(es_a.EnemySet.name);
		this.es_a.add(a);
		this.es_b.add(b);
		
	}
	
	public String run() {
		Event e;
		Event f;
		PokemonTrainer Winner=this.es_a.TrainerSet;
		while(((e = this.es_a.getNext()) != null)&&((f = this.es_b.getNext()) != null)) {
			
			//caso os dois eventos sejam um ataque
			if(e.getClassName()=="Atack" && f.getClassName()=="Atack"){
				
				if(e.atkPriority<=f.atkPriority){
					if(e.ready()) {
						e.action();		
						System.out.println();
						this.es_a.removeCurrent();					
					}
					if(f.ready()) {
						f.action();
						System.out.println();
						this.es_b.removeCurrent();
					}
				}
				if(e.atkPriority>f.atkPriority){
					if(f.ready()) {
						f.action();
						System.out.println();
						this.es_b.removeCurrent();
					}
					if(e.ready()) {
						e.action();		
						System.out.println();
						this.es_a.removeCurrent();					
					}					
				}
			}
			
			
			
			//caso nenhum dos eventos seja um ataque
			else if(e.getClassName()!="Atack"||f.getClassName()!="Atack"){
				if(e.priority<f.priority){
					if(e.getClassName()=="RunAway"){
						e.action();		
						System.out.println();
						this.es_a.removeCurrent();
						Winner=this.es_b.TrainerSet;
						break;
					}
					
					if(e.ready()) {
						e.action();	
						System.out.println();
						this.es_a.removeCurrent();					
					}
					
					if(f.getClassName()=="RunAway"){
						f.action();		
						System.out.println();
						this.es_a.removeCurrent();
						Winner=this.es_a.TrainerSet;
						break;
					}
					if(f.ready()) {
						f.action();
						System.out.println();
						this.es_b.removeCurrent();
					}
				}
				if(e.priority>f.priority){
					if(f.getClassName()=="RunAway") {
						f.action();		
						System.out.println();
						this.es_a.removeCurrent();
						Winner=this.es_a.TrainerSet;
						break;
					}
					if(f.ready()) {
						f.action();
						System.out.println();
						this.es_b.removeCurrent();
					}
					if(e.getClassName()=="RunAway") {
						e.action();		
						System.out.println();
						this.es_a.removeCurrent();
						Winner=this.es_b.TrainerSet;
						break;
					}
					if(e.ready()) {
						e.action();		
						System.out.println();
						this.es_a.removeCurrent();					
					}					
				}
				
			}
			
		}
		return Winner.name;//adicionar caso de vencedor e de empate
	}
}

class Atack extends Event{
	
	int atkPriority;

	public Atack(String name,int atkPriority,int q ) {
		super(name,atkPriority,q);
		this.priority=1;
	}
	
	public void atkTimeSet(long eventTime){
		this.evtTime=eventTime;
	}
	
	public int quantity(){
		return this.quant;
	}

	public void action() {
		
		this.Trainer.getPokqueue();
		
		System.out.println(Trainer.getPokqueue().name+ " usou " +Trainer.getPokqueue().atacks[0].name);		

		//caso o dano seje maior do que o hp do pokemon
		if(quant>Enemy.getPokqueue().hp){
			//o Pokemon é morto
			Enemy.getPokqueue().dead=true;		
			
			
			System.out.println(Enemy.getPokqueue().name+" morreu...");
			
			
			//e removido da fila de pokemons do treinador
			Enemy.queue.remove();
			return;		
		}
		
		//caso o dano seje menor do que o hp do pokemon
		else{		
			
			Enemy.getPokqueue().hp=(Enemy.getPokqueue().hp)-quant;
			System.out.println(Enemy.getPokqueue().name+" ficou com "+Enemy.getPokqueue().hp+" de vida");
		}
			
				

	}
	public String getClassName(){
		return"Atack";
	}


}
class SwapPokemon extends Event{
	
	
	public SwapPokemon() {
		super("Trocar pokemon");		
		priority=3;

	}
	
	public void action() {
		Trainer.queue.add(Trainer.queue.poll());
		System.out.println(Trainer.name+" trocou de pokemon para "+Trainer.queue.peek().name);
		
		
	}
	public String getClassName(){
		return"SwapPokemon";
	}


}

class UseItem extends Event{

	int quant = 10;

	public UseItem() {	

		super("Usar item");
		priority=2;
	}
	
	public void action() {
		Pokemon curPok=Trainer.getPokqueue();
		
		if(curPok.hp + quant >= curPok.hp){
			curPok.hp = Trainer.getPokqueue().max_hp;
			return;
		}
		else
			curPok.hp=curPok.hp + quant;
		
		System.out.println(Trainer.name+" usou o item de cura em "+Trainer.getPokqueue().name);

	}
	public String getClassName(){
		return"UseItem";
	}


}
class RunAway extends Event{
	public RunAway() {
		super("Fugir da luta");		
		priority=4;

	}


	public void action() {
		System.out.println(Trainer.name+" fugiu da luta");
				
	}
	public String getClassName(){
		return"RunAway";
	}


}

class Morte extends Event{
	public Morte() {
		super("Morrer");		
		priority=4;

	}

	public void action() {
		System.out.println(Trainer.name+" Morreu");
				
	}
	public String getClassName(){
		return"RunAway";
	}


}


