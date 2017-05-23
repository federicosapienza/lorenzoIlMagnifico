package it.polimi.ingsw.GC_26_actionHandlers;

import java.util.Observable;

import javax.imageio.ImageTypeSpecifier;

import it.polimi.ingsw.GC_26_actions.BoardAction;
import it.polimi.ingsw.GC_26_board.Tower;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;



// synchronization is ensured by Controllers which calls method of this function, only if the player is allowed
public class FirstActionHandler extends ActionHandler{
	
	
	

	@Override
	public void update(Observable o, Object arg) {
		BoardAction action= (BoardAction) arg;
	//	Boolean flag = isPossible(action);
		//if(!flag)
			return;  // ho già notificato in ifPossible
		//if(flag)
			//pay()	
}
	
	
		 /*public boolean isPossible(Player player, BoardAction action ){
			//spostare sopra
			//if(action.getPlayer().getStatus() != PlayerStatus.PLAYING)  //TODO la spostiamo in controller
				//TODO notificare:forse va lanciata un' eccezione
				return false;
			 // non va bene osì per doppi pagamenti: Warehouse temporaryWarehouse = new Warehouse(action.getPlayer().getWarehouse());
		player.setTemporaryWarehouse();  // prepares the action
			if(player.getTemporaryWarehouse().areResourcesEnough(action.getServantsUsed()))
				return false;
				//TODO notificare player!!
			FamilyMember familyMemberUsed = player.getFamilyMembers().getfamilyMember(action. getFamilyMemberColour()); //togliere puntoe virgola
			if(!familyMemberUsed.isFree())
				return false;
			//TODO notificare
		
		//manda a tutti i diversi metodi per le diverse posizioni della board!
			
		}
	//}
	/*	private boolean towerIsPossible(Player player, FamilyMember familyMember, BoardAction action){
			//if// sono già nella torre non mi fare andare
			//if//TOWEr non libera fai spendere 3 soldi in temporaryWarehouse
			//if(getGameElements().getBoard().getTower(action.getTower()) // è libero)
				//TODO modificare questo metodo
			Tower tower = getGameElements().getBoard().getTower(action.getZone());
			if(tower.isThePlayerInTheTower(player)){
				//notificare il player
				return false;}
			
			if(tower.isTheTowerOccupied()){
				;
					//	if (player.getTemporaryWarehouse().areResourcesEnough(//todo monete));
			//	player.getTemporaryWarehouse().spendResources(//TODO TogliereMonete)
	
				
			//TODO notificare
			//PickACard
			// non si aggiunge guadagno al temporaryWarehouse
			//prova a pagare : qui doppio pagamento non mi interessa: glielo dirò nella carta: se è uno solo paga comunque
				
		}
			*/

		//private void pay()
		//Fa il percorso e tutto ok
		
		//ricordarsi gestione di effetto permanetne di carta personaggio, che non è veramente permanente perchè attivato immediatamente: qui


		}
