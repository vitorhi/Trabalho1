
abstract public class Event {

	int priority;
	
	public PokemonTrainer Trainer;
	public String name;
	protected long evtTime;
	
	//variaveis para evento tipo atack
	int atkPriority;
	int quant;
	
	
	public boolean ready() {
		return System.currentTimeMillis() >= evtTime;
	}
		
	public Event(String name,long eventTime){
		evtTime = eventTime;
		this.name=name;
	}
	public Event(long eventTime){
		evtTime = eventTime;
		
	}
	
	//construtor para evento tipo atack
	public Event(String name,int atkPriority,int q ) {
		this.name=name;
		quant= q;
		priority=1;
		this.atkPriority=atkPriority;

	}
	
	public void setTrainer(PokemonTrainer Trainer){
		System.out.println("2");

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
	
	public EventSet(PokemonTrainer a){
		TrainerSet=a;
	}
	public void add(Event e) {
		System.out.println("1");
		e.setTrainer(TrainerSet);
//		System.out.println("2");
		if(i >= events.length)
			return;
		else{
			events[i] = e;
			i++;

		}

	}
	public Event getNext() {
		boolean looped = false;
		int start = next;
		do {
			next = (next + 1) % events.length;
			// See if it has looped to the beginning:
			if(start == next) looped = true;
			// If it loops past start, the list
			// is empty:
			if((next == (start + 1) % events.length)&& looped)
				return null;
		} while(events[next] == null);
		return events[next];
	}
	public void removeCurrent() {
		events[next] = null;
	}
}
class Controller {
	
	PokemonTrainer t1,t2;
	public Controller(PokemonTrainer x,PokemonTrainer y){
		t1=x;
		t2=y;
	}
	private EventSet es_a = new EventSet(t1);
	private EventSet es_b = new EventSet(t2);
	
	public void addEvent(Event a,Event b) {
		es_a.add(a);
		
		es_b.add(b);
		
	}
	
	public void run() {
		Event e;
		Event f;
		while(((e = es_a.getNext()) != null)&&((f = es_b.getNext()) != null)) {
			
			//caso o evento seja um ataque
			if(e.getClassName()=="Atack"){
				if(e.atkPriority<f.atkPriority){
					if(e.ready()) {
						e.action();					
						es_a.removeCurrent();					
					}
					if(f.ready()) {
						f.action();
						es_b.removeCurrent();
					}
				}
				if(e.atkPriority>f.atkPriority){
					if(f.ready()) {
						f.action();
						es_b.removeCurrent();
					}
					if(e.ready()) {
						e.action();					
						es_a.removeCurrent();					
					}					
				}
			}
			
			//caso o evento não seja um ataque
			else{
				if(e.priority<f.priority){
					if(e.ready()) {
						e.action();					
						es_a.removeCurrent();					
					}
					if(f.ready()) {
						f.action();
						es_b.removeCurrent();
					}
				}
				if(e.priority>f.priority){
					if(f.ready()) {
						f.action();
						es_b.removeCurrent();
					}
					if(e.ready()) {
						e.action();					
						es_a.removeCurrent();					
					}					
				}
				
			}
			
		}
	}
}

class Atack extends Event{
	int quant;
	int atkPriority;

	public Atack(String name,int atkPriority,int q ) {
		super(name,atkPriority,q);
	}
	
	public void atkTimeSet(long eventTime){
		evtTime=eventTime;
	}
	
	public int quantity(){
		return quant;
	}

	public void action() {
		System.out.println("usou "+name);
		//caso o dano seje maior do que o hp do pokemon
		if(quant>Trainer.getPokqueue().hp){
			//o Pokemon é morto
			Trainer.getPokqueue().dead=true;
			
			//e removido da fila de pokemons do treinador
			Trainer.queue.remove();
			
			System.out.println(Trainer.getPokqueue().name+"morreu...");
			return;		
		}
		
		//caso o dano seje menor do que o hp do pokemon
		else
			Trainer.getPokqueue().hp=(Trainer.getPokqueue().hp)-quant;
				

	}
	public String getClassName(){
		return"Atack";
	}


}
class SwapPokemon extends Event{
	
	
	public SwapPokemon(long eventTime) {
		super("Trocar pokemon",eventTime);		
		priority=3;

	}
	
	public void action() {
		Trainer.queue.add(Trainer.queue.poll());
		System.out.println("Trocou de pokemon");
		
		
	}
	public String getClassName(){
		return"SwapPokemon";
	}


}

class UseItem extends Event{

	int quant = 10;

	public UseItem(long eventTime) {
		super("Usar item",eventTime);		
		priority=2;
	}
	
	public void action() {
		Pokemon curPok=Trainer.getPokqueue();
		
		if(curPok.hp + quant >= curPok.hp){
			curPok.hp = Pokemon.getMax_hp();//chance de estar errado, pois max hp é estatico
			return;
		}
		else
			curPok.hp=curPok.hp + quant;
		
		System.out.println("Usou o item de vida");

	}
	public String getClassName(){
		return"UseItem";
	}


}
class RunAway extends Event{
	public RunAway(long eventTime) {
		super("Fugir da luta",eventTime);		
		priority=4;

	}


	public void action() {
		System.out.println("fugiu da luta");

	}
	public String getClassName(){
		return"RunAway";
	}


}
