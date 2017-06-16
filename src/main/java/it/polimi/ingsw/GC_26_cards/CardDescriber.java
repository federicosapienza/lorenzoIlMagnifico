package it.polimi.ingsw.GC_26_cards;

import java.io.Serializable;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.GC_26_cards.excommunicationTile.ExcommunicationTileImplementation;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCardImplementation;


/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * It's the class that describes the card object sent to remote connections.
 */
public class CardDescriber implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * 
	 * 
	 */
	private final String typeOfCard;
	private final String name;
	private final int period;
	private final DevelopmentCardTypes type;
	private final String payment;
	private final String immediateEffect;
	private final String permanentEffect;
	private final int actionValue;
	private final String requirement;
	
	
	public CardDescriber(DevelopmentCard card){ //recasting is needed here
		DevelopmentCardImplementation cardImplementation = (DevelopmentCardImplementation) card;
		this.typeOfCard="Development Card";
		this.name= cardImplementation.getName();
		this.type=cardImplementation.getType();
		this.period=cardImplementation.getPeriod();
		this.payment=cardImplementation.getPaymentDescriber();
		this.immediateEffect=cardImplementation.getImmediateEffectDescriber();
		this.permanentEffect=cardImplementation.getPermanentEffectDescriber();
		this.requirement=null;
		this.actionValue=cardImplementation.getActionValue();
	}
	public CardDescriber(LeaderCard card){
		LeaderCardImplementation cardImplementation = (LeaderCardImplementation) card;
		this.typeOfCard="Leader Card";
		this.name= cardImplementation.getName();
		this.type=null;
		this.period=0;
		this.payment=null;
		this.immediateEffect=cardImplementation.getPermanentEffectDescriber();
		this.permanentEffect=cardImplementation.getPermanentEffectDescriber();
		this.requirement=cardImplementation.getRequirementDescriber();
		this.actionValue=0;
	}
	public CardDescriber(ExcommunicationTile card){
		ExcommunicationTileImplementation cardImplementation = (ExcommunicationTileImplementation) card;
		this.typeOfCard="Excommunication Tile";
		this.name= null;
		this.type=null;
		this.period=cardImplementation.getPeriod();
		this.payment=null;
		this.immediateEffect=null;
		this.permanentEffect=cardImplementation.getPermanentEffectDescriber();
		this.requirement=null;
		this.actionValue=0;
	}
	
	
	

	// getter method to get the type of the card as a string
		public  String getTypeOfCard(){
			return typeOfCard;
		}
		
		//getter method to get the name of the card
		public String getName() {
			return name;
		};
		
		//getter method to get the value of the action
		public  int getActionValue(){
			return actionValue;
		}
		
		//getter method to get the type of the card as type of development card
		public DevelopmentCardTypes getType() {
			return type;
		}
		
		//getter method to get the period of the card
		public int getPeriod() {
			return period;
		}
		
		//getter method to get the immediate effect describer of the card as a string
		public String getImmediateEffectDescriber() {
			return immediateEffect.toString();
		}
		
		//getter method to get the permanent effect describer of the card as a string
		public  String getPermanentEffectDescriber(){
			return permanentEffect;
		}
		
		//getter method to get the requirement describer of the card as a string
		public  String getRequirementDescriber(){
			return requirement;
		}

		@Override
		public String toString() {
			StringBuilder temp= new StringBuilder(" ");
			if(name!=null)
				temp.append(name );
			if(payment!=null)
					temp.append(" PAY: " +payment);
			if(requirement!=null)
					temp.append(" REQ "+ requirement);
			
			if(immediateEffect!=null)
					temp.append(" IMMEDIATE EFF. :" + immediateEffect);
			if(permanentEffect!=null)
				temp.append(" PERMAMENT EFF :" + permanentEffect);
			if(actionValue !=0)
				temp.append(" ACTION VALUE "+ actionValue);
			temp.append(".");
			return temp.toString();
		
		}
		
}
