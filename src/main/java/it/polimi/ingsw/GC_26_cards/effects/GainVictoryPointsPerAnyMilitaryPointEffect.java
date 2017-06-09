package it.polimi.ingsw.GC_26_cards.effects;

//TODO cambiare nome
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;

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
			return " gives "+ toHave+ " for any Military Point"; 
		}
			

	}
