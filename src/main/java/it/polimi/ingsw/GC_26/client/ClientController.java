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

public class ClientController {
	private MainClientView view;
	private InputLogic iOlogic;
	private Output output;
	private ClientMain main;


	public ClientController(InputLogic iOlogic, MainClientView view, Output output, ClientMain main) {
		this.iOlogic= iOlogic;
		this.view= view;
		this.output=output;
		this.main = main;
	}
	

	public void receiveCard(CardDescriber card){
		if(view.getGameStatus()==GameStatus.INITIALIZINGGAME || view.getGameStatus()==GameStatus.INITIALIZINGROUND){
			if("Development Card".equalsIgnoreCase(card.getTypeOfCard())){
				view.getBoard().addCardWhereFree(card);

				return;}
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
	
	public void receiveAction(ActionNotification action){
		if(view.getGameStatus()==GameStatus.PLAYING){
			view.getBoard().update(action);
			if(action.getFamilyMemberColour()!=null) //means it is not second action
				view.getPlayer(action.getPlayerName()).getFamilyMembers().whatIsFree().remove(action.getFamilyMemberColour());
			output.printString(action.toString());
		}
			
	}
	
	public void receivePosition(PositionDescriber position){
		if(view.getGameStatus()==GameStatus.INITIALIZINGGAME)
			view.getBoard().addPosition(new PositionView(position));
		if(view.getGameStatus()==GameStatus.PLAYING || view.getGameStatus()==GameStatus.INITIALIZINGROUND){}
		
	}
	
	public void receivePlayerPocket(PlayerWallet playerWallet){
		view.updatePlayerWallet((playerWallet));
		}
	
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

	

	public void receiveFamilyMembers(FamilyMembersDescriber familyMembersDescriber) {
		view.getPlayer(familyMembersDescriber.getPlayerName()).setFamilyMembers(familyMembersDescriber);
		
	}

	



	public void setLoginDone() {
		view.setLoginDone();
		
	}
	
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
			iOlogic.setWaitingCouncilPriviledge();
			return;
		}
	}
	
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
			main.restart();
		}
			
	}
	
	
	private void handlePersonalBoardChangeNotification(PersonalBoardChangeNotification change) {
		if(view.getGameStatus()==GameStatus.INITIALIZINGGAME && change.getBoardTileValues()!=null)
				view.getPlayer(change.getPlayerName()).setPersonalTileValues(change.getBoardTileValues());
		
		if(view.getGameStatus()==GameStatus.PLAYING && change.getBoardTileValues()==null && change.getCard()!=null)
				view.getPlayer(change.getPlayerName()).addCard(change.getCard());
		
				
	}


	

}