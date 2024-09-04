package com.app.dc.po;

import com.app.common.utils.Consts;
import org.apache.http.util.TextUtils;

import java.util.Map;

/**
 * @Description
 * @Author
 * @Date
 **/
public class UserInfo {

    public String sid;
    public String name;
    public String username;
    public String venueusersid;
    public String userid;
    public String biztypestr;
    public String biztypeid;
    public String roleid;
    public String rolename;
    public String ip;
    public String mac;
    public String mac1;
    public String mac2;
    public String clientType;
    public String groupids;
    public String groupnames;

    public UserInfo() {
    }

    public static UserInfo parse(Map<String, Object> resultMap) {
        UserInfo userInfo = new UserInfo();
        userInfo.sid = get("token", resultMap);

        if (TextUtils.isEmpty(userInfo.sid)) {
            Object obj = resultMap.get("data");
            if (obj != null) {
                Map data = (Map)obj;
                userInfo.sid = get("token", data);

                String code = get(Consts.Code, resultMap);
                if ("0".equals(code)) {
                    userInfo.name = get("name", data);
                    userInfo.username = get("user_name", data);
                    userInfo.userid = get("user_id", data);
                    userInfo.ip = get("ip", data);
                    userInfo.clientType = get("client_type", data);
                } else {
                    userInfo = null;
                }

                return userInfo;
            }
        }

        if (TextUtils.isEmpty(userInfo.sid)) {
            userInfo = null;
        } else {
            String code = get(Consts.Code, resultMap);
            if ("0".equals(code)) {
                userInfo.name = get("name", resultMap);
                userInfo.username = get("user_name", resultMap);
                userInfo.userid = get("user_id", resultMap);
                userInfo.venueusersid = get("venueusersid", resultMap);
                userInfo.biztypestr = get("biztypestr", resultMap);
                userInfo.biztypeid = get("biztypeid", resultMap);
                userInfo.roleid = get("roleid", resultMap);
                userInfo.rolename = get("rolename", resultMap);
                userInfo.ip = get("ip", resultMap);
                userInfo.mac = get("mac", resultMap);
                userInfo.mac1 = get("mac1", resultMap);
                userInfo.mac2 = get("mac2", resultMap);
                userInfo.clientType = get("client_type", resultMap);
                userInfo.groupids = get("groupids", resultMap);
                userInfo.groupnames = get("groupnames", resultMap);
            } else {
                userInfo = null;
            }
        }

        return userInfo;
    }

    private static String get(String string, Map<String, Object> resultMap) {
        Object object = resultMap.get(string);
        return object != null ? object.toString() : null;
    }

}
