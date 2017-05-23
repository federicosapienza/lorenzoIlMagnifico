package it.polimi.ingsw.GC_26_actionHandlers;

import java.awt.Desktop.Action;
import java.util.Observable;

import javax.print.attribute.standard.RequestingUserName;

import it.polimi.ingsw.GC_26_actions.BoardAction;
import it.polimi.ingsw.GC_26_cards.effects.PickACardEffect;
import it.polimi.ingsw.GC_26_cards.payments.Payment;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;

public class TowerActionHandler extends ActionHandler{
	
	
	///////in via di eliminazione!!!!
		
		
	
		
	private boolean isPossible(BoardAction action ){
		if(action.getPlayer().getStatus() != PlayerStatus.PLAYING)
			//TODO notificare:forse va lanciata un' eccezione
			return false;
		 // non va bene osì per doppi pagamenti: Warehouse temporaryWarehouse = new Warehouse(action.getPlayer().getWarehouse());
		if(temporaryWarehouse.areResourcesEnough(action.getServantsUsed()))
			return false;
			//TODO notificare player!!
		if(!familyMember.isFree())
			return false;
		 //TODO notificare
		
	}	
}
	//private void pay()
	//Fa il percorso e tutto ok
	
	//ricordarsi gestione di effetto permanetne di carta personaggio, che non è veramente permanente perchè attivato immediatamente: qui



