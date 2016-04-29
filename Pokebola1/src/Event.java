
abstract public class Event {

	int priority;
	PokemonTrainer Trainer;
	String name;
	private long evtTime;
	
	public boolean ready() {
		return System.currentTimeMillis() >= evtTime;
	}
		
	public Event(String name,long eventTime){
		evtTime = eventTime;
		this.name=name;
	}
	
	public void setTrainer(PokemonTrainer Trainer){
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
		e.setTrainer(TrainerSet);
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


class Atack extends Event{
	int quant;
	int atkPriority;

	public Atack(String name,int atkPriority,int q,long eventTime ) {
		super(name,eventTime);
		quant= q;
		priority=1;
		this.atkPriority=atkPriority;

	}
	public int quantity(){
		return quant;
	}

	public void action() {
		System.out.println("usou "+name);

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
