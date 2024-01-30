
	
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
public class Parties extends BaseMessage{

	private final String GROUP_NoPartyIDs= MessageDictionary.Fields.NoPartyIDs;
	public List<GNoPartyIDs> getNoPartyIDs(){
			return (List<GNoPartyIDs>) this.get(GROUP_NoPartyIDs);
		}
	public void setNoPartyIDs(List<GNoPartyIDs> noPartyIDs){
			this.put(GROUP_NoPartyIDs,noPartyIDs);
		}
	public static class GNoPartyIDs extends BaseMessage{
		public String getPartyID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PartyID));
		}
		public void setPartyID(String partyID){
			this.put(MessageDictionary.Fields.PartyID, partyID);
		}
		public String getPartyIDSource(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PartyIDSource));
		}
		public void setPartyIDSource(String partyIDSource){
			this.put(MessageDictionary.Fields.PartyIDSource, partyIDSource);
		}
		public int getPartyRole(){
			return DataTypeConverter.Utils.toInt(this.get(MessageDictionary.Fields.PartyRole));
		}
		public void setPartyRole(int partyRole){
			this.put(MessageDictionary.Fields.PartyRole, partyRole);
		}

	private final String GROUP_NoPartySubIDs=MessageDictionary.Fields.NoPartySubIDs;
	public List<GNoPartySubIDs> getNoPartySubIDs(){
			return (List<GNoPartySubIDs>) this.get(GROUP_NoPartySubIDs);
		}
	public void setNoPartySubIDs(List<GNoPartySubIDs> noPartySubIDs){
			this.put(GROUP_NoPartySubIDs,noPartySubIDs);
		}
	public static class GNoPartySubIDs extends BaseMessage{
		public String getPartySubID(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.PartySubID));
		}
		public void setPartySubID(String partySubID){
			this.put(MessageDictionary.Fields.PartySubID, partySubID);
		}
		public int getPartySubIDType(){
			return DataTypeConverter.Utils.toInt(this.get(MessageDictionary.Fields.PartySubIDType));
		}
		public void setPartySubIDType(int partySubIDType){
			this.put(MessageDictionary.Fields.PartySubIDType, partySubIDType);
		}

	public GNoPartySubIDs Clone()
	{
		GNoPartySubIDs t=new  GNoPartySubIDs();
				t.setPartySubID(getPartySubID());
		t.setPartySubIDType(getPartySubIDType());
		return t;
	}
	}



	public GNoPartyIDs Clone()
	{
		GNoPartyIDs t=new  GNoPartyIDs();
				t.setPartyID(getPartyID());
		t.setPartyIDSource(getPartyIDSource());
		t.setPartyRole(getPartyRole());
		t.setNoPartySubIDs(new ArrayList<GNoPartySubIDs>());
		for (GNoPartySubIDs g : getNoPartySubIDs()) {
			GNoPartySubIDs gNoPartySubIDs=g.Clone();		 
			t.getNoPartySubIDs().add(gNoPartySubIDs);
		}
		return t;
	}
	}



	public Parties Clone()
	{
		Parties t=new Parties();
		t.setNoPartyIDs(new ArrayList<GNoPartyIDs>());
		for (GNoPartyIDs g : getNoPartyIDs()) {
			GNoPartyIDs gNoPartyIDs=g.Clone();		 
			t.getNoPartyIDs().add(gNoPartyIDs);
		}
		return t;
	}
}

