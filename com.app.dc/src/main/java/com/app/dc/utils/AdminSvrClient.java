package com.app.dc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dc.po.TSymbol;
import com.gw.common.utils.BaseApi;
import com.gw.common.utils.BaseApi.IMessage;

@Component
public class AdminSvrClient {
	@Autowired
	private BaseApi baseApi;
	private String serverName = "SERVER.AdminSvr";

	public void init() {
		baseApi.init(serverName);
	}

	public void subscribeSymbolForAdmin(final IMessage<TSymbol> message) {

		baseApi.subscribeWithImage(serverName, "AdminSvr", "querySymbol", message, TSymbol.class);

	}
 
}
