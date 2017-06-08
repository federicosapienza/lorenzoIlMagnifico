package it.polimi.ingsw.GC_26_cards.effects;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.payments.MilitaryPointPayment;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;

public class GainPointsPerAnyMilitaryPointEffect implements Effect{

	
		private final ResourcesOrPoints toHave;
		
		
		public GainPointsPerAnyMilitaryPointEffect(ResourcesOrPoints toHave) {
			this.toHave= toHave;
		}
		
		

		@Override
		public synchronized void doEffect(Player player, boolean immediate) {
			int temp =player.getWarehouse().getMilitaryPoints();
			ResourcesOrPoints.newResourcesOrPoints(toHave.getCoins()*temp,
					toHave.getServants()*temp, toHave.getWood()*temp, toHave.getStone()*temp,
					toHave.getVictoryPoints()*temp, toHave.getMilitaryPoints()*temp,toHave.getFaithPoints()*temp, 0);
			}
			
		

		@Override
		public String toString(){
			return " gives "+ toHave+ " for any Military Point"; 
		}
			

	}
