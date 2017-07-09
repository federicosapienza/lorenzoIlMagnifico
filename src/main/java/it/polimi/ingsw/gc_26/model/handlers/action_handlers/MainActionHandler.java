package it.polimi.ingsw.gc_26.model.handlers.action_handlers;


import it.polimi.ingsw.gc_26.model.game.game_logic.GameElements;
import it.polimi.ingsw.gc_26.model.handlers.choice_handlers.DiplomaticPrivilegesHandler;
import it.polimi.ingsw.gc_26.model.handlers.choice_handlers.LeaderCardHandler;
import it.polimi.ingsw.gc_26.model.handlers.choice_handlers.TradeHandler;
import it.polimi.ingsw.gc_26.model.handlers.choice_handlers.TwoPaymentsHandler;
import it.polimi.ingsw.gc_26.model.handlers.choice_handlers.VaticanReportHandler;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the handler for main actions
 *
 */



public class MainActionHandler {
	//a list of the actions : all of them are called by different controllers.
	private GameElements gameElements;
	private FirstActionHandler firstActionHandler;
	private SecondActionHandler secondActionHandler;
	private HarvestAndProductionHandler harvestAndProductionHandler;
	private TradeHandler tradeHandler;
	private TwoPaymentsHandler twoPaymentHandler;
	private DiplomaticPrivilegesHandler diplomaticPrivilegesHandler;
	private VaticanReportHandler vaticanReportHandler;
	private LeaderCardHandler leaderCardHandler;
	
	/**
	 * Constructor: it creates a handler for main actions performed with the game elements contained in the parameter
	 * @param gameElements the game elements whose main actions are based on
	 */
	public MainActionHandler(GameElements gameElements) {
		if (gameElements == null) {
			throw new NullPointerException("gameElements are null");
		}
		this.gameElements=gameElements;
		harvestAndProductionHandler = new HarvestAndProductionHandler();
		
		firstActionHandler = new FirstActionHandler(gameElements, harvestAndProductionHandler);
		secondActionHandler = new SecondActionHandler(gameElements, harvestAndProductionHandler);
	
		tradeHandler = new TradeHandler();
		twoPaymentHandler = new TwoPaymentsHandler(gameElements.getPlayers());
		diplomaticPrivilegesHandler = new DiplomaticPrivilegesHandler();
		vaticanReportHandler = new VaticanReportHandler(gameElements);
		leaderCardHandler = new LeaderCardHandler( gameElements.getPlayers());
		
	}

	/**
	 * Method that returns the game elements which this MainActionHandler is based on
	 * @return the game elements which this MainActionHandler is based on
	 */
	public GameElements getGameElements() {
		return gameElements;
	}
	
	/**
	 * Method that returns the FirstActionHandler of this MainActionHandler
	 * @return the FirstActionHandler of this MainActionHandler
	 */
	public FirstActionHandler getFirstActionHandler() {
		return firstActionHandler;
	}
	
	/**
	 * Method that returns the SecondActionHandler of this MainActionHandler
	 * @return the SecondActionHandler of this MainActionHandler
	 */
	public SecondActionHandler getSecondActionHandler() {
		return secondActionHandler;
	}
	
	/**
	 * Method that returns the HarvestAndProductionHandler of this MainActionHandler
	 * @return the HarvestAndProductionHandler of this MainActionHandler
	 */
	public HarvestAndProductionHandler getHarvestAndProductionHandler() {
		return harvestAndProductionHandler;
	}
	
	/**
	 * Method that returns the TradeHandler of this MainActionHandler
	 * @return the TradeHandler of this MainActionHandler
	 */
	public TradeHandler getTradeHandler() {
		return tradeHandler;
	}
	
	/**
	 * Method that returns the TwoPaymentsHandler of this MainActionHandler
	 * @return the TwoPaymentsHandler of this MainActionHandler
	 */
	public TwoPaymentsHandler getTwoPaymentHandler() {
		return twoPaymentHandler;
	}
	
	/**
	 * Method that returns the DiplomaticPrivilegesHandler of this MainActionHandler
	 * @return the DiplomaticPrivilegesHandler of this MainActionHandler
	 */
	public DiplomaticPrivilegesHandler getDiplomaticPrivilegesHandler() {
		return diplomaticPrivilegesHandler;
	}
	
	/**
	 * Method that returns the VaticanReportHandler of this MainActionHandler
	 * @return the VaticanReportHandler of this MainActionHandler
	 */
	public VaticanReportHandler getVaticanReportHandler() {
		return vaticanReportHandler;
	}
	
	/**
	 * Method that returns the LeaderCardHandler of this MainActionHandler
	 * @return the LeaderCardHandler of this MainActionHandler
	 */
	public LeaderCardHandler getLeaderCardHandler() {
		return leaderCardHandler;
	}
}
