package it.polimi.ingsw.GC_26_actionsHandlers;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_board.CouncilPalace;
import it.polimi.ingsw.GC_26_board.MarketPosition;

import it.polimi.ingsw.GC_26_board.Tower;
import it.polimi.ingsw.GC_26_board.TowerPosition;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the handler to use when an action has to be performed
 */
public class ActionPerformerHandler {
	
	/**
	 * Constructor: it creates a handler to perform an action, by the evaluation of the parameters expressed by 
	 * gameElements and harvestAndProductionHandler 
	 * 
	 */
	
	 
	
	/**
	 * If the tower is occupied, pay coins (or any payment if rules are changed) (Brunelleschi effect is not activated)
	 */
	public void payCoinsIfTowerOccupied(Tower tower, Player player){
	if(!tower.isTheTowerFree()&& !player.getPermanentModifiers().isTowerOccupiedMalusDisabled())
		player.getWarehouse().spendResources(GameParameters.getTowerOccupiedMalus());
	}

	/**
	 * getting resources if the permanent effect which revokes this chance is off(preacher card)
	 */
	public void getResourcesBonusFromTowerPositions(TowerPosition position, Player player){
	if(! player.getPermanentModifiers().isTowerBonusRevokedOn())
		player.getWarehouse().add(position.getResourcesOrPointsinPosition());
	}
	
		
	 
	public void getResourcesBonusFromMarketPositions(MarketPosition position, Player player){
	 player.getWarehouse().add(position.getResourcesOrPointsinPosition());
	}
	 
	public void getResourcesBonusFromCouncilPalacePosition(CouncilPalace position, Player player){
		 player.getWarehouse().add(position.getResourcesOrPointsInPosition());
		}

	
	
	
	
}
