package com.app.dc.fix.component;

import com.app.dc.fix.BaseMessage;
import com.app.dc.fix.DataTypeConverter;
import com.app.dc.fix.MessageDictionary;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * 2019.11.20<br/>
 */

public class StandardTrailer extends BaseMessage {

	public StandardTrailer Clone() {
		StandardTrailer t = new StandardTrailer();
		return t;
	}

}
