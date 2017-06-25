package it.polimi.ingsw.GC_26_cards.developmentCards;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.effects.Effect;

import it.polimi.ingsw.GC_26_cards.payments.Payment;
import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.Request;


/**
 *
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * This class represents the implementation of the DevelopmentCard interface.
 *
 */
//It 's the implementation used by Character and Venture Cards. Territories and building cards extend this.
public class DevelopmentCardImplementation implements DevelopmentCard{
    /**
     *
     */
    /**
     *
     */
    private final String name;
    private final int period;
    private final DevelopmentCardTypes type;
    private final Payment payment;
    private final Effect immediateEffect;
    private final Effect permanentEffect;
    private final int actionValue;

    /**
     * Private constructor: this is never called directly by other methods, the following static methods will be called to create the correct development card
     * @param name the name of the card
     * @param period the period of the card
     * @param type the type of the card
     * @param payment the payment required by the card
     * @param immediate the immediate effect of the card
     * @param permanent the permanent effect of the card
     * @param actionValue the value of the action required by the card
     */
    private DevelopmentCardImplementation(String name, int period, DevelopmentCardTypes type, Payment payment, Effect immediate, Effect permanent, int actionValue) {
        this.name=name;
        this.period= period;
        this.type= type;
        if(!(period>=1 && period <=3))
            throw new IllegalArgumentException();
        this.payment= payment;
        this.immediateEffect= immediate;
        this.permanentEffect= permanent;
        this.actionValue= actionValue;
    }

    /**
     * Method that creates a new Territory card with the characteristics contained in the parameters.
     * @param name the name of the card
     * @param period the period of the card
     * @param payment the payment required by the card
     * @param immediate the immediate effect of the card
     * @param permanent the permanent effect of the card
     * @param actionValue the value of the action required by the card
     * @return a new Territory card with the characteristics contained in the parameters
     */
    public static DevelopmentCard territoryCard(String name, int period, Payment payment, Effect immediate, Effect permanent, int actionValue){
        return new DevelopmentCardImplementation(name, period, DevelopmentCardTypes.TERRITORYCARD, payment, immediate, permanent, actionValue);
    }

    /**
     * Method that creates a new Building card with the characteristics contained in the parameters.
     * @param name the name of the card
     * @param period the period of the card
     * @param payment the payment required by the card
     * @param immediate the immediate effect of the card
     * @param permanent the permanent effect of the card
     * @param actionValue the value of the action required by the card
     * @return a new Building card with the characteristics contained in the parameters
     */
    public static DevelopmentCard buildingCard(String name, int period, Payment payment, Effect immediate, Effect permanent, int actionValue){
        return new DevelopmentCardImplementation(name, period, DevelopmentCardTypes.BUILDINGCARD, payment, immediate, permanent, actionValue);
    }

    /**
     * Method that creates a new Character card with the characteristics contained in the parameters.
     * @param name the name of the card
     * @param period the period of the card
     * @param payment the payment required by the card
     * @param immediate the immediate effect of the card
     * @param permanent the permanent effect of the card
     * @param actionValue the value of the action required by the card
     * @return a new Character card with the characteristics contained in the parameters
     */
    public static DevelopmentCard characterCard(String name, int period, Payment payment, Effect immediate, Effect permanent){
        return new DevelopmentCardImplementation(name, period, DevelopmentCardTypes.CHARACTERCARD, payment, immediate, permanent, 0);
    }

    /**
     * Method that creates a new Venture card with the characteristics contained in the parameters.
     * @param name the name of the card
     * @param period the period of the card
     * @param payment the payment required by the card
     * @param immediate the immediate effect of the card
     * @param permanent the permanent effect of the card
     * @param actionValue the value of the action required by the card
     * @return a new Venture card with the characteristics contained in the parameters
     */
    public static DevelopmentCard ventureCard(String name, int period, Payment payment, Effect immediate, Effect permanent){
        return new DevelopmentCardImplementation(name, period, DevelopmentCardTypes.VENTURECARD, payment, immediate, permanent, 0);
    }

    /**
     * Method that returns the name of the card
     * @return the name of the card
     */
    public String getName() {
        return name;
    }

    /**
     * Method that returns the number of period of the card
     * @return the number of period of the card
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Method that returns the type of the card
     * @return the type of the card
     */
    public DevelopmentCardTypes getType() {
        return type;
    }

    /**
     * Method that returns the payment required by the card
     * @return the payment required by the card
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Method that returns the immediate effect of the card
     * @return the immediate effect of the card
     */
    public Effect getImmediateEffect() {
        return immediateEffect;
    }

    /**
     * Method that returns the permanent effect of the card
     * @return the permanent effect of the card
     */
    public Effect getPermanentEffect() {
        return permanentEffect;
    }

    /**
     * Method that returns the value of the action required by the card
     * @return the value of the action required by the card
     */
    @Override
    public int getActionValue() {
        return actionValue;
    }

    /**
     * Method that checks if the player who wants to get the card, can get it.
     */
    @Override
    public boolean canPlayerGetThis(Player player) {
        // if the card is a territory card we first of all need to check if player has enough military points
        //also checks Cesare Borgia's like Effect is not active
        if(type== DevelopmentCardTypes.TERRITORYCARD && !player.getPermanentModifiers().isMilitaryPointRequirementNotNeeded()){
            int territoryCardsOwned = player.getPersonalBoard().getNumberOfCardPerType(DevelopmentCardTypes.TERRITORYCARD);
            if(player.getWarehouse().getMilitaryPoints() < GameParameters.getTerritoryCardRequirements(territoryCardsOwned+1)){//+1 because I want to increase my number of cards
                player.notifyObservers(new Request(player.getStatus(),"not enough resources military "
                        + "Points for getting another military card",new CardDescriber(player.getCardUsed())))	;
                return false;
            }
        }
        if(payment==null){
            return true;
        }
        if(!payment.canPlayerGetThis(player, type)){
            return false;
        }
        else return true;
    }

    /**
     * Method used by the player to pay the payment required by the card
     */
    @Override
    public void pay(Player player) {
        if(payment !=null)
            payment.pay(player, type);

    }

    /**
     * Method used to run the immediate effect of the card on the player
     */
    @Override
    public void runImmediateEffect(Player player) {
        if(immediateEffect!= null)
            immediateEffect.doEffect(player, true);

    }

    /**
     * Method used to run the permanent effect of the card on the player
     */
    @Override
    public void runPermanentEffect(Player player) {
        if(permanentEffect!= null)
            permanentEffect.doEffect(player, false);

    }

    /**
     * Method that returns the immediate effect describer of the card
     * @return the immediate effect describer of the card
     */

    public String getImmediateEffectDescriber() {
        if(immediateEffect!=null){
            return immediateEffect.toString();
        }
        else return null;
    }

    /**
     * Method that returns the permanent effect describer of the card
     * @return the permanent effect describer of the card
     */
    public String getPermanentEffectDescriber() {
        if(permanentEffect!=null){
            return permanentEffect.toString();
        }
        else return null;
    }

    /**
     * Method that returns the payment describer of the card
     * @return the payment describer of the card
     */

    public String getPaymentDescriber(){
        if(payment!=null){
            return payment.toString();
        }
        else return null;
    }

}