package it.polimi.ingsw.GC_26_actionHandlers;

import it.polimi.ingsw.GC_26_gameLogic.GameElements;

/* a list of the actions : all of them are watched by different controllers.
When the game is beginning this classes passes the reference of gameElements to the actionHandlers: 
(it can ' t be done until the list of the player is definitely set.
 */
public class MainActionHandler {
	private GameElements gameElements;
	private TowerActionHandler towerActionHandler;
	//TODO 
	
	public MainActionHandler() {
		towerActionHandler =new TowerActionHandler();
		//TODO
		
	}

	public void setGameElements(GameElements gameElements){
		this.gameElements= gameElements;
		towerActionHandler.setGameElements(gameElements);
	}
}
