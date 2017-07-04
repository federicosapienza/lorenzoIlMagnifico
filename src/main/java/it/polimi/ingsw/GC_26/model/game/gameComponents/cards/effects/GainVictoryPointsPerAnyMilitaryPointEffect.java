package it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects;

import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.Warehouse;
import it.polimi.ingsw.GC_26.model.player.Player;

public class GainVictoryPointsPerAnyMilitaryPointEffect implements Effect{

	
		private final ResourcesOrPoints toHave;
		
		
		public GainVictoryPointsPerAnyMilitaryPointEffect(ResourcesOrPoints toHave) {
			this.toHave= toHave;
		}
		
		

		@Override
		public synchronized void doEffect(Player player, boolean immediate) {
			Warehouse temp =player.getWarehouse();
			int test;
			if(toHave.getMilitaryPoints()==0)
				throw new IllegalArgumentException();
			test = temp.getMilitaryPoints()/toHave.getMilitaryPoints();
			temp.add(ResourcesOrPoints.newPoints(test, 0, 0, 0));
			}
			
		

		@Override
		public String toString(){
			return " Gives "+ toHave+ " for any Military Point"; 
		}
			

	}
