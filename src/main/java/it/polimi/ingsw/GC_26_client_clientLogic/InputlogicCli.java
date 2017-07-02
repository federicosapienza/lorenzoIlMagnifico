package it.polimi.ingsw.GC_26_client_clientLogic;

import java.util.Scanner;


import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_client_connection.ClientConnection;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;

public class InputlogicCli implements Runnable{
		private ClientConnection connection;
		private boolean waitingAction=false;
		private boolean waitingResponse = false;
		private boolean firstAction = true;  //if first action true , if second is false
		private MainClientView view; 
		private Output output;
		private Scanner scanIN= new Scanner(System.in);
	
		
		private int boardChoice=0; //senza lo zero la seconda volta va male
		private int position=0;
		private int servants=-2;
		private Colour familyMemberColour =null;
		private int familyMember=0;
		private BoardZone zone=null;
		private boolean zoneChosen=false;
		private boolean positionChosen=false;;
		private boolean familyMemberChosen=false;
		private String lastString; //it s called any time we want to repeat the last request
		private boolean close=false;
		
		
		public InputlogicCli(ClientConnection connection, MainClientView view, Output output) {
			this.connection=connection;
			this.view=view;
			this.output=output;

		}
		
		//main class
		@Override
		public void run() {
			String username;
			while(true){
			username = scanIN.nextLine();
			if(username!=null)
				break;
			}
			connection.login(username);
			view.setPlayerUsername(username);

			scanIN=new Scanner(System.in);

			while(true){
				while(!scanIN.hasNextInt()) { //used to be sure integer is an input
				    scanIN.next();
				    System.out.println("not valid input");
				}
				int value = scanIN.nextInt();
				/*if(scanIN==null)  // if user insert enter key twice 
					continue; */
				if(value==999){  //if player asks to end the turn
					String	temp="end turn" ;
					connection.sendResponce(temp);
					waitingResponse=false;
					waitingAction=false;
					continue;
				}
				else if(value<0){  //the player asks to reset action , if he was performing an action. useless while waiting response.
					restartValues();
					output.printString(lastString);
					continue;
				}	
				else if(this.getWaitingAction()){
					handleAction(value);
				}
				else if(this.getWaitingResponce()){
					String temp=String.valueOf(value);
					connection.sendResponce(temp);
					waitingResponse=false;
					continue;
				}
				else if(close){
					
					System.out.println("what happens");
					break;
				}
			}	
		}
	
		
		
		public void handleAction(int value){
		  //if waiting action
		if(firstAction && !familyMemberChosen && (value<5 && value>0)){ // family member is not chosen in second action
			familyMember=value;
			familyMemberColour= chooseColour(familyMember);
			output.printString("What Action? 1-terr 2-char 3-buil 4-ven 5-mark 6-prod -7 harv 8-councPalace ");
			familyMemberChosen=true;
			return;
		}
		if(familyMemberChosen && !zoneChosen && (value>0 && value<9)){
			boardChoice=value;
			zone=chooseBoardZone(boardChoice);
			output.printString("what position? '-1'-togoBack");
			zoneChosen=true;
			return;
		}
		if(zoneChosen && !positionChosen &&  (value>0 && value<5)){
			position=value;
			output.printString("How many servants? '-1'-togoBack");
			positionChosen=true;
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
		
		
		
		
		private synchronized boolean getWaitingAction(){
			return waitingAction;
		}
		
		
		private synchronized boolean getWaitingResponce(){
			return waitingResponse;
		}
		
		public synchronized void setWaitingFirstAction(){
			firstAction=true;
			waitingAction=true;
			restartValues();
			//the print of board and complete status at the beginning of the turn are done by clientController()
			printRequest("What family member do you choose? 1-orange 2-black 3-white 4-neutral");
			

		}
		public synchronized void setWaitingSecondAction(){
			output.printResources(view.getThisPlayer());
			output.printString("What Action? 1-terr 2-char 3-buil 4-ven 5-mark 6-prod -7 harv 8-councPalace ");
			restartValues();
			familyMemberChosen=true;
			firstAction=false;
			waitingAction=true;
			waitingResponse=false;
			
		}
	
		public synchronized void setWaitingResponse(){
			output.printResources(view.getThisPlayer());
			output.printString("waiting, 999 to close turn"); // do not use printRequest here (= this line would always be saved as last) 
			restartValues();
			waitingResponse=true;
		}
		
		public synchronized void close(){
			close=true;
			scanIN.close();
			connection.close();
		}
		
		
		public void setActionPerformed() {
			output.printLeaderCards(view.getThisPlayer().getLeadersCardOwned());
			printRequest("Choose a value between 1 and 4 to try activating the correspondent Leader Card");
			this.setWaitingResponse();
		}
		
		public synchronized void setTurnEnded(){
			waitingResponse=false;
			waitingAction=false;
		}
		
		public void setPlayerSuspended(){
			printRequest("You are now suspended : press any key to be able to play again");
			this.setWaitingResponse();
		}
		public void setWaitingVaticanChoice(CardDescriber card) {
			printRequest("Enter 0 to be excommunicated or 1 for not; excommunication:" +card.getPermanentEffectDescriber());
			this.setWaitingResponse();
			
		}


		public void setWaitingPaymentChoice() {
			printRequest("Enter 1 for first payment, 2 per second");
			this.setWaitingResponse();
		}


		public void setWaitingTrading(CardDescriber card) {
			printRequest("Enter 0 for not performing trade, 1 for perform first trade and  2 if there is a second trade"
					+ " and you choose that: "+card.getName()+" :"+ card.getPermanentEffectDescriber());	
			this.setWaitingResponse();

		}

		public void setWaitingCouncilPriviledge() {
			printRequest("Insert the correspondent number");
			this.setWaitingResponse();
		}

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
		
		private void printRequest(String string){  //so we keep track of last string sent
			lastString=string;
			output.printString(string);
		}
		



		
		
}	

