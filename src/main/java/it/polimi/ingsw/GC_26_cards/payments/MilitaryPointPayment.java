package it.polimi.ingsw.GC_26_cards.payments;

public class MilitaryPointPayment implements OnePayment{
	private final int toSpend;  // the amount player have  really to spend
	private final int needed;  // the requirement
	
	public MilitaryPointPayment(int toSpend, int needed) {
		this.toSpend=toSpend;
		this.needed= needed;
	}
	
	private MilitaryPointPayment(MilitaryPointPayment other) {
		this.toSpend = other.getToSpend();
		this.needed = other.getNeeded();
	}
	
	public int getToSpend() {
		return toSpend;
	}
	
	public int getNeeded() {
		return needed;
	}
	
	@Override
	public boolean canPlayerGetThis(Player player, ResourcesOrPoints resourcesUsedUntilNow) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pay(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Payment copy() {
		return new MilitaryPointPayment(this);
		
	}
	
}
