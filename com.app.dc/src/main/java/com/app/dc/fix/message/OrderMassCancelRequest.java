	
package com.app.dc.fix.message;

import com.app.dc.fix.*;  
import com.app.dc.fix.MessageDictionary; 
import java.util.List;
import java.util.ArrayList;


public class OrderMassCancelRequest extends FixMessage{
		public OrderMassCancelRequest()
		{		 
			this.getStandardHeader().setMsgType("q");
		}
		public String getMarketIndicator(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MarketIndicator));
		}
		public void setMarketIndicator(String marketIndicator){
			this.put(MessageDictionary.Fields.MarketIndicator, marketIndicator);
		}
		public String getMassCancelRequestType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MassCancelRequestType));
		}
		public void setMassCancelRequestType(String massCancelRequestType){
			this.put(MessageDictionary.Fields.MassCancelRequestType, massCancelRequestType);
		}
		public String getAlgoName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.AlgoName));
		}
		public void setAlgoName(String algoName){
			this.put(MessageDictionary.Fields.AlgoName, algoName);
		}
		public String getClOrdID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ClOrdID));
		}
		public void setClOrdID(String clOrdID){
			this.put(MessageDictionary.Fields.ClOrdID, clOrdID);
		}
		public String getSecurityID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SecurityID));
		}
		public void setSecurityID(String securityID){
			this.put(MessageDictionary.Fields.SecurityID, securityID);
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
		public String getQuantID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuantID));
		}
		public void setQuantID(String quantID){
			this.put(MessageDictionary.Fields.QuantID, quantID);
		}
		public String getText(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Text));
		}
		public void setText(String text){
			this.put(MessageDictionary.Fields.Text, text);
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
	public OrderMassCancelRequest Clone()
	{
		OrderMassCancelRequest t=new OrderMassCancelRequest();
		t.getStandardHeader().setMsgType(getStandardHeader().getMsgType());		 
		t.setMarketIndicator(getMarketIndicator());
		t.setMassCancelRequestType(getMassCancelRequestType());
		t.setAlgoName(getAlgoName());
		t.setClOrdID(getClOrdID());
		t.setSecurityID(getSecurityID());
		t.setUserID(getUserID());
		t.setUserName(getUserName());
		t.setQuantID(getQuantID());
		t.setText(getText());
		t.setVenueTypeGW(getVenueTypeGW());
		t.setVenues(getVenues());
		return t;
	}

}

