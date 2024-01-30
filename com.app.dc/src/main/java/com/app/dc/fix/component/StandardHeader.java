
	
package com.app.dc.fix.component;

import com.app.dc.fix.BaseMessage;
import com.app.dc.fix.DataTypeConverter;
import com.app.dc.fix.MessageDictionary;

import java.util.List;
import java.util.ArrayList;
/**
 *
 *2019.11.20
 */

public class StandardHeader extends BaseMessage{

		public String getMsgType(){
			return DataTypeConverter.Utils.toString(this.get(MessageDictionary.Fields.MsgType));
		}
		public void setMsgType(String msgType){
			this.put(MessageDictionary.Fields.MsgType, msgType);
		}
public StandardHeader Clone()
	{
		StandardHeader t=new StandardHeader();
 		t.setMsgType(getMsgType());
		return t;
	}
}

