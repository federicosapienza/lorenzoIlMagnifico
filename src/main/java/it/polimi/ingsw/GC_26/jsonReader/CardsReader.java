package it.polimi.ingsw.GC_26.jsonReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.*;

import it.polimi.ingsw.GC_26.model.game.gameComponents.board.BoardZone;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.ActionValueModifierEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.CardsNumberToResourcesEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.ChangeFamilyMembersValue;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.DeletingBonusFloorsEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.DisableMilitaryPointsRequirement;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.DisableTowerOccupiedMalus;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.DoubleImmediateResourcesFromCards;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.DoubleServantsNeededEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.Effect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.FamilyMembersValueSetterEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.GainVictoryPointsPerAnyMilitaryPointEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.GoingToOccupiedActionSpacesAllowedEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.LoseVictoryPointForResourcesOrPointsEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.LoseVictoryPointsforEachNVictoryPointsEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.MarketBanEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.NoVictoryPointForCardTypeEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.PermanentResourcesMalusEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.ReceiveDiscountOnActionsEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.SetSecondAction;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.TradeEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.TwoAndEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.VaticanSupportBonus;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.CardNumbersOrRequirement;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.CardNumbersRequirement;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.PointsOrResourcesRequirement;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.Requirement;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.leaderCard.TwoAndRequirements;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments.MilitaryPointPayment;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments.Payment;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments.ResourcesPayment;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments.TwoOrPayments;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.reflect.TypeToken;

public class CardsReader {
	

	
	private List<Integer> intList = new ArrayList<>();
	private List<Integer> intList2 = new ArrayList<>();
	private List<Integer> intList3 = new ArrayList<>();
	private List<Integer> intList4 = new ArrayList<>();
	private int intInt;
	private int intInt2;
	private String string;
	
	private BufferedReader br= null;
	private Gson gson = new Gson();
	private JsonObject jsonObject= null;
	private JsonElement jsonElement= null;
	private Type listTypeInt = new TypeToken<List<Integer>>() {}.getType();
	private JsonPathData jsonPathData = new JsonPathData();
	private Logger logger;
	private FileReader fileReader;
	
	
	public JsonObject getJsonObject(){
		return jsonObject;
	}
	
	public BufferedReader getBuffedredReader(){
		return br;
	}
	
	public void createJsonObjectFromFile(String path) {
		try {
			fileReader = new FileReader(path);
			br = new BufferedReader(fileReader);
			jsonObject= gson.fromJson(br, JsonObject.class);
			} catch (FileNotFoundException e) {
			logger.log(null, "File not Found!", e);
		}
		finally {
			try {
				fileReader.close();
			} catch (IOException e2) {
			logger.log(null, "FileReader not closed!", e2);
		}
		}
	}
	
	public void closeBufferedReader(){
		try {
			br.close();
			}
		catch (IOException e) {
			logger.log(null, "Buffered Reader not closed!", e);
		}
	}
	
	public String readString(String stringToRead){
		String returnString;
		jsonElement= jsonObject.get(stringToRead);
		returnString = jsonElement.getAsString();
		return returnString;
	}
	
	public int readInt(String stringToRead){
		int returnInt;
		jsonElement = jsonObject.get(stringToRead);
		returnInt = jsonElement.getAsInt();
		return returnInt;
	}
	
	public List<Integer> readIntList(String stringToRead){
		List<Integer> returnIntList;
		returnIntList = new Gson().fromJson(jsonObject.get(stringToRead), listTypeInt);
		return returnIntList;
	}

	
	public Effect createPermanentEffect(String effectType){
		
			if("singleTrade".equals(effectType)){
				intList=readIntList("permanentTradeEffectGive1");
				intList2= readIntList("permanentTradeEffectReceive1");
				ResourcesOrPoints resourcesOrPointsGive1 = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
				ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(intList2.get(0),intList2.get(1),intList2.get(2),intList2.get(3),intList2.get(4),intList2.get(5),intList2.get(6),intList2.get(7));
				return new TradeEffect(resourcesOrPointsGive1, null, resourcesOrPointsReceive1, null);
				}
			if("doubleTrade".equals(effectType)){
				intList=readIntList("permanentTradeEffectGive1");
				intList2= readIntList("permanentTradeEffectReceive1");
				intList3=readIntList("permanentTradeEffectGive2");
				intList4=readIntList("permanentTradeEffectReceive2");
				ResourcesOrPoints resourcesOrPointsGive1 = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
				ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(intList2.get(0),intList2.get(1),intList2.get(2),intList2.get(3),intList2.get(4),intList2.get(5),intList2.get(6),intList2.get(7));
				ResourcesOrPoints resourcesOrPointsGive2 = ResourcesOrPoints.newResourcesOrPoints(intList3.get(0),intList3.get(1),intList3.get(2),intList3.get(3),intList3.get(4),intList3.get(5),intList3.get(6),intList3.get(7));
				ResourcesOrPoints resourcesOrPointsReceive2 = ResourcesOrPoints.newResourcesOrPoints(intList4.get(0),intList4.get(1),intList4.get(2),intList4.get(3),intList4.get(4),intList4.get(5),intList4.get(6),intList4.get(7));
				return new TradeEffect(resourcesOrPointsGive1, resourcesOrPointsGive2, resourcesOrPointsReceive1, resourcesOrPointsReceive2);
				}
			if("cardsNumberToResources".equals(effectType)){
				intList=readIntList("permanentCardsNumberToResourcesResources");
				string=readString("permanentCardsNumberToResourcesCardType");
				DevelopmentCardTypes type = getDevelopmentCardType(string);
				ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
				return new CardsNumberToResourcesEffect(type, resourcesOrPoints);
				}
			if("addResourcesAndPoints".equals(effectType)){
				intList=readIntList("permanentResourcesAndPoints");
				return new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7)));
				}
			if("discountOnAction".equals(effectType)){
				intList = readIntList("permanentResDiscount");
				ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
				string = readString("permanentBoardzone");
				BoardZone boardZoneType = getBoardZoneType(string);
				return new ReceiveDiscountOnActionsEffect(boardZoneType, resourcesOrPoints);
			}
			if ("actionValueModifier".equals(effectType)){
				intInt = readInt("permanentValueModifier");
				string = readString("permanentBoardzone");
				BoardZone boardZoneType = getBoardZoneType(string);
				return new ActionValueModifierEffect(boardZoneType, intInt);
			}
			if ("deletingBonusFloorsEffect".equals(effectType)){
				return new DeletingBonusFloorsEffect();
			}
			if("setSecondAction".equals(effectType)){
				string = readString("permanentBoardzone");
				BoardZone boardZoneType = getBoardZoneType(string);
				intInt =readInt("permanentValue");
				intList= readIntList("permanentDiscount");
				ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
				return new SetSecondAction(boardZoneType, intInt, resourcesOrPoints);
			}
			if("GainPointsPerAnyMilitaryPoint".equals(effectType)){
				intList = readIntList("permanentGainPointsPerAnyMilitaryPointResources");
				ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
				return new GainVictoryPointsPerAnyMilitaryPointEffect(resourcesOrPoints);
			}
			if ("GoingToOccupiedActionSpacesAllowedEffect".equals(effectType)){
				return new GoingToOccupiedActionSpacesAllowedEffect();
			}
			if ("DisableTowerOccupiedMalus".equals(effectType)){
				return new DisableTowerOccupiedMalus();
			}
			if("FamilyMembersValueSetterEffect".equals(effectType)){
				intInt = readInt("permanentFamilyMemberValue");
				intInt2 = readInt("permanentNumberOfDices");
				return new FamilyMembersValueSetterEffect(intInt2, intInt);
			}
			if("ChangeFamilyMembersValue".equals(effectType)){
				intInt = readInt("permanentColouredMemberChange");
				intInt2 = readInt("permanentNeutralMemberChange");
				return new ChangeFamilyMembersValue(intInt, intInt);
			}
			if("DisableMilitaryPointsRequirement".equals(effectType)){
				return new DisableMilitaryPointsRequirement();
			}
			if ("DoublePermanentResourcesFromCards".equals(effectType)) {
				return new DoubleImmediateResourcesFromCards();
			}
			if("vaticanSupportBonus".equals(effectType)){
				intList = readIntList("permanentVaticanSupportBonus");
				ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
				return new VaticanSupportBonus(resourcesOrPoints);
			}
			if("PermanentResourcesMalusEffect".equals(effectType)){
				intList = readIntList("permanentResourcesAndPointsMalus");
				ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
				return new PermanentResourcesMalusEffect(resourcesOrPoints);
			}
			if("MarketBan".equals(effectType)){
				return new MarketBanEffect();
			}
			if("DoubleServantsNeededEffect".equals(effectType)){
				return new DoubleServantsNeededEffect();
			}
			if("NoVictoryPointForCardTypeEffect".equals(effectType)){
				string = readString("permanentCardType");
				DevelopmentCardTypes type = getDevelopmentCardType(string);
				return new NoVictoryPointForCardTypeEffect(type);
			}
			if("LoseVictoryPointForResourcesOrPoints".equals(effectType)){
				intList = readIntList("permanentLoseVictoryPointForResourcesOrPoints");
				ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
				return new LoseVictoryPointForResourcesOrPointsEffect(resourcesOrPoints);
			}
			if("LoseVictoryPointsforEachNVictoryPoints".equals(effectType)){
				intInt = readInt("LoseVictoryPointsforEachNVictoryPointsNumber");
				return new LoseVictoryPointsforEachNVictoryPointsEffect(intInt);
			}
			if("null".equals(effectType)){
				return null;
			}
			return null;
		
	}									
	
	
	public Effect createImmediateEffect(String effectType){
		if("singleTrade".equals(effectType)){
			intList=readIntList("immediateTradeEffectGive1");
			intList2= readIntList("immediateTradeEffectReceive1");
			ResourcesOrPoints resourcesOrPointsGive1 = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
			ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(intList2.get(0),intList2.get(1),intList2.get(2),intList2.get(3),intList2.get(4),intList2.get(5),intList2.get(6),intList2.get(7));
			return new TradeEffect(resourcesOrPointsGive1, null, resourcesOrPointsReceive1, null);
			}
		if("doubleTrade".equals(effectType)){
			intList=readIntList("immediateTradeEffectGive1");
			intList2= readIntList("immediateTradeEffectGive1");
			intList3= readIntList("immediateTradeEffectGive2");
			intList4= readIntList("immediateTradeEffectGive2");
			ResourcesOrPoints resourcesOrPointsGive1 = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
			ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(intList2.get(0),intList2.get(1),intList2.get(2),intList2.get(3),intList2.get(4),intList2.get(5),intList2.get(6),intList2.get(7));
			ResourcesOrPoints resourcesOrPointsGive2 = ResourcesOrPoints.newResourcesOrPoints(intList3.get(0),intList3.get(1),intList3.get(2),intList3.get(3),intList3.get(4),intList3.get(5),intList3.get(6),intList3.get(7));
			ResourcesOrPoints resourcesOrPointsReceive2 = ResourcesOrPoints.newResourcesOrPoints(intList4.get(0),intList4.get(1),intList4.get(2),intList4.get(3),intList4.get(4),intList4.get(5),intList4.get(6),intList4.get(7));	
			return new TradeEffect(resourcesOrPointsGive1, resourcesOrPointsGive2, resourcesOrPointsReceive1, resourcesOrPointsReceive2);
			}
		if("cardsNumberToResources".equals(effectType)){
			intList=readIntList("immediateCardsNumberToResourcesResources");
			string=readString("immediateCardsNumberToResourcesCardType");
			DevelopmentCardTypes type = getDevelopmentCardType(string);
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
			return new CardsNumberToResourcesEffect(type, resourcesOrPoints);
			}
		if("addResourcesAndPoints".equals(effectType)){
			intList=readIntList("immediateResourcesAndPoints");
			return new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7)));
			}
		if("discountOnAction".equals(effectType)){
			intList = readIntList("immediateResDiscount");
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
			string = readString("immediateBoardzone");
			BoardZone boardZoneType = getBoardZoneType(string);
			return new ReceiveDiscountOnActionsEffect(boardZoneType, resourcesOrPoints);
		}
		if ("actionValueModifier".equals(effectType)){
			intInt = readInt("immediateValueModifier");
			string = readString("immediateBoardzone");
			BoardZone boardZoneType = getBoardZoneType(string);
			return new ActionValueModifierEffect(boardZoneType, intInt);
		}
		if ("deletingBonusFloorsEffect".equals(effectType)){
			return new DeletingBonusFloorsEffect();
		}
		if("setSecondAction".equals(effectType)){
			string = readString("immediateBoardzone");
			BoardZone boardZoneType = getBoardZoneType(string);
			intInt =readInt("immediateValue");
			intList= readIntList("immediateDiscount");
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
			return new SetSecondAction(boardZoneType, intInt, resourcesOrPoints);
		}
		if("GainPointsPerAnyMilitaryPoint".equals(effectType)){
			intList = readIntList("immediateGainPointsPerAnyMilitaryPointResources");
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
			return new GainVictoryPointsPerAnyMilitaryPointEffect(resourcesOrPoints);
		}
		if ("GoingToOccupiedActionSpacesAllowedEffect".equals(effectType)){
			return new GoingToOccupiedActionSpacesAllowedEffect();
		}
		if ("DisableTowerOccupiedMalus".equals(effectType)){
			return new DisableTowerOccupiedMalus();
		}
		if("FamilyMembersValueSetterEffect".equals(effectType)){
			intInt = readInt("immediateFamilyMemberValue");
			intInt2 = readInt("immediateNumberOfDices");
			return new FamilyMembersValueSetterEffect(intInt2, intInt);
		}
		if("ChangeFamilyMembersValue".equals(effectType)){
			intInt = readInt("immediateColouredMemberChange");
			intInt2 = readInt("immediateNeutralMemberChange");
			return new ChangeFamilyMembersValue(intInt, intInt);
		}
		if("DisableMilitaryPointsRequirement".equals(effectType)){
			return new DisableMilitaryPointsRequirement();
		}
		if("vaticanSupportBonus".equals(effectType)){
			intList = readIntList("immediateVaticanSupportBonus");
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
			return new VaticanSupportBonus(resourcesOrPoints);
		}
		if("null".equals(effectType)){
			return null;
		}
		
		return null;
	}
	
	
	public Payment createPayment(String paymentType){
		if("resources".equals(paymentType)){
			intList = readIntList("payment");
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResources(intList.get(0),intList.get(1),intList.get(2),intList.get(3));
			return new ResourcesPayment(resourcesOrPoints);
		}
		if("militaryPointPayment".equals(paymentType)){
			intInt = readInt("toSpend");
			intInt2 = readInt("needed");
			return new MilitaryPointPayment(intInt, intInt2);
		}
		return null;
	}
	
	public Requirement createRequirement(String requirementType){
		if("pointsOrResources".equals(requirementType)){
			intList = readIntList("pointsOrResourcesRequirement");
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
			return new PointsOrResourcesRequirement(resourcesOrPoints);
		}
		if("cardsNumber".equals(requirementType)){
			intList = readIntList("cardNumberRequirement");
			return new CardNumbersRequirement(intList.get(0),intList.get(1),intList.get(2),intList.get(3));
		}
		if("cardsNumberOR".equals(requirementType)){
			intList = readIntList("cardNumberRequirementOR");
			return new CardNumbersOrRequirement(intList.get(0),intList.get(1),intList.get(2),intList.get(3));
		}
		return null;
	}
	
	public Effect createDoubleEffect(Effect effect1,Effect effect2){
		 return new TwoAndEffect(effect1, effect2);
	}
	
	public Payment createDoublePayment(Payment payment1,Payment payment2){
		return new TwoOrPayments(payment1, payment2);
		
	}
	
	public Requirement createDoubleRequirement(Requirement requirement,Requirement requirement2){
		return new TwoAndRequirements(requirement, requirement2);
	}
	
	public DevelopmentCardTypes getDevelopmentCardType(String type){
			if("territory".equals(type)){ 
				return DevelopmentCardTypes.TERRITORYCARD;}
			if("building".equals(type)){ 
				return DevelopmentCardTypes.BUILDINGCARD;}
			if("character".equals(type)){ 
				return DevelopmentCardTypes.CHARACTERCARD;}
			if("venture".equals(type)){ 
				return DevelopmentCardTypes.VENTURECARD;}
			return null;
		}
	public BoardZone getBoardZoneType(String type){
		if("territory".equals(type)){
			return BoardZone.TERRITORYTOWER;}
		if("building".equals(type)){
			return BoardZone.BUILDINGTOWER;}
		if("character".equals(type)){
			return BoardZone.CHARACTERTOWER;}
		if("venture".equals(type)){
			return BoardZone.VENTURETOWER;}
		if("harvest".equals(type)){
			return BoardZone.HARVEST;}
		if("production".equals(type)){
			return BoardZone.PRODUCTION;}
		if("market".equals(type)){
			return BoardZone.MARKET;}
		if("council".equals(type)){
			return BoardZone.COUNCILPALACE;}
		return null;
	}
	
	public String[] chooseListOfCards(int numOfList, DevelopmentCardTypes developmentCardTypes){
		switch (numOfList) {
		case 1:
			switch (developmentCardTypes) {
			case TERRITORYCARD:
				return jsonPathData.getTerritoryCardsPeriod1PathArray();
			case CHARACTERCARD:
				return jsonPathData.getCharacterCardsPeriod1PathArray();
			case BUILDINGCARD:
				return jsonPathData.getBuildingCardsPeriod1PathArray();
			case VENTURECARD:
				return jsonPathData.getVentureCardsPeriod1PathArray();
			default:
				break;
			}
				break;
		case 2:
			switch (developmentCardTypes) {
			case TERRITORYCARD:
				return jsonPathData.getTerritoryCardsPeriod2PathArray();
			case CHARACTERCARD:
				return jsonPathData.getCharacterCardsPeriod2PathArray();
			case BUILDINGCARD:
				return jsonPathData.getBuildingCardsPeriod2PathArray();
			case VENTURECARD:
				return jsonPathData.getVentureCardsPeriod2PathArray();
			default:
				break;
			}	
				break;
		case 3:
			switch (developmentCardTypes) {
			case TERRITORYCARD:
				return jsonPathData.getTerritoryCardsPeriod3PathArray();
			case CHARACTERCARD:
				return jsonPathData.getCharacterCardsPeriod3PathArray();
			case BUILDINGCARD:
				return jsonPathData.getBuildingCardsPeriod3PathArray();
			case VENTURECARD:
				return jsonPathData.getVentureCardsPeriod3PathArray();
			default:
				break;
			}
			break;
		default:
			break;
		}
	return new String[0]; //return an empty array of strings instead null
	}
	
	
}
	


