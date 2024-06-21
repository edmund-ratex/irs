	
package com.app.dc.fix.message;


import com.app.dc.fix.*;  
import com.app.dc.fix.MessageDictionary; 
import java.util.List;
import java.util.ArrayList;


public class AccountBalanceReport extends FixMessage{
		public AccountBalanceReport()
		{		 
			this.getStandardHeader().setMsgType("AB");
		}
		public String getUserID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.UserID));
		}
		public void setUserID(String userID){
			this.put(MessageDictionary.Fields.UserID, userID);
		}
		public String getBalance(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Balance));
		}
		public void setBalance(String balance){
			this.put(MessageDictionary.Fields.Balance, balance);
		}
		public String getUsedMargin(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.UsedMargin));
		}
		public void setUsedMargin(String usedMargin){
			this.put(MessageDictionary.Fields.UsedMargin, usedMargin);
		}
		public String getWDBalance(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.WDBalance));
		}
		public void setWDBalance(String wDBalance){
			this.put(MessageDictionary.Fields.WDBalance, wDBalance);
		}
		public String getDayRealizedPnl(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.DayRealizedPnl));
		}
		public void setDayRealizedPnl(String dayRealizedPnl){
			this.put(MessageDictionary.Fields.DayRealizedPnl, dayRealizedPnl);
		}
		public String getHoldRealizedPnl(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.HoldRealizedPnl));
		}
		public void setHoldRealizedPnl(String holdRealizedPnl){
			this.put(MessageDictionary.Fields.HoldRealizedPnl, holdRealizedPnl);
		}
		public String getDayCommission(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.DayCommission));
		}
		public void setDayCommission(String dayCommission){
			this.put(MessageDictionary.Fields.DayCommission, dayCommission);
		}
		public String getFreezedMargin(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.FreezedMargin));
		}
		public void setFreezedMargin(String freezedMargin){
			this.put(MessageDictionary.Fields.FreezedMargin, freezedMargin);
		}
		public String getFreezedCommission(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.FreezedCommission));
		}
		public void setFreezedCommission(String freezedCommission){
			this.put(MessageDictionary.Fields.FreezedCommission, freezedCommission);
		}
		public String getFreezedBalance(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.FreezedBalance));
		}
		public void setFreezedBalance(String freezedBalance){
			this.put(MessageDictionary.Fields.FreezedBalance, freezedBalance);
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
		public String getAccountStatus(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.AccountStatus));
		}
		public void setAccountStatus(String accountStatus){
			this.put(MessageDictionary.Fields.AccountStatus, accountStatus);
		}
	public AccountBalanceReport Clone()
	{
		AccountBalanceReport t=new AccountBalanceReport();
		t.getStandardHeader().setMsgType(getStandardHeader().getMsgType());		 
		t.setUserID(getUserID());
		t.setBalance(getBalance());
		t.setUsedMargin(getUsedMargin());
		t.setWDBalance(getWDBalance());
		t.setDayRealizedPnl(getDayRealizedPnl());
		t.setHoldRealizedPnl(getHoldRealizedPnl());
		t.setDayCommission(getDayCommission());
		t.setFreezedMargin(getFreezedMargin());
		t.setFreezedCommission(getFreezedCommission());
		t.setFreezedBalance(getFreezedBalance());
		t.setInfo1(getInfo1());
		t.setInfo2(getInfo2());
		t.setInfo3(getInfo3());
		t.setInfo4(getInfo4());
		t.setInfo5(getInfo5());
		t.setLocation(getLocation());
		t.setDemo(getDemo());
		t.setAccountStatus(getAccountStatus());
		return t;
	}

}

