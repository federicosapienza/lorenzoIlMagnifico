package it.polimi.ingsw.GC_26.client.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.GC_26.client.view.BoardView;
import it.polimi.ingsw.GC_26.client.view.MainClientView;
import it.polimi.ingsw.GC_26.client.view.PlayerView;
import it.polimi.ingsw.GC_26.client.view.PositionView;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.messages.describers.PositionDescriber;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26.model.game.gameComponents.dices.Colour;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that represents the output displayed in the CLI.
 * Methods are synchronized to avoid ugly mixtures of requests from InputLogic and info from views.
 *
 */

public class OutputCLI implements Output{

	
	/**
	 * Method that prints the board
	 */
	@Override
	public synchronized void printBoard(BoardView board) {
		xPrintSysOut("*****************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************");
		xPrintSysOut("TERRITORY TOWER:");
		xPrintSysOut("VALUE:|-BONUS:-------------------------|-NAME:--------------------|-ACTION VALUE:|-PAYMENT:-----------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
		printList(board.getTerritoriesTower());
		xPrintSysOut("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		xPrintSysOut("CHARACTER TOWER:");
		xPrintSysOut("VALUE:|-BONUS:-------------------------|-NAME:--------------------|-ACTION VALUE:|-PAYMENT:-----------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
		printList(board.getCharactersTower());
		xPrintSysOut("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		xPrintSysOut("BUILDING TOWER:");
		xPrintSysOut("VALUE:|-BONUS:-------------------------|-NAME:--------------------|-ACTION VALUE:|-PAYMENT:-----------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
		printList(board.getBuildingsTower());
		xPrintSysOut("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		xPrintSysOut("VENTURE TOWER:");
		xPrintSysOut("VALUE:|-BONUS:-------------------------|-NAME:--------------------|-ACTION VALUE:|-PAYMENT:-----------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
		printList(board.getVenturesTower());
		xPrintSysOut("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		xPrintSysOut("MARKET:");
		xPrintSysOut("VALUE:|-BONUS:-------------------------|");
		printList(board.getMarketZone());
		xPrintSysOut("---------------------------------------|");
		xPrintSysOut("COUNCIL PALACE:");
		xPrintSysOut("VALUE:|-BONUS:-------------------------|");
		printList(board.getCouncilPalace());
		xPrintSysOut("---------------------------------------|");
		xPrintSysOut("PRODUCTION:");
		xPrintSysOut("VALUE:|");
		printList(board.getProductionZone());
		xPrintSysOut("------|");
		xPrintSysOut("HARVEST:");
		xPrintSysOut("VALUE:|");
		printList(board.getHarvestZone());
		xPrintSysOut("------|");
		xPrintSysOut("*****************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************");

	}
	
	/**
	 * Method that simply prints the title of the game 
	 */
	@Override
	public void printTitle(){
		xPrintSysOut("         |----|       |-----------|  |----------|  |----------|  |----  |---|  |-----------|  |-----------| \n"
		          +"         |    |       |           |  | |-----|  |  |          |  |    \\ |   |  |           |  |           | \n"
		          +"         |    |       |  |-----|  |  | |     |  |  |   |------|  |     \\|   |  |----       |  |  |-----|  | \n"
		          +"         |    |       |  |     |  |  | |-----|  |  |   |         |          |      /       /  |  |     |  | \n"
	 	          +"         |    |       |  |     |  |  |       ---|  |    ------|  |          |     /       /   |  |     |  | \n"
		          +"         |    |       |  |     |  |  |  | \\  \\     |    ------|  |          |    /       /    |  |     |  | \n"
		          +"         |    ------  |  |     |  |  |  |  \\  \\    |   |         |          |   /       /     |  |     |  | \n"
		          +"         |         |  |  |-----|  |  |  |   \\  \\   |   |------|  |          |  |       ----|  |  |-----|  | \n"
		          +"         |         |  |           |  |  |    \\  \\  |          |  |    |\\    |  |           |  |           | \n"
		          +"         |---------|  |-----------|  |--|     \\--\\ |----------|  |----| \\---|  |-----------|  |-----------| \n"
		          +"\n"
		          +"\n"
		          +"                                                |---|  |---| \n"
		          +"                                                |   |  |   | \n"
		          +"                                                |   |  |   | \n"
		          +"                                                |   |  |   | \n"
		          +"                                                |   |  |   ---- \n"
		          +"                                                |   |  |      | \n"
		          +"                                                |---|  |------| \n"
		          +"\n"
		          +"\n"
		          + "   |---     ----|  |----------|  |----------|  |----  |---|  |---|  |--------|  |---|  |----------|  |-----------|\n"
		          + "   |   \\   /    |  |   ----   |  |          |  |    \\ |   |  |   |  |        |  |   |  |          |  |           |\n"
		          + "   |    \\-/     |  |  |    |  |  |   |------|  |     \\|   |  |   |  |   |----|  |   |  |   |------|  |  |-----|  |\n"
		          + "   |            |  |  |    |  |  |   |         |          |  |   |  |   |       |   |  |   |         |  |     |  |\n"
		          + "   |            |  |   ----   |  |   |         |          |  |   |  |   |----|  |   |  |   |         |  |     |  |\n"
		          + "   |            |  |          |  |   | |----|  |          |  |   |  |        |  |   |  |   |         |  |     |  |\n"
		          + "   |   |\\  /|   |  |   |--|   |  |   | |--| |  |          |  |   |  |   |----|  |   |  |   |         |  |     |  |\n"
		          + "   |   | \\/ |   |  |   |  |   |  |   |----| |  |          |  |   |  |   |       |   |  |   |------|  |  |-----|  |\n"
		          + "   |   |    |   |  |   |  |   |  |          |  |    |\\    |  |   |  |   |       |   |  |          |  |           |\n"
		          + "   |---|    |---|  |---|  |---|  |----------|  |----| \\---|  |---|  |---|       |---|  |----------|  |-----------|\n"
		          +"\n"
		          +"\n"
		          +"\n");
	}
	
	/**
	 * Method that prints the string contained in the parameter. It's used just to be quicker when calling the print method
	 * @param string It's the string that has to be printed
	 */
	private void xPrintSysOut(String string){ 
		System.out.println(string);
	}
	
	/**
	 * Method that prints the list of position views
	 * @param positions It's the list of position views
	 */
	private void printList(List<PositionView> positions){
		for(PositionView p : positions){
			printTool(p);
		}
	}
	
	/**
	 * Method that prints the info about the PositionView contained in the parameter 
	 * @param position It's the PositionView whose info have to be displayed
	 */
	private void printTool(PositionView position){
		StringBuilder temp= new StringBuilder(" ");
		String test;
		if(position.getBonusPosition() !=null){
			test = printBonusPosition(position.getPositionDescriber());
			temp.append(test);
		}
		if(position.getCardHere()!=null){
			//temp.append(position.getCardHere()+ " ");//before
			test = printDevelopmentCards(position.getCardHere());//How should it be.
			temp.append(test);
		}
		if(!position.getPlayersHere().isEmpty()){
			Map<String , Colour> here= position.getPlayersHere();
			for (Map.Entry<String, Colour> entry : here.entrySet()){
			    temp.append(entry.getKey()+ " "+ entry.getValue()+" ");
			}

		}
		xPrintSysOut(position.getPositionValue()+"     |"+temp);
	}
	
	/**
	 * Method that prints the bonus of the position described by the PositionDescriber contained in the parameter
	 * @param positionDescriber It's the describer of the position whose bonus has to be displayed
	 * @return the description of the bonus of the position as a string
	 */
	private String printBonusPosition(PositionDescriber positionDescriber){
		String repeated ;
		StringBuilder temp = new StringBuilder(" ");
		if("null".equals(positionDescriber.getResourcesOrPointsOnPosition())){
			return temp.toString();
		}
		if(" ".equals(positionDescriber.getResourcesOrPointsOnPosition())){
			repeated = new String(new char[30]).replace("\0", " ");
			temp.append(repeated +"|");
			}
		else{
			repeated = new String(new char[30-positionDescriber.getResourcesOrPointsOnPosition().length()]).replace("\0", " ");
			temp.append(positionDescriber.getResourcesOrPointsOnPosition() + repeated +"|");
			}
		return temp.toString();
	}
	
	/**
	 * Method that prints the info about Development card described by the CardDescriber contained in the parameter
	 * @param card It's the CardDescriber that describes the info of card to print
	 * @return the info of the card as a string
	 */
	private String printDevelopmentCards(CardDescriber card){
	String repeated;
	StringBuilder temp = new StringBuilder(" ");
	if("Development Card".equals(card.getTypeOfCard())){//Dev cards finished
		if(card.getName()!=null){
			repeated = new String(new char[25-card.getName().length()]).replace("\0", " ");
			temp.append(card.getName() + repeated + "|");
		}
		if(card.getActionValue()==0){ //Valore di inizializzazione
			repeated = new String(new char[14]).replace("\0", " ");
			temp.append (repeated + "|");
		}
		else{ //Valore di inizializzazione
			repeated = new String(new char[13]).replace("\0", " ");
			temp.append (card.getActionValue() + repeated + "|");
		}
		if(card.getPaymentDescriber()!=null){
			repeated = new String(new char[80-card.getPaymentDescriber().length()]).replace("\0", " ");
			temp.append(card.getPaymentDescriber() + repeated + "|");
		}
		else{
			repeated = new String(new char[80]).replace("\0", " ");
			temp.append(repeated + "|");
		}
		if(card.getImmediateEffectDescriber()!=null){
			repeated = new String(new char[120-card.getImmediateEffectDescriber().length()]).replace("\0", " ");
			temp.append(card.getImmediateEffectDescriber() + repeated + "|");
		}
		else{
			repeated = new String(new char[120]).replace("\0", " ");//120 should be enough
			temp.append(repeated + "|");
		}
		if(card.getPermanentEffectDescriber()!=null){
			repeated = new String(new char[140-card.getPermanentEffectDescriber().length()]).replace("\0", " ");
			temp.append(card.getPermanentEffectDescriber() + repeated + "|");
		}
		else{
			repeated = new String(new char[140]).replace("\0", " ");//140 should be enough
			temp.append(repeated + "|");
		}
	}
	return temp.toString();
	}
	
	/**
	 * Method that prints the info about the Leader cards described by the list of CardDescriber contained in the parameter
	 */
	@Override
	public void printLeaderCards(List<CardDescriber> cards){
		String repeated ;
		StringBuilder temp = new StringBuilder(" ");
		xPrintSysOut("\n");
		xPrintSysOut(" LEADER CARDS:");
		xPrintSysOut(" |NAME:-------------------------|-REQUIREMENTS------------------------------------------------------------------------------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:-------------------------------------------------------------------------------------------------------------------------------------");
		for(CardDescriber cardDescriber : cards){
			repeated = new String(new char[30-cardDescriber.getName().length()]).replace("\0", " ");
			temp.append("|" + cardDescriber.getName() + repeated +"|");
			repeated = new String(new char[150-cardDescriber.getRequirementDescriber().length()]).replace("\0", " ");
			temp.append(" " + cardDescriber.getRequirementDescriber() + repeated+"|");
			if(cardDescriber.getImmediateEffectDescriber()!=null){
				repeated = new String(new char[150-cardDescriber.getImmediateEffectDescriber().length()]).replace("\0", " ");
				temp.append(cardDescriber.getImmediateEffectDescriber() + repeated+"|");
			}
			if(cardDescriber.getImmediateEffectDescriber()==null){
				repeated = new String(new char[150]).replace("\0", " ");
				temp.append(repeated +"|");
			}
			if(cardDescriber.getPermanentEffectDescriber()!=null){
				repeated = new String(new char[150-cardDescriber.getPermanentEffectDescriber().length()]).replace("\0", " ");
				temp.append(cardDescriber.getPermanentEffectDescriber() + repeated+"|");
			}
			if(cardDescriber.getPermanentEffectDescriber()==null){
				repeated = new String(new char[150]).replace("\0", " ");
				temp.append(repeated +"|");
			}
			System.out.println(temp); //è uno stringBuilder,lo lascio con sysout
			temp = new StringBuilder(" ");
		}
		xPrintSysOut(" |-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		xPrintSysOut("\n");
	}
	
	/**
	 * Method that prints the Excommunication tiles for the BoardView contained in the parameter
	 */
	@Override
	public void printExcommunicationTiles(BoardView board) {
		String repeated ;
		xPrintSysOut("\n");
		xPrintSysOut(" EXCOMMUNICATION TILES:");
		xPrintSysOut("|PERIOD:|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
		for(CardDescriber card: board.getExcommunicationThisGame()){
			repeated = new String(new char[140-card.getPermanentEffectDescriber().length()]).replace("\0", " ");
			xPrintSysOut("|"+card.getPeriod() +"      |"+  card.getPermanentEffectDescriber()+repeated+"|");
		}
		xPrintSysOut("-----------------------------------------------------------------------------------------------------------------------------------------------------|");
		xPrintSysOut("\n");
	}
	
	/**
	 * Method that simply prints the string contained in the parameter
	 */
	@Override
	public void printString(String string) {
		xPrintSysOut(string);
	}
	
	/**
	 * Method that prints all the resources and points owned by the player that corresponds to the PlayerView contained in the parameter
	 */
	@Override
	public  void printResources(PlayerView player) {
		xPrintSysOut(player.getWallet().toString());
		
	}
	
	/**
	 * Method that prints all the info about the player that corresponds to the PlayerView contained in the parameter
	 */
	@Override
	public  void printCompleteStatus(PlayerView player) {
		xPrintSysOut("\n");
		xPrintSysOut("RECAP OF PLAYER " + "'" + player.getName() + "' :");
		xPrintSysOut("\n");
		xPrintSysOut("Family members value: ");
		xPrintSysOut(player.getFamilyMembers().getStatus().toString());
		xPrintSysOut("\n");
		xPrintSysOut("Free family members:  ");
		xPrintSysOut(player.getFamilyMembers().whatIsFree().toString());
		xPrintSysOut("\n");
		xPrintSysOut("Resources and points:");
		printResources(player);	
		xPrintSysOut("\n");
		xPrintSysOut("Cards owned:");
		if(player.getCurrentCards(DevelopmentCardTypes.TERRITORYCARD).isEmpty() && player.getCurrentCards(DevelopmentCardTypes.CHARACTERCARD).isEmpty()
			&& player.getCurrentCards(DevelopmentCardTypes.BUILDINGCARD).isEmpty()	&& player.getCurrentCards(DevelopmentCardTypes.VENTURECARD).isEmpty()){
			xPrintSysOut("No cards owned yet");
		}
		else{
			printCards(player);
		}
		xPrintSysOut("\n");
		
		if(!player.getLeaderCardUsed().isEmpty()){
			printLeaderCards(player.getLeaderCardUsed());
			xPrintSysOut("\n");
		}
		
		xPrintSysOut("Personal tile values: ");
		xPrintSysOut(player.getPersonalTileValues());
		xPrintSysOut("\n");
		
	}
	
	/**
	 * Method that prints the rankings of the game 
	 */
	@Override
	public  void printRankings(MainClientView view) {
		Map<String, PlayerView> players=view.getPlayers();
		List<PlayerView> playerViews  = new ArrayList<>(players.values());
		xPrintSysOut("\n");
		xPrintSysOut("RANKINGS: ");
		for(int i=0; i<playerViews.size();i++){
			xPrintSysOut("PLAYER " + "'"  + playerViews.get(i).getName()  + "'" + " : " +"|Victory Points :" + playerViews.get(i).getWallet().getVictoryPoints() +" |Military Points: " 
					+ playerViews.get(i).getWallet().getMilitaryPoints() + " |Faith Points: " + playerViews.get(i).getWallet().getFaithPoints());
		}
		xPrintSysOut("\n");
	}
	
	/**
	 * Method that prints the cards described in the Set of CardDescriber contained in the parameter
	 */
	public void printCards(Set<CardDescriber> cards){
		for(CardDescriber cardDescriber : cards)
			System.out.println(cardDescriber);
	}
	
	/**
	 * Method that prints the Development cards described in the Set of CardDescriber contained in the parameter
	 * @param cards
	 */
	public void printDevelopmentCardsSet(Set<CardDescriber> cards){
		for(CardDescriber cardDescriber : cards){
			String temp = printDevelopmentCards(cardDescriber);
			xPrintSysOut("|"+temp);
		}
	}
	
	/**
	 * Method that prints the info about the Family Members of the player that corresponds to the PlayerView contained in the parameter
	 */
	public void printFamilyMembers(PlayerView player){
		System.out.println(player.getFamilyMembers().getStatus());
		xPrintSysOut(player.getFamilyMembers().whatIsFree().toString());
	}
	
	/**
	 * Method that prints all the cards which are contained in the Personal Board of the player that corresponds to the PlayerView 
	 * contained in the parameter and the permanent effects that are affecting the player
	 */
	@Override
	public void printCards(PlayerView player) {  
		if(!player.getCurrentCards(DevelopmentCardTypes.TERRITORYCARD).isEmpty()){
			xPrintSysOut(" Territory Cards owned:");
			xPrintSysOut("|-NAME:--------------------|-ACTION VALUE:|-PAYMENT:-----------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
			printDevelopmentCardsSet(player.getCurrentCards(DevelopmentCardTypes.TERRITORYCARD));}
		if(!player.getCurrentCards(DevelopmentCardTypes.CHARACTERCARD).isEmpty()){
			xPrintSysOut(" Character cards owned:");
			xPrintSysOut("|-NAME:--------------------|-ACTION VALUE:|-PAYMENT:-----------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
			printDevelopmentCardsSet(player.getCurrentCards(DevelopmentCardTypes.CHARACTERCARD));}
		if(!player.getCurrentCards(DevelopmentCardTypes.BUILDINGCARD).isEmpty()){
			xPrintSysOut(" Building cards owned:");
			xPrintSysOut("|-NAME:--------------------|-ACTION VALUE:|-PAYMENT:-----------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
			printDevelopmentCardsSet(player.getCurrentCards(DevelopmentCardTypes.BUILDINGCARD));}
		if(!player.getCurrentCards(DevelopmentCardTypes.VENTURECARD).isEmpty()){
			xPrintSysOut(" Venture cards owned:");
			xPrintSysOut("|-NAME:--------------------|-ACTION VALUE:|-PAYMENT:-----------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
			printDevelopmentCardsSet(player.getCurrentCards(DevelopmentCardTypes.VENTURECARD));}
		
		if(!player.getPermamentsEffect().isEmpty()){
			xPrintSysOut("\n");
			xPrintSysOut("Permanents effect owned:");
			for(String s: player.getPermamentsEffect())
				xPrintSysOut(s);
		}
	

	}

	/**
	 * Method that prints the final ranking with the final score
	 */
	@Override
	public void printFinalRankings(MainClientView view) {
		Map<String, PlayerView> players=view.getPlayers();
		List<PlayerView> playerViews  = new ArrayList<>(players.values());
		xPrintSysOut("\n");
		xPrintSysOut("FINAL SCORE: ");
		for(int i=0; i<playerViews.size();i++){
			xPrintSysOut("PLAYER " + "'"  + playerViews.get(i).getName()  + "'" + " : " +"|Victory Points :" + playerViews.get(i).getWallet().getVictoryPoints());
		}
		xPrintSysOut("\n");
	}
	}
	


