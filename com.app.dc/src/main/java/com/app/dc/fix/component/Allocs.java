
	
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
public class Allocs extends BaseMessage {

	private final String GROUP_NoAllocs= MessageDictionary.Fields.NoAllocs;
	public List<GNoAllocs> getNoAllocs(){
			return (List<GNoAllocs>) this.get(GROUP_NoAllocs);
		}
	public void setNoAllocs(List<GNoAllocs> noAllocs){
			this.put(GROUP_NoAllocs,noAllocs);
		}
	public static class GNoAllocs extends BaseMessage{
		public String getAllocType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.AllocType));
		}
		public void setAllocType(String allocType){
			this.put(MessageDictionary.Fields.AllocType, allocType);
		}
		public String getIndividualAllocID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.IndividualAllocID));
		}
		public void setIndividualAllocID(String individualAllocID){
			this.put(MessageDictionary.Fields.IndividualAllocID, individualAllocID);
		}
		public String getAllocAccount(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.AllocAccount));
		}
		public void setAllocAccount(String allocAccount){
			this.put(MessageDictionary.Fields.AllocAccount, allocAccount);
		}
		public String getAllocText(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.AllocText));
		}
		public void setAllocText(String allocText){
			this.put(MessageDictionary.Fields.AllocText, allocText);
		}

	public GNoAllocs Clone()
	{
		GNoAllocs t=new  GNoAllocs();
				t.setAllocType(getAllocType());
		t.setIndividualAllocID(getIndividualAllocID());
		t.setAllocAccount(getAllocAccount());
		t.setAllocText(getAllocText());
		return t;
	}
	}



	public Allocs Clone()
	{
		Allocs t=new Allocs();
		t.setNoAllocs(new ArrayList<GNoAllocs>());
		for (GNoAllocs g : getNoAllocs()) {
			GNoAllocs gNoAllocs=g.Clone();		 
			t.getNoAllocs().add(gNoAllocs);
		}
		return t;
	}
}

