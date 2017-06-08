package it.polimi.ingsw.GC_26_cards.effects;

//TODO cambiare nome
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
			Warehouse temp =player.getWarehouse();
			int test = toHave.getCoins()*temp.getCoins()+
					toHave.getServants()+temp.getServants()+ toHave.getWood()*temp.getWood()+ toHave.getStone()*temp.getStone()+
					toHave.getVictoryPoints()*temp.getVictoryPoints()+toHave.getMilitaryPoints()*temp.getMilitaryPoints()+
					toHave.getFaithPoints()*temp.getFaithPoints();
			temp.add(ResourcesOrPoints.newPoints(test, 0, 0, 0));
			}
			
		

		@Override
		public String toString(){
			return " gives "+ toHave+ " for any Military Point"; 
		}
			

	}
