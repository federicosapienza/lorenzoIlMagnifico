package it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard;



import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the interface that represents all the requirements
 *
 */
public interface Requirement {
	boolean checkRequirement(Player player);


}
