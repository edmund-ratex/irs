package com.app.dc.fix;

import com.app.dc.fix.component.*; 
 

public class FixMessage extends BaseMessage{
	
	public FixMessage()
	{
		  StandardHeader standardHeader = new StandardHeader();
		  setStandardHeader(standardHeader);
          StandardTrailer standardTrailer = new StandardTrailer();
          setStandardTrailer(standardTrailer);
	}
	public void setStandardHeader(StandardHeader standardHeader){
		this.put(MessageDictionary.Fields.StandardHeader, standardHeader);
	}
	public StandardHeader getStandardHeader(){
		return (StandardHeader)this.get(MessageDictionary.Fields.StandardHeader);
	}
	
	public void setStandardTrailer(StandardTrailer standardTrailer){
		this.put(MessageDictionary.Fields.StandardTrailer, standardTrailer);
	}
	public StandardTrailer getStandardTrailer(){
		return (StandardTrailer)this.get(MessageDictionary.Fields.StandardTrailer);
	}
}
