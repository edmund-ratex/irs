
package com.app.dc.fix.message;

import java.util.concurrent.CopyOnWriteArrayList;

import com.app.dc.fix.BaseMessage;
import com.app.dc.fix.DataTypeConverter;
import com.app.dc.fix.FixMessage;
import com.app.dc.fix.MessageDictionary;

public class MarketDataSnapshotFullRefresh extends FixMessage{
		public MarketDataSnapshotFullRefresh()
		{		 
			this.getStandardHeader().setMsgType("W");
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
		public String getMDBookType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDBookType));
		}
		public void setMDBookType(String mDBookType){
			this.put(MessageDictionary.Fields.MDBookType, mDBookType);
		}
		public String getMDReqID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDReqID));
		}
		public void setMDReqID(String mDReqID){
			this.put(MessageDictionary.Fields.MDReqID, mDReqID);
		}
		public String getClOrdID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ClOrdID));
		}
		public void setClOrdID(String clOrdID){
			this.put(MessageDictionary.Fields.ClOrdID, clOrdID);
		}
		public String getMarketIndicator(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MarketIndicator));
		}
		public void setMarketIndicator(String marketIndicator){
			this.put(MessageDictionary.Fields.MarketIndicator, marketIndicator);
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
		public String getCCDCValuation(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CCDCValuation));
		}
		public void setCCDCValuation(String cCDCValuation){
			this.put(MessageDictionary.Fields.CCDCValuation, cCDCValuation);
		}
		public String getCCDCValuaDate(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CCDCValuaDate));
		}
		public void setCCDCValuaDate(String cCDCValuaDate){
			this.put(MessageDictionary.Fields.CCDCValuaDate, cCDCValuaDate);
		}
		public String getCCDCValuaType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CCDCValuaType));
		}
		public void setCCDCValuaType(String cCDCValuaType){
			this.put(MessageDictionary.Fields.CCDCValuaType, cCDCValuaType);
		}
		public String getSendingTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SendingTime));
		}
		public void setSendingTime(String sendingTime){
			this.put(MessageDictionary.Fields.SendingTime, sendingTime);
		}
		public String getLocalTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.LocalTime));
		}
		public void setLocalTime(String localTime){
			this.put(MessageDictionary.Fields.LocalTime, localTime);
		}
		public String getOpenPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OpenPrice));
		}
		public void setOpenPrice(String openPrice){
			this.put(MessageDictionary.Fields.OpenPrice, openPrice);
		}
		public String getHighPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.HighPrice));
		}
		public void setHighPrice(String highPrice){
			this.put(MessageDictionary.Fields.HighPrice, highPrice);
		}
		public String getLowPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.LowPrice));
		}
		public void setLowPrice(String lowPrice){
			this.put(MessageDictionary.Fields.LowPrice, lowPrice);
		}
		public String getLastPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.LastPrice));
		}
		public void setLastPrice(String lastPrice){
			this.put(MessageDictionary.Fields.LastPrice, lastPrice);
		}
		public String getClosePrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ClosePrice));
		}
		public void setClosePrice(String closePrice){
			this.put(MessageDictionary.Fields.ClosePrice, closePrice);
		}
		public String getPreClosePrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PreClosePrice));
		}
		public void setPreClosePrice(String preClosePrice){
			this.put(MessageDictionary.Fields.PreClosePrice, preClosePrice);
		}
		public String getSettlePrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SettlePrice));
		}
		public void setSettlePrice(String settlePrice){
			this.put(MessageDictionary.Fields.SettlePrice, settlePrice);
		}
		public String getPreSettlePrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PreSettlePrice));
		}
		public void setPreSettlePrice(String preSettlePrice){
			this.put(MessageDictionary.Fields.PreSettlePrice, preSettlePrice);
		}
		public String getOpenInterest(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OpenInterest));
		}
		public void setOpenInterest(String openInterest){
			this.put(MessageDictionary.Fields.OpenInterest, openInterest);
		}
		public String getPreOpenInterest(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PreOpenInterest));
		}
		public void setPreOpenInterest(String preOpenInterest){
			this.put(MessageDictionary.Fields.PreOpenInterest, preOpenInterest);
		}
		public String getTurnover(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Turnover));
		}
		public void setTurnover(String turnover){
			this.put(MessageDictionary.Fields.Turnover, turnover);
		}
		public String getVolume(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Volume));
		}
		public void setVolume(String volume){
			this.put(MessageDictionary.Fields.Volume, volume);
		}
		public String getUpperLimitPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.UpperLimitPrice));
		}
		public void setUpperLimitPrice(String upperLimitPrice){
			this.put(MessageDictionary.Fields.UpperLimitPrice, upperLimitPrice);
		}
		public String getLowerLimitPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.LowerLimitPrice));
		}
		public void setLowerLimitPrice(String lowerLimitPrice){
			this.put(MessageDictionary.Fields.LowerLimitPrice, lowerLimitPrice);
		}
		public String getTradingPhaseCode(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TradingPhaseCode));
		}
		public void setTradingPhaseCode(String tradingPhaseCode){
			this.put(MessageDictionary.Fields.TradingPhaseCode, tradingPhaseCode);
		}
		public String getTradingType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TradingType));
		}
		public void setTradingType(String tradingType){
			this.put(MessageDictionary.Fields.TradingType, tradingType);
		}
		public String getIndexPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.IndexPrice));
		}
		public void setIndexPrice(String indexPrice){
			this.put(MessageDictionary.Fields.IndexPrice, indexPrice);
		}
		public String getMarkPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MarkPrice));
		}
		public void setMarkPrice(String markPrice){
			this.put(MessageDictionary.Fields.MarkPrice, markPrice);
		}
		public String getFee(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Fee));
		}
		public void setFee(String fee){
			this.put(MessageDictionary.Fields.Fee, fee);
		}
		public String getLocation(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Location));
		}
		public void setLocation(String location){
			this.put(MessageDictionary.Fields.Location, location);
		}

	private final String GROUP_NoMDEntries=MessageDictionary.Fields.NoMDEntries;
	public CopyOnWriteArrayList<GNoMDEntries> getNoMDEntries(){
			return (CopyOnWriteArrayList<GNoMDEntries>) this.get(GROUP_NoMDEntries);
		}
	public void setNoMDEntries(CopyOnWriteArrayList<GNoMDEntries> noMDEntries){
			this.put(GROUP_NoMDEntries,noMDEntries);
		}
	public static class GNoMDEntries extends BaseMessage{
		public String getQuoteTransType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuoteTransType));
		}
		public void setQuoteTransType(String quoteTransType){
			this.put(MessageDictionary.Fields.QuoteTransType, quoteTransType);
		}
		public String getQuoteStatus(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuoteStatus));
		}
		public void setQuoteStatus(String quoteStatus){
			this.put(MessageDictionary.Fields.QuoteStatus, quoteStatus);
		}
		public String getFirmFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.FirmFlag));
		}
		public void setFirmFlag(String firmFlag){
			this.put(MessageDictionary.Fields.FirmFlag, firmFlag);
		}
		public String getMDEntryType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDEntryType));
		}
		public void setMDEntryType(String mDEntryType){
			this.put(MessageDictionary.Fields.MDEntryType, mDEntryType);
		}
		public String getMDPriceLevel(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDPriceLevel));
		}
		public void setMDPriceLevel(String mDPriceLevel){
			this.put(MessageDictionary.Fields.MDPriceLevel, mDPriceLevel);
		}
		public String getMDEntryID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDEntryID));
		}
		public void setMDEntryID(String mDEntryID){
			this.put(MessageDictionary.Fields.MDEntryID, mDEntryID);
		}
		public String getMDReqID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDReqID));
		}
		public void setMDReqID(String mDReqID){
			this.put(MessageDictionary.Fields.MDReqID, mDReqID);
		}
		public String getQuantID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.QuantID));
		}
		public void setQuantID(String quantID){
			this.put(MessageDictionary.Fields.QuantID, quantID);
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
		public String getMDEntryDate(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDEntryDate));
		}
		public void setMDEntryDate(String mDEntryDate){
			this.put(MessageDictionary.Fields.MDEntryDate, mDEntryDate);
		}
		public String getMDEntryTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDEntryTime));
		}
		public void setMDEntryTime(String mDEntryTime){
			this.put(MessageDictionary.Fields.MDEntryTime, mDEntryTime);
		}
		public String getMDEntryAmount(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDEntryAmount));
		}
		public void setMDEntryAmount(String mDEntryAmount){
			this.put(MessageDictionary.Fields.MDEntryAmount, mDEntryAmount);
		}
		public String getValidUntilTime(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.ValidUntilTime));
		}
		public void setValidUntilTime(String validUntilTime){
			this.put(MessageDictionary.Fields.ValidUntilTime, validUntilTime);
		}
		public String getNetPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.NetPrice));
		}
		public void setNetPrice(String netPrice){
			this.put(MessageDictionary.Fields.NetPrice, netPrice);
		}
		public String getMDEntryPx(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDEntryPx));
		}
		public void setMDEntryPx(String mDEntryPx){
			this.put(MessageDictionary.Fields.MDEntryPx, mDEntryPx);
		}
		public String getYield(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Yield));
		}
		public void setYield(String yield){
			this.put(MessageDictionary.Fields.Yield, yield);
		}
		public String getDirtyPrice(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.DirtyPrice));
		}
		public void setDirtyPrice(String dirtyPrice){
			this.put(MessageDictionary.Fields.DirtyPrice, dirtyPrice);
		}
		public String getUnMatchQty(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.UnMatchQty));
		}
		public void setUnMatchQty(String unMatchQty){
			this.put(MessageDictionary.Fields.UnMatchQty, unMatchQty);
		}
		public String getMDEntrySize(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MDEntrySize));
		}
		public void setMDEntrySize(String mDEntrySize){
			this.put(MessageDictionary.Fields.MDEntrySize, mDEntrySize);
		}
		public String getTradeVolume(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.TradeVolume));
		}
		public void setTradeVolume(String tradeVolume){
			this.put(MessageDictionary.Fields.TradeVolume, tradeVolume);
		}
		public String getSettlType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.SettlType));
		}
		public void setSettlType(String settlType){
			this.put(MessageDictionary.Fields.SettlType, settlType);
		}
		public String getOrigSettlType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OrigSettlType));
		}
		public void setOrigSettlType(String origSettlType){
			this.put(MessageDictionary.Fields.OrigSettlType, origSettlType);
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
		public String getInternalFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.InternalFlag));
		}
		public void setInternalFlag(String internalFlag){
			this.put(MessageDictionary.Fields.InternalFlag, internalFlag);
		}
		public String getBargainFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.BargainFlag));
		}
		public void setBargainFlag(String bargainFlag){
			this.put(MessageDictionary.Fields.BargainFlag, bargainFlag);
		}
		public String getOCOFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.OCOFlag));
		}
		public void setOCOFlag(String oCOFlag){
			this.put(MessageDictionary.Fields.OCOFlag, oCOFlag);
		}
		public String getText(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.Text));
		}
		public void setText(String text){
			this.put(MessageDictionary.Fields.Text, text);
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
		public String getBizType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.BizType));
		}
		public void setBizType(String bizType){
			this.put(MessageDictionary.Fields.BizType, bizType);
		}
		public String getWholeFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.WholeFlag));
		}
		public void setWholeFlag(String wholeFlag){
			this.put(MessageDictionary.Fields.WholeFlag, wholeFlag);
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
		public String getCalFlag(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.CalFlag));
		}
		public void setCalFlag(String calFlag){
			this.put(MessageDictionary.Fields.CalFlag, calFlag);
		}

	public GNoMDEntries Clone()
	{
		GNoMDEntries t=new  GNoMDEntries();
				t.setQuoteTransType(getQuoteTransType());
		t.setQuoteStatus(getQuoteStatus());
		t.setFirmFlag(getFirmFlag());
		t.setMDEntryType(getMDEntryType());
		t.setMDPriceLevel(getMDPriceLevel());
		t.setMDEntryID(getMDEntryID());
		t.setMDReqID(getMDReqID());
		t.setQuantID(getQuantID());
		t.setVenueTypeGW(getVenueTypeGW());
		t.setVenues(getVenues());
		t.setMDEntryDate(getMDEntryDate());
		t.setMDEntryTime(getMDEntryTime());
		t.setMDEntryAmount(getMDEntryAmount());
		t.setValidUntilTime(getValidUntilTime());
		t.setNetPrice(getNetPrice());
		t.setMDEntryPx(getMDEntryPx());
		t.setYield(getYield());
		t.setDirtyPrice(getDirtyPrice());
		t.setUnMatchQty(getUnMatchQty());
		t.setMDEntrySize(getMDEntrySize());
		t.setTradeVolume(getTradeVolume());
		t.setSettlType(getSettlType());
		t.setOrigSettlType(getOrigSettlType());
		t.setClearingMethod(getClearingMethod());
		t.setDeliveryType(getDeliveryType());
		t.setPartyID(getPartyID());
		t.setPartyName(getPartyName());
		t.setPartySubID(getPartySubID());
		t.setPartySubName(getPartySubName());
		t.setCounterPartyID(getCounterPartyID());
		t.setCounterPartyName(getCounterPartyName());
		t.setCounterPartySubID(getCounterPartySubID());
		t.setCounterPartySubName(getCounterPartySubName());
		t.setInternalFlag(getInternalFlag());
		t.setBargainFlag(getBargainFlag());
		t.setOCOFlag(getOCOFlag());
		t.setText(getText());
		t.setUserID(getUserID());
		t.setUserName(getUserName());
		t.setBizType(getBizType());
		t.setWholeFlag(getWholeFlag());
		t.setInstitutionID(getInstitutionID());
		t.setInstitutionName(getInstitutionName());
		t.setTraderID(getTraderID());
		t.setTraderName(getTraderName());
		t.setShowTrader(getShowTrader());
		t.setRoleName(getRoleName());
		t.setRoleID(getRoleID());
		t.setInfo1(getInfo1());
		t.setInfo2(getInfo2());
		t.setInfo3(getInfo3());
		t.setInfo4(getInfo4());
		t.setInfo5(getInfo5());
		t.setExerciseFlag(getExerciseFlag());
		t.setPriceType(getPriceType());
		t.setSSDetect(getSSDetect());
		t.setCalFlag(getCalFlag());
		return t;
	}
	}
	

	public MarketDataSnapshotFullRefresh Clone()
	{
		MarketDataSnapshotFullRefresh t=new MarketDataSnapshotFullRefresh();
		t.getStandardHeader().setMsgType(getStandardHeader().getMsgType());		 
		t.setVenueTypeGW(getVenueTypeGW());
		t.setVenues(getVenues());
		t.setMDBookType(getMDBookType());
		t.setMDReqID(getMDReqID());
		t.setClOrdID(getClOrdID());
		t.setMarketIndicator(getMarketIndicator());
		t.setSecurityID(getSecurityID());
		t.setSymbol(getSymbol());
		t.setSecurityType(getSecurityType());
		t.setCCDCValuation(getCCDCValuation());
		t.setCCDCValuaDate(getCCDCValuaDate());
		t.setCCDCValuaType(getCCDCValuaType());
		t.setSendingTime(getSendingTime());
		t.setLocalTime(getLocalTime());
		t.setOpenPrice(getOpenPrice());
		t.setHighPrice(getHighPrice());
		t.setLowPrice(getLowPrice());
		t.setLastPrice(getLastPrice());
		t.setClosePrice(getClosePrice());
		t.setPreClosePrice(getPreClosePrice());
		t.setSettlePrice(getSettlePrice());
		t.setPreSettlePrice(getPreSettlePrice());
		t.setOpenInterest(getOpenInterest());
		t.setPreOpenInterest(getPreOpenInterest());
		t.setTurnover(getTurnover());
		t.setVolume(getVolume());
		t.setUpperLimitPrice(getUpperLimitPrice());
		t.setLowerLimitPrice(getLowerLimitPrice());
		t.setTradingPhaseCode(getTradingPhaseCode());
		t.setTradingType(getTradingType());
		t.setIndexPrice(getIndexPrice());
		t.setMarkPrice(getMarkPrice());
		t.setFee(getFee());
		t.setLocation(getLocation());
		
		 CopyOnWriteArrayList<GNoMDEntries> gNoMDEntrieslt=getNoMDEntries();
		 if(gNoMDEntrieslt!=null)
		 {
			t.setNoMDEntries(new CopyOnWriteArrayList<GNoMDEntries>());
			for (GNoMDEntries g : gNoMDEntrieslt) {
				GNoMDEntries gNoMDEntries=g.Clone();		 
				t.getNoMDEntries().add(gNoMDEntries);
			}
		}
		return t;
	}

}
