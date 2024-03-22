package com.app.dc.service.table;

import com.app.dc.entity.*;
import com.app.dc.interfaces.IUserService;
import com.app.dc.service.day.CommonService;
import com.app.dc.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author
 * @Date
 **/
@Service
public class UserService extends CommonService implements IUserService {

    private String tableName = "dc_users";

    @Override
    public BatchResult countUser(SearchParam req) {

        BatchResult result = new BatchResult();

        try {
            String sql = CreateSql(req, tableName, false);
            String countSql = sql.replace("*", "count(*) as Counts");
            result.totalSize = getCount(countSql);

            logger.info("countUser size:" + (result.totalSize));
        } catch (Exception e) {
            logger.error("countUser exception:{}", e);
        }

        return result;
    }


    public BatchResult selectUserByAddress(SearchParam req) {
        BatchResult result = new BatchResult();

        try {
            String sql = "select * from dc_users where user_name = '" + req.getUser_id() + "'";
            List<TUsers> usersList = getResult(sql, TUsers.class);
            result.data = usersList;

            logger.info("selectUserByAddress size:" + (usersList==null?0:usersList.size()));
        } catch (Exception e) {
            logger.error("countUser exception:{}", e);
        }

        return result;
    }
}
