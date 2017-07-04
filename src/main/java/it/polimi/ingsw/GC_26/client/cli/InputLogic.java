package it.polimi.ingsw.GC_26.client.cli;

import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;

public interface InputLogic extends Runnable{
	public void setWaitingFirstAction();
	public void setWaitingSecondAction();
	public void setActionPerformed();
	public  void setTurnEnded();
	public void setPlayerSuspended();
	public void setWaitingVaticanChoice(CardDescriber card);
	public void setWaitingPaymentChoice();
	public void setWaitingTrading(CardDescriber card);
	public void setWaitingCouncilPriviledge();
	
	public void close();
	
}
