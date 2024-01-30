
	
package com.app.dc.fix.component;


import com.app.dc.fix.BaseMessage;
import com.app.dc.fix.DataTypeConverter;
import com.app.dc.fix.MessageDictionary;

import java.util.List;
import java.util.ArrayList;
/**
 *
 *2021.09.27
 */
public class Payments extends BaseMessage{

	private final String GROUP_NoPayments= MessageDictionary.Fields.NoPayments;
	public List<GNoPayments> getNoPayments(){
			return (List<GNoPayments>) this.get(GROUP_NoPayments);
		}
	public void setNoPayments(List<GNoPayments> noPayments){
			this.put(GROUP_NoPayments,noPayments);
		}
	public static class GNoPayments extends BaseMessage{
		public int getPaymentType(){
			return DataTypeConverter.Utils.toInt(this.get(MessageDictionary.Fields.PaymentType));
		}
		public void setPaymentType(int paymentType){
			this.put(MessageDictionary.Fields.PaymentType, paymentType);
		}
		public String getPaymentFixedRate(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PaymentFixedRate));
		}
		public void setPaymentFixedRate(String paymentFixedRate){
			this.put(MessageDictionary.Fields.PaymentFixedRate, paymentFixedRate);
		}
		public String getPaymentAmount(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PaymentAmount));
		}
		public void setPaymentAmount(String paymentAmount){
			this.put(MessageDictionary.Fields.PaymentAmount, paymentAmount);
		}
		public String getPaymentUnitOfMeasure(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PaymentUnitOfMeasure));
		}
		public void setPaymentUnitOfMeasure(String paymentUnitOfMeasure){
			this.put(MessageDictionary.Fields.PaymentUnitOfMeasure, paymentUnitOfMeasure);
		}

	public GNoPayments Clone()
	{
		GNoPayments t=new  GNoPayments();
				t.setPaymentType(getPaymentType());
		t.setPaymentFixedRate(getPaymentFixedRate());
		t.setPaymentAmount(getPaymentAmount());
		t.setPaymentUnitOfMeasure(getPaymentUnitOfMeasure());
		return t;
	}
	}



	public Payments Clone()
	{
		Payments t=new Payments();
		t.setNoPayments(new ArrayList<GNoPayments>());
		for (GNoPayments g : getNoPayments()) {
			GNoPayments gNoPayments=g.Clone();		 
			t.getNoPayments().add(gNoPayments);
		}
		return t;
	}
}

