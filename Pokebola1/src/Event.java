
abstract public class Event {

	int priority;
	String name;


	public Event(String name){

		this.name=name;

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
	public void add(Event e) {
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
	private EventSet es = new EventSet();
	public void addEvent(Event c) { es.add(c); }
	public void run() {
		Event e;
		while((e = es.getNext()) != null) {

			e.action();
			
			es.removeCurrent();

		}
	}
}	
class Atack extends Event{
	int quant;
	int atkPriority;

	public Atack(String name,int atkPriority,int q ) {
		super(name);
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


	public SwapPokemon() {
		super("Trocar pokemon");		
		priority=3;

	}
	
	public void action() {
		System.out.println("Trocou de pokemon");
		
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
	// O quanto ele recuperou de vida
	public int quantity(){
		return quant;
	}

	public void action() {
		System.out.println("Usou o item de vida");

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
		System.out.println("fugiu da luta");

	}
	public String getClassName(){
		return"RunAway";
	}


}
