	
package com.app.dc.fix.message;


import com.app.dc.fix.*;  
import com.app.dc.fix.MessageDictionary; 
import java.util.List;
import java.util.ArrayList;

public class ExecutionAcknowledgement extends FixMessage{
		public ExecutionAcknowledgement()
		{		 
			this.getStandardHeader().setMsgType("BN");
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
		public String getClOrdID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ClOrdID));
		}
		public void setClOrdID(String clOrdID){
			this.put(MessageDictionary.Fields.ClOrdID, clOrdID);
		}
		public String getExecAckStatus(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ExecAckStatus));
		}
		public void setExecAckStatus(String execAckStatus){
			this.put(MessageDictionary.Fields.ExecAckStatus, execAckStatus);
		}
		public String getExecID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ExecID));
		}
		public void setExecID(String execID){
			this.put(MessageDictionary.Fields.ExecID, execID);
		}
	public ExecutionAcknowledgement Clone()
	{
		ExecutionAcknowledgement t=new ExecutionAcknowledgement();
		t.getStandardHeader().setMsgType(getStandardHeader().getMsgType());		 
		t.setVenueTypeGW(getVenueTypeGW());
		t.setVenues(getVenues());
		t.setClOrdID(getClOrdID());
		t.setExecAckStatus(getExecAckStatus());
		t.setExecID(getExecID());
		return t;
	}

}

