package com.app.dc.interfaces;

import com.app.dc.entity.BatchResult;

public interface IIntelligentDataInsightService {

    public BatchResult selectDataByTable(String tableStr) throws Exception;

}
