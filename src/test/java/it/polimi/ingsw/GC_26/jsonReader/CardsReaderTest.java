package it.polimi.ingsw.GC_26.jsonReader;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.GC_26.json_reader.CardsReader;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.ActionValueModifierEffect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.CardsNumberToResourcesEffect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.ChangeFamilyMembersValue;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.DeletingBonusFloorsEffect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.DisableMilitaryPointsRequirement;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.DisableTowerOccupiedMalus;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.DoubleImmediateResourcesFromCards;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.DoubleServantsNeededEffect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.Effect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.FamilyMembersValueSetterEffect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.GainVictoryPointsPerAnyMilitaryPointEffect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.GoingToOccupiedActionSpacesAllowedEffect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.LoseVictoryPointForResourcesOrPointsEffect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.LoseVictoryPointsforEachNVictoryPointsEffect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.MarketBanEffect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.NoVictoryPointForCardTypeEffect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.PermanentResourcesMalusEffect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.ReceiveDiscountOnActionsEffect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.SetSecondAction;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.TradeEffect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.TwoAndEffect;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.effects.VaticanSupportBonus;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.leaderCard.CardNumbersOrRequirement;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.leaderCard.CardNumbersRequirement;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.leaderCard.PointsOrResourcesRequirement;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.leaderCard.Requirement;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.leaderCard.TwoAndRequirements;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.payments.MilitaryPointPayment;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.payments.ResourcesPayment;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.payments.TwoOrPayments;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.payments.common.Payment;

public class CardsReaderTest {

	CardsReader cardsReader = new CardsReader();
	
	@Test
	public void createJsonObjectFromFileBufferedReaderTest(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Territory_cards/Period_1/City.json");
		assertNotNull(cardsReader.getBufferedReader());
	}
	
	@Test
	public void createJsonObjectFromFileJsonObjectTest(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Territory_cards/Period_1/City.json");
		assertNotNull(cardsReader.getJsonObject());
	}
	
	
	@Test
	public void readStringTestTrue(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Territory_cards/Period_1/City.json");
		assertEquals("City", cardsReader.readString("name"));
	}
	
	@Test
	public void readStringTestfalse(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Territory_cards/Period_1/City.json");
		assertNotEquals("LOL", cardsReader.readString("name"));
	}
	
	@Test
	public void readIntTestTrue(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Territory_cards/Period_1/City.json");
		assertEquals(1, cardsReader.readInt("period"));
	}
	
	@Test
	public void readIntTestFalse(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Territory_cards/Period_1/City.json");
		assertNotEquals(2, cardsReader.readInt("period"));
	}
	
	@Test 
	public void readIntListTrue(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Territory_cards/Period_1/City.json");
		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		assertEquals(list, cardsReader.readIntList("immediateResourcesAndPoints"));
	}
	
	@Test 
	public void readIntListFalse(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Territory_cards/Period_1/City.json");
		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		assertNotEquals(list, cardsReader.readIntList("permanentResourcesAndPoints"));
	}
	
	@Test
	public void createPermanentReceiveResourcesOrPointsEffectTest(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Territory_cards/Period_1/City.json");
		Effect effect = cardsReader.createPermanentEffect("addResourcesAndPoints");
		assertTrue(effect instanceof ReceiveResourcesOrPointsEffect);
	}
	
	@Test
	public void createPermanentSingleTradeEffectTest(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Building_cards/Period_2/Baptistery.json");
		Effect effect = cardsReader.createPermanentEffect("singleTrade");
		assertTrue(effect instanceof TradeEffect);
	}
	
	@Test
	public void createPermanentDoubleTradeEffectTest(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Building_cards/Period_2/Sculptors_Guild.json");
		Effect effect = cardsReader.createPermanentEffect("doubleTrade");
		assertTrue(effect instanceof TradeEffect);
	}
	
	@Test 
	public void createPermanentCardNumberToResourcesEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Building_cards/Period_1/Mint.json");
		Effect effect = cardsReader.createPermanentEffect("cardsNumberToResources");
		assertTrue(effect instanceof CardsNumberToResourcesEffect);
	}
	
	@Test 
	public void createPermanentDiscountOnActionEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Character_cards/Period_1/Stonemason.json");
		Effect effect = cardsReader.createPermanentEffect("discountOnAction");
		assertTrue(effect instanceof ReceiveDiscountOnActionsEffect);
	}
	
	@Test 
	public void createPermanentActionValueModifierEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Character_cards/Period_1/Warlord.json");
		Effect effect = cardsReader.createPermanentEffect("actionValueModifier");
		assertTrue(effect instanceof ActionValueModifierEffect);
	}
	
	@Test 
	public void createPermanentDeletingBonusFloorEffectEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Character_cards/Period_1/Preacher.json");
		Effect effect = cardsReader.createPermanentEffect("deletingBonusFloorsEffect");
		assertTrue(effect instanceof DeletingBonusFloorsEffect);
	}
	
	@Test 
	public void createPermanentDisableMilitaryPointsRequirementEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Leader_cards/Cesare Borgia.json");
		Effect effect = cardsReader.createPermanentEffect("DisableMilitaryPointsRequirement");
		assertTrue(effect instanceof DisableMilitaryPointsRequirement);
	}
	
	@Test 
	public void createPermanentDisableTowerOccupiedMalusEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Leader_cards/Filippo Brunelleschi.json");
		Effect effect = cardsReader.createPermanentEffect("DisableTowerOccupiedMalus");
		assertTrue(effect instanceof DisableTowerOccupiedMalus);
	}
	
	@Test 
	public void createPermanentChangeFamilyMembersValueEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Leader_cards/Lucrezia Borgia.json");
		Effect effect = cardsReader.createPermanentEffect("ChangeFamilyMembersValue");
		assertTrue(effect instanceof ChangeFamilyMembersValue);
	}
	
	@Test 
	public void createPermanentGoingToOccupiedActionSpacesAllowedEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Leader_cards/Ludovico Ariosto.json");
		Effect effect = cardsReader.createPermanentEffect("GoingToOccupiedActionSpacesAllowedEffect");
		assertTrue(effect instanceof GoingToOccupiedActionSpacesAllowedEffect);
	}
	
	@Test 
	public void createPermanentFamilyMembersValueSetterEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Leader_cards/Ludovico il Moro.json");
		Effect effect = cardsReader.createPermanentEffect("FamilyMembersValueSetterEffect");
		assertTrue(effect instanceof FamilyMembersValueSetterEffect);
	}
	
	@Test 
	public void createPermanentVaticanSupportBonusEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Leader_cards/Sisto IV.json");
		Effect effect = cardsReader.createPermanentEffect("vaticanSupportBonus");
		assertTrue(effect instanceof VaticanSupportBonus);
	}
	
	@Test 
	public void createPermanentDoublePermanentResourcesFromCardsEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Leader_cards/Santa Rita.json");
		Effect effect = cardsReader.createPermanentEffect("DoublePermanentResourcesFromCards");
		assertTrue(effect instanceof DoubleImmediateResourcesFromCards);
	}
	
	@Test 
	public void createPermanentResourcesMalusEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Excommunication Tiles/Period_1/1.json");
		Effect effect = cardsReader.createPermanentEffect("PermanentResourcesMalusEffect");
		assertTrue(effect instanceof PermanentResourcesMalusEffect);
	}
	
	@Test 
	public void createPermanentMarketBanEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Excommunication Tiles/Period_2/5.json");
		Effect effect = cardsReader.createPermanentEffect("MarketBan");
		assertTrue(effect instanceof MarketBanEffect);
	}
	
	@Test 
	public void createPermanentDoubleServantsNeededEffectEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Excommunication Tiles/Period_2/6.json");
		Effect effect = cardsReader.createPermanentEffect("DoubleServantsNeededEffect");
		assertTrue(effect instanceof DoubleServantsNeededEffect);
	}
	
	@Test 
	public void createPermanentNoVictoryPointForCardTypeEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Excommunication Tiles/Period_3/1.json");
		Effect effect = cardsReader.createPermanentEffect("NoVictoryPointForCardTypeEffect");
		assertTrue(effect instanceof NoVictoryPointForCardTypeEffect);
	}
	
	@Test 
	public void createPermanentLoseVictoryPointsforEachNVictoryPointsEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Excommunication Tiles/Period_3/4.json");
		Effect effect = cardsReader.createPermanentEffect("LoseVictoryPointsforEachNVictoryPoints");
		assertTrue(effect instanceof LoseVictoryPointsforEachNVictoryPointsEffect);
	}
	
	@Test 
	public void createPermanentLoseVictoryPointForResourcesOrPointsEffect(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Excommunication Tiles/Period_3/5.json");
		Effect effect = cardsReader.createPermanentEffect("LoseVictoryPointForResourcesOrPoints");
		assertTrue(effect instanceof LoseVictoryPointForResourcesOrPointsEffect);
	}
	
	@Test
	public void createImmediatecardsNumberToResourcesEffectTest(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Character_cards/Period_3/Noble.json");
		Effect effect = cardsReader.createImmediateEffect("cardsNumberToResources");
		assertTrue(effect instanceof CardsNumberToResourcesEffect);
	}
	
	@Test
	public void createImmediateResourcesAndPointsEffectTest(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Territory_cards/Period_1/City.json");
		Effect effect = cardsReader.createImmediateEffect("addResourcesAndPoints");
		assertTrue(effect instanceof ReceiveResourcesOrPointsEffect);
	}
	
	@Test
	public void createImmediateSetSecondActionEffectTest(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Character_cards/Period_2/Architect.json");
		Effect effect = cardsReader.createImmediateEffect("setSecondAction");
		assertTrue(effect instanceof SetSecondAction);
	}
	
	@Test
	public void createImmediateGainPointsPerAnyMilitaryPointEffectTest(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Character_cards/Period_3/General.json");
		Effect effect = cardsReader.createImmediateEffect("GainPointsPerAnyMilitaryPoint");
		assertTrue(effect instanceof GainVictoryPointsPerAnyMilitaryPointEffect);
	}
	

	@Test
	public void createPaymentResourcesTest(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Character_cards/Period_3/General.json");
		Payment payment = cardsReader.createPayment("resources");
		assertTrue(payment instanceof ResourcesPayment);
	}
	
	@Test
	public void createPaymentMilitaryPointTest(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Venture_cards/Period1/Military_Campaign.json");
		Payment payment = cardsReader.createPayment("militaryPointPayment");
		assertTrue(payment instanceof MilitaryPointPayment);
	}
	
	@Test
	public void createPointOrResourcesRequirementTest(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Leader_cards/Girolamo Savonarola.json");
		Requirement requirement = cardsReader.createRequirement("pointsOrResources");
		assertTrue(requirement instanceof PointsOrResourcesRequirement);
	}
	
	
	@Test
	public void createCardsNumberORRequirementTest(){
		cardsReader.createJsonObjectFromFile("doc/Cards/Leader_cards/Lucrezia Borgia.json");
		Requirement requirement = cardsReader.createRequirement("cardsNumberOR");
		assertTrue(requirement instanceof CardNumbersOrRequirement);
	}
	
	@Test
	public void createDoubleEffectTest(){
		Effect effect1 = new MarketBanEffect();
		Effect effect2 = new MarketBanEffect();
		Effect effect = cardsReader.createDoubleEffect(effect1, effect2);
		assertTrue(effect instanceof TwoAndEffect);
	}
	
	@Test
	public void createDoublePaymentTest(){
		Payment payment1 = new MilitaryPointPayment(0, 0);
		Payment payment2 = new MilitaryPointPayment(0, 0);
		Payment payment = cardsReader.createDoublePayment(payment1, payment2);
		assertTrue(payment instanceof TwoOrPayments);
	}
	
	@Test
	public void createDoubleRequirementTest(){
		Requirement requirement1 = new CardNumbersRequirement(0, 0, 0, 0);
		Requirement requirement2 = new CardNumbersRequirement(0, 0, 0, 0);
		Requirement requirement = cardsReader.createDoubleRequirement(requirement1, requirement2);
		assertTrue(requirement instanceof TwoAndRequirements);
	}
	

}