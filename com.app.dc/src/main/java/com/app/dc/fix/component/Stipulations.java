
	
package com.app.dc.fix.component;


import com.app.dc.fix.BaseMessage;
import com.app.dc.fix.DataTypeConverter;
import com.app.dc.fix.MessageDictionary;

import java.util.List;
import java.util.ArrayList;
/**
 *
 *2021.09.27<br/>
 */
public class Stipulations extends BaseMessage{

	private final String GROUP_NoStipulations= MessageDictionary.Fields.NoStipulations;
	public List<GNoStipulations> getNoStipulations(){
			return (List<GNoStipulations>) this.get(GROUP_NoStipulations);
		}
	public void setNoStipulations(List<GNoStipulations> noStipulations){
			this.put(GROUP_NoStipulations,noStipulations);
		}
	public static class GNoStipulations extends BaseMessage{
		public String getStipulationType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.StipulationType));
		}
		public void setStipulationType(String stipulationType){
			this.put(MessageDictionary.Fields.StipulationType, stipulationType);
		}
		public String getStipulationValue(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.StipulationValue));
		}
		public void setStipulationValue(String stipulationValue){
			this.put(MessageDictionary.Fields.StipulationValue, stipulationValue);
		}

	public GNoStipulations Clone()
	{
		GNoStipulations t=new  GNoStipulations();
				t.setStipulationType(getStipulationType());
		t.setStipulationValue(getStipulationValue());
		return t;
	}
	}



	public Stipulations Clone()
	{
		Stipulations t=new Stipulations();
		t.setNoStipulations(new ArrayList<GNoStipulations>());
		for (GNoStipulations g : getNoStipulations()) {
			GNoStipulations gNoStipulations=g.Clone();		 
			t.getNoStipulations().add(gNoStipulations);
		}
		return t;
	}
}

