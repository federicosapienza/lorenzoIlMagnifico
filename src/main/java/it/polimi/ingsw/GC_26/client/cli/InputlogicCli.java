package it.polimi.ingsw.GC_26.client.cli;

import java.util.Scanner;

import it.polimi.ingsw.GC_26.client.connection.ClientConnection;
import it.polimi.ingsw.GC_26.client.main.ClientMain;
import it.polimi.ingsw.GC_26.client.view.MainClientView;
import it.polimi.ingsw.GC_26.messages.action.Action;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.game.game_components.board.BoardZone;
import it.polimi.ingsw.GC_26.model.game.game_components.dices.Colour;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the logic that manages the input in the CLI
 *
 */
public class InputlogicCli implements InputLogic{
	private ClientConnection connection;
	private boolean waitingAction=false;
	private boolean waitingResponse = false;
	private boolean firstAction = true;  //if first action true , if second is false
	private MainClientView view; 
	private Output output;
	private Scanner scanIN; 

	
	private int boardChoice=0; //senza lo zero la seconda volta va male
	private int position=0;
	private int servants=-2;
	private Colour familyMemberColour =null;
	private int familyMember=0;
	private BoardZone zone=null;
	private boolean zoneChosen=false;
	private boolean positionChosen=false;
	private boolean familyMemberChosen=false;
	private String lastString; //it s called any time we want to repeat the last request
	private boolean close=false;
	private ClientMain main;
	private int exitingValue=0;
	private String username;
	
	/**
	 * Constructor: it creates the InputlogicCli with the attributes indicated in the parameters
	 * @param connection
	 * @param view
	 * @param output
	 */
	public InputlogicCli(String username, ClientMain main, Scanner scanner, ClientConnection connection, MainClientView view, Output output) {
		this.username=username;
		this.main= main;
		this.connection=connection;
		this.view=view;
		this.output=output;
		this.scanIN=scanner;

	}
	
	/**
	 * Method that runs the CLI logic in input
	 */
	@Override
	public void run() {
		while(username==null){
			output.printString("Enter a username");
			while(!scanIN.hasNextLine()) { //used to be sure integer is an input
			    scanIN.next();
			    output.printString("not valid input");
				}
		    username = scanIN.nextLine();
		}
		connection.login(username);
		view.setPlayerUsername(username);
		

		output.printString("Entering a game: please wait");

		while(true){
			while(!scanIN.hasNextInt()) { //used to be sure integer is an input
			    scanIN.next();
			    output.printString("not valid input");
			}
			int value = scanIN.nextInt();
			
			if(value==999){  //if player asks to end the turn
				String temp="end turn" ;
				connection.sendResponse(temp);
				waitingResponse=false;
				waitingAction=false;
			}
			else if(value<0){  //the player asks to reset action , if he was performing an action. useless while waiting response.
				restartValues();
				output.printString(lastString);
			}
			else if(this.getWaitingAction()){
				handleAction(value);
			}
			else if(this.getWaitingResponce()){
				String temp=String.valueOf(value);
				connection.sendResponse(temp);
				waitingResponse=false;
			}
			else if(close){
				if(value==1 || value ==0)
					exitingValue=value;
				else output.printString("not valid ");
				break;
			}		
			
		}
		if(exitingValue==1)
			main.start(false, username);
			
		if(exitingValue==0){
			//ends the client
			scanIN.close();
			output.printString("See you!");
		}
	}
	
	/**
	 * Method that handles the selected action checking the value contained in the parameter
	 * @param value It's the value of the action
	 */
	private void handleAction(int value){
		
		//if waiting action
		if(firstAction && !familyMemberChosen && (value>0 && value<=view.getThisPlayer().getFamilyMembers().size())){ // family member is not chosen in second action
			familyMember=value;
			familyMemberColour= chooseColour(familyMember);
			output.printString("What Action? Choose: 1-Territories Tower 2-Characters Tower 3-Buildings Tower 4-Ventures Tower" +System.lineSeparator()
									+" 5-market  6-production zone -7 Harvest 8-Council Palace ");
			familyMemberChosen=true;
			return;
		}
		if(familyMemberChosen && !zoneChosen && (value>0 && value<9)){
			boardChoice=value;
			zone=chooseBoardZone(boardChoice);
			zoneChosen=true;
			//new request
			askPosition(); //method created to reduce cognitive complexity
			return;
		}
		if(zoneChosen && !positionChosen &&value>=1 ){
			boolean temp= validatingPosition(value); //method created to reduce cognitive complexity
			if(temp)
				return;
		
		}
		if(positionChosen && value>=0){
			servants=value;
			Action action = new Action(zone, position, familyMemberColour, servants);
			connection.performAction(action);
			this.waitingAction=false;
			this.firstAction=false;
			restartValues();
			waitingAction=false;
			return;	
		}
		output.printString("Not Valid! Repeat!");
	}
	
	/**
	 * Method that asks the client to specify the position of the zone involved in his action
	 */
	private void askPosition(){
		String stringRepeated= " ('-1'-togoBack)";
		if(view.getBoard().getZone(zone).size()==1){
			positionChosen=true;
			position=1;
			output.printString("How many servants? ('-1'-to go Back)");	
			return;
		}
		//case thee are more than one position in the zone "zone"
		if(zone==BoardZone.TERRITORYTOWER|| zone==BoardZone.BUILDINGTOWER|| zone==BoardZone.CHARACTERTOWER || zone==BoardZone.VENTURETOWER)
			output.printString("What floor? choose a value between 1 and "+ view.getBoard().getBuildingsTower().size() + stringRepeated);
			//number of floors in building tower is the same of that in other towers
		String request= "what Position? Choose a value between 1 and ";
		if(zone==BoardZone.MARKET)
			output.printString(request+view.getBoard().getMarketZone().size()+stringRepeated );
		if(zone==BoardZone.PRODUCTION)
			output.printString(request+view.getBoard().getProductionZone().size()+stringRepeated );
		if(zone==BoardZone.HARVEST)
			output.printString(request+view.getBoard().getHarvestZone().size()+stringRepeated );
		if(zone== BoardZone.COUNCILPALACE && view.getBoard().getProductionZone().size()>1){  //in the hypothesis council palace positions will always be 1
			output.printString(request+view.getBoard().getHarvestZone().size()+stringRepeated );
	
		}
	}
	
	
	/**
	 * Method that checks if the specified position is valid
	 * @param value It's the value that indicates the position
	 * @return true if the specified position is valid; false if it isn't
	 */
	private boolean validatingPosition(int value ){
		boolean temp=false;
		if((zone==BoardZone.TERRITORYTOWER|| zone==BoardZone.BUILDINGTOWER)&& value <=view.getBoard().getBuildingsTower().size())
			temp=true; //number of floors in building tower is the same of that in other towers
		if((zone==BoardZone.CHARACTERTOWER|| zone==BoardZone.VENTURETOWER)&& value <=view.getBoard().getBuildingsTower().size())
			temp=true;	
		if(zone==BoardZone.MARKET&& value<=view.getBoard().getMarketZone().size())
			temp=true;
		if( zone==BoardZone.PRODUCTION && value<=view.getBoard().getProductionZone().size())
			temp=true;
		if( zone==BoardZone.HARVEST&& value<=view.getBoard().getHarvestZone().size())
			temp=true;
		//no choice for council palace: 1 position only
		if(temp){ //the action is valid
			position=value;
			output.printString("How many servants? ('-1'-to go Back)");
			positionChosen=true;
			}
		return temp;
	}
	
	/**
	 * Method that checks if the CLI is waiting for an action in input or not
	 * @return true if the CLI is waiting for an action in input; false if it isn't
	 */
	private synchronized boolean getWaitingAction(){
		return waitingAction;
	}
	
	/**
	 * Method that checks if the CLI is waiting for a response or not
	 * @return true if the CLI is waiting for a response; false if it isn't
	 */
	private synchronized boolean getWaitingResponce(){
		return waitingResponse;
	}
	
	/**
	 * Method called to specify that a first action has to be waited
	 */
	
	@Override
	public synchronized void setWaitingFirstAction(){
		firstAction=true;
		waitingAction=true;
		restartValues();
		//the print of board and complete status at the beginning of the turn are done by clientController()
		printRequest("What family member do you choose? 1-orange 2-black 3-white 4-neutral");
	}
	
	/**
	 * Method called to specify that a second action has to be waited
	 */
	@Override
	public synchronized void setWaitingSecondAction(){
		output.printResources(view.getThisPlayer());
		output.printString("What Action? 1-Territories Tower 2-Characters Tower 3-Buildings Tower 4-Ventures Tower" +System.lineSeparator()
		+" 5-market  6-production zone -7 Harvest 8-CouncPalace ");
		restartValues();
		familyMemberChosen=true;
		firstAction=false;
		waitingAction=true;
		waitingResponse=false;
		
	}

	/**
	 * Method called to specify that a response has to be waited
	 * @param askToEndTurn It's a boolean that, if true, specifies that the turn can be ended with a specific command
	 */
	private synchronized void setWaitingResponse(boolean askToEndTurn){
		if(askToEndTurn)
			output.printString("waiting, 999 to close turn"); // do not use printRequest here (= this line would always be saved as last) 
		restartValues();
		waitingResponse=true;
		firstAction=false;
		waitingAction=false;
	}
	
	
	
	/**
	 * Method that closes the connection
	 */
	@Override
	public synchronized void close(){
		setTurnEnded();
		printRequest("Do you want to start a new game? 1 to continue , 0 to exit");

		close=true;
		connection.close();
		
	}
	
	/**
	 * Method called to specify that an action has been performed and a response has to be waited 
	 */
	@Override
	public void setActionPerformed() {
		output.printResources(view.getThisPlayer());
		printRequest("Choose a value between 1 and 4 to try activating the correspondent Leader Card");
		output.printLeaderCards(view.getThisPlayer().getLeadersCardOwned());
		this.setWaitingResponse(true);
	}
	
	/**
	 * Method called to specify that the turn is ended and so it isn't necessary to wait for an action or a response
	 */
	@Override
	public synchronized void setTurnEnded(){
		waitingResponse=false;
		waitingAction=false;
	}
	
	/**
	 * Method that notifies that the player has been suspended 
	 */
	@Override
	public void setPlayerSuspended(){
		printRequest("You are suspended : insert any number to be able to play again");
		this.setWaitingResponse(false);
	}
	
	/**
	 * Method called to specify that the player has to choose if he wants to support the Church or not
	 */
	@Override
	public void setWaitingVaticanChoice(CardDescriber card) {
		output.printExcommunicationTiles(view.getBoard());
		output.printResources(view.getThisPlayer());
		printRequest("Enter 0 to be excommunicated or 1 for not; excommunication:" +card.getPermanentEffectDescriber());
		this.setWaitingResponse(false);
		
	}

	/**
	 * Method which notifies that the player has to choose the payment that he wants to pay, among the two possible payments
	 */
	@Override
	public void setWaitingPaymentChoice() {
		output.printResources(view.getThisPlayer());
		printRequest("Enter 1 for first payment, 2 per second");
		this.setWaitingResponse(false);
	}

	/**
	 * Method which notifies that the player has to choose if he wants to perform the trade or not
	 */
	@Override
	public void setWaitingTrading(CardDescriber card) {
		output.printResources(view.getThisPlayer());
		printRequest("Enter 0 not to perform any trade, 1 to perform the first trade and 2 if there is a second trade"
				+ " and you choose that: "+card.getName()+" :"+ card.getPermanentEffectDescriber());	
		this.setWaitingResponse(false);

	}

	/**
	 * Method that asks the player which Council Privilege he wants to obtain
	 */
	@Override
	public void setWaitingCouncilPrivilege() {
		printRequest("Insert the correspondent number");
		output.printResources(view.getThisPlayer());
		this.setWaitingResponse(false);
	}

	/**
	 * Method that resets the values to the default ones
	 */
	private void restartValues() {
		boardChoice=0;
		position=0;
		servants=-2;
		familyMemberColour =null;
		familyMember=0;
		zone=null;
		zoneChosen=false;
		positionChosen=false;
		if(firstAction)
			familyMemberChosen=false;
		else familyMemberChosen=true;
	}

	/**
	 * Method that returns the colour of the Family Member contained in the parameter
	 * @param familyMember It's the Family Member whose colour has to be returned
	 * @return the colour of the Family Member contained in the parameter
	 */
	private Colour chooseColour(int familyMember) {
		switch (familyMember) {
		case 1:
			return Colour.ORANGE;
		case 2:
			return Colour.BLACK;
		case 3:
			return Colour.WHITE;
		case 4:
			return Colour.NEUTRAL;
		default: throw new IllegalArgumentException();
		}
	}

	/**
	 * Method that returns the Board Zone that corresponds to the number contained in the parameter
	 * @param boardChoice It's the number that specifies the Board Zone to get
	 * @return the Board Zone that corresponds to the number contained in the parameter
	 */
	private BoardZone chooseBoardZone(int boardChoice) {
		switch (boardChoice) {
		case 1:
			return BoardZone.TERRITORYTOWER;
		case 2:
			return BoardZone.CHARACTERTOWER;
		case 3:
			return BoardZone.BUILDINGTOWER;
		case 4:
			return BoardZone.VENTURETOWER;
		case 5:
			return BoardZone.MARKET;
		case 6:
			return BoardZone.PRODUCTION;
		case 7:
			return BoardZone.HARVEST;
		case 8:
			return BoardZone.COUNCILPALACE;

		default: throw new IllegalArgumentException();
			
		}
	}
	
	/**
	 * Method that prints the string contained in the parameter
	 * @param string
	 */
	private void printRequest(String string){  //so we keep track of last string sent
		lastString=string;
		output.printString(string);
	}
		



		
		
}	

