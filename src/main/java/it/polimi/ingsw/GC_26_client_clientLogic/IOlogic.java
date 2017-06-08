package it.polimi.ingsw.GC_26_client_clientLogic;

import java.net.Socket;
import java.sql.Connection;
import java.util.Scanner;

import javax.security.auth.login.LoginContext;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_client_connection.ClientConnection;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;

public class IOlogic  implements Runnable{
		private boolean loginDone=false;;
		private ClientConnection connection;
		private boolean waitingPlayer;  //useless
		private boolean waitingAction=false;
		private boolean waitingResponse = false;
		private boolean firstAction =true;  //if first action true , if second is false
	
		private Scanner socketIn;
		
		public IOlogic(ClientConnection connection) {
			this.connection=connection;

		}
		

		@Override
		public void run() { 
			while (!loginDone) {
				// reads a new Line from the Scanner
				String username = socketIn.nextLine();
				String password = socketIn.nextLine();
				connection.login(username, password);
				}
			while(true){
				if(waitingPlayer){
					System.out.println("What Action?");
					int boardChoice=0; //senza lo zero la seconda volta va male
					int position=0;
					int servants=-1;
					boolean goBack=false;
					Colour familyMemberColour =null;
					int familyMember=0;
					if(firstAction){
								while(waitingAction && (position<1 || position>4)){
								System.out.println("what family member? 1-orange 2-black 3-white 4-neutral");
								position = socketIn.nextInt();}	
								familyMemberColour=chooseColour(familyMember);
						}
					while(waitingAction && (boardChoice<1 || boardChoice>8)){
						System.out.println("What Action? 1-terr 2-buil 3-char 4-ven 5-mark 6-prod -7 harv 8-councPalace ");
						boardChoice = socketIn.nextInt();}
					//TODO ovviamente aggiusteremo con limitazioni che varieranno a seconda di pos e quindi di partita,
					//TODO possiamo fare cosa carina che intanto gli lanciamo view  della zona di interesse
	
					while(waitingAction && (position<1 || position>4)&& position!= -1){ //!waitingAction In case timeout occurs
							System.out.println("what position? '-1'-togoBack");
							position = socketIn.nextInt();
							if(position==5)
								goBack=true;
							}
					while(!waitingAction && servants>-2){
						System.out.println("how many servants? '-1'togoBack");
						servants = socketIn.nextInt();
					}
					if(position!= -1 &&servants!=-1){
						BoardZone boardZone=chooseBoardZone(boardChoice);
						Action action = new Action(boardZone, position, familyMemberColour, servants);
						connection.performAction(action);
						waitingAction=false;
					}


					
					
				}
				
				if(waitingResponse){
					System.out.println("waiting");
					int responce = socketIn.nextInt();
					connection.sendResponce(responce);
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
				return BoardZone.BUILDINGTOWER;
			case 3:
				return BoardZone.CHARACTERTOWER;
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


		public synchronized void setWaitingPlayer(){
			waitingPlayer= true;
		}
		
		private synchronized void setNotWaitingPlayer(){
			waitingPlayer=true;
		}
		
		public synchronized void setWaitingFirstAction(){
			waitingAction=true;
			firstAction=true;
		}
		public synchronized void setWaitingSecondAction(){
			waitingAction=true;
			firstAction=false;
		}
		
		
		
		public synchronized void setWaitingResponse(){
			waitingResponse=true;
		}
		
	
		
		

}
