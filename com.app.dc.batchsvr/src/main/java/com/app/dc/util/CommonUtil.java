package com.app.dc.util;

import com.app.common.utils.IdUtil;

/**
 * @Description
 * @Author
 * @Date
 **/
public class CommonUtil {

    public static String getClientId(String idType) {
        return idType + IdUtil.getId();
    }


    public static int Exange_User_id = 99999;

    public static int Exchange_Fee_rate_user_id = 88888;

    public static String Currency = "USD";

}
