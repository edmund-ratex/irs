	
package com.app.dc.fix.message;


import com.app.dc.fix.*;  
import com.app.dc.fix.MessageDictionary; 
import java.util.List;
import java.util.ArrayList;


public class OrderCancelReject extends FixMessage{
		public OrderCancelReject()
		{		 
			this.getStandardHeader().setMsgType("9");
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
		public String getOrderID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrderID));
		}
		public void setOrderID(String orderID){
			this.put(MessageDictionary.Fields.OrderID, orderID);
		}
		public String getCxlRejReason(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CxlRejReason));
		}
		public void setCxlRejReason(String cxlRejReason){
			this.put(MessageDictionary.Fields.CxlRejReason, cxlRejReason);
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
	public OrderCancelReject Clone()
	{
		OrderCancelReject t=new OrderCancelReject();
		t.getStandardHeader().setMsgType(getStandardHeader().getMsgType());		 
		t.setVenueTypeGW(getVenueTypeGW());
		t.setVenues(getVenues());
		t.setMarketIndicator(getMarketIndicator());
		t.setClOrdID(getClOrdID());
		t.setOrderID(getOrderID());
		t.setCxlRejReason(getCxlRejReason());
		t.setRejectText(getRejectText());
		t.setTransactTime(getTransactTime());
		t.setInfo1(getInfo1());
		t.setInfo2(getInfo2());
		t.setInfo3(getInfo3());
		t.setInfo4(getInfo4());
		t.setInfo5(getInfo5());
		t.setLocation(getLocation());
		return t;
	}

}

