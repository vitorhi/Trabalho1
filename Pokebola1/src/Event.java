
abstract public class Event {
	private long evtTime;
	double atkPower;
	boolean ready;
	
	public Event(long eventTime){
		evtTime = eventTime;
	}
	
}
class Atack extends Event{
	long quant;
	
	public Atack(long eventTime,long q ) {
		super(eventTime);
		quant= q;
		// TODO Auto-generated constructor stub
	}
	public long quantity(){
		return quant;
	}
	
	
}
class SwapPokemon extends Event{
	
}
class UseItem extends Event{
	
}
class RunAway extends Event{
	
}
