package it.polimi.ingsw.GC_26.model.actionHandlers;


import it.polimi.ingsw.GC_26.model.game.gameLogic.GameElements;

/* a list of the actions : all of them are called by different controllers.
 */
public class MainActionHandler {
	private GameElements gameElements;
	private FirstActionHandler firstActionHandler;
	private SecondActionHandler secondActionHandler;
	private HarvestAndProductionHandler harvestAndProductionHandler;
	private TradeHandler tradeHandler;
	private TwoPaymentsHandler twoPaymentHandler;
	private DiplomaticPrivilegesHandler diplomaticPrivilegesHandler;
	private VaticanReportHandler vaticanReportHandler;
	private LeaderCardHandler leaderCardHandler;
	 
	public MainActionHandler(GameElements gameElements) {
		if (gameElements == null) {
			throw new NullPointerException("gameElements are null");
		}
		this.gameElements=gameElements;
		harvestAndProductionHandler = new HarvestAndProductionHandler();
		
		firstActionHandler =new FirstActionHandler(gameElements, harvestAndProductionHandler);
		secondActionHandler= new SecondActionHandler(gameElements, harvestAndProductionHandler);
	
		tradeHandler = new TradeHandler();
		twoPaymentHandler = new TwoPaymentsHandler(gameElements.getPlayers());
		diplomaticPrivilegesHandler= new DiplomaticPrivilegesHandler();
		vaticanReportHandler = new VaticanReportHandler(gameElements);
		leaderCardHandler =new LeaderCardHandler( gameElements.getPlayers());
		
	}

	public GameElements getGameElements() {
		return gameElements;
	}
	
	public FirstActionHandler getFirstActionHandler() {
		return firstActionHandler;
	}
	
	public SecondActionHandler getSecondActionHandler() {
		return secondActionHandler;
	}
	
	public HarvestAndProductionHandler getHarvestAndProductionHandler() {
		return harvestAndProductionHandler;
	}
	
	public TradeHandler getTradeHandler() {
		return tradeHandler;
	}
	public TwoPaymentsHandler getTwoPaymentHandler() {
		return twoPaymentHandler;
	}
	
	public DiplomaticPrivilegesHandler getDiplomaticPrivilegesHandler() {
		return diplomaticPrivilegesHandler;
	}
	
	public VaticanReportHandler getVaticanReportHandler() {
		return vaticanReportHandler;
	}
	public LeaderCardHandler getLeaderCardHandler() {
		return leaderCardHandler;
	}
}
