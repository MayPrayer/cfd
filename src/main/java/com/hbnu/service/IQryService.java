package com.hbnu.service;

import com.github.pagehelper.PageInfo;
import com.hbnu.entity.Roles;
import com.hbnu.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: IQryService <br/>
 * Description: <br/>
 * date: 2020/2/21 11:55<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public interface IQryService {

     PageInfo findAll(int pageNum,int pageSize);
     PageInfo selectLikeUser(int pageNum,int pageSize,String account);
     Users selectByAccountAndPassword(String account, String pwd);
     Roles selectByUserid(int userid);
     List<Users> selectByAccount(String account);

}
