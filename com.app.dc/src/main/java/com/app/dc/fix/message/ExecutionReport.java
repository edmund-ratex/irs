	
package com.app.dc.fix.message;


import com.app.dc.fix.*;  
import com.app.dc.fix.MessageDictionary; 
import java.util.List;
import java.util.ArrayList;


public class ExecutionReport extends FixMessage{
		public ExecutionReport()
		{		 
			this.getStandardHeader().setMsgType("8");
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
		public String getAlgoName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.AlgoName));
		}
		public void setAlgoName(String algoName){
			this.put(MessageDictionary.Fields.AlgoName, algoName);
		}
		public String getStrategyOrderId(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.StrategyOrderId));
		}
		public void setStrategyOrderId(String strategyOrderId){
			this.put(MessageDictionary.Fields.StrategyOrderId, strategyOrderId);
		}
		public String getStrategyOrderVenues(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.StrategyOrderVenues));
		}
		public void setStrategyOrderVenues(String strategyOrderVenues){
			this.put(MessageDictionary.Fields.StrategyOrderVenues, strategyOrderVenues);
		}
		public String getMarketIndicator(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MarketIndicator));
		}
		public void setMarketIndicator(String marketIndicator){
			this.put(MessageDictionary.Fields.MarketIndicator, marketIndicator);
		}
		public String getTerminal(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Terminal));
		}
		public void setTerminal(String terminal){
			this.put(MessageDictionary.Fields.Terminal, terminal);
		}
		public String getOrdStatus(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrdStatus));
		}
		public void setOrdStatus(String ordStatus){
			this.put(MessageDictionary.Fields.OrdStatus, ordStatus);
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
		public String getQuoteID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuoteID));
		}
		public void setQuoteID(String quoteID){
			this.put(MessageDictionary.Fields.QuoteID, quoteID);
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
		public String getDcsexecID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.DcsexecID));
		}
		public void setDcsexecID(String dcsexecID){
			this.put(MessageDictionary.Fields.DcsexecID, dcsexecID);
		}
		public String getQuoteType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuoteType));
		}
		public void setQuoteType(String quoteType){
			this.put(MessageDictionary.Fields.QuoteType, quoteType);
		}
		public String getMDReqID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDReqID));
		}
		public void setMDReqID(String mDReqID){
			this.put(MessageDictionary.Fields.MDReqID, mDReqID);
		}
		public String getQuoteTransType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuoteTransType));
		}
		public void setQuoteTransType(String quoteTransType){
			this.put(MessageDictionary.Fields.QuoteTransType, quoteTransType);
		}
		public String getQuoteStatusDesc(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuoteStatusDesc));
		}
		public void setQuoteStatusDesc(String quoteStatusDesc){
			this.put(MessageDictionary.Fields.QuoteStatusDesc, quoteStatusDesc);
		}
		public String getOrdStatusReqID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrdStatusReqID));
		}
		public void setOrdStatusReqID(String ordStatusReqID){
			this.put(MessageDictionary.Fields.OrdStatusReqID, ordStatusReqID);
		}
		public String getExecAckStatus(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ExecAckStatus));
		}
		public void setExecAckStatus(String execAckStatus){
			this.put(MessageDictionary.Fields.ExecAckStatus, execAckStatus);
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
		public String getTransactTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TransactTime));
		}
		public void setTransactTime(String transactTime){
			this.put(MessageDictionary.Fields.TransactTime, transactTime);
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
		public String getOrdType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrdType));
		}
		public void setOrdType(String ordType){
			this.put(MessageDictionary.Fields.OrdType, ordType);
		}
		public String getTimeInForce(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TimeInForce));
		}
		public void setTimeInForce(String timeInForce){
			this.put(MessageDictionary.Fields.TimeInForce, timeInForce);
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
		public String getLeavesQty(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.LeavesQty));
		}
		public void setLeavesQty(String leavesQty){
			this.put(MessageDictionary.Fields.LeavesQty, leavesQty);
		}
		public String getLeavesQty2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.LeavesQty2));
		}
		public void setLeavesQty2(String leavesQty2){
			this.put(MessageDictionary.Fields.LeavesQty2, leavesQty2);
		}
		public String getCumQty(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CumQty));
		}
		public void setCumQty(String cumQty){
			this.put(MessageDictionary.Fields.CumQty, cumQty);
		}
		public String getCumQty2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CumQty2));
		}
		public void setCumQty2(String cumQty2){
			this.put(MessageDictionary.Fields.CumQty2, cumQty2);
		}
		public String getOrdStatus2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrdStatus2));
		}
		public void setOrdStatus2(String ordStatus2){
			this.put(MessageDictionary.Fields.OrdStatus2, ordStatus2);
		}
		public String getTradeDate(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TradeDate));
		}
		public void setTradeDate(String tradeDate){
			this.put(MessageDictionary.Fields.TradeDate, tradeDate);
		}
		public String getTradeTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TradeTime));
		}
		public void setTradeTime(String tradeTime){
			this.put(MessageDictionary.Fields.TradeTime, tradeTime);
		}
		public String getSettlDate(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SettlDate));
		}
		public void setSettlDate(String settlDate){
			this.put(MessageDictionary.Fields.SettlDate, settlDate);
		}
		public String getSettlType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SettlType));
		}
		public void setSettlType(String settlType){
			this.put(MessageDictionary.Fields.SettlType, settlType);
		}
		public String getClearingMethod(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ClearingMethod));
		}
		public void setClearingMethod(String clearingMethod){
			this.put(MessageDictionary.Fields.ClearingMethod, clearingMethod);
		}
		public String getDeliveryType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.DeliveryType));
		}
		public void setDeliveryType(String deliveryType){
			this.put(MessageDictionary.Fields.DeliveryType, deliveryType);
		}
		public String getExecID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ExecID));
		}
		public void setExecID(String execID){
			this.put(MessageDictionary.Fields.ExecID, execID);
		}
		public String getExecType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ExecType));
		}
		public void setExecType(String execType){
			this.put(MessageDictionary.Fields.ExecType, execType);
		}
		public String getExecType2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ExecType2));
		}
		public void setExecType2(String execType2){
			this.put(MessageDictionary.Fields.ExecType2, execType2);
		}
		public String getLastPx(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.LastPx));
		}
		public void setLastPx(String lastPx){
			this.put(MessageDictionary.Fields.LastPx, lastPx);
		}
		public String getLastQty(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.LastQty));
		}
		public void setLastQty(String lastQty){
			this.put(MessageDictionary.Fields.LastQty, lastQty);
		}
		public String getLastPx2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.LastPx2));
		}
		public void setLastPx2(String lastPx2){
			this.put(MessageDictionary.Fields.LastPx2, lastPx2);
		}
		public String getLastQty2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.LastQty2));
		}
		public void setLastQty2(String lastQty2){
			this.put(MessageDictionary.Fields.LastQty2, lastQty2);
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
		public String getPartyCode(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PartyCode));
		}
		public void setPartyCode(String partyCode){
			this.put(MessageDictionary.Fields.PartyCode, partyCode);
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
		public String getCounterPartyFullName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CounterPartyFullName));
		}
		public void setCounterPartyFullName(String counterPartyFullName){
			this.put(MessageDictionary.Fields.CounterPartyFullName, counterPartyFullName);
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
		public String getCounterPartyOrdID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CounterPartyOrdID));
		}
		public void setCounterPartyOrdID(String counterPartyOrdID){
			this.put(MessageDictionary.Fields.CounterPartyOrdID, counterPartyOrdID);
		}
		public String getCounterPartyCode(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CounterPartyCode));
		}
		public void setCounterPartyCode(String counterPartyCode){
			this.put(MessageDictionary.Fields.CounterPartyCode, counterPartyCode);
		}
		public String getDirtyPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.DirtyPrice));
		}
		public void setDirtyPrice(String dirtyPrice){
			this.put(MessageDictionary.Fields.DirtyPrice, dirtyPrice);
		}
		public String getOpenQty(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OpenQty));
		}
		public void setOpenQty(String openQty){
			this.put(MessageDictionary.Fields.OpenQty, openQty);
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
		public String getAvgPx(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.AvgPx));
		}
		public void setAvgPx(String avgPx){
			this.put(MessageDictionary.Fields.AvgPx, avgPx);
		}
		public String getAvgPx2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.AvgPx2));
		}
		public void setAvgPx2(String avgPx2){
			this.put(MessageDictionary.Fields.AvgPx2, avgPx2);
		}
		public String getRefOrderID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.RefOrderID));
		}
		public void setRefOrderID(String refOrderID){
			this.put(MessageDictionary.Fields.RefOrderID, refOrderID);
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
		public String getDealType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.DealType));
		}
		public void setDealType(String dealType){
			this.put(MessageDictionary.Fields.DealType, dealType);
		}
		public String getCrossVenues(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CrossVenues));
		}
		public void setCrossVenues(String crossVenues){
			this.put(MessageDictionary.Fields.CrossVenues, crossVenues);
		}
		public String getAllocID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.AllocID));
		}
		public void setAllocID(String allocID){
			this.put(MessageDictionary.Fields.AllocID, allocID);
		}
		public String getUpdateTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.UpdateTime));
		}
		public void setUpdateTime(String updateTime){
			this.put(MessageDictionary.Fields.UpdateTime, updateTime);
		}
		public String getUpdateUser(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.UpdateUser));
		}
		public void setUpdateUser(String updateUser){
			this.put(MessageDictionary.Fields.UpdateUser, updateUser);
		}
		public String getSynchFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SynchFlag));
		}
		public void setSynchFlag(String synchFlag){
			this.put(MessageDictionary.Fields.SynchFlag, synchFlag);
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
		public String getShowTrader(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ShowTrader));
		}
		public void setShowTrader(String showTrader){
			this.put(MessageDictionary.Fields.ShowTrader, showTrader);
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
		public String getBrokerExecID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.BrokerExecID));
		}
		public void setBrokerExecID(String brokerExecID){
			this.put(MessageDictionary.Fields.BrokerExecID, brokerExecID);
		}
		public String getAppointedID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.AppointedID));
		}
		public void setAppointedID(String appointedID){
			this.put(MessageDictionary.Fields.AppointedID, appointedID);
		}
		public String getInitiatorID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.InitiatorID));
		}
		public void setInitiatorID(String initiatorID){
			this.put(MessageDictionary.Fields.InitiatorID, initiatorID);
		}
		public String getInitiatorSubID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.InitiatorSubID));
		}
		public void setInitiatorSubID(String initiatorSubID){
			this.put(MessageDictionary.Fields.InitiatorSubID, initiatorSubID);
		}
		public String getBridgecounterPartyFullName(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.BridgecounterPartyFullName));
		}
		public void setBridgecounterPartyFullName(String bridgecounterPartyFullName){
			this.put(MessageDictionary.Fields.BridgecounterPartyFullName, bridgecounterPartyFullName);
		}
		public String getUpdateFiled(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.UpdateFiled));
		}
		public void setUpdateFiled(String updateFiled){
			this.put(MessageDictionary.Fields.UpdateFiled, updateFiled);
		}
		public String getTransType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TransType));
		}
		public void setTransType(String transType){
			this.put(MessageDictionary.Fields.TransType, transType);
		}
		public String getRoleType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.RoleType));
		}
		public void setRoleType(String roleType){
			this.put(MessageDictionary.Fields.RoleType, roleType);
		}
		public String getPartyOrderSource(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PartyOrderSource));
		}
		public void setPartyOrderSource(String partyOrderSource){
			this.put(MessageDictionary.Fields.PartyOrderSource, partyOrderSource);
		}
		public String getCounterPartyOrderSource(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CounterPartyOrderSource));
		}
		public void setCounterPartyOrderSource(String counterPartyOrderSource){
			this.put(MessageDictionary.Fields.CounterPartyOrderSource, counterPartyOrderSource);
		}
		public String getRecordNum(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.RecordNum));
		}
		public void setRecordNum(String recordNum){
			this.put(MessageDictionary.Fields.RecordNum, recordNum);
		}
		public String getSendingTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SendingTime));
		}
		public void setSendingTime(String sendingTime){
			this.put(MessageDictionary.Fields.SendingTime, sendingTime);
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
		public String getHandlInst(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.HandlInst));
		}
		public void setHandlInst(String handlInst){
			this.put(MessageDictionary.Fields.HandlInst, handlInst);
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
		public String getCounterPartyVenues(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CounterPartyVenues));
		}
		public void setCounterPartyVenues(String counterPartyVenues){
			this.put(MessageDictionary.Fields.CounterPartyVenues, counterPartyVenues);
		}
		public String getParentFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ParentFlag));
		}
		public void setParentFlag(String parentFlag){
			this.put(MessageDictionary.Fields.ParentFlag, parentFlag);
		}
		public String getParentFlagQty(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ParentFlagQty));
		}
		public void setParentFlagQty(String parentFlagQty){
			this.put(MessageDictionary.Fields.ParentFlagQty, parentFlagQty);
		}
		public String getActionFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ActionFlag));
		}
		public void setActionFlag(String actionFlag){
			this.put(MessageDictionary.Fields.ActionFlag, actionFlag);
		}
		public String getExecTypeReason(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ExecTypeReason));
		}
		public void setExecTypeReason(String execTypeReason){
			this.put(MessageDictionary.Fields.ExecTypeReason, execTypeReason);
		}
		public String getTradeMethod(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TradeMethod));
		}
		public void setTradeMethod(String tradeMethod){
			this.put(MessageDictionary.Fields.TradeMethod, tradeMethod);
		}
		public String getSecurityType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SecurityType));
		}
		public void setSecurityType(String securityType){
			this.put(MessageDictionary.Fields.SecurityType, securityType);
		}
		public String getA1(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.A1));
		}
		public void setA1(String a1){
			this.put(MessageDictionary.Fields.A1, a1);
		}
		public String getB1(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.B1));
		}
		public void setB1(String b1){
			this.put(MessageDictionary.Fields.B1, b1);
		}
		public String getA0(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.A0));
		}
		public void setA0(String a0){
			this.put(MessageDictionary.Fields.A0, a0);
		}
		public String getB2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.B2));
		}
		public void setB2(String b2){
			this.put(MessageDictionary.Fields.B2, b2);
		}
		public String getM5b1(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.M5b1));
		}
		public void setM5b1(String m5b1){
			this.put(MessageDictionary.Fields.M5b1, m5b1);
		}
		public String getM5a1(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.M5a1));
		}
		public void setM5a1(String m5a1){
			this.put(MessageDictionary.Fields.M5a1, m5a1);
		}
		public String getA2(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.A2));
		}
		public void setA2(String a2){
			this.put(MessageDictionary.Fields.A2, a2);
		}
		public String getB0(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.B0));
		}
		public void setB0(String b0){
			this.put(MessageDictionary.Fields.B0, b0);
		}
		public String getVs(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Vs));
		}
		public void setVs(String vs){
			this.put(MessageDictionary.Fields.Vs, vs);
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
		public String getInfo7(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Info7));
		}
		public void setInfo7(String info7){
			this.put(MessageDictionary.Fields.Info7, info7);
		}
		public String getInfo8(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Info8));
		}
		public void setInfo8(String info8){
			this.put(MessageDictionary.Fields.Info8, info8);
		}
		public String getInfo9(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Info9));
		}
		public void setInfo9(String info9){
			this.put(MessageDictionary.Fields.Info9, info9);
		}
		public String getInfo10(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Info10));
		}
		public void setInfo10(String info10){
			this.put(MessageDictionary.Fields.Info10, info10);
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
		public String getExerciseFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ExerciseFlag));
		}
		public void setExerciseFlag(String exerciseFlag){
			this.put(MessageDictionary.Fields.ExerciseFlag, exerciseFlag);
		}
		public String getPriceType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PriceType));
		}
		public void setPriceType(String priceType){
			this.put(MessageDictionary.Fields.PriceType, priceType);
		}
		public String getSSDetect(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SSDetect));
		}
		public void setSSDetect(String sSDetect){
			this.put(MessageDictionary.Fields.SSDetect, sSDetect);
		}
		public String getQuoteReqID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuoteReqID));
		}
		public void setQuoteReqID(String quoteReqID){
			this.put(MessageDictionary.Fields.QuoteReqID, quoteReqID);
		}
		public String getQuoteStatus(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuoteStatus));
		}
		public void setQuoteStatus(String quoteStatus){
			this.put(MessageDictionary.Fields.QuoteStatus, quoteStatus);
		}
		public String getFee(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Fee));
		}
		public void setFee(String fee){
			this.put(MessageDictionary.Fields.Fee, fee);
		}
		public String getRealizedPnl(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.RealizedPnl));
		}
		public void setRealizedPnl(String realizedPnl){
			this.put(MessageDictionary.Fields.RealizedPnl, realizedPnl);
		}
		public String getMaker(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Maker));
		}
		public void setMaker(String maker){
			this.put(MessageDictionary.Fields.Maker, maker);
		}
		public String getTrigger(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Trigger));
		}
		public void setTrigger(String trigger){
			this.put(MessageDictionary.Fields.Trigger, trigger);
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
	public ExecutionReport Clone()
	{
		ExecutionReport t=new ExecutionReport();
		t.getStandardHeader().setMsgType(getStandardHeader().getMsgType());		 
		t.setVenueTypeGW(getVenueTypeGW());
		t.setVenues(getVenues());
		t.setAlgoName(getAlgoName());
		t.setStrategyOrderId(getStrategyOrderId());
		t.setStrategyOrderVenues(getStrategyOrderVenues());
		t.setMarketIndicator(getMarketIndicator());
		t.setTerminal(getTerminal());
		t.setOrdStatus(getOrdStatus());
		t.setClOrdID(getClOrdID());
		t.setOrderID(getOrderID());
		t.setQuoteID(getQuoteID());
		t.setBookID(getBookID());
		t.setQuantID(getQuantID());
		t.setOCType(getOCType());
		t.setDcsexecID(getDcsexecID());
		t.setQuoteType(getQuoteType());
		t.setMDReqID(getMDReqID());
		t.setQuoteTransType(getQuoteTransType());
		t.setQuoteStatusDesc(getQuoteStatusDesc());
		t.setOrdStatusReqID(getOrdStatusReqID());
		t.setExecAckStatus(getExecAckStatus());
		t.setOrdRejReason(getOrdRejReason());
		t.setRejectText(getRejectText());
		t.setTransactTime(getTransactTime());
		t.setSecurityID(getSecurityID());
		t.setSymbol(getSymbol());
		t.setValidUntilTime(getValidUntilTime());
		t.setFirmToNonTime(getFirmToNonTime());
		t.setOrdType(getOrdType());
		t.setTimeInForce(getTimeInForce());
		t.setSide(getSide());
		t.setPrice(getPrice());
		t.setPrice2(getPrice2());
		t.setNetPrice(getNetPrice());
		t.setNetPrice2(getNetPrice2());
		t.setYield(getYield());
		t.setYield2(getYield2());
		t.setOrderQty(getOrderQty());
		t.setOrderQty2(getOrderQty2());
		t.setLeavesQty(getLeavesQty());
		t.setLeavesQty2(getLeavesQty2());
		t.setCumQty(getCumQty());
		t.setCumQty2(getCumQty2());
		t.setOrdStatus2(getOrdStatus2());
		t.setTradeDate(getTradeDate());
		t.setTradeTime(getTradeTime());
		t.setSettlDate(getSettlDate());
		t.setSettlType(getSettlType());
		t.setClearingMethod(getClearingMethod());
		t.setDeliveryType(getDeliveryType());
		t.setExecID(getExecID());
		t.setExecType(getExecType());
		t.setExecType2(getExecType2());
		t.setLastPx(getLastPx());
		t.setLastQty(getLastQty());
		t.setLastPx2(getLastPx2());
		t.setLastQty2(getLastQty2());
		t.setPartyID(getPartyID());
		t.setPartyName(getPartyName());
		t.setPartySubID(getPartySubID());
		t.setPartySubID2(getPartySubID2());
		t.setPartySubName(getPartySubName());
		t.setPartyCode(getPartyCode());
		t.setCounterPartyID(getCounterPartyID());
		t.setCounterPartyName(getCounterPartyName());
		t.setCounterPartyFullName(getCounterPartyFullName());
		t.setCounterPartySubID(getCounterPartySubID());
		t.setCounterPartySubName(getCounterPartySubName());
		t.setCounterPartyOrdID(getCounterPartyOrdID());
		t.setCounterPartyCode(getCounterPartyCode());
		t.setDirtyPrice(getDirtyPrice());
		t.setOpenQty(getOpenQty());
		t.setUserID(getUserID());
		t.setUserName(getUserName());
		t.setName(getName());
		t.setBizType(getBizType());
		t.setBizTypeID(getBizTypeID());
		t.setAvgPx(getAvgPx());
		t.setAvgPx2(getAvgPx2());
		t.setRefOrderID(getRefOrderID());
		t.setFirmFlag(getFirmFlag());
		t.setWholeFlag(getWholeFlag());
		t.setVenuesSelectF(getVenuesSelectF());
		t.setVenuesRdmNum(getVenuesRdmNum());
		t.setSelfSelectVenues(getSelfSelectVenues());
		t.setBlockNum(getBlockNum());
		t.setDealType(getDealType());
		t.setCrossVenues(getCrossVenues());
		t.setAllocID(getAllocID());
		t.setUpdateTime(getUpdateTime());
		t.setUpdateUser(getUpdateUser());
		t.setSynchFlag(getSynchFlag());
		t.setOCOFlag(getOCOFlag());
		t.setInstitutionID(getInstitutionID());
		t.setInstitutionName(getInstitutionName());
		t.setTraderID(getTraderID());
		t.setTraderName(getTraderName());
		t.setShowTrader(getShowTrader());
		t.setRoleName(getRoleName());
		t.setRoleID(getRoleID());
		t.setSalesAgentFlag(getSalesAgentFlag());
		t.setCounterpartySalesAgentFlag(getCounterpartySalesAgentFlag());
		t.setOrigOrdPrice(getOrigOrdPrice());
		t.setMacID(getMacID());
		t.setPublishIP(getPublishIP());
		t.setInsideIP(getInsideIP());
		t.setHardDiskID(getHardDiskID());
		t.setBrokerExecID(getBrokerExecID());
		t.setAppointedID(getAppointedID());
		t.setInitiatorID(getInitiatorID());
		t.setInitiatorSubID(getInitiatorSubID());
		t.setBridgecounterPartyFullName(getBridgecounterPartyFullName());
		t.setUpdateFiled(getUpdateFiled());
		t.setTransType(getTransType());
		t.setRoleType(getRoleType());
		t.setPartyOrderSource(getPartyOrderSource());
		t.setCounterPartyOrderSource(getCounterPartyOrderSource());
		t.setRecordNum(getRecordNum());
		t.setSendingTime(getSendingTime());
		t.setIocTimeOut(getIocTimeOut());
		t.setIocPrice(getIocPrice());
		t.setHandlInst(getHandlInst());
		t.setIocVenueTypeGW(getIocVenueTypeGW());
		t.setIocVenues(getIocVenues());
		t.setCounterPartyVenues(getCounterPartyVenues());
		t.setParentFlag(getParentFlag());
		t.setParentFlagQty(getParentFlagQty());
		t.setActionFlag(getActionFlag());
		t.setExecTypeReason(getExecTypeReason());
		t.setTradeMethod(getTradeMethod());
		t.setSecurityType(getSecurityType());
		t.setA1(getA1());
		t.setB1(getB1());
		t.setA0(getA0());
		t.setB2(getB2());
		t.setM5b1(getM5b1());
		t.setM5a1(getM5a1());
		t.setA2(getA2());
		t.setB0(getB0());
		t.setVs(getVs());
		t.setInfo1(getInfo1());
		t.setInfo2(getInfo2());
		t.setInfo3(getInfo3());
		t.setInfo4(getInfo4());
		t.setInfo5(getInfo5());
		t.setInfo6(getInfo6());
		t.setInfo7(getInfo7());
		t.setInfo8(getInfo8());
		t.setInfo9(getInfo9());
		t.setInfo10(getInfo10());
		t.setTrigEvent(getTrigEvent());
		t.setCancEvent(getCancEvent());
		t.setInValidFlag(getInValidFlag());
		t.setEREventName(getEREventName());
		t.setCancEventName(getCancEventName());
		t.setMaxFloor(getMaxFloor());
		t.setIntentionID(getIntentionID());
		t.setOffsetFlag(getOffsetFlag());
		t.setHedgeFlag(getHedgeFlag());
		t.setExerciseFlag(getExerciseFlag());
		t.setPriceType(getPriceType());
		t.setSSDetect(getSSDetect());
		t.setQuoteReqID(getQuoteReqID());
		t.setQuoteStatus(getQuoteStatus());
		t.setFee(getFee());
		t.setRealizedPnl(getRealizedPnl());
		t.setMaker(getMaker());
		t.setTrigger(getTrigger());
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
