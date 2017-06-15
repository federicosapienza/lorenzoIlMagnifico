package it.polimi.ingsw.GC_26_client_clientLogic;

import java.util.Scanner;


import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_client_connection.ClientConnection;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;

public class InputlogicCli implements Runnable{
		private ClientConnection connection;
		private boolean waitingPlayer;  //useless
		private boolean waitingAction=false;
		private boolean waitingResponse = false;
		private boolean firstAction = true;  //if first action true , if second is false
		private MainClientView view; 
		private Output output;
	
		private Scanner scanIN = new Scanner(System.in);
		
		public InputlogicCli(ClientConnection connection, MainClientView view, Output output) {
			this.connection=connection;
			this.view=view;
			this.output=output;

		}
		

		@Override
		public void run() {
			String username;


				username = scanIN.nextLine();
				String password = scanIN.nextLine();
				connection.login(username, password);
				view.setPlayerUsername(username);

			while(true){
				if(view.isLoginDone())
					break;
				}
			while(true){
				if(this.getWaitingAction()){
					System.out.println("io46");
					output.printFamilyMembers(view.getThisPlayer());
					output.printResources(view.getThisPlayer());
					output.printString("What Action?");
					int boardChoice=0; //senza lo zero la seconda volta va male
					int position=0;
					int servants=-2;
					Colour familyMemberColour = null;
					int familyMember=0;
					if(this.firstAction){
						while(this.waitingAction && (familyMember<1 || familyMember>4)){
							output.printString("what family member? 1-orange 2-black 3-white 4-neutral");
							familyMember = scanIN.nextInt();
						}
						familyMemberColour=chooseColour(familyMember);	
					}
					while(this.waitingAction && (boardChoice<1 || boardChoice>8)){
						output.printString("What Action? 1-terr 2-char 3-buil 4-ven 5-mark 6-prod -7 harv 8-councPalace ");
						boardChoice = scanIN.nextInt();
					}
					//TODO ovviamente aggiusteremo con limitazioni che varieranno a seconda di pos e quindi di partita,
					//TODO possiamo fare cosa carina che intanto gli lanciamo view  della zona di interesse
	
					while(this.waitingAction && (position<1 || position>8)){ //!waitingAction In case timeout occurs
						output.printString("what position? '-1'-togoBack");
						position = scanIN.nextInt();
					}
					
					while(this.waitingAction && servants<-1 ){
						output.printString("how many servants?; '-1' to go Back");
						servants = scanIN.nextInt();
					}
					//mettere un sei sure?
					if(servants!=-1){
						BoardZone boardZone=chooseBoardZone(boardChoice);
						Action action = new Action(boardZone, position, familyMemberColour, servants);
						connection.performAction(action);
						this.waitingAction=false;
						this.firstAction=false;
					}


					
					
				}
				
				if(this.getWaitingResponce()){
					System.out.println("waiting, 999 to close turn");
					int responce = scanIN.nextInt();
					String temp;
					if(responce==999){
						temp =	"end turn";
					}
					else {
						temp=String.valueOf(responce);
					}
					connection.sendResponce(temp);
					waitingResponse=false;
				}
			}
	
		}
		
		
		
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
		
		private synchronized boolean getWaitingAction(){
			return waitingAction;
		}
		
		
		private synchronized boolean getWaitingResponce(){
			return waitingResponse;
		}
		
		public synchronized void setWaitingPlayer(){
			waitingPlayer = true;
		}
		
		private synchronized void setNotWaitingPlayer(){
			waitingPlayer = false;
		}
		
		public synchronized void setWaitingFirstAction(){
			firstAction=true;
			waitingAction=true;
			System.out.println("io logic 169 waiting called");

		}
		public synchronized void setWaitingSecondAction(){
			firstAction=false;
			waitingAction=true;
		}
		
		
		
		public synchronized void setWaitingResponse(){
			waitingResponse=true;
		}
		
		public synchronized void askForUsernameAgain(){
			
		}

		public void setActionPerformed() {
			output.printCards(view.getThisPlayer().getLeadersCardOwned());
			output.printString("choose a value between 1 and 4 to try activating the correspondent Leader Card");
			this.setWaitingResponse();
		}

}