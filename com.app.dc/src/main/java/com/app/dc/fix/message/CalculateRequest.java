	
package com.app.dc.fix.message;


import com.app.dc.fix.*;  
import com.app.dc.fix.MessageDictionary; 
import java.util.List;
import java.util.ArrayList;


public class CalculateRequest extends FixMessage{
		public CalculateRequest()
		{		 
			this.getStandardHeader().setMsgType("U201");
		}
		public String getCalculateRequestType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CalculateRequestType));
		}
		public void setCalculateRequestType(String calculateRequestType){
			this.put(MessageDictionary.Fields.CalculateRequestType, calculateRequestType);
		}
		public String getCalculateRequestID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CalculateRequestID));
		}
		public void setCalculateRequestID(String calculateRequestID){
			this.put(MessageDictionary.Fields.CalculateRequestID, calculateRequestID);
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
		public String getSettlDate(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SettlDate));
		}
		public void setSettlDate(String settlDate){
			this.put(MessageDictionary.Fields.SettlDate, settlDate);
		}
		public String getSettlType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SettlType));
		}
		public void setSettlType(String settlType){
			this.put(MessageDictionary.Fields.SettlType, settlType);
		}
		public String getSettlCurrency(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SettlCurrency));
		}
		public void setSettlCurrency(String settlCurrency){
			this.put(MessageDictionary.Fields.SettlCurrency, settlCurrency);
		}
		public String getNetPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.NetPrice));
		}
		public void setNetPrice(String netPrice){
			this.put(MessageDictionary.Fields.NetPrice, netPrice);
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
		public String getTradeDate(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TradeDate));
		}
		public void setTradeDate(String tradeDate){
			this.put(MessageDictionary.Fields.TradeDate, tradeDate);
		}
		public String getSettlCurrFxRate(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SettlCurrFxRate));
		}
		public void setSettlCurrFxRate(String settlCurrFxRate){
			this.put(MessageDictionary.Fields.SettlCurrFxRate, settlCurrFxRate);
		}
		public String getAccruedInterestAmt(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.AccruedInterestAmt));
		}
		public void setAccruedInterestAmt(String accruedInterestAmt){
			this.put(MessageDictionary.Fields.AccruedInterestAmt, accruedInterestAmt);
		}
	public CalculateRequest Clone()
	{
		CalculateRequest t=new CalculateRequest();
		t.getStandardHeader().setMsgType(getStandardHeader().getMsgType());		 
		t.setCalculateRequestType(getCalculateRequestType());
		t.setCalculateRequestID(getCalculateRequestID());
		t.setMarketIndicator(getMarketIndicator());
		t.setSecurityID(getSecurityID());
		t.setSettlDate(getSettlDate());
		t.setSettlType(getSettlType());
		t.setSettlCurrency(getSettlCurrency());
		t.setNetPrice(getNetPrice());
		t.setYield(getYield());
		t.setYield2(getYield2());
		t.setTradeDate(getTradeDate());
		t.setSettlCurrFxRate(getSettlCurrFxRate());
		t.setAccruedInterestAmt(getAccruedInterestAmt());
		return t;
	}

}

