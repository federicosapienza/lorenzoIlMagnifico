package it.polimi.ingsw.GC_26.client.cli;

import java.util.Scanner;

import it.polimi.ingsw.GC_26.client.connection.ClientConnection;
import it.polimi.ingsw.GC_26.client.view.MainClientView;
import it.polimi.ingsw.GC_26.messages.action.Action;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.game.gameComponents.board.BoardZone;
import it.polimi.ingsw.GC_26.model.game.gameComponents.dices.Colour;

public class InputlogicCli implements InputLogic{
		private ClientConnection connection;
		private boolean waitingAction=false;
		private boolean waitingResponse = false;
		private boolean firstAction = true;  //if first action true , if second is false
		private MainClientView view; 
		private Output output;
		private  Scanner scanIN=new Scanner(System.in);
	
		
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
		
		
		public InputlogicCli( ClientConnection connection, MainClientView view, Output output) {
			//this.scanIN=scanner;
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

			while(true){
				while(!scanIN.hasNextInt()) { //used to be sure integer is an input
				    scanIN.next();
				    output.printString("not valid input");
				}
				int value = scanIN.nextInt();
				
				if(value==999){  //if player asks to end the turn
					String	temp="end turn" ;
					connection.sendResponce(temp);
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
					connection.sendResponce(temp);
					waitingResponse=false;
				}
				else if(close){
					break;
				}
			}	
		}
	
		
		
		private void handleAction(int value){
		  //if waiting action
		if(firstAction && !familyMemberChosen && (value<5 && value>0)){ // family member is not chosen in second action
			familyMember=value;
			familyMemberColour= chooseColour(familyMember);
			output.printString("What Action? 1-Territories Tower 2-Characters Tower 3-Buildings Tower 4-Ventures Tower" +System.lineSeparator()
									+" 5-market  6-production zone -7 Harvest 8-CouncPalace ");
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
		
		
		private void askPosition(){
			String stringRepeated= " ('-1'-togoBack)";
			if(zone==BoardZone.TERRITORYTOWER|| zone==BoardZone.BUILDINGTOWER|| zone==BoardZone.CHARACTERTOWER || zone==BoardZone.VENTURETOWER)
				output.printString("What floor? chose a value between 1 and "+ view.getBoard().getBuildingsTower().size() + stringRepeated);
				//number of floors in building tower is the same of that in other towers
			String request= "what Position? Choose a value between 1 and ";
			if(zone==BoardZone.MARKET)
				output.printString(request+view.getBoard().getMarketZone().size()+stringRepeated );
			if(zone==BoardZone.PRODUCTION)
				output.printString(request+view.getBoard().getProductionZone().size()+stringRepeated );
			if(zone==BoardZone.HARVEST)
				output.printString(request+view.getBoard().getHarvestZone().size()+stringRepeated );
			if(zone== BoardZone.COUNCILPALACE){  //in the hypothesis council palace positions will always be 1
				//no choice of position:
				positionChosen=true;
				position=1;
				output.printString("How many servants? ('-1'-to go Back)");				
			}
		}
		
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
		
		
		private synchronized boolean getWaitingAction(){
			return waitingAction;
		}
		
		
		private synchronized boolean getWaitingResponce(){
			return waitingResponse;
		}
		
		@Override
		public synchronized void setWaitingFirstAction(){
			firstAction=true;
			waitingAction=true;
			restartValues();
			//the print of board and complete status at the beginning of the turn are done by clientController()
			printRequest("What family member do you choose? 1-orange 2-black 3-white 4-neutral");
			

		}
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
		
		private synchronized void setWaitingResponse(boolean askToEndTurn){
			output.printResources(view.getThisPlayer());
			if(askToEndTurn)
				output.printString("waiting, 999 to close turn"); // do not use printRequest here (= this line would always be saved as last) 
			restartValues();
			waitingResponse=true;
			firstAction=false;
			waitingAction=false;
		}
		@Override
		public synchronized void close(){
			setTurnEnded();
			close=true;
			connection.close();
			
		}
		
		@Override
		public void setActionPerformed() {
			printRequest("Choose a value between 1 and 4 to try activating the correspondent Leader Card");
			output.printLeaderCards(view.getThisPlayer().getLeadersCardOwned());
			this.setWaitingResponse(true);
		}
		@Override
		public synchronized void setTurnEnded(){
			waitingResponse=false;
			waitingAction=false;
		}
		@Override
		public void setPlayerSuspended(){
			printRequest("You are now suspended : press any key to be able to play again");
			this.setWaitingResponse(false);
		}
		@Override
		public void setWaitingVaticanChoice(CardDescriber card) {
			printRequest("Enter 0 to be excommunicated or 1 for not; excommunication:" +card.getPermanentEffectDescriber());
			this.setWaitingResponse(false);
			
		}

		@Override
		public void setWaitingPaymentChoice() {
			printRequest("Enter 1 for first payment, 2 per second");
			this.setWaitingResponse(false);
		}

		@Override
		public void setWaitingTrading(CardDescriber card) {
			printRequest("Enter 0 for not performing trade, 1 for perform first trade and  2 if there is a second trade"
					+ " and you choose that: "+card.getName()+" :"+ card.getPermanentEffectDescriber());	
			this.setWaitingResponse(false);

		}
		@Override
		public void setWaitingCouncilPriviledge() {
			printRequest("Insert the correspondent number");
			this.setWaitingResponse(false);
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

