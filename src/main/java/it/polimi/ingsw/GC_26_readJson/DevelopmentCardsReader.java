package it.polimi.ingsw.GC_26_readJson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.*;


import com.google.gson.*;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.effects.ActionValueModifierEffect;
import it.polimi.ingsw.GC_26_cards.effects.CardsNumberToResourcesEffect;
import it.polimi.ingsw.GC_26_cards.effects.DeletingBonusFloorsEffect;
import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_cards.effects.GainPointsPerAnyMilitaryPointEffect;
import it.polimi.ingsw.GC_26_cards.effects.ReceiveDiscountOnActionsEffect;
import it.polimi.ingsw.GC_26_cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.GC_26_cards.effects.SetSecondAction;
import it.polimi.ingsw.GC_26_cards.effects.TradeEffect;
import it.polimi.ingsw.GC_26_cards.effects.TwoAndEffect;
import it.polimi.ingsw.GC_26_cards.leaderCard.PointsOrResourcesRequirement;
import it.polimi.ingsw.GC_26_cards.payments.MilitaryPointPayment;
import it.polimi.ingsw.GC_26_cards.payments.Payment;
import it.polimi.ingsw.GC_26_cards.payments.ResourcesPayment;
import it.polimi.ingsw.GC_26_cards.payments.TwoOrPayments;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.lang.model.type.IntersectionType;

import com.google.gson.reflect.TypeToken;

public class DevelopmentCardsReader {
	
	private String name;
	private int period;
	private List<Integer> paymentList = new ArrayList<Integer>();
	private List<Integer> permanentResourcesAndPointsList = new ArrayList<Integer>();
	private List<Integer> permanentCardsNumberToResourcesEffectResourcesAndPointsList = new ArrayList<Integer>();
	private List<Integer> permanentTradeEffectGive1ResList = new ArrayList<Integer>();
	private List<Integer> permanentTradeEffectReceive1ResList = new ArrayList<Integer>();
	private List<Integer> permanentTradeEffectGive2ResList = new ArrayList<Integer>();
	private List<Integer> permanentTradeEffectReceive2ResList = new ArrayList<Integer>();
	private List<Integer> immediateResourcesAndPointsList = new ArrayList<Integer>();
	private List<Integer> immediateCardsNumberToResourcesEffectResourcesAndPointsList = new ArrayList<Integer>();
	private List<Integer> immediateTradeEffectGive1ResList = new ArrayList<Integer>();
	private List<Integer> immediateTradeEffectReceive1ResList = new ArrayList<Integer>();
	private List<Integer> immediateTradeEffectGive2ResList = new ArrayList<Integer>();
	private List<Integer> immediateTradeEffectReceive2ResList = new ArrayList<Integer>();
	
	private List<Integer> intList = new ArrayList<Integer>();
	private int intInt;
	private int intInt2;
	
	private String permanentEffectType;
	private String immediateEffectType;
	private String paymentType;
	private int actionValue;
	private BufferedReader br= null;
	private Gson gson = new Gson();
	private JsonObject jsonObject= null;
	private JsonElement jsonElement= null;
	private Type listTypeInt = new TypeToken<List<Integer>>() {}.getType();
	private List<DevelopmentCard> territoryCards = new ArrayList<DevelopmentCard>();//getter! -> non credo sia utile,passo i file direttamente ad un'altra classe
	private JsonPathData jsonPathData = new JsonPathData();
	private String permanentCardsNumberToResourcesCardType;
	private String immediateCardsNumberToResourcesCardType;
	private int valueModifier;
	private String boardzone;
	
	private String returnString;
	private int returnInt;
	private List<Integer> returnIntList = new ArrayList<Integer>();

	
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

	///////////////////////SINCRONIZZARE createPermanentEffect e createImmediateEffect
	
	
	public Effect createPermanentEffect(String effectType){
		
			if(effectType.equals("singleTrade")){
				permanentTradeEffectGive1ResList=readIntList("permanentTradeEffectGive1");
				permanentTradeEffectReceive1ResList= readIntList("permanentTradeEffectReceive1");
				ResourcesOrPoints resourcesOrPointsGive1 = ResourcesOrPoints.newResourcesOrPoints(permanentTradeEffectGive1ResList.get(0),permanentTradeEffectGive1ResList.get(1),permanentTradeEffectGive1ResList.get(2),permanentTradeEffectGive1ResList.get(3),permanentTradeEffectGive1ResList.get(4),permanentTradeEffectGive1ResList.get(5),permanentTradeEffectGive1ResList.get(6),permanentTradeEffectGive1ResList.get(7));
				ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(permanentTradeEffectReceive1ResList.get(0),permanentTradeEffectReceive1ResList.get(1),permanentTradeEffectReceive1ResList.get(2),permanentTradeEffectReceive1ResList.get(3),permanentTradeEffectReceive1ResList.get(4),permanentTradeEffectReceive1ResList.get(5),permanentTradeEffectReceive1ResList.get(6),permanentTradeEffectReceive1ResList.get(7));
				TradeEffect Effect = new TradeEffect(resourcesOrPointsGive1, null, resourcesOrPointsReceive1, null);
				return Effect;
				}
			if(effectType.equals("doubleTrade")){
				permanentTradeEffectGive1ResList=readIntList("permanentTradeEffectGive1");
				permanentTradeEffectReceive1ResList= readIntList("permanentTradeEffectReceive1");
				permanentTradeEffectGive2ResList=readIntList("permanentTradeEffectGive2");
				permanentTradeEffectReceive2ResList=readIntList("permanentTradeEffectReceive2");
				ResourcesOrPoints resourcesOrPointsGive1 = ResourcesOrPoints.newResourcesOrPoints(permanentTradeEffectGive1ResList.get(0),permanentTradeEffectGive1ResList.get(1),permanentTradeEffectGive1ResList.get(2),permanentTradeEffectGive1ResList.get(3),permanentTradeEffectGive1ResList.get(4),permanentTradeEffectGive1ResList.get(5),permanentTradeEffectGive1ResList.get(6),permanentTradeEffectGive1ResList.get(7));
				ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(permanentTradeEffectReceive1ResList.get(0),permanentTradeEffectReceive1ResList.get(1),permanentTradeEffectReceive1ResList.get(2),permanentTradeEffectReceive1ResList.get(3),permanentTradeEffectReceive1ResList.get(4),permanentTradeEffectReceive1ResList.get(5),permanentTradeEffectReceive1ResList.get(6),permanentTradeEffectReceive1ResList.get(7));
				ResourcesOrPoints resourcesOrPointsGive2 = ResourcesOrPoints.newResourcesOrPoints(permanentTradeEffectGive2ResList.get(0),permanentTradeEffectGive2ResList.get(1),permanentTradeEffectGive2ResList.get(2),permanentTradeEffectGive2ResList.get(3),permanentTradeEffectGive2ResList.get(4),permanentTradeEffectGive2ResList.get(5),permanentTradeEffectGive2ResList.get(6),permanentTradeEffectGive2ResList.get(7));
				ResourcesOrPoints resourcesOrPointsReceive2 = ResourcesOrPoints.newResourcesOrPoints(permanentTradeEffectReceive2ResList.get(0),permanentTradeEffectReceive2ResList.get(1),permanentTradeEffectReceive2ResList.get(2),permanentTradeEffectReceive2ResList.get(3),permanentTradeEffectReceive2ResList.get(4),permanentTradeEffectReceive2ResList.get(5),permanentTradeEffectReceive2ResList.get(6),permanentTradeEffectReceive2ResList.get(7));
				TradeEffect Effect = new TradeEffect(resourcesOrPointsGive1, resourcesOrPointsGive2, resourcesOrPointsReceive1, resourcesOrPointsReceive2);
				return Effect;
				}
			if(effectType.equals("cardsNumberToResources")){
				permanentCardsNumberToResourcesEffectResourcesAndPointsList=readIntList("permanentCardsNumberToResourcesResources");
				permanentCardsNumberToResourcesCardType=readString("permanentCardsNumberToResourcesCardType");
				DevelopmentCardTypes type = getDevelopmentCardType(permanentCardsNumberToResourcesCardType);
				ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(permanentCardsNumberToResourcesEffectResourcesAndPointsList.get(0),permanentCardsNumberToResourcesEffectResourcesAndPointsList.get(1),permanentCardsNumberToResourcesEffectResourcesAndPointsList.get(2),permanentCardsNumberToResourcesEffectResourcesAndPointsList.get(3),permanentCardsNumberToResourcesEffectResourcesAndPointsList.get(4),permanentCardsNumberToResourcesEffectResourcesAndPointsList.get(5),permanentCardsNumberToResourcesEffectResourcesAndPointsList.get(6),permanentCardsNumberToResourcesEffectResourcesAndPointsList.get(7));
				CardsNumberToResourcesEffect Effect = new CardsNumberToResourcesEffect(type, resourcesOrPoints);
				return Effect;
				}
			if(effectType.equals("addResourcesAndPoints")){
				permanentResourcesAndPointsList=readIntList("permanentResourcesAndPoints");
				ReceiveResourcesOrPointsEffect Effect= new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newResourcesOrPoints(permanentResourcesAndPointsList.get(0),permanentResourcesAndPointsList.get(1),permanentResourcesAndPointsList.get(2),permanentResourcesAndPointsList.get(3),permanentResourcesAndPointsList.get(4),permanentResourcesAndPointsList.get(5),permanentResourcesAndPointsList.get(6),permanentResourcesAndPointsList.get(7)));
				return Effect;
				}
			if(effectType.equals("discountOnAction")){
				intList = readIntList("permanentResDiscount");
				ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
				boardzone = readString("permanentBoardzone");
				BoardZone boardZoneType = getBoardZoneType(boardzone);
				ReceiveDiscountOnActionsEffect effect = new ReceiveDiscountOnActionsEffect(boardZoneType, resourcesOrPoints);
				return effect;
			}
			
			if(effectType.equals("null")){
				return null;
			}
			return null;
		
	}//salva gli effetti e con quei due ci ritorna 	
											//si ma come faccio a dire alla fine che deve prendere i due effetti salvati 
								//per fare la variabile finale?
											//ci penso domani 
											//potrei farlo con dei flag ma sbatta
											//immediate action type doubleeffecttipo1tipo2
											//però in quest'ultimo caso non diventa piu configurabile?
											//mi faccio tornare una lista di effetti ?
											//se entra in 2 if allora un counter si accorge che 
											//l'effetto è doppio

	
	public Payment createPayment(String paymentType){
		if(paymentType.equals("resources")){
			paymentList = readIntList("payment");
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResources(paymentList.get(0),paymentList.get(1),paymentList.get(2),paymentList.get(3));
			ResourcesPayment payment = new ResourcesPayment(resourcesOrPoints);
			return payment;
		}
		/*if(paymentType.equals("militaryPoints")){
			MilitaryPointPayment payment = new MilitaryPointPayment(toSpend, needed);// direi che prima di fare ciò devo leggere le carte.
			return payment;
		}*/
		return null;
	}
	
	public Effect createImmediateEffect(String effectType){
		if(effectType.equals("singleTrade")){
			immediateTradeEffectGive1ResList=readIntList("immediateTradeEffectGive1");
			immediateTradeEffectReceive1ResList= readIntList("immediateTradeEffectReceive1");
			ResourcesOrPoints resourcesOrPointsGive1 = ResourcesOrPoints.newResourcesOrPoints(immediateTradeEffectGive1ResList.get(0),immediateTradeEffectGive1ResList.get(1),immediateTradeEffectGive1ResList.get(2),immediateTradeEffectGive1ResList.get(3),immediateTradeEffectGive1ResList.get(4),immediateTradeEffectGive1ResList.get(5),immediateTradeEffectGive1ResList.get(6),immediateTradeEffectGive1ResList.get(7));
			ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(immediateTradeEffectReceive1ResList.get(0),immediateTradeEffectReceive1ResList.get(1),immediateTradeEffectReceive1ResList.get(2),immediateTradeEffectReceive1ResList.get(3),immediateTradeEffectReceive1ResList.get(4),immediateTradeEffectReceive1ResList.get(5),immediateTradeEffectReceive1ResList.get(6),immediateTradeEffectReceive1ResList.get(7));
			TradeEffect effect = new TradeEffect(resourcesOrPointsGive1, null, resourcesOrPointsReceive1, null);
			return effect;
			}
		if(effectType.equals("doubleTrade")){
			immediateTradeEffectGive1ResList=readIntList("immediateTradeEffectGive1");
			immediateTradeEffectReceive1ResList= readIntList("immediateTradeEffectGive1");
			immediateTradeEffectGive2ResList= readIntList("immediateTradeEffectGive2");
			immediateTradeEffectReceive2ResList= readIntList("immediateTradeEffectGive2");
			ResourcesOrPoints resourcesOrPointsGive1 = ResourcesOrPoints.newResourcesOrPoints(immediateTradeEffectGive1ResList.get(0),immediateTradeEffectGive1ResList.get(1),immediateTradeEffectGive1ResList.get(2),immediateTradeEffectGive1ResList.get(3),immediateTradeEffectGive1ResList.get(4),immediateTradeEffectGive1ResList.get(5),immediateTradeEffectGive1ResList.get(6),immediateTradeEffectGive1ResList.get(7));
			ResourcesOrPoints resourcesOrPointsReceive1 = ResourcesOrPoints.newResourcesOrPoints(immediateTradeEffectReceive1ResList.get(0),immediateTradeEffectReceive1ResList.get(1),immediateTradeEffectReceive1ResList.get(2),immediateTradeEffectReceive1ResList.get(3),immediateTradeEffectReceive1ResList.get(4),immediateTradeEffectReceive1ResList.get(5),immediateTradeEffectReceive1ResList.get(6),immediateTradeEffectReceive1ResList.get(7));
			ResourcesOrPoints resourcesOrPointsGive2 = ResourcesOrPoints.newResourcesOrPoints(immediateTradeEffectGive2ResList.get(0),immediateTradeEffectGive2ResList.get(1),immediateTradeEffectGive2ResList.get(2),immediateTradeEffectGive2ResList.get(3),immediateTradeEffectGive2ResList.get(4),immediateTradeEffectGive2ResList.get(5),immediateTradeEffectGive2ResList.get(6),immediateTradeEffectGive2ResList.get(7));
			ResourcesOrPoints resourcesOrPointsReceive2 = ResourcesOrPoints.newResourcesOrPoints(immediateTradeEffectReceive2ResList.get(0),immediateTradeEffectReceive2ResList.get(1),immediateTradeEffectReceive2ResList.get(2),immediateTradeEffectReceive2ResList.get(3),immediateTradeEffectReceive2ResList.get(4),immediateTradeEffectReceive2ResList.get(5),immediateTradeEffectReceive2ResList.get(6),immediateTradeEffectReceive2ResList.get(7));	
			TradeEffect effect = new TradeEffect(resourcesOrPointsGive1, resourcesOrPointsGive2, resourcesOrPointsReceive1, resourcesOrPointsReceive2);
			return effect;
			}
		if(effectType.equals("cardsNumberToResources")){
			immediateCardsNumberToResourcesEffectResourcesAndPointsList=readIntList("immediateCardsNumberToResourcesResources");
			immediateCardsNumberToResourcesCardType=readString("immediateCardsNumberToResourcesCardType");
			DevelopmentCardTypes type = getDevelopmentCardType(immediateCardsNumberToResourcesCardType);
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(immediateCardsNumberToResourcesEffectResourcesAndPointsList.get(0),immediateCardsNumberToResourcesEffectResourcesAndPointsList.get(1),immediateCardsNumberToResourcesEffectResourcesAndPointsList.get(2),immediateCardsNumberToResourcesEffectResourcesAndPointsList.get(3),immediateCardsNumberToResourcesEffectResourcesAndPointsList.get(4),immediateCardsNumberToResourcesEffectResourcesAndPointsList.get(5),immediateCardsNumberToResourcesEffectResourcesAndPointsList.get(6),immediateCardsNumberToResourcesEffectResourcesAndPointsList.get(7));
			CardsNumberToResourcesEffect effect = new CardsNumberToResourcesEffect(type, resourcesOrPoints);
			return effect;
			}
		if(effectType.equals("addResourcesAndPoints")){
			immediateResourcesAndPointsList=readIntList("immediateResourcesAndPoints");
			ReceiveResourcesOrPointsEffect effect= new ReceiveResourcesOrPointsEffect(ResourcesOrPoints.newResourcesOrPoints(immediateResourcesAndPointsList.get(0),immediateResourcesAndPointsList.get(1),immediateResourcesAndPointsList.get(2),immediateResourcesAndPointsList.get(3),immediateResourcesAndPointsList.get(4),immediateResourcesAndPointsList.get(5),immediateResourcesAndPointsList.get(6),immediateResourcesAndPointsList.get(7)));
			return effect;
			}
		if (effectType.equals("actionValueModifier")){
			valueModifier = readInt("immediateValueModifier");
			boardzone = readString("immediateBoardzone");
			BoardZone boardZoneType = getBoardZoneType(boardzone);
			ActionValueModifierEffect effect = new ActionValueModifierEffect(boardZoneType, valueModifier);
			return effect;
		}
		if (effectType.equals("deletingBonusFloorsEffect")){
			DeletingBonusFloorsEffect effect = new DeletingBonusFloorsEffect();
			return effect;
		}
		if(effectType.equals("setSecondAction")){
			boardzone = readString("immediateBoardzone");
			BoardZone boardZoneType = getBoardZoneType(boardzone);//gestito il null
			intInt =readInt("immediateValue");
			intList= readIntList("immediateDiscount");
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
			SetSecondAction effect = new SetSecondAction(boardZoneType, intInt, resourcesOrPoints);
			return effect;
		}
		if(effectType.equals("GainPointsPerAnyMilitaryPoint")){
			intList = readIntList("immediateGainPointsPerAnyMilitaryPointResources");
			ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(intList.get(0),intList.get(1),intList.get(2),intList.get(3),intList.get(4),intList.get(5),intList.get(6),intList.get(7));
			GainPointsPerAnyMilitaryPointEffect effect = new GainPointsPerAnyMilitaryPointEffect(resourcesOrPoints);
			return effect;
		}
		if(effectType.equals("null")){
			return null;
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
	
	//forse ho duplicato le variabili senza motivo... intendo immediate e permanent.
	
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
	


