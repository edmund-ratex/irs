	
package com.app.dc.fix.message;


import com.app.dc.fix.*;  
import com.app.dc.fix.MessageDictionary; 
import java.util.List;
import java.util.ArrayList;


public class MarketDataRequest extends FixMessage{
		public MarketDataRequest()
		{		 
			this.getStandardHeader().setMsgType("V");
		}
		public String getVenueTypeGW(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.VenueTypeGW));
		}
		public void setVenueTypeGW(String venueTypeGW){
			this.put(MessageDictionary.Fields.VenueTypeGW, venueTypeGW);
		}
		public String getVenues(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Venues));
		}
		public void setVenues(String venues){
			this.put(MessageDictionary.Fields.Venues, venues);
		}
		public String getMDBookType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDBookType));
		}
		public void setMDBookType(String mDBookType){
			this.put(MessageDictionary.Fields.MDBookType, mDBookType);
		}
		public String getSubscriptionRequestType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SubscriptionRequestType));
		}
		public void setSubscriptionRequestType(String subscriptionRequestType){
			this.put(MessageDictionary.Fields.SubscriptionRequestType, subscriptionRequestType);
		}
		public String getClOrdID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ClOrdID));
		}
		public void setClOrdID(String clOrdID){
			this.put(MessageDictionary.Fields.ClOrdID, clOrdID);
		}
		public String getMDReqID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDReqID));
		}
		public void setMDReqID(String mDReqID){
			this.put(MessageDictionary.Fields.MDReqID, mDReqID);
		}
		public String getMarketIndicator(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MarketIndicator));
		}
		public void setMarketIndicator(String marketIndicator){
			this.put(MessageDictionary.Fields.MarketIndicator, marketIndicator);
		}
		public String getSecurityID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SecurityID));
		}
		public void setSecurityID(String securityID){
			this.put(MessageDictionary.Fields.SecurityID, securityID);
		}
		public String getSymbol(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Symbol));
		}
		public void setSymbol(String symbol){
			this.put(MessageDictionary.Fields.Symbol, symbol);
		}
		public String getQuoteStatus(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuoteStatus));
		}
		public void setQuoteStatus(String quoteStatus){
			this.put(MessageDictionary.Fields.QuoteStatus, quoteStatus);
		}
		public String getSettlType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SettlType));
		}
		public void setSettlType(String settlType){
			this.put(MessageDictionary.Fields.SettlType, settlType);
		}
		public String getSide(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Side));
		}
		public void setSide(String side){
			this.put(MessageDictionary.Fields.Side, side);
		}
		public String getOrderQty(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrderQty));
		}
		public void setOrderQty(String orderQty){
			this.put(MessageDictionary.Fields.OrderQty, orderQty);
		}
		public String getDeliveryType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.DeliveryType));
		}
		public void setDeliveryType(String deliveryType){
			this.put(MessageDictionary.Fields.DeliveryType, deliveryType);
		}
		public String getClearingMethod(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ClearingMethod));
		}
		public void setClearingMethod(String clearingMethod){
			this.put(MessageDictionary.Fields.ClearingMethod, clearingMethod);
		}
		public String getValidUntilTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ValidUntilTime));
		}
		public void setValidUntilTime(String validUntilTime){
			this.put(MessageDictionary.Fields.ValidUntilTime, validUntilTime);
		}
		public String getTransactTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TransactTime));
		}
		public void setTransactTime(String transactTime){
			this.put(MessageDictionary.Fields.TransactTime, transactTime);
		}
		public String getPartyID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PartyID));
		}
		public void setPartyID(String partyID){
			this.put(MessageDictionary.Fields.PartyID, partyID);
		}
		public String getPartySubID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PartySubID));
		}
		public void setPartySubID(String partySubID){
			this.put(MessageDictionary.Fields.PartySubID, partySubID);
		}
		public String getMarkerID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MarkerID));
		}
		public void setMarkerID(String markerID){
			this.put(MessageDictionary.Fields.MarkerID, markerID);
		}
		public String getInfo1(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Info1));
		}
		public void setInfo1(String info1){
			this.put(MessageDictionary.Fields.Info1, info1);
		}
		public String getInfo2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Info2));
		}
		public void setInfo2(String info2){
			this.put(MessageDictionary.Fields.Info2, info2);
		}
		public String getInfo3(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Info3));
		}
		public void setInfo3(String info3){
			this.put(MessageDictionary.Fields.Info3, info3);
		}
		public String getInfo4(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Info4));
		}
		public void setInfo4(String info4){
			this.put(MessageDictionary.Fields.Info4, info4);
		}
		public String getInfo5(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Info5));
		}
		public void setInfo5(String info5){
			this.put(MessageDictionary.Fields.Info5, info5);
		}
	public MarketDataRequest Clone()
	{
		MarketDataRequest t=new MarketDataRequest();
		t.getStandardHeader().setMsgType(getStandardHeader().getMsgType());		 
		t.setVenueTypeGW(getVenueTypeGW());
		t.setVenues(getVenues());
		t.setMDBookType(getMDBookType());
		t.setSubscriptionRequestType(getSubscriptionRequestType());
		t.setClOrdID(getClOrdID());
		t.setMDReqID(getMDReqID());
		t.setMarketIndicator(getMarketIndicator());
		t.setSecurityID(getSecurityID());
		t.setSymbol(getSymbol());
		t.setQuoteStatus(getQuoteStatus());
		t.setSettlType(getSettlType());
		t.setSide(getSide());
		t.setOrderQty(getOrderQty());
		t.setDeliveryType(getDeliveryType());
		t.setClearingMethod(getClearingMethod());
		t.setValidUntilTime(getValidUntilTime());
		t.setTransactTime(getTransactTime());
		t.setPartyID(getPartyID());
		t.setPartySubID(getPartySubID());
		t.setMarkerID(getMarkerID());
		t.setInfo1(getInfo1());
		t.setInfo2(getInfo2());
		t.setInfo3(getInfo3());
		t.setInfo4(getInfo4());
		t.setInfo5(getInfo5());
		return t;
	}

}

