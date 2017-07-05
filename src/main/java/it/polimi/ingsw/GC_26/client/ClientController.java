package it.polimi.ingsw.GC_26.client;


import it.polimi.ingsw.GC_26.client.cli.InputLogic;
import it.polimi.ingsw.GC_26.client.cli.Output;
import it.polimi.ingsw.GC_26.client.main.ClientMain;
import it.polimi.ingsw.GC_26.client.view.MainClientView;
import it.polimi.ingsw.GC_26.client.view.PositionView;
import it.polimi.ingsw.GC_26.messages.Info;
import it.polimi.ingsw.GC_26.messages.Message;
import it.polimi.ingsw.GC_26.messages.PersonalBoardChangeNotification;
import it.polimi.ingsw.GC_26.messages.Request;
import it.polimi.ingsw.GC_26.messages.action.ActionNotification;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.messages.describers.FamilyMembersDescriber;
import it.polimi.ingsw.GC_26.messages.describers.PositionDescriber;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.PlayerWallet;
import it.polimi.ingsw.GC_26.model.game.gameLogic.GameStatus;
import it.polimi.ingsw.GC_26.model.player.PlayerStatus;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the controller for the client
 *
 */
public class ClientController {
	private MainClientView view;
	private InputLogic iOlogic;
	private Output output;
	private ClientMain main;

	/**
	 * Constructor: it creates the ClientController with the following parameters
	 * @param iOlogic It's the InputLogic to control
	 * @param view It's the MainClientView to control
	 * @param output It's the Output to control
	 * @param main It's the ClientMain to control
	 */
	public ClientController(InputLogic iOlogic, MainClientView view, Output output, ClientMain main) {
		this.iOlogic= iOlogic;
		this.view= view;
		this.output=output;
		this.main = main;
	}
	
	/**
	 * Method that checks if it's a phase of the game in which it's possible to distribute the cards to the Board or to players, and if the
	 * check is positive, it performs the correct distribution, checking the type of the card from the CardDescriber contained in the parameter
	 * @param card It's the CardDescriber that has to be checked to determine the correct distribution to perform
	 */
	public void receiveCard(CardDescriber card){
		if(view.getGameStatus()==GameStatus.INITIALIZINGGAME || view.getGameStatus()==GameStatus.INITIALIZINGROUND){
			if("Development Card".equalsIgnoreCase(card.getTypeOfCard())){
				view.getBoard().addCardWhereFree(card);
				return;
			}
			if(("Excommunication Tile").equalsIgnoreCase(card.getTypeOfCard())){
				view.getBoard().addExcommunication(card);
				return;
			}
			
		
			if(("Leader Card").equalsIgnoreCase(card.getTypeOfCard())){
				view.getPlayer(view.getPlayerUsername()).addLeaderCardOwned(card); //the card is sent only to "this" client
				return;
			}
			else throw new IllegalArgumentException();	
		}
		
	}
	
	/**
	 * Method called when receiving ActionNotification. It checks that this can be received only when the GameStatus is equal to 
	 * PLAYING and, depending on the ActionNotification contained in the parameter, it updates the Board and sets the Family
	 * Member used in the action as occupied and it prints the content of the action on the console 
	 * @param action It's the ActionNotification received 
	 */
	public void receiveAction(ActionNotification action){
		if(view.getGameStatus()==GameStatus.PLAYING){
			view.getBoard().update(action);
			if(action.getFamilyMemberColour()!=null) //means it is not second action
				view.getPlayer(action.getPlayerName()).getFamilyMembers().whatIsFree().remove(action.getFamilyMemberColour());
			output.printString(action.toString());
		}
			
	}
	
	/**
	 * Method called when receiving a PositionDescriber. It checks if the status of the game is equal to INITIALIZINGGAME (i.e. it's 
	 * the only phase of the game in which PositionDescriber can be received) and if the check is positive, it adds the PositionView
	 * that corresponds to the PositionDescriber contained in the parameter to the BoardView
	 * @param position It's the PositionDescriber that corresponds to the PositionView to add in the BoardView
	 */
	public void receivePosition(PositionDescriber position){
		if(view.getGameStatus()==GameStatus.INITIALIZINGGAME)
			view.getBoard().addPosition(new PositionView(position));
		
	}
	
	/**
	 * Method that updates the PlayerWallet of the MainClientView of this ClientController with 
	 * the values of the PlayerWallet contained in the parameter
	 * @param playerWallet It's the PlayerWallet received and whose values will be the new values of the current PlayerWallet
	 */
	public void receivePlayerPocket(PlayerWallet playerWallet){
		view.updatePlayerWallet((playerWallet));
	}
	
	/**
	 * Method called when receiving messages. It updates the game depending on the content of the Message contained in the 
	 * parameter
	 * @param message It's the message which contains the info that determines the update of the game progress
	 */
	public void receiveMessage(Message message){
		if(message instanceof Request ){
			Request request = (Request) message;
			handleRequests(request);
		}
		if(message instanceof Info ){
			Info info = (Info) message;
			handleInfo(info);
		}
		if(message instanceof PersonalBoardChangeNotification){
			PersonalBoardChangeNotification change= (PersonalBoardChangeNotification) message;
			handlePersonalBoardChangeNotification(change);
		}
	}

	/**
	 * Method called to give to the player the Family Members described by the FamilyMembersDescriber contained in the parameter
	 * @param familyMembersDescriber It's the FamilyMembersDescriber that describes the Family Members to give to the player
	 */
	public void receiveFamilyMembers(FamilyMembersDescriber familyMembersDescriber) {
		view.getPlayer(familyMembersDescriber.getPlayerName()).setFamilyMembers(familyMembersDescriber);
		
	}

	/**
	 * Method that sets the login as done successfully
	 */
	public void setLoginDone() {
		view.setLoginDone();
		
	}
	
	/**
	 * Method that handles the Request contained in the parameter 
	 * @param request It's the Request that has to be handled
	 */
	private void handleRequests(Request request){
		if(view.getGameStatus()==GameStatus.INITIALIZINGGAME){
			//in our protocol player is been informed about his real username: 
			//can be different from the chosen one if two client in the same game have chosen the same username;
			if(request.getMessage()!=null){
				view.setPlayerUsername(request.getMessage());
				output.printString(" Your username will be '" + request.getMessage()+"'");
			}
			return;
		}
		
		view.setPlayerStatus(request.getStatus());
		if(request.getMessage()!=null)
			output.printString(request.getMessage());
		if(request.getStatus()==PlayerStatus.PLAYING){
			if(request.getMessage()==null) {//in our protocol means starting new round(if there is a message, is a error message=request to repeat)
				output.printBoard(view.getBoard());
				output.printCompleteStatus(view.getThisPlayer());
			}
			iOlogic.setWaitingFirstAction();
			return;}
		if(request.getStatus()== PlayerStatus.SECONDPLAY){
				iOlogic.setWaitingSecondAction();
				return;
		}
		if(request.getStatus()==PlayerStatus.SUSPENDED){
			iOlogic.setPlayerSuspended();
			return;
		}
			
		if(request.getStatus()==PlayerStatus.WAITINGHISTURN){
			iOlogic.setTurnEnded();
			return;
		}
		
		if(request.getStatus()==PlayerStatus.ACTIONPERFORMED){
			iOlogic.setActionPerformed();
			return;}
		
		if(request.getStatus()==PlayerStatus.VATICANREPORTDECISION){
			iOlogic.setWaitingVaticanChoice(request.getCard());
			return;}
		if(request.getStatus()==PlayerStatus.CHOOSINGPAYMENT){
			iOlogic.setWaitingPaymentChoice();
			return;
		}
		if(request.getStatus()==PlayerStatus.TRADING){
			iOlogic.setWaitingTrading(request.getCard());
			return;
			}
		if(request.getStatus()==PlayerStatus.TRADINGCOUNCILPRIVILEGES){
			iOlogic.setWaitingCouncilPrivilege();
			return;
		}
	}
	
	/**
	 *  Method that handles the Info contained in the parameter 
	 * @param info It's the Info which has to be handled
	 */
	private void handleInfo(Info info) {
		if(info.getMessage()!=null){
			output.printString(info.getMessage());
		}
		GameStatus old=view.getGameStatus();
		view.setGameStatus(info.getGameStatus());

		if(old== GameStatus.INITIALIZINGGAME && info.getGameStatus()==GameStatus.INITIALIZINGROUND){
			output.printExcommunicationTiles(view.getBoard());
			output.printLeaderCards(view.getThisPlayer().getLeadersCardOwned());
		}
			
		if(old== GameStatus.INITIALIZINGROUND && info.getGameStatus()==GameStatus.PLAYING)  {
			output.printBoard(view.getBoard());
		}
		if(old== GameStatus.PLAYING && info.getGameStatus()==GameStatus.INITIALIZINGROUND)  {
			output.printRankings(view);
		}
			
		if(info.getGameStatus()==GameStatus.INITIALIZINGROUND){
			view.getBoard().cleanBoard();
		}
		
		if("ended the turn".contains(info.getMessage())) 
				output.printCompleteStatus(view.getPlayer(info.getPlayerReferred()));
		
		if(info.getGameStatus()==GameStatus.ENDING){
			output.printFinalRankings(view);
			main.closeGame();
		}
			
	}
	
	/**
	 *  Method that handles the PersonalBoardChangeNotification contained in the parameter 
	 * @param change It's the PersonalBoardChangeNotification to handle
	 */
	private void handlePersonalBoardChangeNotification(PersonalBoardChangeNotification change) {
		if(view.getGameStatus()==GameStatus.INITIALIZINGGAME && change.getBoardTileValues()!=null)
				view.getPlayer(change.getPlayerName()).setPersonalTileValues(change.getBoardTileValues());
		
		if(view.getGameStatus()==GameStatus.PLAYING && change.getBoardTileValues()==null && change.getCard()!=null)
				view.getPlayer(change.getPlayerName()).addCard(change.getCard());
		
				
	}


	

}