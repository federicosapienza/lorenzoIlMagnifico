package it.polimi.ingsw.GC_26.model.game.game_components.cards.leaderCard;


import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the interface that represents the Leader cards
 *
 */
public interface LeaderCard {
	

	public abstract String getName();
	public abstract boolean checkRequirement(Player player);
	public abstract void runImmediateEffect(Player player);
	public abstract void runPermanentEffect(Player player);
	public abstract boolean hasAPermanentEffect(); // true for permanent effects, false otherwise(once per round ability.

	


}
