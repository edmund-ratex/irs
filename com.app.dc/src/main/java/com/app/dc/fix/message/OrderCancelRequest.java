	
package com.app.dc.fix.message;


import com.app.dc.fix.*;  
import com.app.dc.fix.MessageDictionary; 
 
import java.util.concurrent.CopyOnWriteArrayList;


public class OrderCancelRequest extends FixMessage{
		public OrderCancelRequest()
		{		 
			this.getStandardHeader().setMsgType("F");
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
		public String getMarketIndicator(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MarketIndicator));
		}
		public void setMarketIndicator(String marketIndicator){
			this.put(MessageDictionary.Fields.MarketIndicator, marketIndicator);
		}
		public String getClOrdID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ClOrdID));
		}
		public void setClOrdID(String clOrdID){
			this.put(MessageDictionary.Fields.ClOrdID, clOrdID);
		}
		public String getOrigClOrdID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrigClOrdID));
		}
		public void setOrigClOrdID(String origClOrdID){
			this.put(MessageDictionary.Fields.OrigClOrdID, origClOrdID);
		}
		public String getOrderID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrderID));
		}
		public void setOrderID(String orderID){
			this.put(MessageDictionary.Fields.OrderID, orderID);
		}
		public String getQuoteID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuoteID));
		}
		public void setQuoteID(String quoteID){
			this.put(MessageDictionary.Fields.QuoteID, quoteID);
		}
		public String getQuoteType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuoteType));
		}
		public void setQuoteType(String quoteType){
			this.put(MessageDictionary.Fields.QuoteType, quoteType);
		}
		public String getAlgoName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.AlgoName));
		}
		public void setAlgoName(String algoName){
			this.put(MessageDictionary.Fields.AlgoName, algoName);
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
		public String getSide(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Side));
		}
		public void setSide(String side){
			this.put(MessageDictionary.Fields.Side, side);
		}
		public String getPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Price));
		}
		public void setPrice(String price){
			this.put(MessageDictionary.Fields.Price, price);
		}
		public String getPrice2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Price2));
		}
		public void setPrice2(String price2){
			this.put(MessageDictionary.Fields.Price2, price2);
		}
		public String getNetPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.NetPrice));
		}
		public void setNetPrice(String netPrice){
			this.put(MessageDictionary.Fields.NetPrice, netPrice);
		}
		public String getNetPrice2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.NetPrice2));
		}
		public void setNetPrice2(String netPrice2){
			this.put(MessageDictionary.Fields.NetPrice2, netPrice2);
		}
		public String getYield(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Yield));
		}
		public void setYield(String yield){
			this.put(MessageDictionary.Fields.Yield, yield);
		}
		public String getYield2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Yield2));
		}
		public void setYield2(String yield2){
			this.put(MessageDictionary.Fields.Yield2, yield2);
		}
		public String getOrderQty(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrderQty));
		}
		public void setOrderQty(String orderQty){
			this.put(MessageDictionary.Fields.OrderQty, orderQty);
		}
		public String getOrderQty2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrderQty2));
		}
		public void setOrderQty2(String orderQty2){
			this.put(MessageDictionary.Fields.OrderQty2, orderQty2);
		}
		public String getPartyID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PartyID));
		}
		public void setPartyID(String partyID){
			this.put(MessageDictionary.Fields.PartyID, partyID);
		}
		public String getPartyName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PartyName));
		}
		public void setPartyName(String partyName){
			this.put(MessageDictionary.Fields.PartyName, partyName);
		}
		public String getPartySubID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PartySubID));
		}
		public void setPartySubID(String partySubID){
			this.put(MessageDictionary.Fields.PartySubID, partySubID);
		}
		public String getPartySubID2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PartySubID2));
		}
		public void setPartySubID2(String partySubID2){
			this.put(MessageDictionary.Fields.PartySubID2, partySubID2);
		}
		public String getPartySubName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PartySubName));
		}
		public void setPartySubName(String partySubName){
			this.put(MessageDictionary.Fields.PartySubName, partySubName);
		}
		public String getUserID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.UserID));
		}
		public void setUserID(String userID){
			this.put(MessageDictionary.Fields.UserID, userID);
		}
		public String getUserName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.UserName));
		}
		public void setUserName(String userName){
			this.put(MessageDictionary.Fields.UserName, userName);
		}
		public String getName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Name));
		}
		public void setName(String name){
			this.put(MessageDictionary.Fields.Name, name);
		}
		public String getBizType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.BizType));
		}
		public void setBizType(String bizType){
			this.put(MessageDictionary.Fields.BizType, bizType);
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
		public String getLocation(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Location));
		}
		public void setLocation(String location){
			this.put(MessageDictionary.Fields.Location, location);
		}
	public OrderCancelRequest Clone()
	{
		OrderCancelRequest t=new OrderCancelRequest();
		t.getStandardHeader().setMsgType(getStandardHeader().getMsgType());		 
		t.setVenueTypeGW(getVenueTypeGW());
		t.setVenues(getVenues());
		t.setMarketIndicator(getMarketIndicator());
		t.setClOrdID(getClOrdID());
		t.setOrigClOrdID(getOrigClOrdID());
		t.setOrderID(getOrderID());
		t.setQuoteID(getQuoteID());
		t.setQuoteType(getQuoteType());
		t.setAlgoName(getAlgoName());
		t.setValidUntilTime(getValidUntilTime());
		t.setTransactTime(getTransactTime());
		t.setSecurityID(getSecurityID());
		t.setSymbol(getSymbol());
		t.setSide(getSide());
		t.setPrice(getPrice());
		t.setPrice2(getPrice2());
		t.setNetPrice(getNetPrice());
		t.setNetPrice2(getNetPrice2());
		t.setYield(getYield());
		t.setYield2(getYield2());
		t.setOrderQty(getOrderQty());
		t.setOrderQty2(getOrderQty2());
		t.setPartyID(getPartyID());
		t.setPartyName(getPartyName());
		t.setPartySubID(getPartySubID());
		t.setPartySubID2(getPartySubID2());
		t.setPartySubName(getPartySubName());
		t.setUserID(getUserID());
		t.setUserName(getUserName());
		t.setName(getName());
		t.setBizType(getBizType());
		t.setInfo1(getInfo1());
		t.setInfo2(getInfo2());
		t.setInfo3(getInfo3());
		t.setInfo4(getInfo4());
		t.setInfo5(getInfo5());
		t.setLocation(getLocation());
		return t;
	}

}
