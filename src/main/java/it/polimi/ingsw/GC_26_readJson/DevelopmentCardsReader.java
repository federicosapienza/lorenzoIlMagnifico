package it.polimi.ingsw.GC_26_readJson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.*;


import com.google.gson.*;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.effects.ActionValueModifierEffect;
import it.polimi.ingsw.GC_26_cards.effects.CardsNumberToResourcesEffect;
import it.polimi.ingsw.GC_26_cards.effects.DeletingBonusFloorsEffect;
import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_cards.effects.GainVictoryPointsPerAnyMilitaryPointEffect;
import it.polimi.ingsw.GC_26_cards.effects.ReceiveDiscountOnActionsEffect;
import it.polimi.ingsw.GC_26_cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.GC_26_cards.effects.SetSecondAction;
import it.polimi.ingsw.GC_26_cards.effects.TradeEffect;
import it.polimi.ingsw.GC_26_cards.effects.TwoAndEffect;
import it.polimi.ingsw.GC_26_cards.payments.MilitaryPointPayment;
import it.polimi.ingsw.GC_26_cards.payments.Payment;
import it.polimi.ingsw.GC_26_cards.payments.ResourcesPayment;
import it.polimi.ingsw.GC_26_cards.payments.TwoOrPayments;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class DevelopmentCardsReader {
	

	
	private List<Integer> intList = new ArrayList<Integer>();
	private List<Integer> intList2 = new ArrayList<Integer>();
	private List<Integer> intList3 = new ArrayList<Integer>();
	private List<Integer> intList4 = new ArrayList<Integer>();
	private int intInt;
	private int intInt2;
	private String string;
	private String returnString;
	private int returnInt;
	private List<Integer> returnIntList = new ArrayList<Integer>();

	private BufferedReader br= null;
	private Gson gson = new Gson();
	private JsonObject jsonObject= null;
	private JsonElement jsonElement= null;
	private Type listTypeInt = new TypeToken<List<Integer>>() {}.getType();



	
	public JsonObject createJsonObjectFromFile(String path){
		try {
			br = new BufferedReader(new FileReader(path));
			return jsonObject= gson.fromJson(br, JsonObject.class);
		} catch (FileNotFoundException e) {
			   e.printStackTrace();
			   return null;
		}
	}
	
	public String readString(String stringToRead){
		jsonElement= jsonObject.get(stringToRead);
		return returnString = jsonElement.getAsString();
	}
	
	public int readInt(String stringToRead){
			jsonElement = jsonObject.get(stringToRead);
			return returnInt = jsonElement.getAsInt();
	}
	
	public List<Integer> readIntList(String stringToRead){
		jsonElement = jsonObject.get(stringToRead).getAsJsonArray();
		return returnIntList = new Gson().fromJson(jsonObject.get(stringToRead), listTypeInt);
	}

	
	public Effect createPermanentEffect(String effectType){
		
			if(effectType.equals("singleTrade")){
				intList=readIntList("permanentTradeEffectGive1");
				intList2= readIntList("permanentTradeEffectReceive1");
				ResourcesOrPoints resourcesOrPointsGive1 = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
				ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(intList2.get(0),intList2.get(1),intList2.get(2),intList2.get(3),intList2.get(4),intList2.get(5),intList2.get(6),intList2.get(7));
				TradeEffect Effect = new TradeEffect(resourcesOrPointsGive1, null, resourcesOrPointsReceive1, null);
				return Effect;
				}
			if(effectType.equals("doubleTrade")){
				intList=readIntList("permanentTradeEffectGive1");
				intList2= readIntList("permanentTradeEffectReceive1");
				intList3=readIntList("permanentTradeEffectGive2");
				intList4=readIntList("permanentTradeEffectReceive2");
				ResourcesOrPoints resourcesOrPointsGive1 = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
				ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(intList2.get(0),intList2.get(1),intList2.get(2),intList2.get(3),intList2.get(4),intList2.get(5),intList2.get(6),intList2.get(7));
				ResourcesOrPoints resourcesOrPointsGive2 = ResourcesOrPoints.newResourcesOrPoints(intList3.get(0),intList3.get(1),intList3.get(2),intList3.get(3),intList3.get(4),intList3.get(5),intList3.get(6),intList3.get(7));
				ResourcesOrPoints resourcesOrPointsReceive2 = ResourcesOrPoints.newResourcesOrPoints(intList4.get(0),intList4.get(1),intList4.get(2),intList4.get(3),intList4.get(4),intList4.get(5),intList4.get(6),intList4.get(7));
				TradeEffect Effect = new TradeEffect(resourcesOrPointsGive1, resourcesOrPointsGive2, resourcesOrPointsReceive1, resourcesOrPointsReceive2);
				return Effect;
				}
			if(effectType.equals("cardsNumberToResources")){
				intList=readIntList("permanentCardsNumberToResourcesResources");
				string=readString("permanentCardsNumberToResourcesCardType");
				DevelopmentCardTypes type = getDevelopmentCardType(string);
				ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
				CardsNumberToResourcesEffect Effect = new CardsNumberToResourcesEffect(type, resourcesOrPoints);
				return Effect;
				}
			if(effectType.equals("addResourcesAndPoints")){
				intList=readIntList("permanentResourcesAndPoints");
				//System.out.println(intList); it seems to work..wow
				ReceiveResourcesOrPointsEffect Effect= new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7)));
				return Effect;
				}
			if(effectType.equals("discountOnAction")){
				intList = readIntList("permanentResDiscount");
				ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
				string = readString("permanentBoardzone");
				BoardZone boardZoneType = getBoardZoneType(string);
				ReceiveDiscountOnActionsEffect effect = new ReceiveDiscountOnActionsEffect(boardZoneType, resourcesOrPoints);
				return effect;
			}
			if (effectType.equals("actionValueModifier")){
				intInt = readInt("permanentValueModifier");
				string = readString("permanentBoardzone");
				BoardZone boardZoneType = getBoardZoneType(string);
				ActionValueModifierEffect effect = new ActionValueModifierEffect(boardZoneType, intInt);
				return effect;
			}
			if (effectType.equals("deletingBonusFloorsEffect")){
				DeletingBonusFloorsEffect effect = new DeletingBonusFloorsEffect();
				return effect;
			}
			if(effectType.equals("setSecondAction")){
				string = readString("permanentBoardzone");
				BoardZone boardZoneType = getBoardZoneType(string);
				intInt =readInt("permanentValue");
				intList= readIntList("permanentDiscount");
				ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
				SetSecondAction effect = new SetSecondAction(boardZoneType, intInt, resourcesOrPoints);
				return effect;
			}
			if(effectType.equals("GainPointsPerAnyMilitaryPoint")){
				intList = readIntList("permanentGainPointsPerAnyMilitaryPointResources");
				ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
				GainVictoryPointsPerAnyMilitaryPointEffect effect = new GainVictoryPointsPerAnyMilitaryPointEffect(resourcesOrPoints);
				return effect;
			}
			if(effectType.equals("null")){
				return null;
			}
			return null;
		
	}									
	
	
	public Effect createImmediateEffect(String effectType){
		if(effectType.equals("singleTrade")){
			intList=readIntList("immediateTradeEffectGive1");
			intList2= readIntList("immediateTradeEffectReceive1");
			ResourcesOrPoints resourcesOrPointsGive1 = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
			ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(intList2.get(0),intList2.get(1),intList2.get(2),intList2.get(3),intList2.get(4),intList2.get(5),intList2.get(6),intList2.get(7));
			TradeEffect effect = new TradeEffect(resourcesOrPointsGive1, null, resourcesOrPointsReceive1, null);
			return effect;
			}
		if(effectType.equals("doubleTrade")){
			intList=readIntList("immediateTradeEffectGive1");
			intList2= readIntList("immediateTradeEffectGive1");
			intList3= readIntList("immediateTradeEffectGive2");
			intList4= readIntList("immediateTradeEffectGive2");
			ResourcesOrPoints resourcesOrPointsGive1 = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
			ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(intList2.get(0),intList2.get(1),intList2.get(2),intList2.get(3),intList2.get(4),intList2.get(5),intList2.get(6),intList2.get(7));
			ResourcesOrPoints resourcesOrPointsGive2 = ResourcesOrPoints.newResourcesOrPoints(intList3.get(0),intList3.get(1),intList3.get(2),intList3.get(3),intList3.get(4),intList3.get(5),intList3.get(6),intList3.get(7));
			ResourcesOrPoints resourcesOrPointsReceive2 = ResourcesOrPoints.newResourcesOrPoints(intList4.get(0),intList4.get(1),intList4.get(2),intList4.get(3),intList4.get(4),intList4.get(5),intList4.get(6),intList4.get(7));	
			TradeEffect effect = new TradeEffect(resourcesOrPointsGive1, resourcesOrPointsGive2, resourcesOrPointsReceive1, resourcesOrPointsReceive2);
			return effect;
			}
		if(effectType.equals("cardsNumberToResources")){
			intList=readIntList("immediateCardsNumberToResourcesResources");
			string=readString("immediateCardsNumberToResourcesCardType");
			DevelopmentCardTypes type = getDevelopmentCardType(string);
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
			CardsNumberToResourcesEffect effect = new CardsNumberToResourcesEffect(type, resourcesOrPoints);
			return effect;
			}
		if(effectType.equals("addResourcesAndPoints")){
			intList=readIntList("immediateResourcesAndPoints");
			ReceiveResourcesOrPointsEffect effect= new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7)));
			return effect;
			}
		if(effectType.equals("discountOnAction")){
			intList = readIntList("immediateResDiscount");
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
			string = readString("immediateBoardzone");
			BoardZone boardZoneType = getBoardZoneType(string);
			ReceiveDiscountOnActionsEffect effect = new ReceiveDiscountOnActionsEffect(boardZoneType, resourcesOrPoints);
			return effect;
		}
		if (effectType.equals("actionValueModifier")){
			intInt = readInt("immediateValueModifier");
			string = readString("immediateBoardzone");
			BoardZone boardZoneType = getBoardZoneType(string);
			ActionValueModifierEffect effect = new ActionValueModifierEffect(boardZoneType, intInt);
			return effect;
		}
		if (effectType.equals("deletingBonusFloorsEffect")){
			DeletingBonusFloorsEffect effect = new DeletingBonusFloorsEffect();
			return effect;
		}
		if(effectType.equals("setSecondAction")){
			string = readString("immediateBoardzone");
			BoardZone boardZoneType = getBoardZoneType(string);//gestito il null
			intInt =readInt("immediateValue");
			intList= readIntList("immediateDiscount");
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
			SetSecondAction effect = new SetSecondAction(boardZoneType, intInt, resourcesOrPoints);
			return effect;
		}
		if(effectType.equals("GainPointsPerAnyMilitaryPoint")){
			intList = readIntList("immediateGainPointsPerAnyMilitaryPointResources");
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
			GainVictoryPointsPerAnyMilitaryPointEffect effect = new GainVictoryPointsPerAnyMilitaryPointEffect(resourcesOrPoints);
			return effect;
		}
		if(effectType.equals("null")){
			return null;
		}
		
		return null;
	}
	
	
	public Payment createPayment(String paymentType){
		if(paymentType.equals("resources")){
			intList = readIntList("payment");
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResources(intList.get(0),intList.get(1),intList.get(2),intList.get(3));
			ResourcesPayment payment = new ResourcesPayment(resourcesOrPoints);
			return payment;
		}
		if(paymentType.equals("militaryPointPayment")){
			intInt = readInt("toSpend");
			intInt2 = readInt("needed");
			MilitaryPointPayment militaryPointPayment = new MilitaryPointPayment(intInt, intInt2);
			return militaryPointPayment;
		}
		return null;
	}
	
	public Effect createDoubleEffect(Effect effect1,Effect effect2){
		 TwoAndEffect effect = new TwoAndEffect(effect1, effect2);
		 return effect;
	}
	
	public Payment createDoublePayment(Payment payment1,Payment payment2){
		TwoOrPayments payment = new TwoOrPayments(payment1, payment2);
		return payment;
	}
	
	private DevelopmentCardTypes getDevelopmentCardType(String type){
			if(type.equals("territory")){ return DevelopmentCardTypes.TERRITORYCARD;}
			if(type.equals("building")){ return DevelopmentCardTypes.BUILDINGCARD;}
			if(type.equals("character")){ return DevelopmentCardTypes.CHARACTERCARD;}
			if(type.equals("venture")){ return DevelopmentCardTypes.VENTURECARD;}
			return null;
		}
	private BoardZone getBoardZoneType(String type){
		if(type.equals("territory")){return BoardZone.TERRITORYTOWER;}
		if(type.equals("building")){return BoardZone.BUILDINGTOWER;}
		if(type.equals("character")){return BoardZone.CHARACTERTOWER;}
		if(type.equals("venture")){return BoardZone.VENTURETOWER;}
		if(type.equals("harvest")){return BoardZone.HARVEST;}
		if(type.equals("production")){return BoardZone.PRODUCTION;}
		if(type.equals("market")){return BoardZone.MARKET;}
		if(type.equals("council")){return BoardZone.COUNCILPALACE;}
		return null;
	}
}
	


