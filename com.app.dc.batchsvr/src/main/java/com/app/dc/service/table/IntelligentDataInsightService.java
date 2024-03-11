package com.app.dc.service.table;

import com.app.common.db.DBUtils;
import com.app.dc.entity.BatchResult;
import com.app.dc.interfaces.IIntelligentDataInsightService;
import com.app.dc.utils.CodeMsgEnum;
import com.app.dc.utils.Consts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class IntelligentDataInsightService implements IIntelligentDataInsightService {

    @Value("#{'${intelligent.data.insight.tables}'.split(',')}")
    private List<String> tables;


    @Override
    public BatchResult selectDataByTable(String tableStr) {
        BatchResult result = new BatchResult();
        if (tables.contains(tableStr)){
            try {
                String sql = String.format("select * from %s", tableStr);
                log.info("sql:"+sql);
                List<Map<String,Object>> list = DBUtils.queryListThrowsException(sql, new Object[]{});
                log.info("selectDataByTable list size:{}",list == null ? 0 : list.size());
                result.data = list;
            }catch (Exception e) {
                result.code = Consts.NoKnowCode;
                result.msg = e.getMessage();
                log.error("selectDataByTable error:{}",e);
            }
        }else {
            result.code = CodeMsgEnum.PARAMETER_ERROR.getCode();
            result.msg = CodeMsgEnum.PARAMETER_ERROR.name();
            log.warn("Table does not exist, table:{}",tableStr);
        }
        return result;
    }
}
