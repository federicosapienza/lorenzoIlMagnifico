package it.polimi.ingsw.GC_26_actionHandlers;

import java.util.Observable;

import it.polimi.ingsw.GC_26_actions.BoardAction;
import it.polimi.ingsw.GC_26_player.PlayerStatus;

public class FirstActionHandler extends ActionHandler{
	

	@Override
	public void update(Observable o, Object arg) {
		BoardAction action= (BoardAction) arg;
		Boolean flag = isPossible(action);
		if(!flag)
			return;  // ho già notificato in ifPossible
		//if(flag)
			//pay()	
}
	
	
	public boolean isPossible(){
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
		
		//manda a tutti i diversi metodi per le diverse posizioni della board!
			
		}
	}
		private boolean towerIsPossible(){
			//if// sono già nella torre non mi fare andare
			//if//TOWEr non libera fai spendere 3 soldi in temporaryWarehouse
			//if(getGameElements().getBoard().getTower(action.getTower()) // è libero)
				//TODO modificare questo metodo
				return false;
				//TODO notificare
			//PickACard
			// non si aggiunge guadagno al temporaryWarehouse
			//prova a pagare : qui doppio pagamento non mi interessa: glielo dirò nella carta: se è uno solo paga comunque
		}
			

		//private void pay()
		//Fa il percorso e tutto ok
		
		//ricordarsi gestione di effetto permanetne di carta personaggio, che non è veramente permanente perchè attivato immediatamente: qui


		}
		