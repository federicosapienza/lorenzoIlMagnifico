package it.polimi.ingsw.GC_26.client.cli;

import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the interface that represents the input logic
 *
 */
public interface InputLogic extends Runnable{
	public void setWaitingFirstAction();
	public void setWaitingSecondAction();
	public void setActionPerformed();
	public  void setTurnEnded();
	public void setPlayerSuspended();
	public void setWaitingVaticanChoice(CardDescriber card);
	public void setWaitingPaymentChoice();
	public void setWaitingTrading(CardDescriber card);
	public void setWaitingCouncilPrivilege();
	
	public void close();
	
}
