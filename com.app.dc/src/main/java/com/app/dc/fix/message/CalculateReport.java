	
package com.app.dc.fix.message;


import com.app.dc.fix.*;  
import com.app.dc.fix.MessageDictionary; 
import java.util.List;
import java.util.ArrayList;


public class CalculateReport extends FixMessage{
		public CalculateReport()
		{		 
			this.getStandardHeader().setMsgType("U202");
		}
		public String getCalculateReportID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CalculateReportID));
		}
		public void setCalculateReportID(String calculateReportID){
			this.put(MessageDictionary.Fields.CalculateReportID, calculateReportID);
		}
		public String getOrdRejReason(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrdRejReason));
		}
		public void setOrdRejReason(String ordRejReason){
			this.put(MessageDictionary.Fields.OrdRejReason, ordRejReason);
		}
		public String getRejectText(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.RejectText));
		}
		public void setRejectText(String rejectText){
			this.put(MessageDictionary.Fields.RejectText, rejectText);
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
		public String getAccruedInterestAmt(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.AccruedInterestAmt));
		}
		public void setAccruedInterestAmt(String accruedInterestAmt){
			this.put(MessageDictionary.Fields.AccruedInterestAmt, accruedInterestAmt);
		}
	public CalculateReport Clone()
	{
		CalculateReport t=new CalculateReport();
		t.getStandardHeader().setMsgType(getStandardHeader().getMsgType());		 
		t.setCalculateReportID(getCalculateReportID());
		t.setOrdRejReason(getOrdRejReason());
		t.setRejectText(getRejectText());
		t.setSecurityID(getSecurityID());
		t.setSettlDate(getSettlDate());
		t.setNetPrice(getNetPrice());
		t.setYield(getYield());
		t.setYield2(getYield2());
		t.setAccruedInterestAmt(getAccruedInterestAmt());
		return t;
	}

}

