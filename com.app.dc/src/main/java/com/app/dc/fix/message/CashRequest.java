	
package com.app.dc.fix.message;


import com.app.dc.fix.*;  
import com.app.dc.fix.MessageDictionary; 
import java.util.List;
import java.util.ArrayList;

public class CashRequest extends FixMessage{
		public CashRequest()
		{		 
			this.getStandardHeader().setMsgType("C");
		}
		public String getUserID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.UserID));
		}
		public void setUserID(String userID){
			this.put(MessageDictionary.Fields.UserID, userID);
		}
		public String getType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Type));
		}
		public void setType(String type){
			this.put(MessageDictionary.Fields.Type, type);
		}
		public String getAmount(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Amount));
		}
		public void setAmount(String amount){
			this.put(MessageDictionary.Fields.Amount, amount);
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
		public String getDemo(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Demo));
		}
		public void setDemo(String demo){
			this.put(MessageDictionary.Fields.Demo, demo);
		}
	public CashRequest Clone()
	{
		CashRequest t=new CashRequest();
		t.getStandardHeader().setMsgType(getStandardHeader().getMsgType());		 
		t.setUserID(getUserID());
		t.setType(getType());
		t.setAmount(getAmount());
		t.setInfo1(getInfo1());
		t.setInfo2(getInfo2());
		t.setInfo3(getInfo3());
		t.setInfo4(getInfo4());
		t.setInfo5(getInfo5());
		t.setLocation(getLocation());
		t.setDemo(getDemo());
		return t;
	}

}

