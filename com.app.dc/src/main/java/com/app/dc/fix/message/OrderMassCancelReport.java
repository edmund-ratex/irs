	
package com.app.dc.fix.message;


import java.util.concurrent.CopyOnWriteArrayList;

import com.app.dc.fix.BaseMessage;
import com.app.dc.fix.DataTypeConverter;
import com.app.dc.fix.FixMessage;
import com.app.dc.fix.MessageDictionary;


public class OrderMassCancelReport extends FixMessage{
		public OrderMassCancelReport()
		{		 
			this.getStandardHeader().setMsgType("r");
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
		public String getMassCancelRequestType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MassCancelRequestType));
		}
		public void setMassCancelRequestType(String massCancelRequestType){
			this.put(MessageDictionary.Fields.MassCancelRequestType, massCancelRequestType);
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
		public String getMassCancelResponse(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MassCancelResponse));
		}
		public void setMassCancelResponse(String massCancelResponse){
			this.put(MessageDictionary.Fields.MassCancelResponse, massCancelResponse);
		}
		public String getMassCancelRejectReason(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MassCancelRejectReason));
		}
		public void setMassCancelRejectReason(String massCancelRejectReason){
			this.put(MessageDictionary.Fields.MassCancelRejectReason, massCancelRejectReason);
		}
		public int getTotalAffectedOrders(){
			return DataTypeConverter.Utils.toInt(this.get(MessageDictionary.Fields.TotalAffectedOrders));
		}
		public void setTotalAffectedOrders(int totalAffectedOrders){
			this.put(MessageDictionary.Fields.TotalAffectedOrders, totalAffectedOrders);
		}
		public String getLocation(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Location));
		}
		public void setLocation(String location){
			this.put(MessageDictionary.Fields.Location, location);
		}

	private final String GROUP_AffectedOrdGrp=MessageDictionary.Fields.AffectedOrdGrp;
	public CopyOnWriteArrayList<GAffectedOrdGrp> getAffectedOrdGrp(){
			return (CopyOnWriteArrayList<GAffectedOrdGrp>) this.get(GROUP_AffectedOrdGrp);
		}
	public void setAffectedOrdGrp(CopyOnWriteArrayList<GAffectedOrdGrp> affectedOrdGrp){
			this.put(GROUP_AffectedOrdGrp,affectedOrdGrp);
		}
	public static class GAffectedOrdGrp extends BaseMessage{
		public String getOrderID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrderID));
		}
		public void setOrderID(String orderID){
			this.put(MessageDictionary.Fields.OrderID, orderID);
		}

	public GAffectedOrdGrp Clone()
	{
		GAffectedOrdGrp t=new  GAffectedOrdGrp();
				t.setOrderID(getOrderID());
		return t;
	}
	}
	

	public OrderMassCancelReport Clone()
	{
		OrderMassCancelReport t=new OrderMassCancelReport();
		t.getStandardHeader().setMsgType(getStandardHeader().getMsgType());		 
		t.setMarketIndicator(getMarketIndicator());
		t.setClOrdID(getClOrdID());
		t.setMassCancelRequestType(getMassCancelRequestType());
		t.setVenueTypeGW(getVenueTypeGW());
		t.setVenues(getVenues());
		t.setMassCancelResponse(getMassCancelResponse());
		t.setMassCancelRejectReason(getMassCancelRejectReason());
		t.setTotalAffectedOrders(getTotalAffectedOrders());
		t.setLocation(getLocation());
		
		 CopyOnWriteArrayList<GAffectedOrdGrp> gAffectedOrdGrplt=getAffectedOrdGrp();
		 if(gAffectedOrdGrplt!=null)
		 {
			t.setAffectedOrdGrp(new CopyOnWriteArrayList<GAffectedOrdGrp>());
			for (GAffectedOrdGrp g : gAffectedOrdGrplt) {
				GAffectedOrdGrp gAffectedOrdGrp=g.Clone();		 
				t.getAffectedOrdGrp().add(gAffectedOrdGrp);
			}
		}
		return t;
	}

}
