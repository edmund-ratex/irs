	
package com.app.dc.fix.message;

import com.app.dc.fix.*;  
import com.app.dc.fix.MessageDictionary; 
import java.util.List;
import java.util.ArrayList;



public class NewOrderSingle extends FixMessage{
		public NewOrderSingle()
		{		 
			this.getStandardHeader().setMsgType("D");
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
		public String getStrategyOrderVenues(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.StrategyOrderVenues));
		}
		public void setStrategyOrderVenues(String strategyOrderVenues){
			this.put(MessageDictionary.Fields.StrategyOrderVenues, strategyOrderVenues);
		}
		public String getStrategyOrderId(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.StrategyOrderId));
		}
		public void setStrategyOrderId(String strategyOrderId){
			this.put(MessageDictionary.Fields.StrategyOrderId, strategyOrderId);
		}
		public String getMarketIndicator(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MarketIndicator));
		}
		public void setMarketIndicator(String marketIndicator){
			this.put(MessageDictionary.Fields.MarketIndicator, marketIndicator);
		}
		public String getRefOrderID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.RefOrderID));
		}
		public void setRefOrderID(String refOrderID){
			this.put(MessageDictionary.Fields.RefOrderID, refOrderID);
		}
		public String getQuoteID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuoteID));
		}
		public void setQuoteID(String quoteID){
			this.put(MessageDictionary.Fields.QuoteID, quoteID);
		}
		public String getMDReqID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDReqID));
		}
		public void setMDReqID(String mDReqID){
			this.put(MessageDictionary.Fields.MDReqID, mDReqID);
		}
		public String getBookID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.BookID));
		}
		public void setBookID(String bookID){
			this.put(MessageDictionary.Fields.BookID, bookID);
		}
		public String getQuantID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuantID));
		}
		public void setQuantID(String quantID){
			this.put(MessageDictionary.Fields.QuantID, quantID);
		}
		public String getOCType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OCType));
		}
		public void setOCType(String oCType){
			this.put(MessageDictionary.Fields.OCType, oCType);
		}
		public String getTerminal(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Terminal));
		}
		public void setTerminal(String terminal){
			this.put(MessageDictionary.Fields.Terminal, terminal);
		}
		public String getValidUntilTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ValidUntilTime));
		}
		public void setValidUntilTime(String validUntilTime){
			this.put(MessageDictionary.Fields.ValidUntilTime, validUntilTime);
		}
		public String getFirmToNonTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.FirmToNonTime));
		}
		public void setFirmToNonTime(String firmToNonTime){
			this.put(MessageDictionary.Fields.FirmToNonTime, firmToNonTime);
		}
		public String getTransactTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TransactTime));
		}
		public void setTransactTime(String transactTime){
			this.put(MessageDictionary.Fields.TransactTime, transactTime);
		}
		public String getOrdType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrdType));
		}
		public void setOrdType(String ordType){
			this.put(MessageDictionary.Fields.OrdType, ordType);
		}
		public String getQuoteType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuoteType));
		}
		public void setQuoteType(String quoteType){
			this.put(MessageDictionary.Fields.QuoteType, quoteType);
		}
		public String getQuoteTransType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuoteTransType));
		}
		public void setQuoteTransType(String quoteTransType){
			this.put(MessageDictionary.Fields.QuoteTransType, quoteTransType);
		}
		public String getOrdStatus(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrdStatus));
		}
		public void setOrdStatus(String ordStatus){
			this.put(MessageDictionary.Fields.OrdStatus, ordStatus);
		}
		public String getTimeInForce(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TimeInForce));
		}
		public void setTimeInForce(String timeInForce){
			this.put(MessageDictionary.Fields.TimeInForce, timeInForce);
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
		public String getSymbol(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Symbol));
		}
		public void setSymbol(String symbol){
			this.put(MessageDictionary.Fields.Symbol, symbol);
		}
		public String getSecurityType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SecurityType));
		}
		public void setSecurityType(String securityType){
			this.put(MessageDictionary.Fields.SecurityType, securityType);
		}
		public String getSide(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Side));
		}
		public void setSide(String side){
			this.put(MessageDictionary.Fields.Side, side);
		}
		public String getPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Price));
		}
		public void setPrice(String price){
			this.put(MessageDictionary.Fields.Price, price);
		}
		public String getPrice2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Price2));
		}
		public void setPrice2(String price2){
			this.put(MessageDictionary.Fields.Price2, price2);
		}
		public String getNetPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.NetPrice));
		}
		public void setNetPrice(String netPrice){
			this.put(MessageDictionary.Fields.NetPrice, netPrice);
		}
		public String getNetPrice2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.NetPrice2));
		}
		public void setNetPrice2(String netPrice2){
			this.put(MessageDictionary.Fields.NetPrice2, netPrice2);
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
		public String getOrderQty(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrderQty));
		}
		public void setOrderQty(String orderQty){
			this.put(MessageDictionary.Fields.OrderQty, orderQty);
		}
		public String getOrderQty2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrderQty2));
		}
		public void setOrderQty2(String orderQty2){
			this.put(MessageDictionary.Fields.OrderQty2, orderQty2);
		}
		public String getClearingMethod(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ClearingMethod));
		}
		public void setClearingMethod(String clearingMethod){
			this.put(MessageDictionary.Fields.ClearingMethod, clearingMethod);
		}
		public String getSettlType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SettlType));
		}
		public void setSettlType(String settlType){
			this.put(MessageDictionary.Fields.SettlType, settlType);
		}
		public String getDeliveryType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.DeliveryType));
		}
		public void setDeliveryType(String deliveryType){
			this.put(MessageDictionary.Fields.DeliveryType, deliveryType);
		}
		public String getAlgoName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.AlgoName));
		}
		public void setAlgoName(String algoName){
			this.put(MessageDictionary.Fields.AlgoName, algoName);
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
		public String getPartySubID2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PartySubID2));
		}
		public void setPartySubID2(String partySubID2){
			this.put(MessageDictionary.Fields.PartySubID2, partySubID2);
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
		public String getName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Name));
		}
		public void setName(String name){
			this.put(MessageDictionary.Fields.Name, name);
		}
		public String getBizType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.BizType));
		}
		public void setBizType(String bizType){
			this.put(MessageDictionary.Fields.BizType, bizType);
		}
		public String getBizTypeID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.BizTypeID));
		}
		public void setBizTypeID(String bizTypeID){
			this.put(MessageDictionary.Fields.BizTypeID, bizTypeID);
		}
		public String getVenuesSelectF(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.VenuesSelectF));
		}
		public void setVenuesSelectF(String venuesSelectF){
			this.put(MessageDictionary.Fields.VenuesSelectF, venuesSelectF);
		}
		public String getVenuesRdmNum(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.VenuesRdmNum));
		}
		public void setVenuesRdmNum(String venuesRdmNum){
			this.put(MessageDictionary.Fields.VenuesRdmNum, venuesRdmNum);
		}
		public String getSelfSelectVenues(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SelfSelectVenues));
		}
		public void setSelfSelectVenues(String selfSelectVenues){
			this.put(MessageDictionary.Fields.SelfSelectVenues, selfSelectVenues);
		}
		public String getBlockNum(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.BlockNum));
		}
		public void setBlockNum(String blockNum){
			this.put(MessageDictionary.Fields.BlockNum, blockNum);
		}
		public String getFirmFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.FirmFlag));
		}
		public void setFirmFlag(String firmFlag){
			this.put(MessageDictionary.Fields.FirmFlag, firmFlag);
		}
		public String getWholeFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.WholeFlag));
		}
		public void setWholeFlag(String wholeFlag){
			this.put(MessageDictionary.Fields.WholeFlag, wholeFlag);
		}
		public String getCrossVenues(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CrossVenues));
		}
		public void setCrossVenues(String crossVenues){
			this.put(MessageDictionary.Fields.CrossVenues, crossVenues);
		}
		public String getRiskManualCk(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.RiskManualCk));
		}
		public void setRiskManualCk(String riskManualCk){
			this.put(MessageDictionary.Fields.RiskManualCk, riskManualCk);
		}
		public String getOCOFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OCOFlag));
		}
		public void setOCOFlag(String oCOFlag){
			this.put(MessageDictionary.Fields.OCOFlag, oCOFlag);
		}
		public String getInstitutionID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.InstitutionID));
		}
		public void setInstitutionID(String institutionID){
			this.put(MessageDictionary.Fields.InstitutionID, institutionID);
		}
		public String getInstitutionName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.InstitutionName));
		}
		public void setInstitutionName(String institutionName){
			this.put(MessageDictionary.Fields.InstitutionName, institutionName);
		}
		public String getTraderID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TraderID));
		}
		public void setTraderID(String traderID){
			this.put(MessageDictionary.Fields.TraderID, traderID);
		}
		public String getTraderName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TraderName));
		}
		public void setTraderName(String traderName){
			this.put(MessageDictionary.Fields.TraderName, traderName);
		}
		public String getRoleName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.RoleName));
		}
		public void setRoleName(String roleName){
			this.put(MessageDictionary.Fields.RoleName, roleName);
		}
		public String getRoleID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.RoleID));
		}
		public void setRoleID(String roleID){
			this.put(MessageDictionary.Fields.RoleID, roleID);
		}
		public String getShowTrader(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ShowTrader));
		}
		public void setShowTrader(String showTrader){
			this.put(MessageDictionary.Fields.ShowTrader, showTrader);
		}
		public String getSalesAgentFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SalesAgentFlag));
		}
		public void setSalesAgentFlag(String salesAgentFlag){
			this.put(MessageDictionary.Fields.SalesAgentFlag, salesAgentFlag);
		}
		public String getCounterpartySalesAgentFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CounterpartySalesAgentFlag));
		}
		public void setCounterpartySalesAgentFlag(String counterpartySalesAgentFlag){
			this.put(MessageDictionary.Fields.CounterpartySalesAgentFlag, counterpartySalesAgentFlag);
		}
		public String getOrigOrdPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrigOrdPrice));
		}
		public void setOrigOrdPrice(String origOrdPrice){
			this.put(MessageDictionary.Fields.OrigOrdPrice, origOrdPrice);
		}
		public String getMacID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MacID));
		}
		public void setMacID(String macID){
			this.put(MessageDictionary.Fields.MacID, macID);
		}
		public String getPublishIP(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PublishIP));
		}
		public void setPublishIP(String publishIP){
			this.put(MessageDictionary.Fields.PublishIP, publishIP);
		}
		public String getInsideIP(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.InsideIP));
		}
		public void setInsideIP(String insideIP){
			this.put(MessageDictionary.Fields.InsideIP, insideIP);
		}
		public String getHardDiskID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.HardDiskID));
		}
		public void setHardDiskID(String hardDiskID){
			this.put(MessageDictionary.Fields.HardDiskID, hardDiskID);
		}
		public String getIocTimeOut(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.IocTimeOut));
		}
		public void setIocTimeOut(String iocTimeOut){
			this.put(MessageDictionary.Fields.IocTimeOut, iocTimeOut);
		}
		public String getIocPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.IocPrice));
		}
		public void setIocPrice(String iocPrice){
			this.put(MessageDictionary.Fields.IocPrice, iocPrice);
		}
		public String getIocVenueTypeGW(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.IocVenueTypeGW));
		}
		public void setIocVenueTypeGW(String iocVenueTypeGW){
			this.put(MessageDictionary.Fields.IocVenueTypeGW, iocVenueTypeGW);
		}
		public String getIocVenues(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.IocVenues));
		}
		public void setIocVenues(String iocVenues){
			this.put(MessageDictionary.Fields.IocVenues, iocVenues);
		}
		public String getHandlInst(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.HandlInst));
		}
		public void setHandlInst(String handlInst){
			this.put(MessageDictionary.Fields.HandlInst, handlInst);
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
		public String getInfo6(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Info6));
		}
		public void setInfo6(String info6){
			this.put(MessageDictionary.Fields.Info6, info6);
		}
		public String getTrigEvent(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TrigEvent));
		}
		public void setTrigEvent(String trigEvent){
			this.put(MessageDictionary.Fields.TrigEvent, trigEvent);
		}
		public String getCancEvent(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CancEvent));
		}
		public void setCancEvent(String cancEvent){
			this.put(MessageDictionary.Fields.CancEvent, cancEvent);
		}
		public String getInValidFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.InValidFlag));
		}
		public void setInValidFlag(String inValidFlag){
			this.put(MessageDictionary.Fields.InValidFlag, inValidFlag);
		}
		public String getEREventName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.EREventName));
		}
		public void setEREventName(String eREventName){
			this.put(MessageDictionary.Fields.EREventName, eREventName);
		}
		public String getCancEventName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CancEventName));
		}
		public void setCancEventName(String cancEventName){
			this.put(MessageDictionary.Fields.CancEventName, cancEventName);
		}
		public String getMaxFloor(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MaxFloor));
		}
		public void setMaxFloor(String maxFloor){
			this.put(MessageDictionary.Fields.MaxFloor, maxFloor);
		}
		public String getIntentionID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.IntentionID));
		}
		public void setIntentionID(String intentionID){
			this.put(MessageDictionary.Fields.IntentionID, intentionID);
		}
		public String getOffsetFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OffsetFlag));
		}
		public void setOffsetFlag(String offsetFlag){
			this.put(MessageDictionary.Fields.OffsetFlag, offsetFlag);
		}
		public String getHedgeFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.HedgeFlag));
		}
		public void setHedgeFlag(String hedgeFlag){
			this.put(MessageDictionary.Fields.HedgeFlag, hedgeFlag);
		}
		public String getTriggerType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TriggerType));
		}
		public void setTriggerType(String triggerType){
			this.put(MessageDictionary.Fields.TriggerType, triggerType);
		}
		public String getTriggerPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TriggerPrice));
		}
		public void setTriggerPrice(String triggerPrice){
			this.put(MessageDictionary.Fields.TriggerPrice, triggerPrice);
		}
		public String getStopLossPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.StopLossPrice));
		}
		public void setStopLossPrice(String stopLossPrice){
			this.put(MessageDictionary.Fields.StopLossPrice, stopLossPrice);
		}
		public String getTakeProfitPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TakeProfitPrice));
		}
		public void setTakeProfitPrice(String takeProfitPrice){
			this.put(MessageDictionary.Fields.TakeProfitPrice, takeProfitPrice);
		}
		public String getCloseBy(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CloseBy));
		}
		public void setCloseBy(String closeBy){
			this.put(MessageDictionary.Fields.CloseBy, closeBy);
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
	public NewOrderSingle Clone()
	{
		NewOrderSingle t=new NewOrderSingle();
		t.getStandardHeader().setMsgType(getStandardHeader().getMsgType());		 
		t.setVenueTypeGW(getVenueTypeGW());
		t.setVenues(getVenues());
		t.setStrategyOrderVenues(getStrategyOrderVenues());
		t.setStrategyOrderId(getStrategyOrderId());
		t.setMarketIndicator(getMarketIndicator());
		t.setRefOrderID(getRefOrderID());
		t.setQuoteID(getQuoteID());
		t.setMDReqID(getMDReqID());
		t.setBookID(getBookID());
		t.setQuantID(getQuantID());
		t.setOCType(getOCType());
		t.setTerminal(getTerminal());
		t.setValidUntilTime(getValidUntilTime());
		t.setFirmToNonTime(getFirmToNonTime());
		t.setTransactTime(getTransactTime());
		t.setOrdType(getOrdType());
		t.setQuoteType(getQuoteType());
		t.setQuoteTransType(getQuoteTransType());
		t.setOrdStatus(getOrdStatus());
		t.setTimeInForce(getTimeInForce());
		t.setClOrdID(getClOrdID());
		t.setSecurityID(getSecurityID());
		t.setSymbol(getSymbol());
		t.setSecurityType(getSecurityType());
		t.setSide(getSide());
		t.setPrice(getPrice());
		t.setPrice2(getPrice2());
		t.setNetPrice(getNetPrice());
		t.setNetPrice2(getNetPrice2());
		t.setYield(getYield());
		t.setYield2(getYield2());
		t.setOrderQty(getOrderQty());
		t.setOrderQty2(getOrderQty2());
		t.setClearingMethod(getClearingMethod());
		t.setSettlType(getSettlType());
		t.setDeliveryType(getDeliveryType());
		t.setAlgoName(getAlgoName());
		t.setPartyID(getPartyID());
		t.setPartyName(getPartyName());
		t.setPartySubID(getPartySubID());
		t.setPartySubID2(getPartySubID2());
		t.setPartySubName(getPartySubName());
		t.setCounterPartyID(getCounterPartyID());
		t.setCounterPartyName(getCounterPartyName());
		t.setCounterPartySubID(getCounterPartySubID());
		t.setCounterPartySubName(getCounterPartySubName());
		t.setUserID(getUserID());
		t.setUserName(getUserName());
		t.setName(getName());
		t.setBizType(getBizType());
		t.setBizTypeID(getBizTypeID());
		t.setVenuesSelectF(getVenuesSelectF());
		t.setVenuesRdmNum(getVenuesRdmNum());
		t.setSelfSelectVenues(getSelfSelectVenues());
		t.setBlockNum(getBlockNum());
		t.setFirmFlag(getFirmFlag());
		t.setWholeFlag(getWholeFlag());
		t.setCrossVenues(getCrossVenues());
		t.setRiskManualCk(getRiskManualCk());
		t.setOCOFlag(getOCOFlag());
		t.setInstitutionID(getInstitutionID());
		t.setInstitutionName(getInstitutionName());
		t.setTraderID(getTraderID());
		t.setTraderName(getTraderName());
		t.setRoleName(getRoleName());
		t.setRoleID(getRoleID());
		t.setShowTrader(getShowTrader());
		t.setSalesAgentFlag(getSalesAgentFlag());
		t.setCounterpartySalesAgentFlag(getCounterpartySalesAgentFlag());
		t.setOrigOrdPrice(getOrigOrdPrice());
		t.setMacID(getMacID());
		t.setPublishIP(getPublishIP());
		t.setInsideIP(getInsideIP());
		t.setHardDiskID(getHardDiskID());
		t.setIocTimeOut(getIocTimeOut());
		t.setIocPrice(getIocPrice());
		t.setIocVenueTypeGW(getIocVenueTypeGW());
		t.setIocVenues(getIocVenues());
		t.setHandlInst(getHandlInst());
		t.setInfo1(getInfo1());
		t.setInfo2(getInfo2());
		t.setInfo3(getInfo3());
		t.setInfo4(getInfo4());
		t.setInfo5(getInfo5());
		t.setInfo6(getInfo6());
		t.setTrigEvent(getTrigEvent());
		t.setCancEvent(getCancEvent());
		t.setInValidFlag(getInValidFlag());
		t.setEREventName(getEREventName());
		t.setCancEventName(getCancEventName());
		t.setMaxFloor(getMaxFloor());
		t.setIntentionID(getIntentionID());
		t.setOffsetFlag(getOffsetFlag());
		t.setHedgeFlag(getHedgeFlag());
		t.setTriggerType(getTriggerType());
		t.setTriggerPrice(getTriggerPrice());
		t.setStopLossPrice(getStopLossPrice());
		t.setTakeProfitPrice(getTakeProfitPrice());
		t.setCloseBy(getCloseBy());
		t.setLocation(getLocation());
		t.setDemo(getDemo());
		return t;
	}

}
