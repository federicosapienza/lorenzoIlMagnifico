package it.polimi.ingsw.gc_26.model.handlers.action_handlers;

import it.polimi.ingsw.gc_26.model.game.game_components.board.positions.CouncilPalace;
import it.polimi.ingsw.gc_26.model.game.game_components.board.positions.MarketPosition;
import it.polimi.ingsw.gc_26.model.game.game_components.board.positions.TowerPosition;
import it.polimi.ingsw.gc_26.model.game.game_components.board.zones.Tower;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.gc_26.model.game.game_components.family_members.FamilyMember;
import it.polimi.ingsw.gc_26.model.game.game_logic.GameParameters;
import it.polimi.ingsw.gc_26.model.player.Player;
import it.polimi.ingsw.gc_26.model.player.PlayerStatus;

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

	/**setting the family member and
	 * getting resources if the permanent effect which revokes this chance is off(preacher card)
	 * @param familyMember 
	 */
	public void goToTowerPosition(TowerPosition position, FamilyMember familyMember, Player player){
		if(familyMember!=null) //means second action
			position.setFamilyMember(familyMember);
		if(!player.getPermanentModifiers().isTowerBonusRevokedOn())
			player.getWarehouse().add(position.getResourcesOrPointsinPosition());
	}
	
		
	 
	public void goToMarketPositions(MarketPosition position,FamilyMember familyMember, Player player){
		position.setFamilyMember(familyMember);
		player.getWarehouse().add(position.getResourcesOrPointsinPosition());
	}
	 
	public void goToCouncilPalacePosition(CouncilPalace position, FamilyMember familyMember, Player player){
		position.setFamilyMember(familyMember);
		player.getWarehouse().add(position.getResourcesOrPointsInPosition());
	}

	public void useCard(DevelopmentCard card, FamilyMember familyMember , Player player){
		/**
		 * Paying the card
		 */
		card.pay(player);
		synchronized (player) {
			if(player.getStatus()==PlayerStatus.CHOOSINGPAYMENT)
				return;
		}
		
		card.runImmediateEffect(player);  //repeat any change here in TwoPaymentHandler
		/** 
		 * The permanent effects of character cards are immediately activated
		 */
		if(card.getType() == DevelopmentCardTypes.CHARACTERCARD)
			card.runPermanentEffect(player);
	
		/**
		 * cleaning the parameter of the card that will no more be used
		 */
		player.setCardUsed(null); 
		
	}
	
	
	
}
