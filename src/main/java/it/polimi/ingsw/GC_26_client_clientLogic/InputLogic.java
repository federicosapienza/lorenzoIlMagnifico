package it.polimi.ingsw.GC_26_client_clientLogic;

import it.polimi.ingsw.GC_26_cards.CardDescriber;

public interface InputLogic extends Runnable{
	public void setWaitingFirstAction();
	public void setWaitingSecondAction();
	public void setWaitingResponse();
	public void setActionPerformed();
	public  void setTurnEnded();
	public void setPlayerSuspended();
	public void setWaitingVaticanChoice(CardDescriber card);
	public void setWaitingPaymentChoice();
	public void setWaitingTrading(CardDescriber card);
	public void setWaitingCouncilPriviledge();
	
	public void close();
	
}
