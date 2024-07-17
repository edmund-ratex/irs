	
package com.app.dc.fix.message;


import com.app.dc.fix.*;  
import com.app.dc.fix.MessageDictionary; 
import java.util.List;
import java.util.ArrayList;
/**
 *
 *2020.05.13: 创建. 模板插件(V1.3.6825.19619(build:20180908)) 模板版本(v1.0.1.2)<br/>
 */
public class MarketDataRequestReject extends FixMessage{
		public MarketDataRequestReject()
		{		 
			this.getStandardHeader().setMsgType("Y");
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
		public String getMDReqID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDReqID));
		}
		public void setMDReqID(String mDReqID){
			this.put(MessageDictionary.Fields.MDReqID, mDReqID);
		}
		public String getClOrdID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ClOrdID));
		}
		public void setClOrdID(String clOrdID){
			this.put(MessageDictionary.Fields.ClOrdID, clOrdID);
		}
		public String getMDBookType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDBookType));
		}
		public void setMDBookType(String mDBookType){
			this.put(MessageDictionary.Fields.MDBookType, mDBookType);
		}
		public String getMDReqRejReason(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDReqRejReason));
		}
		public void setMDReqRejReason(String mDReqRejReason){
			this.put(MessageDictionary.Fields.MDReqRejReason, mDReqRejReason);
		}
		public String getRejectText(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.RejectText));
		}
		public void setRejectText(String rejectText){
			this.put(MessageDictionary.Fields.RejectText, rejectText);
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
		public String getPartySubName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PartySubName));
		}
		public void setPartySubName(String partySubName){
			this.put(MessageDictionary.Fields.PartySubName, partySubName);
		}
		public String getCounterPartyID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CounterPartyID));
		}
		public void setCounterPartyID(String counterPartyID){
			this.put(MessageDictionary.Fields.CounterPartyID, counterPartyID);
		}
		public String getCounterPartyName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CounterPartyName));
		}
		public void setCounterPartyName(String counterPartyName){
			this.put(MessageDictionary.Fields.CounterPartyName, counterPartyName);
		}
		public String getCounterPartySubID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CounterPartySubID));
		}
		public void setCounterPartySubID(String counterPartySubID){
			this.put(MessageDictionary.Fields.CounterPartySubID, counterPartySubID);
		}
		public String getCounterPartySubName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CounterPartySubName));
		}
		public void setCounterPartySubName(String counterPartySubName){
			this.put(MessageDictionary.Fields.CounterPartySubName, counterPartySubName);
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
	public MarketDataRequestReject Clone()
	{
		MarketDataRequestReject t=new MarketDataRequestReject();
		t.getStandardHeader().setMsgType(getStandardHeader().getMsgType());		 
		t.setVenueTypeGW(getVenueTypeGW());
		t.setVenues(getVenues());
		t.setMDReqID(getMDReqID());
		t.setClOrdID(getClOrdID());
		t.setMDBookType(getMDBookType());
		t.setMDReqRejReason(getMDReqRejReason());
		t.setRejectText(getRejectText());
		t.setTransactTime(getTransactTime());
		t.setPartyID(getPartyID());
		t.setPartyName(getPartyName());
		t.setPartySubID(getPartySubID());
		t.setPartySubName(getPartySubName());
		t.setCounterPartyID(getCounterPartyID());
		t.setCounterPartyName(getCounterPartyName());
		t.setCounterPartySubID(getCounterPartySubID());
		t.setCounterPartySubName(getCounterPartySubName());
		t.setInfo1(getInfo1());
		t.setInfo2(getInfo2());
		t.setInfo3(getInfo3());
		t.setInfo4(getInfo4());
		t.setInfo5(getInfo5());
		return t;
	}

}

