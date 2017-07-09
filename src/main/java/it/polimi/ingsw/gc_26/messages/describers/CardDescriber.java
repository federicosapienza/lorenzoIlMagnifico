package it.polimi.ingsw.gc_26.messages.describers;

import java.io.Serializable;

import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.excommunicationTile.ExcommunicationTileImplementation;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.leaderCard.LeaderCard;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.leaderCard.LeaderCardImplementation;


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

    /**
     * Constructor that creates a card describer which describes a development card
     * @param card is the development card described by the card describer
     */
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

    /**
     * Constructor that creates a card describer which describes an Leader card.
     * @param card is the Leader card described by the card describer
     */
    public CardDescriber(LeaderCard card){
        LeaderCardImplementation cardImplementation = (LeaderCardImplementation) card;
        this.typeOfCard="Leader Card";
        this.name= cardImplementation.getName();
        this.type=null;
        this.period=0;
        this.payment=null;
        this.immediateEffect=cardImplementation.getImmediateEffectDescriber();
        this.permanentEffect=cardImplementation.getPermanentEffectDescriber();
        this.requirement=cardImplementation.getRequirementDescriber();
        this.actionValue=0;
    }

    /**
     * Constructor that creates a card describer which describes an excommunication tile.
     * @param card is the excommunication tile described by the card describer
     */
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

    /**
     * Method that returns the type of the card described by the card describer
     * @return the type of the card
     */
    public  String getTypeOfCard(){
        return typeOfCard;
    }

    /**
     * Getter method that returns the name of the card
     * @return the name of the card
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method that returns the value of the action, required by the card
     * @return the value of the action required by the card
     */
    public int getActionValue(){
        return actionValue;
    }

    /**
     * Getter method that returns the type of development card described by the card describer
     * @return the type of development card described by the card describer
     */
    public DevelopmentCardTypes getType() {
        return type;
    }

    /**
     * Getter method that returns the period of the card described by the card describer
     * @return the period of the card described by the card describer
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Getter method that returns the immediate effect describer of the card described by the card describer
     * @return the immediate effect describer of the card described by the card describer
     */
    public String getImmediateEffectDescriber() {
        return immediateEffect;
    }

    /**
     * Getter method that returns the permanent effect describer of the card described by the card describer
     * @return the permanent effect describer of the card described by the card describer
     */
    public String getPermanentEffectDescriber(){
        return permanentEffect;
    }

    /**
     * Getter method that returns the resources or points requirement describer of the card described by the card describer
     * @return the resources or points requirement describer of the card described by the card describer
     */
    public  String getRequirementDescriber(){
        return requirement;
    }
    
    public String getPaymentDescriber(){
    	return payment;
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