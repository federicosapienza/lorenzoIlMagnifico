package it.polimi.ingsw.GC_26_actionsHandlers;

import it.polimi.ingsw.GC_26_gameLogic.GameElements;

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
	 
	public MainActionHandler() {
		harvestAndProductionHandler = new HarvestAndProductionHandler(gameElements);
		
		firstActionHandler =new FirstActionHandler(gameElements, harvestAndProductionHandler);
		secondActionHandler= new SecondActionHandler(gameElements, harvestAndProductionHandler);
		harvestAndProductionHandler = new HarvestAndProductionHandler(gameElements);
		tradeHandler = new TradeHandler(gameElements, harvestAndProductionHandler);
		twoPaymentHandler = new TwoPaymentsHandler(gameElements);
		diplomaticPrivilegesHandler= new DiplomaticPrivilegesHandler();
		vaticanReportHandler = new VaticanReportHandler(gameElements);
		leaderCardHandler =new LeaderCardHandler( gameElements);
		
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
