package it.polimi.ingsw.GC_26_client_clientLogic;

import java.util.Scanner;


import it.polimi.ingsw.GC_26_board.BoardZone;
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
				
				
			scanIN=new Scanner(System.in);

			while(true){
				int value = scanIN.nextInt();
				if(value==999){  //if player asks to end the turn
					String	temp="end turn" ;
					connection.sendResponce(temp);
					waitingResponse=false;
					waitingAction=false;
					continue;
				}
				if(value<0){  //the player asks to reset action , if he was performing an action. useless while waiting response.
					restartValues();
					if(firstAction)
						output.printString("What family member do you choose? 1-orange 2-black 3-white 4-neutral");
					else output.printString("choose a value between 1 and 4 to try activating the correspondent Leader Card");

					continue;
				}	
				if(this.getWaitingAction()){  //if waiting action
					if(firstAction && !familyMemberChosen && (value<5 && value>0)){ // family member is not chosen in second action
						familyMember=value;
						familyMemberColour= chooseColour(familyMember);
						output.printString("What Action? 1-terr 2-char 3-buil 4-ven 5-mark 6-prod -7 harv 8-councPalace ");
						familyMemberChosen=true;
						continue;
					}
					if(!zoneChosen && (value>0 && value<9)){
						boardChoice=value;
						zone=chooseBoardZone(boardChoice);
						output.printString("what position? '-1'-togoBack");
						zoneChosen=true;
						continue;
					}
					if(!positionChosen &&  (value>0 && value<5)){
						position=value;
						output.printString("How many servants? '-1'-togoBack");
						positionChosen=true;
						continue;
					}
					if(value>=0){
						servants=value;
						Action action = new Action(zone, position, familyMemberColour, servants);
						connection.performAction(action);
						this.waitingAction=false;
						this.firstAction=false;
						restartValues();
						waitingAction=false;
						continue;	
					}
					output.printString("Not Valid! Repeat!");
				}
				if(this.getWaitingResponce()){
					String temp=String.valueOf(value);
					connection.sendResponce(temp);
					waitingResponse=false;
					continue;
				}
				System.out.println("iologic107");
			}
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
			output.printFamilyMembers(view.getThisPlayer());
			output.printResources(view.getThisPlayer());
			output.printString("What family member do you choose? 1-orange 2-black 3-white 4-neutral");
			

		}
		public synchronized void setWaitingSecondAction(){
			output.printString("What Action? 1-terr 2-char 3-buil 4-ven 5-mark 6-prod -7 harv 8-councPalace ");
			restartValues();
			firstAction=false;
			waitingAction=true;
			
		}
	
		public synchronized void setWaitingResponse(){
			System.out.println("waiting, 999 to close turn");
			restartValues();
			waitingResponse=true;
		}
		
		public void setActionPerformed() {
			output.printCards(view.getThisPlayer().getLeadersCardOwned());
			output.printString("choose a value between 1 and 4 to try activating the correspondent Leader Card");
			this.setWaitingResponse();
		}
		
		private void restartValues() {
			boardChoice=0; //senza lo zero la seconda volta va male
			position=0;
			servants=-2;
			familyMemberColour =null;
			familyMember=0;
			zone=null;
			zoneChosen=false;
			positionChosen=false;;
			familyMemberChosen=false;
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
		
		
	

}