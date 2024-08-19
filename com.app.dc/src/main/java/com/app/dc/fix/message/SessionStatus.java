	
package com.app.dc.fix.message;


import com.app.dc.fix.*;  
import com.app.dc.fix.MessageDictionary; 
import java.util.List;
import java.util.ArrayList;


public class SessionStatus extends FixMessage{
		public SessionStatus()
		{		 
			this.getStandardHeader().setMsgType("IC4");
		}
		public String getTargetSessionId(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TargetSessionId));
		}
		public void setTargetSessionId(String targetSessionId){
			this.put(MessageDictionary.Fields.TargetSessionId, targetSessionId);
		}
		public String getNetStatus(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.NetStatus));
		}
		public void setNetStatus(String netStatus){
			this.put(MessageDictionary.Fields.NetStatus, netStatus);
		}
		public String getNetLatency(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.NetLatency));
		}
		public void setNetLatency(String netLatency){
			this.put(MessageDictionary.Fields.NetLatency, netLatency);
		}
		public String getNetLatencyStatus(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.NetLatencyStatus));
		}
		public void setNetLatencyStatus(String netLatencyStatus){
			this.put(MessageDictionary.Fields.NetLatencyStatus, netLatencyStatus);
		}
		public String getMarketIndicator(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MarketIndicator));
		}
		public void setMarketIndicator(String marketIndicator){
			this.put(MessageDictionary.Fields.MarketIndicator, marketIndicator);
		}
		public String getTradSesStatus(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TradSesStatus));
		}
		public void setTradSesStatus(String tradSesStatus){
			this.put(MessageDictionary.Fields.TradSesStatus, tradSesStatus);
		}
		public String getTradSesStartTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TradSesStartTime));
		}
		public void setTradSesStartTime(String tradSesStartTime){
			this.put(MessageDictionary.Fields.TradSesStartTime, tradSesStartTime);
		}
		public String getTradSesCloseTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TradSesCloseTime));
		}
		public void setTradSesCloseTime(String tradSesCloseTime){
			this.put(MessageDictionary.Fields.TradSesCloseTime, tradSesCloseTime);
		}
		public String getTradSesOpenTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TradSesOpenTime));
		}
		public void setTradSesOpenTime(String tradSesOpenTime){
			this.put(MessageDictionary.Fields.TradSesOpenTime, tradSesOpenTime);
		}
		public String getTradSesEndTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TradSesEndTime));
		}
		public void setTradSesEndTime(String tradSesEndTime){
			this.put(MessageDictionary.Fields.TradSesEndTime, tradSesEndTime);
		}
		public String getVenueTypeGW(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.VenueTypeGW));
		}
		public void setVenueTypeGW(String venueTypeGW){
			this.put(MessageDictionary.Fields.VenueTypeGW, venueTypeGW);
		}
		public String getText(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Text));
		}
		public void setText(String text){
			this.put(MessageDictionary.Fields.Text, text);
		}
	public SessionStatus Clone()
	{
		SessionStatus t=new SessionStatus();
		t.getStandardHeader().setMsgType(getStandardHeader().getMsgType());		 
		t.setTargetSessionId(getTargetSessionId());
		t.setNetStatus(getNetStatus());
		t.setNetLatency(getNetLatency());
		t.setNetLatencyStatus(getNetLatencyStatus());
		t.setMarketIndicator(getMarketIndicator());
		t.setTradSesStatus(getTradSesStatus());
		t.setTradSesStartTime(getTradSesStartTime());
		t.setTradSesCloseTime(getTradSesCloseTime());
		t.setTradSesOpenTime(getTradSesOpenTime());
		t.setTradSesEndTime(getTradSesEndTime());
		t.setVenueTypeGW(getVenueTypeGW());
		t.setText(getText());
		return t;
	}

}

