package it.polimi.ingsw.GC_26_client;

import it.polimi.ingsw.GC_26_board.PositionDescriber;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_client_clientLogic.InputlogicCli;
import it.polimi.ingsw.GC_26_client_clientLogic.MainClientView;
import it.polimi.ingsw.GC_26_client_clientLogic.Output;
import it.polimi.ingsw.GC_26_client_clientLogic.PositionView;
import it.polimi.ingsw.GC_26_client_connection.ClientMain;
import it.polimi.ingsw.GC_26_gameLogic.ActionNotification;
import it.polimi.ingsw.GC_26_gameLogic.GameStatus;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.Info;
import it.polimi.ingsw.GC_26_utilities.Message;
import it.polimi.ingsw.GC_26_utilities.PersonalBoardChangeNotification;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembersDescriber;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;

public class ClientController {
	private MainClientView view;
	private InputlogicCli iOlogic;
	private Output output;
	private ClientMain main;
	

	public ClientController(InputlogicCli iOlogic, MainClientView view, Output output, ClientMain main) {
		this.iOlogic= iOlogic;
		this.view= view;
		this.output=output;
		this.main = main;
	}
	

	public void receiveCard(CardDescriber card){
		if(view.getGameStatus()==GameStatus.INITIALIZINGGAME || view.getGameStatus()==GameStatus.INITIALIZINGTURN){
			if(card.getTypeOfCard().equalsIgnoreCase("Development Card")){
				view.getBoard().addCardWhereFree(card);
				return;}
			if(card.getTypeOfCard().equalsIgnoreCase("Excommunication Tile")){
				view.getBoard().addExcommunication(card);
				return;
			}
			
		
			if(card.getTypeOfCard().equalsIgnoreCase("Leader Card")){
					view.getPlayer(view.getPlayerUsername()).addLeaderCardOwned(card); //the card is sent only to "this" client
					return;
					}
			else throw new IllegalArgumentException();	
		}
		
	}
	
	public void receiveAction(ActionNotification action){
		if(view.getGameStatus()==GameStatus.INITIALIZINGGAME||view.getGameStatus()==GameStatus.INITIALIZINGTURN)
			throw new IllegalStateException();  //TODO come gestirla
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
		if(view.getGameStatus()==GameStatus.PLAYING || view.getGameStatus()==GameStatus.INITIALIZINGTURN){}
			//TODO lancia eccezione;
		
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
			iOlogic.setWaitingVaticanChoice();
			return;}
		if(request.getStatus()==PlayerStatus.CHOOSINGPAYMENT){
			iOlogic.setWaitingPaymentChoice();
			return;
		}
		if(request.getStatus()==PlayerStatus.TRADING){
			iOlogic.setWaitingTrading();
			return;
			}
		if(request.getStatus()==PlayerStatus.TRADINGCOUNCILPRIVILEDGES){
			iOlogic.setWaitingCouncilPriviledge();
			return;
		}
	}
	
	private void handleInfo(Info info) {
		if(info.getMessage()!=null){
			System.out.println(info.getMessage());
		}
		GameStatus old=view.getGameStatus();
		view.setGameStatus(info.getGameStatus());
		if(old== GameStatus.INITIALIZINGTURN && info.getGameStatus()==GameStatus.PLAYING)  //TODO
			output.printBoard(view.getBoard());
		
		if(info.getGameStatus()==GameStatus.INITIALIZINGTURN)
			view.getBoard().cleanBoard();
		if(info.getMessage().contains("ended the turn")) //TODO cambiare se cambia in EndTurnController
				output.printCompleteStatus(view.getPlayer(info.getPlayerReferred()));
		
		if(info.getGameStatus()==GameStatus.ENDING)
			main.restart();
			
	}
	
	
	private void handlePersonalBoardChangeNotification(PersonalBoardChangeNotification change) {
		if(view.getGameStatus()==GameStatus.INITIALIZINGGAME){
			if(change.getBoardTileValues()!=null)
				view.getPlayer(change.getPlayerName()).setPersonalTileValues(change.getBoardTileValues());
		}
		if(view.getGameStatus()==GameStatus.PLAYING){
			if(change.getBoardTileValues()==null && change.getCard()!=null)
				view.getPlayer(change.getPlayerName()).addCard(change.getCard());
		}
				
	}


	

}