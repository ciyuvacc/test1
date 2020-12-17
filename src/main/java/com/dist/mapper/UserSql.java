package com.dist.mapper;

import com.dist.param.UserParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 *
 *
 */
@Slf4j
public class UserSql {


    public String queryUserList(UserParam userParam){
        StringBuffer sql = new StringBuffer("");
        sql.append("select * from t_dist_user where 1=1 ");
        sql.append("and userStatus = '1' ");
        sql.append("order by userLevel ");
        sql.append("limit ");
        sql.append(userParam.getBeginLine() == null ? 0 : userParam.getBeginLine());
        sql.append(",");
        sql.append(userParam.getPageSize() == null ? 10 : userParam.getPageSize());
        log.info("queryUserList sql :" + sql.toString());
        return sql.toString();
    }

    public String queryUserListCount(UserParam userParam){
        StringBuffer sql = new StringBuffer("");
        sql.append("select count(*) from t_dist_user where 1=1 ");
        sql.append("and userStatus = '1' ");
        return sql.toString();
    }

}
