	
package com.app.dc.fix.message;


import com.app.dc.fix.*;  
import com.app.dc.fix.MessageDictionary; 
import java.util.List;
import java.util.ArrayList;



public class PositionReport extends FixMessage{
		public PositionReport()
		{		 
			this.getStandardHeader().setMsgType("P");
		}
		public String getUserID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.UserID));
		}
		public void setUserID(String userID){
			this.put(MessageDictionary.Fields.UserID, userID);
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
		public String getLongPosition(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.LongPosition));
		}
		public void setLongPosition(String longPosition){
			this.put(MessageDictionary.Fields.LongPosition, longPosition);
		}
		public String getLongAverage(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.LongAverage));
		}
		public void setLongAverage(String longAverage){
			this.put(MessageDictionary.Fields.LongAverage, longAverage);
		}
		public String getLongUsedMargin(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.LongUsedMargin));
		}
		public void setLongUsedMargin(String longUsedMargin){
			this.put(MessageDictionary.Fields.LongUsedMargin, longUsedMargin);
		}
		public String getShortPosition(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ShortPosition));
		}
		public void setShortPosition(String shortPosition){
			this.put(MessageDictionary.Fields.ShortPosition, shortPosition);
		}
		public String getShortAverage(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ShortAverage));
		}
		public void setShortAverage(String shortAverage){
			this.put(MessageDictionary.Fields.ShortAverage, shortAverage);
		}
		public String getShortUsedMargin(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ShortUsedMargin));
		}
		public void setShortUsedMargin(String shortUsedMargin){
			this.put(MessageDictionary.Fields.ShortUsedMargin, shortUsedMargin);
		}
		public String getRealizedPnl(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.RealizedPnl));
		}
		public void setRealizedPnl(String realizedPnl){
			this.put(MessageDictionary.Fields.RealizedPnl, realizedPnl);
		}
		public String getLongLockedPosition(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.LongLockedPosition));
		}
		public void setLongLockedPosition(String longLockedPosition){
			this.put(MessageDictionary.Fields.LongLockedPosition, longLockedPosition);
		}
		public String getShortLockedPosition(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ShortLockedPosition));
		}
		public void setShortLockedPosition(String shortLockedPosition){
			this.put(MessageDictionary.Fields.ShortLockedPosition, shortLockedPosition);
		}
		public String getPositionType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PositionType));
		}
		public void setPositionType(String positionType){
			this.put(MessageDictionary.Fields.PositionType, positionType);
		}
		public String getLeverage(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Leverage));
		}
		public void setLeverage(String leverage){
			this.put(MessageDictionary.Fields.Leverage, leverage);
		}
		public String getLongLiqPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.LongLiqPrice));
		}
		public void setLongLiqPrice(String longLiqPrice){
			this.put(MessageDictionary.Fields.LongLiqPrice, longLiqPrice);
		}
		public String getShortLiqPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ShortLiqPrice));
		}
		public void setShortLiqPrice(String shortLiqPrice){
			this.put(MessageDictionary.Fields.ShortLiqPrice, shortLiqPrice);
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
		public String getPositionStatus(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PositionStatus));
		}
		public void setPositionStatus(String positionStatus){
			this.put(MessageDictionary.Fields.PositionStatus, positionStatus);
		}
	public PositionReport Clone()
	{
		PositionReport t=new PositionReport();
		t.getStandardHeader().setMsgType(getStandardHeader().getMsgType());		 
		t.setUserID(getUserID());
		t.setSecurityID(getSecurityID());
		t.setSymbol(getSymbol());
		t.setLongPosition(getLongPosition());
		t.setLongAverage(getLongAverage());
		t.setLongUsedMargin(getLongUsedMargin());
		t.setShortPosition(getShortPosition());
		t.setShortAverage(getShortAverage());
		t.setShortUsedMargin(getShortUsedMargin());
		t.setRealizedPnl(getRealizedPnl());
		t.setLongLockedPosition(getLongLockedPosition());
		t.setShortLockedPosition(getShortLockedPosition());
		t.setPositionType(getPositionType());
		t.setLeverage(getLeverage());
		t.setLongLiqPrice(getLongLiqPrice());
		t.setShortLiqPrice(getShortLiqPrice());
		t.setInfo1(getInfo1());
		t.setInfo2(getInfo2());
		t.setInfo3(getInfo3());
		t.setInfo4(getInfo4());
		t.setInfo5(getInfo5());
		t.setLocation(getLocation());
		t.setDemo(getDemo());
		t.setPositionStatus(getPositionStatus());
		return t;
	}

}
