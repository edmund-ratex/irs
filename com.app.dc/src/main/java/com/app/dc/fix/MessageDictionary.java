package com.app.dc.fix;
/**
*
*2023.11.14
*/

public class MessageDictionary {

	public static class FieldsEnum{
		
public static class ExecAckStatus{
					public static final String RECEIVED ="0";
														public static final String ACCEPTED ="1";
														public static final String REJECTED ="2";
												}
			
public static class TradSesStatus{
					public static final String START ="7";
														public static final String OPEN ="2";
														public static final String BREAK ="8";
														public static final String CLOSED ="3";
														public static final String ENDED ="9";
														public static final String CQSTIME ="18";
														public static final String CQETIME ="22";
														public static final String CMSTIME ="19";
												}
						
public static class VenueTypeGW{
					public static final String CDH ="CDH";
														public static final String SOR ="SOR";
														public static final String IDB ="IDB";
														public static final String ODM ="ODM";
														public static final String QDMESP ="QDMESP";
														public static final String NDM ="NDM";
														public static final String QDM ="QDM";
														public static final String INNER ="INNER";
														public static final String HTQDM ="HTQDM";
														public static final String TBOOK ="TBOOK";
														public static final String ManualBroker ="ManualBroker";
														public static final String OFFLINE ="OFFLINE";
														public static final String SHE ="SHE";
												}
			
public static class Venues{
					public static final String TPSC ="TPSC";
														public static final String CNEX ="CNEX";
														public static final String CCTB ="CCTB";
														public static final String PTCN ="PTCN";
														public static final String CTTJ ="CTTJ";
														public static final String IDB ="IDB";
														public static final String ODM ="ODM";
														public static final String QDMESP ="QDMESP";
														public static final String NDM ="NDM";
														public static final String QDM ="QDM";
														public static final String QUANT ="QUANT";
														public static final String MANUAL ="MANUAL";
														public static final String HTQDM ="HTQDM";
														public static final String TBOOK ="TBOOK";
														public static final String OFFLINE ="OFFLINE";
												}
			
public static class MDBookType{
					public static final String QDMESP ="123";
														public static final String QDMESPZ ="124";
														public static final String QDM ="201";
														public static final String ODM ="202";
												}
			
public static class SubscriptionRequestType{
					public static final String Subscribe ="1";
														public static final String Unsubscribe ="2";
												}
				
public static class MarketIndicator{
					public static final String Bond ="4";
												}
				
public static class SecurityType{
					public static final String Interest_Rate ="BNC";
														public static final String Credit_Bond ="BOC";
												}
			
public static class SettlType{
					public static final String T0 ="1";
														public static final String T1 ="2";
														public static final String Tom0 ="3";
														public static final String Tom1 ="4";
												}
			
public static class Side{
					public static final String Bid ="1";
														public static final String Ofr ="2";
												}
																			
public static class QuoteTransType{
					public static final String New ="N";
														public static final String Update ="R";
														public static final String Canceled ="C";
														public static final String Confirmed ="1";
														public static final String Refuse ="2";
														public static final String Refuse_Ack ="3";
												}
																		
public static class OrdType{
					public static final String Market ="1";
														public static final String Limit ="2";
												}
									
public static class OrdStatus{
					public static final String Pending_New ="N";
														public static final String New ="0";
														public static final String Partially_filled ="2";
														public static final String Canceled ="4";
														public static final String Freezed ="I";
														public static final String Rejected ="5";
														public static final String Invalid ="8";
														public static final String Expired ="21";
												}
										
public static class ExecType{
					public static final String Trade ="F";
														public static final String Rejected ="5";
														public static final String Canceled ="4";
												}
						
public static class TimeInForce{
					public static final String Day ="0";
														public static final String IOC ="3";
														public static final String GFT ="A";
												}
																									
public static class InternalFlag{
					public static final String External ="1";
														public static final String Internal ="2";
												}
			
public static class BargainFlag{
					public static final String Zero_Star ="0";
														public static final String One_Star ="1";
														public static final String Two_Star ="2";
												}
			
public static class OCOFlag{
					public static final String OCO ="1";
														public static final String Bale ="2";
												}
			
public static class Terminal{
					public static final String Terminals ="0";
														public static final String API ="2";
												}
																
public static class WholeFlag{
					public static final String Whole ="1";
														public static final String None ="0";
												}
							
public static class VenuesSelectF{
					public static final String None ="1";
														public static final String Default ="2";
														public static final String SelfSelection ="3";
														public static final String RandomVenues ="4";
												}
					
public static class DealType{
					public static final String InterDealFlag ="1";
														public static final String ExternalDealFlag ="2";
												}
				
public static class CrossVenues{
					public static final String All ="1";
														public static final String Inner ="2";
														public static final String InnerXBond ="3";
												}
			
public static class RiskManualCk{
					public static final String Y ="Y";
														public static final String N ="N";
												}
							
public static class ShowTrader{
					public static final String Y ="Y";
														public static final String N ="N";
												}
						
public static class SynchFlag{
					public static final String Accomplish ="Y";
														public static final String PendingParty ="S";
														public static final String PendingCounterParty ="C";
												}
																						
public static class PartyOrderSource{
					public static final String TBOOK ="TBOOK";
														public static final String IDB ="IDB";
														public static final String QUANT ="QUANT";
												}
			
public static class CounterPartyOrderSource{
					public static final String TBOOK ="TBOOK";
														public static final String IDB ="IDB";
														public static final String QUANT ="QUANT";
														public static final String ODM ="ODM";
														public static final String QDMESP ="QDMESP";
														public static final String TPSC ="TPSC";
														public static final String CTTJ ="CTTJ";
														public static final String CNEX ="CNEX";
														public static final String CCTB ="CCTB";
														public static final String PTCN ="PTCN";
														public static final String MARKET ="MARKET";
												}
			
public static class RoleType{
					public static final String SalesAgentFlag ="SalesAgentFlag";
														public static final String SelfDealsFlag ="SelfDealsFlag";
												}
			
public static class TransType{
					public static final String Add ="A";
														public static final String Update ="U";
														public static final String Resend ="S";
														public static final String Delete ="D";
												}
					
public static class HandlInst{
					public static final String Hide ="1";
														public static final String Show ="2";
												}
							
public static class CounterPartyVenues{
					public static final String IDB ="IDB";
														public static final String MANUAL ="MANUAL";
														public static final String MARKET ="MARKET";
														public static final String SALESAGENT ="SALESAGENT";
														public static final String SELFDEALS ="SELFDEALS";
														public static final String TPSC ="TPSC";
														public static final String CTTJ ="CTTJ";
														public static final String CNEX ="CNEX";
														public static final String CCTB ="CCTB";
														public static final String PTCN ="PTCN";
														public static final String ODM ="ODM";
														public static final String QDMESP ="QDMESP";
														public static final String QUANT ="QUANT";
												}
			
public static class ParentFlag{
					public static final String Y ="Y";
														public static final String N ="N";
												}
								
public static class TradeMethod{
					public static final String SendNegotiate ="1";
														public static final String SendRFQ ="4";
														public static final String ReceivedNegotiate ="30";
														public static final String ReceivedRFQ ="50";
														public static final String Unknown ="60";
												}
																											
public static class ExecType2{
					public static final String Trade ="F";
														public static final String Rejected ="8";
														public static final String Canceled ="4";
												}
					
public static class NetLatencyStatus{
					public static final String Normal ="1";
														public static final String Latency ="0";
												}
				
public static class MassCancelRequestType{
					public static final String CANCEL_ORDERS_FOR_A_SECURITY ="1";
														public static final String CANCEL_ORDERS_FOR_AN_UNDERLYING_SECURITY ="2";
														public static final String CANCEL_ORDERS_FOR_A_PRODUCT ="3";
														public static final String CANCEL_ORDERS_FOR_A_CFICODE ="4";
														public static final String CANCEL_ORDERS_FOR_A_SECURITYTYPE ="5";
														public static final String CANCEL_ORDERS_FOR_A_TRADING_SESSION ="6";
														public static final String CANCEL_ALL_ORDERS ="7";
												}
			
public static class MassCancelResponse{
					public static final String CANCEL_REQUEST_REJECTED ="0";
														public static final String CANCEL_ORDERS_FOR_A_SECURITY ="1";
														public static final String CANCEL_ORDERS_FOR_AN_UNDERLYING_SECURITY ="2";
														public static final String CANCEL_ORDERS_FOR_A_PRODUCT ="3";
														public static final String CANCEL_ORDERS_FOR_A_CFICODE ="4";
														public static final String CANCEL_ORDERS_FOR_A_SECURITYTYPE ="5";
														public static final String CANCEL_ORDERS_FOR_A_TRADING_SESSION ="6";
														public static final String CANCEL_ALL_ORDERS ="7";
												}
			
public static class MassCancelRejectReason{
					public static final String MASS_CANCEL_NOT_SUPPORTED ="0";
														public static final String INVALID_OR_UNKNOWN_SECURITY ="1";
														public static final String INVALID_OR_UNKNOWN_UNDERLYING ="2";
														public static final String INVALID_OR_UNKNOWN_PRODUCT ="3";
														public static final String INVALID_OR_UNKNOWN_CFICODE ="4";
														public static final String INVALID_OR_UNKNOWN_SECURITY_TYPE ="5";
														public static final String INVALID_OR_UNKNOWN_TRADING_SESSION ="6";
														public static final String OTHER ="99";
												}
																																																																												
public static class QuoteRespType{
					public static final String Accept ="1";
														public static final String Reject ="7";
												}
																																										
public static class TriggerType{
					public static final String MarkPrice ="1";
														public static final String TradePrice ="2";
												}
																	}


	public static class Fields{
				public static final String ExecAckStatus = "ExecAckStatus";
				public static final String TradSesStatus = "TradSesStatus";
				public static final String MsgType = "MsgType";
				public static final String TargetSessionId = "TargetSessionId";
				public static final String NetStatus = "NetStatus";
				public static final String VenueTypeGW = "VenueTypeGW";
				public static final String Venues = "Venues";
				public static final String MDBookType = "MDBookType";
				public static final String SubscriptionRequestType = "SubscriptionRequestType";
				public static final String MDReqID = "MDReqID";
				public static final String MarketIndicator = "MarketIndicator";
				public static final String SecurityID = "SecurityID";
				public static final String SecurityType = "SecurityType";
				public static final String SettlType = "SettlType";
				public static final String Side = "Side";
				public static final String OrderQty = "OrderQty";
				public static final String OrderQty2 = "OrderQty2";
				public static final String ClearingMethod = "ClearingMethod";
				public static final String ValidUntilTime = "ValidUntilTime";
				public static final String PartyID = "PartyID";
				public static final String PartySubID = "PartySubID";
				public static final String MarkerID = "MarkerID";
				public static final String Info1 = "Info1";
				public static final String Info2 = "Info2";
				public static final String Info3 = "Info3";
				public static final String Info4 = "Info4";
				public static final String Info5 = "Info5";
				public static final String MDReqRejReason = "MDReqRejReason";
				public static final String RejectText = "RejectText";
				public static final String Text = "Text";
				public static final String NoMDEntries = "NoMDEntries";
				public static final String QuoteTransType = "QuoteTransType";
				public static final String QuoteStatus = "QuoteStatus";
				public static final String MDEntryType = "MDEntryType";
				public static final String MDPriceLevel = "MDPriceLevel";
				public static final String MDEntryID = "MDEntryID";
				public static final String MDEntryDate = "MDEntryDate";
				public static final String MDEntryTime = "MDEntryTime";
				public static final String MDEntryPx = "MDEntryPx";
				public static final String Yield = "Yield";
				public static final String DirtyPrice = "DirtyPrice";
				public static final String UnMatchQty = "UnMatchQty";
				public static final String MDEntrySize = "MDEntrySize";
				public static final String DeliveryType = "DeliveryType";
				public static final String PartyName = "PartyName";
				public static final String PartySubName = "PartySubName";
				public static final String TransactTime = "TransactTime";
				public static final String OrdType = "OrdType";
				public static final String ClOrdID = "ClOrdID";
				public static final String Price = "Price";
				public static final String OrigClOrdID = "OrigClOrdID";
				public static final String Price2 = "Price2";
				public static final String OrderID = "OrderID";
				public static final String CxlRejReason = "CxlRejReason";
				public static final String OrdStatus = "OrdStatus";
				public static final String OrdStatusReqID = "OrdStatusReqID";
				public static final String OrdRejReason = "OrdRejReason";
				public static final String YieldType = "YieldType";
				public static final String LeavesQty = "LeavesQty";
				public static final String SettlDate = "SettlDate";
				public static final String TradeDate = "TradeDate";
				public static final String ExecID = "ExecID";
				public static final String ExecType = "ExecType";
				public static final String PriceType = "PriceType";
				public static final String LastPx = "LastPx";
				public static final String LastQty = "LastQty";
				public static final String TimeInForce = "TimeInForce";
				public static final String MaxFloor = "MaxFloor";
				public static final String Symbol = "Symbol";
				public static final String AvgPx = "AvgPx";
				public static final String CumQty = "CumQty";
				public static final String RefOrderID = "RefOrderID";
				public static final String QuoteID = "QuoteID";
				public static final String QuoteType = "QuoteType";
				public static final String QuoteStatusDesc = "QuoteStatusDesc";
				public static final String TradSesStartTime = "TradSesStartTime";
				public static final String TradSesOpenTime = "TradSesOpenTime";
				public static final String TradSesCloseTime = "TradSesCloseTime";
				public static final String TradSesEndTime = "TradSesEndTime";
				public static final String SettlCurrency = "SettlCurrency";
				public static final String SettlCurrFxRate = "SettlCurrFxRate";
				public static final String AccruedInterestAmt = "AccruedInterestAmt";
				public static final String TradeVolume = "TradeVolume";
				public static final String AllocID = "AllocID";
				public static final String CounterPartyID = "CounterPartyID";
				public static final String CounterPartyName = "CounterPartyName";
				public static final String CounterPartySubID = "CounterPartySubID";
				public static final String CounterPartySubName = "CounterPartySubName";
				public static final String AlgoName = "AlgoName";
				public static final String InternalFlag = "InternalFlag";
				public static final String BargainFlag = "BargainFlag";
				public static final String OCOFlag = "OCOFlag";
				public static final String Terminal = "Terminal";
				public static final String UserName = "UserName";
				public static final String Yield2 = "Yield2";
				public static final String LeavesQty2 = "LeavesQty2";
				public static final String NetPrice = "NetPrice";
				public static final String NetPrice2 = "NetPrice2";
				public static final String StrategyOrderId = "StrategyOrderId";
				public static final String BizType = "BizType";
				public static final String CalculateRequestID = "CalculateRequestID";
				public static final String CalculateRequestType = "CalculateRequestType";
				public static final String CalculateReportID = "CalculateReportID";
				public static final String OpenQty = "OpenQty";
				public static final String FirmFlag = "FirmFlag";
				public static final String UserID = "UserID";
				public static final String WholeFlag = "WholeFlag";
				public static final String QuantID = "QuantID";
				public static final String FirmToNonTime = "FirmToNonTime";
				public static final String VenuesRdmNum = "VenuesRdmNum";
				public static final String SelfSelectVenues = "SelfSelectVenues";
				public static final String VenuesSelectF = "VenuesSelectF";
				public static final String BlockNum = "BlockNum";
				public static final String CounterPartyOrdID = "CounterPartyOrdID";
				public static final String DealType = "DealType";
				public static final String CCDCValuation = "CCDCValuation";
				public static final String CrossVenues = "CrossVenues";
				public static final String RiskManualCk = "RiskManualCk";
				public static final String InstitutionID = "InstitutionID";
				public static final String InstitutionName = "InstitutionName";
				public static final String TraderID = "TraderID";
				public static final String TraderName = "TraderName";
				public static final String ShowTrader = "ShowTrader";
				public static final String RoleName = "RoleName";
				public static final String UpdateTime = "UpdateTime";
				public static final String UpdateUser = "UpdateUser";
				public static final String SynchFlag = "SynchFlag";
				public static final String BizTypeID = "BizTypeID";
				public static final String SalesAgentFlag = "SalesAgentFlag";
				public static final String RoleID = "RoleID";
				public static final String OrigOrdPrice = "OrigOrdPrice";
				public static final String MacID = "MacID";
				public static final String PublishIP = "PublishIP";
				public static final String InsideIP = "InsideIP";
				public static final String HardDiskID = "HardDiskID";
				public static final String CounterpartySalesAgentFlag = "CounterpartySalesAgentFlag";
				public static final String BrokerExecID = "BrokerExecID";
				public static final String AppointedID = "AppointedID";
				public static final String InitiatorID = "InitiatorID";
				public static final String InitiatorSubID = "InitiatorSubID";
				public static final String CounterPartyFullName = "CounterPartyFullName";
				public static final String OrigSettlType = "OrigSettlType";
				public static final String IocTimeOut = "IocTimeOut";
				public static final String IocPrice = "IocPrice";
				public static final String IocVenueTypeGW = "IocVenueTypeGW";
				public static final String IocVenues = "IocVenues";
				public static final String PartyOrderSource = "PartyOrderSource";
				public static final String CounterPartyOrderSource = "CounterPartyOrderSource";
				public static final String RoleType = "RoleType";
				public static final String TransType = "TransType";
				public static final String UpdateFiled = "UpdateFiled";
				public static final String BridgecounterPartyFullName = "BridgecounterPartyFullName";
				public static final String HandlInst = "HandlInst";
				public static final String RecordNum = "RecordNum";
				public static final String SendingTime = "SendingTime";
				public static final String LocalTime = "LocalTime";
				public static final String StrategyOrderVenues = "StrategyOrderVenues";
				public static final String CounterPartyVenues = "CounterPartyVenues";
				public static final String ParentFlag = "ParentFlag";
				public static final String DcsexecID = "DcsexecID";
				public static final String ParentFlagQty = "ParentFlagQty";
				public static final String ActionFlag = "ActionFlag";
				public static final String ExecTypeReason = "ExecTypeReason";
				public static final String TradeTime = "TradeTime";
				public static final String TradeMethod = "TradeMethod";
				public static final String Info6 = "Info6";
				public static final String Info7 = "Info7";
				public static final String Info8 = "Info8";
				public static final String Info9 = "Info9";
				public static final String Info10 = "Info10";
				public static final String B1 = "B1";
				public static final String A1 = "A1";
				public static final String B0 = "B0";
				public static final String B2 = "B2";
				public static final String M5b1 = "M5b1";
				public static final String M5a1 = "M5a1";
				public static final String A2 = "A2";
				public static final String A0 = "A0";
				public static final String Vs = "Vs";
				public static final String TrigEvent = "TrigEvent";
				public static final String CancEvent = "CancEvent";
				public static final String InValidFlag = "InValidFlag";
				public static final String EREventName = "EREventName";
				public static final String CancEventName = "CancEventName";
				public static final String LastPx2 = "LastPx2";
				public static final String LastQty2 = "LastQty2";
				public static final String CumQty2 = "CumQty2";
				public static final String OrdStatus2 = "OrdStatus2";
				public static final String AvgPx2 = "AvgPx2";
				public static final String ExecType2 = "ExecType2";
				public static final String BookID = "BookID";
				public static final String NetLatency = "NetLatency";
				public static final String NetLatencyStatus = "NetLatencyStatus";
				public static final String ExerciseFlag = "ExerciseFlag";
				public static final String MassCancelRequestType = "MassCancelRequestType";
				public static final String MassCancelResponse = "MassCancelResponse";
				public static final String MassCancelRejectReason = "MassCancelRejectReason";
				public static final String TotalAffectedOrders = "TotalAffectedOrders";
				public static final String AffectedOrdGrp = "AffectedOrdGrp";
				public static final String ConfirmID = "ConfirmID";
				public static final String TradeReportID = "TradeReportID";
				public static final String TradeRequestID = "TradeRequestID";
				public static final String TradeID = "TradeID";
				public static final String SecondaryTradeID = "SecondaryTradeID";
				public static final String TradeRequestType = "TradeRequestType";
				public static final String TrdType = "TrdType";
				public static final String TrdSubType = "TrdSubType";
				public static final String TrdMatchID = "TrdMatchID";
				public static final String TradeRptStat = "TradeRptStat";
				public static final String MaturityDate = "MaturityDate";
				public static final String SettlCurrAmt = "SettlCurrAmt";
				public static final String TradeMatchTimestamp = "TradeMatchTimestamp";
				public static final String MatchStatus = "MatchStatus";
				public static final String NoPayments = "NoPayments";
				public static final String PaymentType = "PaymentType";
				public static final String PaymentFixedRate = "PaymentFixedRate";
				public static final String PaymentAmount = "PaymentAmount";
				public static final String PaymentUnitOfMeasure = "PaymentUnitOfMeasure";
				public static final String NoPartyIDs = "NoPartyIDs";
				public static final String PartyIDSource = "PartyIDSource";
				public static final String PartyRole = "PartyRole";
				public static final String NoPartySubIDs = "NoPartySubIDs";
				public static final String PartySubIDType = "PartySubIDType";
				public static final String NoStipulations = "NoStipulations";
				public static final String StipulationType = "StipulationType";
				public static final String StipulationValue = "StipulationValue";
				public static final String NoAllocs = "NoAllocs";
				public static final String AllocType = "AllocType";
				public static final String IndividualAllocID = "IndividualAllocID";
				public static final String AllocAccount = "AllocAccount";
				public static final String AllocText = "AllocText";
				public static final String CleanPrice = "CleanPrice";
				public static final String OnBehalfOfCompID = "OnBehalfOfCompID";
				public static final String TradeRequestResult = "TradeRequestResult";
				public static final String TradeRequestStatus = "TradeRequestStatus";
				public static final String PartyCode = "PartyCode";
				public static final String CounterPartyCode = "CounterPartyCode";
				public static final String SSDetect = "SSDetect";
				public static final String OpenPrice = "OpenPrice";
				public static final String HighPrice = "HighPrice";
				public static final String LowPrice = "LowPrice";
				public static final String PreClosePrice = "PreClosePrice";
				public static final String SettlePrice = "SettlePrice";
				public static final String PreSettlePrice = "PreSettlePrice";
				public static final String OpenInterest = "OpenInterest";
				public static final String PreOpenInterest = "PreOpenInterest";
				public static final String Turnover = "Turnover";
				public static final String Volume = "Volume";
				public static final String UpperLimitPrice = "UpperLimitPrice";
				public static final String LowerLimitPrice = "LowerLimitPrice";
				public static final String LastPrice = "LastPrice";
				public static final String TradeNumber = "TradeNumber";
				public static final String OrderSource = "OrderSource";
				public static final String ReferenceInfo1 = "ReferenceInfo1";
				public static final String ReferenceInfo2 = "ReferenceInfo2";
				public static final String ReferenceInfo3 = "ReferenceInfo3";
				public static final String ReferenceInfo4 = "ReferenceInfo4";
				public static final String ReferenceInfo5 = "ReferenceInfo5";
				public static final String Name = "Name";
				public static final String PartySubID2 = "PartySubID2";
				public static final String IntentionID = "IntentionID";
				public static final String OffsetFlag = "OffsetFlag";
				public static final String HedgeFlag = "HedgeFlag";
				public static final String TradingPhaseCode = "TradingPhaseCode";
				public static final String TradingType = "TradingType";
				public static final String CalFlag = "CalFlag";
				public static final String CCDCValuaDate = "CCDCValuaDate";
				public static final String CCDCValuaType = "CCDCValuaType";
				public static final String LastWorkDay = "LastWorkDay";
				public static final String QuoteReqID = "QuoteReqID";
				public static final String QuoteRespType = "QuoteRespType";
				public static final String QuoteRespID = "QuoteRespID";
				public static final String QuoteRequestType = "QuoteRequestType";
				public static final String QuoteStatusReqID = "QuoteStatusReqID";
				public static final String QuoteRejectReason = "QuoteRejectReason";
				public static final String Comments = "Comments";
				public static final String ClosePrice = "ClosePrice";
				public static final String IndexPrice = "IndexPrice";
				public static final String MarkPrice = "MarkPrice";
				public static final String MDEntryAmount = "MDEntryAmount";
				public static final String Fee = "Fee";
				public static final String Balance = "Balance";
				public static final String UsedMargin = "UsedMargin";
				public static final String WDBalance = "WDBalance";
				public static final String DayRealizedPnl = "DayRealizedPnl";
				public static final String HoldRealizedPnl = "HoldRealizedPnl";
				public static final String DayCommission = "DayCommission";
				public static final String FreezedMargin = "FreezedMargin";
				public static final String FreezedCommission = "FreezedCommission";
				public static final String LongPosition = "LongPosition";
				public static final String LongAverage = "LongAverage";
				public static final String LongUsedMargin = "LongUsedMargin";
				public static final String ShortPosition = "ShortPosition";
				public static final String ShortAverage = "ShortAverage";
				public static final String ShortUsedMargin = "ShortUsedMargin";
				public static final String RealizedPnl = "RealizedPnl";
				public static final String Type = "Type";
				public static final String Amount = "Amount";
				public static final String Source = "Source";
				public static final String SourceID = "SourceID";
				public static final String ID = "ID";
				public static final String OCType = "OCType";
				public static final String LongLockedPosition = "LongLockedPosition";
				public static final String ShortLockedPosition = "ShortLockedPosition";
				public static final String PositionType = "PositionType";
				public static final String Leverage = "Leverage";
				public static final String LongLiqPrice = "LongLiqPrice";
				public static final String ShortLiqPrice = "ShortLiqPrice";
				public static final String FreezedBalance = "FreezedBalance";
				public static final String Maker = "Maker";
				public static final String TriggerType = "TriggerType";
				public static final String TriggerPrice = "TriggerPrice";
				public static final String StopLossPrice = "StopLossPrice";
				public static final String TakeProfitPrice = "TakeProfitPrice";
				public static final String Trigger = "Trigger";
				public static final String CloseBy = "CloseBy";
				public static final String Location = "Location";
				public static final String Demo = "Demo";
				public static final String PositionStatus = "PositionStatus";
				public static final String AccountStatus = "AccountStatus";
				public static final String StandardHeader = "StandardHeader";
				public static final String StandardTrailer = "StandardTrailer";
				public static final String Parties = "Parties";
				public static final String Payments = "Payments";
				public static final String Stipulations = "Stipulations";
				public static final String Allocs = "Allocs";
		}
	
}
